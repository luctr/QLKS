package com.codegym.service.order;

import com.codegym.model.Orders;
import com.codegym.model.Room;
import com.codegym.model.User;
import com.codegym.service.IGeneralService;
import org.hibernate.criterion.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IOrderService extends IGeneralService<Orders> {
    Page<Orders> findAll(Pageable pageable);
    Iterable<Orders> findAllByUser(User user);
    Iterable<Orders> findAllByRoom(Room room);
}
