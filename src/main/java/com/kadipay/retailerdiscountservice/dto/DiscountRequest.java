package com.kadipay.retailerdiscountservice.dto;

import lombok.Data;

@Data
public class DiscountRequest {

  private User user;
  private Bill bill;

}
