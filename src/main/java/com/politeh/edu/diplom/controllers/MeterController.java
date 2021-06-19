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
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;

@Controller
@RequestMapping("/meter")
public class MeterController {
    private final MeterService meterService;

    @Autowired
    public MeterController(MeterService meterService) {
        this.meterService = meterService;
    }

    @GetMapping("")
    @PreAuthorize("hasAuthority('user:read')")
    public String listOfMeters(Model model) {
        List<Meter> meters = meterService.findAllSorted();
        model.addAttribute("meters", meters);

        return "new/meter";
    }

    @GetMapping("/create")
    @PreAuthorize("hasAuthority('admin:write')")
    public String createMetersForm(@ModelAttribute("meter") Meter meter,
                                   @ModelAttribute("date") String date){

        return "meter/create";
    }

    @PostMapping()
    @PreAuthorize("hasAuthority('admin:write')")
    public String createMeter(@ModelAttribute("meter") @Valid Meter meter, @ModelAttribute("date") String date,
                             BindingResult bindingResult){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        meter.setData(LocalDate.parse(date, dateTimeFormatter));
        if (bindingResult.hasErrors()) {
            return "meter/create";
        }

        meterService.saveMeter(meter);
        return "redirect:/meter/";
    }

    @GetMapping("/{id}/edit")
    @PreAuthorize("hasAuthority('admin:write')")
    public String edit(Model model, @PathVariable("id") Long id) {
        model.addAttribute("meter", meterService.findById(id));
        return "meter/";
    }

    @PostMapping("/{id}/edit")
    @PreAuthorize("hasAuthority('admin:write')")
    public String update(@ModelAttribute("meter") @Valid Meter meter,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "meter/";


        String url = meter.getId().toString();

        meterService.saveMeter(meter);
        return "redirect:/meter/index/" + url;
    }

    @PostMapping("/{id}")
    @PreAuthorize("hasAuthority('admin:write')")
    public String delete(@PathVariable("id") Long id) {
        meterService.deleteById(id);
        return "redirect:/meter";
    }

}


