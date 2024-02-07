package com.kadipay.retailerdiscountservice.transformer;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public interface Transformer<E, M> {

  M toModel(E e);

  default List<M> toModels(List<E> e) {
    return e == null ? new ArrayList<>() : e.stream().map(this::toModel).collect(Collectors.toList());
  }
}
