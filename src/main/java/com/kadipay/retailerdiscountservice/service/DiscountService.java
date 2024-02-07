package com.kadipay.retailerdiscountservice.service;

import com.kadipay.retailerdiscountservice.dto.Bill;
import com.kadipay.retailerdiscountservice.dto.User;

public interface DiscountService {

  double calculateNetPayableAmount(User user, Bill bill);
}
