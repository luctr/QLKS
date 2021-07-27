package com.codegym.controller;


import com.codegym.model.Room;
import com.codegym.model.Type;
import com.codegym.service.room.IRoomService;
import com.codegym.service.type.ITypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

@Controller
@RequestMapping("/room")
public class RoomController {

    @Autowired
    private IRoomService roomService;

    @Autowired
    private ITypeService typeService;



    @ModelAttribute("type")
    public Iterable<Type> types(){
        return typeService.findAll();
    }


    @GetMapping("/create-room")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("/room/create");
        modelAndView.addObject("room", new Room());
        return modelAndView;
    }

    @PostMapping("/create-room")
    public ModelAndView saveCustomer(Room room,@RequestParam MultipartFile file) {
        String fileName = file.getOriginalFilename();
        try {
            FileCopyUtils.copy(file.getBytes(),
                    new File("C:/Users/Huy Hoang/Downloads/" + fileName));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        room.setImage(fileName);
        roomService.save(room);
        ModelAndView modelAndView = new ModelAndView("/room/list");
        modelAndView.addObject("room", new Room());
        modelAndView.addObject("message", "New customer created successfully");
        return modelAndView;
    }

    @GetMapping("/listRoom")
    public ModelAndView listRoom() {
        Iterable<Room> rooms = roomService.findAll();
        ModelAndView modelAndView = new ModelAndView("/room/list");
        modelAndView.addObject("rooms", rooms);
        return modelAndView;
    }

    @GetMapping("/edit-room/{id}")
    public ModelAndView showEditForm(@PathVariable Long id) {
        Optional<Room> rooms = roomService.findById(id);
        if (rooms.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("/room/update");
            modelAndView.addObject("room", rooms.get());
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("/error-404");
            return modelAndView;
        }
    }

    @PostMapping(value = "/update-room")
    public String updateRoom(@ModelAttribute("room") Room room) {
        roomService.save(room);
        ModelAndView modelAndView = new ModelAndView("/room/list");
//        modelAndView.addObject("room", room);
//        modelAndView.addObject("message", "Room updated successfully");
        return "redirect:/room/listRoom";
    }

    @GetMapping("/delete-room/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id) {
        Optional<Room> room = roomService.findById(id);
        if (room.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("/room/delete");
            modelAndView.addObject("room", room.get());
            return modelAndView;

        } else {
            ModelAndView modelAndView = new ModelAndView("/error-404");
            return modelAndView;
        }
    }

    @PostMapping("/delete-room")
    public String deleteCustomer(@ModelAttribute("room") Room room) {
        roomService.delete(room.getId());
        return "redirect:/room/listRoom";
    }
}
