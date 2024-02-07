package com.kadipay.retailerdiscountservice.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.kadipay.retailerdiscountservice.dto.Bill;
import com.kadipay.retailerdiscountservice.dto.BillDTO;
import com.kadipay.retailerdiscountservice.dto.User;
import com.kadipay.retailerdiscountservice.dto.UserDTO;
import com.kadipay.retailerdiscountservice.service.impl.DiscountServiceImpl;
import com.kadipay.retailerdiscountservice.transformer.Transformer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class DiscountServiceImplTest {

  @InjectMocks
  private DiscountServiceImpl discountService;

  @Mock
  private Transformer<User, UserDTO> userTransformer;

  @Mock
  private Transformer<Bill, BillDTO> billTransformer;

  @Test
  public void testCalculateNetPayableAmount_employeeWithoutGroceries() {
    UserDTO userDTO = new UserDTO(true, false, 0, false);
    BillDTO billDTO = new BillDTO(150, false);

    Mockito.when(userTransformer.toModel(Mockito.any())).thenReturn(userDTO);
    Mockito.when(billTransformer.toModel(Mockito.any())).thenReturn(billDTO);

    double result = discountService.calculateNetPayableAmount(new User(), new Bill());

    assertEquals(150, result, 0.01);
  }

  @Test
  public void testCalculateNetPayableAmount_affiliateWithGroceries() {
    UserDTO userDTO = new UserDTO(false, true, 0, true);
    BillDTO billDTO = new BillDTO(200, true);

    Mockito.when(userTransformer.toModel(Mockito.any())).thenReturn(userDTO);
    Mockito.when(billTransformer.toModel(Mockito.any())).thenReturn(billDTO);

    double result = discountService.calculateNetPayableAmount(new User(), new Bill());

    assertEquals(200, result, 0.01);
  }

  @Test
  public void testCalculateNetPayableAmount_customerOverTwoYearsWithGroceries() {
    UserDTO userDTO = new UserDTO(false, false, 3, true);
    BillDTO billDTO = new BillDTO(300, true);

    Mockito.when(userTransformer.toModel(Mockito.any())).thenReturn(userDTO);
    Mockito.when(billTransformer.toModel(Mockito.any())).thenReturn(billDTO);

    double result = discountService.calculateNetPayableAmount(new User(), new Bill());

    assertEquals(300, result, 0.01);
  }

  @Test
  public void testCalculateNetPayableAmount_regularCustomerWithAmountDiscount() {
    UserDTO userDTO = new UserDTO(false, false, 1, false);
    BillDTO billDTO = new BillDTO(450, false);

    Mockito.when(userTransformer.toModel(Mockito.any())).thenReturn(userDTO);
    Mockito.when(billTransformer.toModel(Mockito.any())).thenReturn(billDTO);

    double result = discountService.calculateNetPayableAmount(new User(), new Bill());

    assertEquals(430, result, 0.01);
  }

  @Test
  public void testCalculateNetPayableAmount_employeeWithAmountDiscount() {
    UserDTO userDTO = new UserDTO(true, false, 0, false);
    BillDTO billDTO = new BillDTO(990, false);

    Mockito.when(userTransformer.toModel(Mockito.any())).thenReturn(userDTO);
    Mockito.when(billTransformer.toModel(Mockito.any())).thenReturn(billDTO);

    double result = discountService.calculateNetPayableAmount(new User(), new Bill());

    assertEquals(935, result, 0.01);
  }

  @Test
  public void testCalculateNetPayableAmount_affiliateOverTwoYearsWithAmountDiscount() {
    UserDTO userDTO = new UserDTO(false, true, 3, false);
    BillDTO billDTO = new BillDTO(500, false);

    Mockito.when(userTransformer.toModel(Mockito.any())).thenReturn(userDTO);
    Mockito.when(billTransformer.toModel(Mockito.any())).thenReturn(billDTO);

    double result = discountService.calculateNetPayableAmount(new User(), new Bill());

    assertEquals(470, result, 0.01);
  }

  @Test
  public void testCalculateNetPayableAmount_regularCustomerWithGroceries() {
    UserDTO userDTO = new UserDTO(false, false, 1, true);
    BillDTO billDTO = new BillDTO(200, true);

    Mockito.when(userTransformer.toModel(Mockito.any())).thenReturn(userDTO);
    Mockito.when(billTransformer.toModel(Mockito.any())).thenReturn(billDTO);

    double result = discountService.calculateNetPayableAmount(new User(), new Bill());

    // Since groceries are not eligible for percentage discount, result should be the same as the bill amount
    assertEquals(200, result, 0.01);
  }

  @Test
  public void testCalculateNetPayableAmount_customerOverTwoYearsWithoutGroceries() {
    UserDTO userDTO = new UserDTO(false, false, 3, false);
    BillDTO billDTO = new BillDTO(500, false);

    Mockito.when(userTransformer.toModel(Mockito.any())).thenReturn(userDTO);
    Mockito.when(billTransformer.toModel(Mockito.any())).thenReturn(billDTO);

    double result = discountService.calculateNetPayableAmount(new User(), new Bill());

    // Since the customer is over two years, a 5% discount should be applied
    assertEquals(475, result, 0.01);
  }

  @Test
  public void testCalculateNetPayableAmount_regularCustomerWithLargeAmount() {
    UserDTO userDTO = new UserDTO(false, false, 1, false);
    BillDTO billDTO = new BillDTO(1500, false);

    Mockito.when(userTransformer.toModel(Mockito.any())).thenReturn(userDTO);
    Mockito.when(billTransformer.toModel(Mockito.any())).thenReturn(billDTO);

    double result = discountService.calculateNetPayableAmount(new User(), new Bill());

    // For every $100 on the bill, there should be a $5 discount
    // So, for $1500, the discount should be $75
    assertEquals(1425, result, 0.01);
  }

}

