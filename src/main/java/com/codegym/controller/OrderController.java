package com.codegym.controller;

import com.codegym.model.Orders;
import com.codegym.service.order.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.swing.text.html.Option;
import java.util.Optional;

@Controller
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private IOrderService orderService;

    @GetMapping("")
    public ModelAndView listByPaging(@PageableDefault(size = 5) Pageable pageable) {
        ModelAndView modelAndView =  new ModelAndView("order/list");
        modelAndView.addObject("orders",orderService.findAll(pageable));
        return modelAndView;
    }
    @GetMapping("/create")
    public ModelAndView showCreate() {
        return new ModelAndView("order/create");
    }
    @PostMapping("/create")
    public String createOrder(Orders order, BindingResult bindingResult) {
        System.out.println(bindingResult);
        orderService.save(order);
        return "redirect:/orders";
    }
    @GetMapping("/{id}/detail")
    public ModelAndView showDetailById(@PathVariable long id) {
        Optional<Orders> order = orderService.findById(id);
        if(order.isPresent()){
            return new ModelAndView("order/detail").addObject("order",order);
        }
        return new ModelAndView("redirect:/orders");
    }
    @GetMapping("/{id}/edit")
    public ModelAndView showEditForm(@PathVariable long id) {
        if(orderService.findById(id).isPresent()) {
            return new ModelAndView("order/edit").addObject("order",orderService.findById(id));
        }
        return new ModelAndView("redirect:/orders");
    }

    @PostMapping("/{id}/edit")
    public String saveEdit(@PathVariable long id, Orders order,BindingResult bindingResult) {
        System.out.println(bindingResult);
        orderService.save(order);
        return "redirect:/orders";
    }
    @GetMapping("/{id}/delete")
    public String delete(@PathVariable long id) {
        orderService.delete(id);
        return "redirect:/orders";
    }

}
