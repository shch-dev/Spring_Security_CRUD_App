package ru.jm.crudApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.jm.crudApp.models.Role;
import ru.jm.crudApp.models.User;
import ru.jm.crudApp.service.UserService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public String index(Model model) {
        model.addAttribute("AllUsers", userService.getAllUsers());
        return "admin/listAllUsers";
    }

    @GetMapping("/new")
    public String newUser(@ModelAttribute("user") User user, Model model) {
        List<Role> allRoles = userService.getRoles();
        model.addAttribute("allRoles", allRoles);
        return "admin/addNewUser";
    }

    @PostMapping("/save")
    public String saveUser(@ModelAttribute("user") @Valid User user,
                           BindingResult bindingResult, Model model) {
        List<Role> allRoles = userService.getRoles();
        model.addAttribute("allRoles", allRoles);
        if (bindingResult.hasErrors()) {
            return "admin/addNewUser";
        }
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/update/{id}")
    public String editUser(@PathVariable(name = "id") int id, Model model) {
        model.addAttribute("user", userService.getUser(id));
        List<Role> allRoles = userService.getRoles();
        model.addAttribute("allRoles", allRoles);
        return "admin/update";
    }

    @PatchMapping(value = "/update/{id}")
    public String update(@ModelAttribute("user") @Valid User user, BindingResult bindingResult,
                         @PathVariable("id") int id, Model model) {
        List<Role> allRoles = userService.getRoles();
        model.addAttribute("allRoles", allRoles);
        if (bindingResult.hasErrors()) {
            return "admin/update";
        }
        userService.updateUser(id, user);
        return "redirect:/admin";
    }

    @RequestMapping("/delete/{id}")
    public String deleteCustomerForm(@PathVariable(name = "id") int id) {
        userService.removeUser(id);
        return "redirect:/admin";
    }
}