package com.codegym.controller;

import com.codegym.service.order.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/order-manager")
public class OrderController {
    @Autowired
    private IOrderService orderService;

    @GetMapping("")
    public ModelAndView orderListByPaging(Pageable pageable) {
        ModelAndView modelAndView =  new ModelAndView("order/list");
        modelAndView.addObject("orders",orderService.findAll(pageable));
        return modelAndView;
    }
}
