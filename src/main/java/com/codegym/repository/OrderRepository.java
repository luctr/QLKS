package com.codegym.repository;

import com.codegym.model.Orders;
import com.codegym.model.Room;
import com.codegym.model.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends PagingAndSortingRepository<Orders, Long> {

    Iterable<Orders> findAllByUser(User user);
    Iterable<Orders> findAllByRoom(Room room);
}
