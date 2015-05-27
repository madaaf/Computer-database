package com.excilys.aflak.mapper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by loic on 28/05/2015.
 */
public interface Mapper<T, U> {
  T fromDto(U o);
  U toDto(T o);

  default List<T> fromListDto(List<U> o ) {

    List<T> result = new ArrayList<>(o.size());

    for (U obj : o) {
      result.add(fromDto(obj));
    }

    return result;
  }

  default List<U> toListDto(List<T> o ) {

    List<U> result = new ArrayList<>(o.size());

    for (T obj : o) {
      result.add(toDto(obj));
    }

    return result;
  }
}
