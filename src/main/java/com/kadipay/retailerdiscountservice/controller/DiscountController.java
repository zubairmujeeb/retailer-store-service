package com.kadipay.retailerdiscountservice.controller;

import com.kadipay.retailerdiscountservice.dto.Bill;
import com.kadipay.retailerdiscountservice.dto.DiscountRequest;
import com.kadipay.retailerdiscountservice.dto.User;
import com.kadipay.retailerdiscountservice.service.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/discount")
public class DiscountController {

  @Autowired
  private DiscountService discountService;

  @PostMapping("/calculate")
  public ResponseEntity<Double> calculateDiscount(@RequestBody DiscountRequest discountRequest) {
    try {
      User user = discountRequest.getUser();
      Bill bill = discountRequest.getBill();
      double netPayableAmount = discountService.calculateNetPayableAmount(user, bill);
      return ResponseEntity.ok(netPayableAmount);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }
}

