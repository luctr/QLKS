package com.codegym.repository;


import java.util.Optional;

public interface UserRepository <T> {

    Iterable<T> findAll();

    Optional<T> findById(Long id);

    void save(T t);

    void delete(Long id);
}
