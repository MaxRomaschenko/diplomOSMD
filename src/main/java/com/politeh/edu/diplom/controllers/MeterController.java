package com.politeh.edu.diplom.controllers;


import com.politeh.edu.diplom.model.Meter;
import com.politeh.edu.diplom.model.User;
import com.politeh.edu.diplom.services.MeterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class MeterController {
    private final MeterService meterService;

    @Autowired
    public MeterController(MeterService meterService) {
        this.meterService = meterService;
    }

    @GetMapping("/list")
    @PreAuthorize("hasAuthority('user:read')")
    public String listOfMeters(Model model) {
        List<Meter> meters = meterService.findAll();
        model.addAttribute("meters", meters);

        return "profile/list";
    }

    @GetMapping("/create")
    @PreAuthorize("hasAuthority('admin:write')")
    public String createMetersForm(@ModelAttribute("meter") Meter meter){
        return "profile/create";
    }

    @PostMapping()
    @PreAuthorize("hasAuthority('admin:write')")
    public String createUser(@ModelAttribute("meter") @Valid Meter meter,
                             BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return "profile/create";
        }

        meterService.saveMeter(meter);
        return "redirect:/profile/list";
    }

    @GetMapping("/{id}/edit")
    @PreAuthorize("hasAuthority('admin:write')")
    public String edit(Model model, @PathVariable("id") Long id) {
        model.addAttribute("user", meterService.findById(id));
        return "profile/edit";
    }

    @PostMapping("/{id}/edit")
    @PreAuthorize("hasAuthority('admin:write')")
    public String update(@ModelAttribute("meter") @Valid Meter meter,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "profile/edit";


        String url = meter.getId().toString();

        meterService.saveMeter(meter);
        return "redirect:/profile/index/" + url;
    }

    @PostMapping("/{id}")
    @PreAuthorize("hasAuthority('admin:write')")
    public String delete(@PathVariable("id") Long id) {
        meterService.deleteById(id);
        return "redirect:/profile/list";
    }

}


