package com.kadipay.retailerdiscountservice.service.impl;

import com.kadipay.retailerdiscountservice.dto.Bill;
import com.kadipay.retailerdiscountservice.dto.BillDTO;
import com.kadipay.retailerdiscountservice.dto.User;
import com.kadipay.retailerdiscountservice.dto.UserDTO;
import com.kadipay.retailerdiscountservice.service.DiscountService;
import com.kadipay.retailerdiscountservice.transformer.Transformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiscountServiceImpl implements DiscountService {
  @Autowired
  private Transformer<User, UserDTO> userTransformer;

  @Autowired
  private Transformer<Bill, BillDTO> billTransformer;

  @Override
  public double calculateNetPayableAmount(User user, Bill bill) {
    UserDTO userDTO = userTransformer.toModel(user);
    BillDTO billDTO = billTransformer.toModel(bill);

    double totalAmount = billDTO.getTotalAmount();

    // Apply percentage-based discounts
    double percentageDiscount = calculatePercentageDiscount(userDTO, totalAmount);

    // Apply $5 discount for every $100 on the bill
    double amountDiscount = calculateAmountDiscount(totalAmount);

    // Calculate net payable amount
    double netPayableAmount = totalAmount - percentageDiscount - amountDiscount;

    // Ensure the net payable amount is non-negative
    return Math.max(netPayableAmount, 0);
  }

  private double calculatePercentageDiscount(UserDTO userDTO, double totalAmount) {
    if (userDTO.isEmployee()) {
      return userDTO.isGroceries() ? 0 : 0.3 * totalAmount;
    } else if (userDTO.isAffiliate()) {
      return userDTO.isGroceries() ? 0 : 0.1 * totalAmount;
    } else if (userDTO.getYearsAsCustomer() >= 2) {
      return userDTO.isGroceries() ? 0 : 0.05 * totalAmount;
    } else {
      return 0;
    }
  }

  private double calculateAmountDiscount(double totalAmount) {
    return (int) (totalAmount / 100) * 5;
  }
}
