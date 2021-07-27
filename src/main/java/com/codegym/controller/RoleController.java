package com.codegym.controller;

import com.codegym.model.Role;
import com.codegym.service.role.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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
            return new ModelAndView("/error-404");
        }
    }

    @PostMapping("/{id}/edit")
    public ModelAndView update(@ModelAttribute("role") Role role) {
        roleService.save(role);
        return new ModelAndView("redirect:/roles");
    }

    @GetMapping("/{id}/delete")
    public ModelAndView showFormDelete(@PathVariable Long id) {
        Optional<Role> role = roleService.findById(id);
        if (role.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("/role/delete");
            modelAndView.addObject("role", role.get());
            return modelAndView;
        } else {
            return new ModelAndView("/error-404");
        }
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        roleService.delete(id);
        return "redirect:/roles";
    }

}
