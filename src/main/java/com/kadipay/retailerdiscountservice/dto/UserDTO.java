package com.kadipay.retailerdiscountservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
//@RequiredArgsConstructor
@AllArgsConstructor
public class UserDTO {

  private boolean isEmployee;
  private boolean isAffiliate;
  private int yearsAsCustomer;
  private boolean isGroceries;

}
