package com.politeh.edu.diplom.controllers;

import com.politeh.edu.diplom.model.Flat;
import com.politeh.edu.diplom.model.House;
import com.politeh.edu.diplom.services.FlatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/flat")
public class FlatController {

    private final FlatService flatService;

    @Autowired
    public FlatController(FlatService flatService) {
        this.flatService = flatService;
    }

    @GetMapping("/list")
    @PreAuthorize("hasAuthority('user:read')")
    public String listOfUsers(Model model) {
        List<Flat> flats = flatService.findAll();
        model.addAttribute("flat", flats);

        return "flat/list";
    }

    @GetMapping("/create")
    @PreAuthorize("hasAuthority('admin:write')")
    public String createUserForm(@ModelAttribute("flat") Flat flat){
        return "flat/create";
    }

    @PostMapping()
    @PreAuthorize("hasAuthority('admin:write')")
    public String createUser(@ModelAttribute("flat") @Valid Flat flat,
                             BindingResult bindingResult){

        if (bindingResult.hasErrors()) {
            return "flat/create";
        }

        flatService.saveFlat(flat);
        return "redirect:/flat/list";
    }

    @GetMapping("/{id}/edit")
    @PreAuthorize("hasAuthority('admin:write')")
    public String edit(Model model, @PathVariable("id") Long id) {
        model.addAttribute("flat", flatService.findById(id));
        return "flat/edit";
    }

    @PostMapping("/{id}/edit")
    @PreAuthorize("hasAuthority('admin:write')")
    public String update(@ModelAttribute("flat") @Valid Flat flat,
                         BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "flat/edit";
        }

        flatService.saveFlat(flat);
        return "redirect:/flat/list";
    }

    @PostMapping("/{id}")
    @PreAuthorize("hasAuthority('admin:write')")
    public String delete(@PathVariable("id") Long id) {
        flatService.deleteById(id);
        return "redirect:/flat/list";
    }
}
