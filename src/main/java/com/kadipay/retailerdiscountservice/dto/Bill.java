package com.kadipay.retailerdiscountservice.dto;

import lombok.Data;

@Data
public class Bill {

  private double totalAmount;
  private boolean isGroceries;
}
