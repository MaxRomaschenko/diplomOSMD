package com.politeh.edu.diplom.controllers;

import com.politeh.edu.diplom.model.Tariff;
import com.politeh.edu.diplom.services.TariffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/tariff")
public class TariffController {
    private final TariffService tariffService;

    @Autowired
    public TariffController(TariffService tariffService) {
        this.tariffService = tariffService;
    }

    @GetMapping("/list")
    @PreAuthorize("hasAuthority('user:read')")
    public String listOfUsers(Model model) {
        List<Tariff> tariffs = tariffService.findAll();
        model.addAttribute("tariff", tariffs);

        return "tariff/list";
    }

    @GetMapping("/create")
    @PreAuthorize("hasAuthority('admin:write')")
    public String createUserForm(@ModelAttribute("tariff") Tariff tariff){
        return "tariff/create";
    }

    @PostMapping()
    @PreAuthorize("hasAuthority('admin:write')")
    public String createUser(@ModelAttribute("tariff") @Valid Tariff tariff,
                             BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return "tariff/create";
        }

        tariffService.saveTariff(tariff);
        return "redirect:/tariff/list";
    }

    @GetMapping("/{id}/edit")
    @PreAuthorize("hasAuthority('admin:write')")
    public String edit(Model model, @PathVariable("id") Long id) {
        model.addAttribute("tariff", tariffService.findById(id));
        return "tariff/edit";
    }

    @PostMapping("/{id}/edit")
    @PreAuthorize("hasAuthority('admin:write')")
    public String update(@ModelAttribute("tariff") @Valid Tariff tariff,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "tariff/edit";

        tariffService.saveTariff(tariff);
        return "redirect:/tariff/list";
    }

    @PostMapping("/{id}")
    @PreAuthorize("hasAuthority('admin:write')")
    public String delete(@PathVariable("id") Long id) {
        tariffService.deleteById(id);
        return "redirect:/tariff/list";
    }
}
