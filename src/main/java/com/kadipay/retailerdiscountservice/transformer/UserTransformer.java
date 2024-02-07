package com.kadipay.retailerdiscountservice.transformer;

import com.kadipay.retailerdiscountservice.dto.User;
import com.kadipay.retailerdiscountservice.dto.UserDTO;
import org.springframework.stereotype.Component;

@Component
public class UserTransformer implements Transformer<User, UserDTO> {

  @Override
  public UserDTO toModel(User user) {
    return new UserDTO(
        user.isEmployee(),
        user.isAffiliate(),
        user.getYearsAsCustomer(),
        user.isGroceries()
    );
  }
}