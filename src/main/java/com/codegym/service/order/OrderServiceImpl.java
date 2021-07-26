package com.codegym.service.order;

import com.codegym.model.Orders;
import com.codegym.model.Room;
import com.codegym.model.User;
import com.codegym.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderServiceImpl implements IOrderService{
    @Autowired
    private OrderRepository orderRepository;


    @Override
    public Iterable<Orders> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Optional<Orders> findById(Long id) {
        return orderRepository.findById(id);
    }

    @Override
    public void save(Orders orders) {
        orderRepository.save(orders);
    }

    @Override
    public void delete(Long id) {
        orderRepository.deleteById(id);
    }

    public Page<Orders> findAll(Pageable pageable) {
        return orderRepository.findAll(pageable);
    }

    @Override
    public Iterable<Orders> findAllByUser(User user) {
        return orderRepository.findAllByUser(user);
    }

    @Override
    public Iterable<Orders> findAllByRoom(Room room) {
        return orderRepository.findAllByRoom(room);
    }

}
