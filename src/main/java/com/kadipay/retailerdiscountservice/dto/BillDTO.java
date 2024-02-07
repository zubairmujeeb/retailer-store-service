package com.kadipay.retailerdiscountservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BillDTO {

  private double totalAmount;
  private boolean isGroceries;
}
