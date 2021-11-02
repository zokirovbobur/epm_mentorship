package com.epam.mvc.service;

import java.util.List;
import java.util.Optional;

public interface GeneralService<T> {

    Optional<T> findById(Long id);

    T save(T object);

    List<T> getAll();
}
