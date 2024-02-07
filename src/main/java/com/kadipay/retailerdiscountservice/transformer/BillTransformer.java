package com.kadipay.retailerdiscountservice.transformer;

import com.kadipay.retailerdiscountservice.dto.Bill;
import com.kadipay.retailerdiscountservice.dto.BillDTO;
import org.springframework.stereotype.Component;

@Component
public class BillTransformer implements Transformer<Bill, BillDTO> {

  @Override
  public BillDTO toModel(Bill bill) {
    return new BillDTO(
        bill.getTotalAmount(),
        bill.isGroceries()
    );
  }
}