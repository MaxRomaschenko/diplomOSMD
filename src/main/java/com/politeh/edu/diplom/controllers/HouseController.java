package com.politeh.edu.diplom.controllers;

import com.politeh.edu.diplom.model.House;
import com.politeh.edu.diplom.services.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/house")
public class HouseController {
    private final HouseService houseService;

    @Autowired
    public HouseController(HouseService houseService) {
        this.houseService = houseService;
    }

    @GetMapping("/list")
    @PreAuthorize("hasAuthority('user:read')")
    public String listOfUsers(Model model) {
        List<House> houses = houseService.findAll();
        model.addAttribute("house", houses);

        return "house/list";
    }

    @GetMapping("/create")
    @PreAuthorize("hasAuthority('admin:write')")
    public String createUserForm(@ModelAttribute("house") House house){
        return "house/create";
    }

    @PostMapping()
    @PreAuthorize("hasAuthority('admin:write')")
    public String createUser(@ModelAttribute("house") @Valid House house,
                             BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return "house/create";
        }

        houseService.saveHouse(house);
        return "redirect:/house/list";
    }

    @GetMapping("/{id}/edit")
    @PreAuthorize("hasAuthority('admin:write')")
    public String edit(Model model, @PathVariable("id") Long id) {
        model.addAttribute("house", houseService.findById(id));
        return "house/edit";
    }

    @PostMapping("/{id}/edit")
    @PreAuthorize("hasAuthority('admin:write')")
    public String update(@ModelAttribute("house") @Valid House house,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "house/edit";

        houseService.saveHouse(house);
        return "redirect:/house/list";
    }

    @PostMapping("/{id}")
    @PreAuthorize("hasAuthority('admin:write')")
    public String delete(@PathVariable("id") Long id) {
        houseService.deleteById(id);
        return "redirect:/house/list";
    }
}
