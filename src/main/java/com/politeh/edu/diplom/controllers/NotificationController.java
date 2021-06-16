package com.politeh.edu.diplom.controllers;

import com.politeh.edu.diplom.model.Notification;
import com.politeh.edu.diplom.model.User;
import com.politeh.edu.diplom.services.NotificationService;
import com.politeh.edu.diplom.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/news")
public class NotificationController {

    private final NotificationService notificationService;
    private final UserService userService;



    @Autowired
    public NotificationController(NotificationService notificationService, UserService userService) {
        this.notificationService = notificationService;
        this.userService = userService;
    }

    @GetMapping("/index/{id}")
    @PreAuthorize("hasAuthority('user:read')")
    public String index(@PathVariable("id") Long id, Model model) {
        model.addAttribute("notification", notificationService.findById(id));
        return "notification/index";
    }

    @GetMapping("/list")
    public String listOfNotifications(Model model) {
        List<Notification> notifications = notificationService.findAll();
        model.addAttribute("notification", notifications);
        return "notification/list";
    }

    @GetMapping("/create")
    @PreAuthorize("hasAuthority('admin:write')")
    public String createNotificationForm(@ModelAttribute("notification") Notification notification){
        return "notification/create";
    }

    @PostMapping()
    @PreAuthorize("hasAuthority('admin:write')")
    public String createNotification(@ModelAttribute("notification") @Valid Notification notification,
                                     BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return "notification/create";
        }
        notification.setCreated_at(LocalDateTime.now());
        notification.setUpdated_at(LocalDateTime.now());

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.findByEmail(userDetails.getUsername());
        notification.setUser(userService.findById(user.getId()));

        notificationService.saveNotification(notification);
        return "redirect:/news/list";
    }

    @GetMapping("/{id}/edit")
    @PreAuthorize("hasAuthority('admin:write')")
    public String edit(Model model, @PathVariable("id") Long id) {
        model.addAttribute("notification", notificationService.findById(id));
        return "notification/edit";
    }

    @PostMapping("/{id}/edit")
    @PreAuthorize("hasAuthority('admin:write')")
    public String update(@ModelAttribute("notification") @Valid Notification notification,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "notification/edit";

        Notification notification1 = notificationService.findById(notification.getId());
        notification.setCreated_at(notification1.getCreated_at());
        notification.setUpdated_at(LocalDateTime.now());

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.findByEmail(userDetails.getUsername());
        notification.setUser(userService.findById(user.getId()));

        String url = notification.getId().toString();

        notificationService.saveNotification(notification);
        return "redirect:/news/index/" + url;
    }

    @PostMapping("/{id}")
    @PreAuthorize("hasAuthority('admin:write')")
    public String delete(@PathVariable("id") Long id) {
        notificationService.deleteById(id);
        return "redirect:/news/list";
    }
}
