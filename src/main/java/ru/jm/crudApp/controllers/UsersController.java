package ru.jm.crudApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.jm.crudApp.models.User;
import ru.jm.crudApp.service.UserService;


@Controller
public class UsersController {

    private final UserService userService;

    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/user/{id}")
    public String helloUser(Model model, @PathVariable("id") int id) {
        User user = userService.getUser(id);
        model.addAttribute("user", user);
        return "user/user";
    }
}
