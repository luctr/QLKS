package com.codegym.controller;

import com.codegym.model.Role;
import com.codegym.service.role.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("/roles")
public class RoleController {

    @Autowired
    private IRoleService roleService;

    @GetMapping("/create")
    public ModelAndView showFormCreate() {
        return new ModelAndView("/role/create");
    }

    @PostMapping("/create")
    public ModelAndView saveRole(Role role) {
        roleService.save(role);
        return new ModelAndView("redirect:/roles");
    }

    @GetMapping
    public ModelAndView showList() {
        Iterable<Role> roles = roleService.findAll();
        ModelAndView modelAndView = new ModelAndView("/role/list");
        modelAndView.addObject("roles", roles);
        return modelAndView;
    }

    @GetMapping("/{id}/view")
    public ModelAndView showDetails(@PathVariable Long id) {
        Optional<Role> role = roleService.findById(id);
        if (role.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("/role/view");
            modelAndView.addObject("role", role.get());
            return modelAndView;
        } else {
            return new ModelAndView("redirect:/roles");
        }
    }

    @GetMapping("/{id}/edit")
    public ModelAndView showFormEdit(@PathVariable Long id) {
        Optional<Role> role = roleService.findById(id);
        if (role.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("/role/edit");
            modelAndView.addObject("role", role.get());
            return modelAndView;
        } else {
            return new ModelAndView("redirect:/roles");
        }
    }

    @PostMapping("/{id}/edit")
    public ModelAndView update(Role role) {
        roleService.save(role);
        return new ModelAndView("redirect:/roles");
    }

}
