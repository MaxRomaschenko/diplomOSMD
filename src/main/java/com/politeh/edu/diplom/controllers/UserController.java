package com.politeh.edu.diplom.controllers;

import com.politeh.edu.diplom.model.User;
import com.politeh.edu.diplom.services.UserService;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/profile")
public class UserController {

    private final PasswordEncoder passwordEncoder;
    private final UserService userService;

    public UserController(PasswordEncoder passwordEncoder, UserService userService) {
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }

    @GetMapping("/index/{id}")
    @PreAuthorize("hasAuthority('user:read')")
    public String index(@PathVariable("id") Long id, Model model) { //переход на профиль
        model.addAttribute("user", userService.findById(id));
        return "profile/index";
    }

    @GetMapping("/list")
    @PreAuthorize("hasAuthority('user:read')")
    public String listOfUsers(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("profile", users);

        return "profile/list";
    }

    @GetMapping("/create")
    @PreAuthorize("hasAuthority('admin:write')")
    public String createUserForm(@ModelAttribute("user") User user){
        return "profile/create";
    }

    @PostMapping()
    @PreAuthorize("hasAuthority('admin:write')")
    public String createUser(@ModelAttribute("user") @Valid User user,
                             BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return "profile/create";
        }
        User user1 = userService.findByEmail(user.getEmail());

        if(user1 != null) {
            if (user1.getEmail().equals(user.getEmail())) {
                return "profile/create";
            }
        }
        user.setCreated_at(LocalDateTime.now());
        user.setUpdated_at(LocalDateTime.now());

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userService.saveUser(user);
        return "redirect:/profile/list";
    }

    @GetMapping("/{id}/edit")
    @PreAuthorize("hasAuthority('admin:write')")
    public String edit(Model model, @PathVariable("id") Long id) {
        model.addAttribute("user", userService.findById(id));
        return "profile/edit";
    }

    @PostMapping("/{id}/edit")
    @PreAuthorize("hasAuthority('admin:write')")
    public String update(@ModelAttribute("user") @Valid User user,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "profile/edit";

        User user1 = userService.findById(user.getId());
        user.setCreated_at(user1.getCreated_at());
        user.setUpdated_at(LocalDateTime.now());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        String url = user.getId().toString();

        userService.saveUser(user);
        return "redirect:/profile/index/" + url;
    }

    @PostMapping("/{id}")
    @PreAuthorize("hasAuthority('admin:write')")
    public String delete(@PathVariable("id") Long id) {
        userService.deleteById(id);
        return "redirect:/profile/list";
    }

}
