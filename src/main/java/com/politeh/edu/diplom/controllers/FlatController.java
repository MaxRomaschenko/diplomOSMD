package com.politeh.edu.diplom.controllers;

import com.politeh.edu.diplom.model.Flat;
import com.politeh.edu.diplom.repository.FlatRepo;
import com.politeh.edu.diplom.repository.TariffRepo;
import com.politeh.edu.diplom.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/flat")
public class FlatController {

    private final FlatService flatService;
    private final FloorService floorService;
    private final TariffService tariffService;
    private final UserService userService;
    private final HouseService houseService;
    private final SectionService sectionService;


    @Autowired
    public FlatController(FlatService flatService, FloorService floorService,
                          TariffService tariffService, UserService userService,
                          HouseService houseService, SectionService sectionService) {
        this.flatService = flatService;
        this.floorService = floorService;
        this.tariffService = tariffService;
        this.userService = userService;
        this.houseService = houseService;
        this.sectionService = sectionService;
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
    public String createFlat(@ModelAttribute("flat") @Valid Flat flat,
                             BindingResult bindingResult){

        if (bindingResult.hasErrors()) {
            return "flat/create";
        }

        Flat flat1 = new Flat.Builder(flat)
                .setUser( userService.findByEmail(flat.getUser().getEmail()))
                .setFloor(floorService.findByfloor(flat.getFloor().getFloorNumber()))
                .setSection(sectionService.findBySectionNumber(flat.getSection().getSectionNumber()))
                .setHouse(houseService.findByAddress(flat.getHouse().getAddress()))
                .build();

        flatService.saveFlat(flat1);
        return "redirect:/flat/list";
    }

    @GetMapping("/index/{id}")
    @PreAuthorize("hasAuthority('user:read')")
    public String index(@PathVariable("id") Long id, Model model) {
        model.addAttribute("flat", flatService.findById(id));
        return "flat/index";
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
        Flat flat1 = new Flat.Builder(flat)
                .setUser( userService.findByEmail(flat.getUser().getEmail()))
                .setFloor(floorService.findByfloor(flat.getFloor().getFloorNumber()))
                .setSection(sectionService.findBySectionNumber(flat.getSection().getSectionNumber()))
                .setHouse(houseService.findByAddress(flat.getHouse().getAddress()))
                .build();

        flatService.saveFlat(flat1);
        return "redirect:/flat/list";
    }

    @PostMapping("/{id}")
    @PreAuthorize("hasAuthority('admin:write')")
    public String delete(@PathVariable("id") Long id) {
        flatService.deleteById(id);
        return "redirect:/flat/list";
    }


}
