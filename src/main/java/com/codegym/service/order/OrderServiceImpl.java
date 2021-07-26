package com.codegym.service.order;

import com.codegym.model.Orders;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderServiceImpl implements IOrderService{



    @Override
    public Iterable<Orders> findAll() {
        return null;
    }

    @Override
    public Optional<Orders> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public void save(Orders orders) {

    }

    @Override
    public void delete(Long id) {

    }
}
