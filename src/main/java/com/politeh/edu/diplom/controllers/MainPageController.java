package com.politeh.edu.diplom.controllers;

import com.politeh.edu.diplom.model.Notification;
import com.politeh.edu.diplom.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MainPageController {

    private final NotificationService notificationService;

    @Autowired
    public MainPageController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping("/")
    public String mainPage(Model model){
        List<Notification> notifications = notificationService.findAll();
        model.addAttribute("notification", notifications);
        return "new/main";
    }

    @GetMapping("/closeWindow")
    public String closeWindow(){
        return "redirect:/";
    }


}
