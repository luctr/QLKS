package com.codegym.controller;

import com.codegym.model.Role;
import com.codegym.model.Type;
import com.codegym.service.type.ITypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("/types")
public class TypeController {

    @Autowired
    private ITypeService typeService;

    @GetMapping("/create")
    public ModelAndView showFormCreate() {
        return new ModelAndView("/type/create");
    }

    @PostMapping("/create")
    public ModelAndView save(Type type) {
        typeService.save(type);
        return new ModelAndView("redirect:/types");
    }

    @GetMapping
    public ModelAndView showList() {
        Iterable<Type> types = typeService.findAll();
        ModelAndView modelAndView = new ModelAndView("/type/list");
        modelAndView.addObject("types", types);
        return modelAndView;
    }

    @GetMapping("/{id}/view")
    public ModelAndView showDetails(@PathVariable Long id) {
        Optional<Type> type = typeService.findById(id);
        if (type.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("/type/view");
            modelAndView.addObject("type", type.get());
            return modelAndView;
        } else {
            return new ModelAndView("redirect:/types");
        }
    }

    @GetMapping("/{id}/edit")
    public ModelAndView showFormEdit(@PathVariable Long id) {
        Optional<Type> type = typeService.findById(id);
        if (type.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("/type/edit");
            modelAndView.addObject("type", type.get());
            return modelAndView;
        } else {
            return new ModelAndView("/error-404");
        }
    }

    @PostMapping("/{id}/edit")
    public ModelAndView update(Type type) {
        typeService.save(type);
        return new ModelAndView("redirect:/types");
    }

    @GetMapping("/{id}/delete")
    public ModelAndView showFormDelete(@PathVariable Long id) {
        Optional<Type> type = typeService.findById(id);
        if (type.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("/type/delete");
            modelAndView.addObject("type", type.get());
            return modelAndView;
        } else {
            return new ModelAndView("/error-404");
        }
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        typeService.delete(id);
        return "redirect:/types";
    }
}
