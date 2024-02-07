package com.kadipay.retailerdiscountservice.dto;

import lombok.Data;

@Data
public class User {

  private String type;
  private boolean isEmployee;
  private boolean isAffiliate;
  private int yearsAsCustomer;
  private boolean isGroceries;

}
