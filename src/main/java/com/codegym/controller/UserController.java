package com.codegym.controller;

import com.codegym.model.User;
import com.codegym.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {
@Autowired
    private IUserService userService;
@GetMapping("list")
    public ModelAndView showList(){
    ModelAndView modelAndView=new ModelAndView("/user/list");
    Iterable<User> users=userService.findAll();
    modelAndView.addObject("user",users);
    return modelAndView;
}
@GetMapping("create")
    public ModelAndView showCreate(){
    ModelAndView modelAndView=new ModelAndView( "/user/create");
    modelAndView.addObject("user",new User());
    return modelAndView;
}
@PostMapping("create")
    public ModelAndView saveCreate(@Valid @ModelAttribute("user") User user, BindingResult bindingResult){
    ModelAndView modelAttribute =new ModelAndView("/user/create");
    if(bindingResult.hasErrors()){
        return modelAttribute;
    }
    userService.save(user);
    modelAttribute.addObject("user",new User());
    return modelAttribute;
}
@GetMapping("edit/{id}")
    public ModelAndView showEdit( @PathVariable Long id){
    ModelAndView modelAndView=new ModelAndView("/user/edit");
    Optional<User>user= userService.findById(id);
    modelAndView.addObject("user",user);
    return modelAndView;
}
@PostMapping("edit")
    public ModelAndView saveEdit(@Valid @ModelAttribute("user") User user,BindingResult bindingResult){
    if(bindingResult.hasErrors()){
        ModelAndView modelAndView=new ModelAndView("/user/edit");
        return modelAndView;
    }
    userService.save(user);
     ModelAndView modelAndViewList=new ModelAndView("/user/list");
     Iterable<User> listUser = userService.findAll();
    modelAndViewList.addObject("user", listUser);
    return modelAndViewList;
}
@GetMapping("/delete/{id}")
    public ModelAndView showDelete(@PathVariable Long id){
    ModelAndView modelAndView=new ModelAndView("/user/delete");
    Optional<User>user= userService.findById(id);
    modelAndView.addObject("user",user);
    return modelAndView;
}
@PostMapping("delete")
    public String saveDelete(@ModelAttribute("user") User user){
    userService.remove(user.getId());
    return "redirect:list";
}

}
