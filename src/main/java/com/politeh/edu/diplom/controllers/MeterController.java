package com.politeh.edu.diplom.controllers;


import com.politeh.edu.diplom.model.Flat;
import com.politeh.edu.diplom.model.Meter;
import com.politeh.edu.diplom.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequestMapping("/meter")
public class MeterController {
    private final MeterService meterService;
    private final FlatService flatService;
    private final HouseService houseService;
    private final SectionService sectionService;
    private final FloorService floorService;

    @Autowired
    public MeterController(MeterService meterService, FlatService flatService,
                           HouseService houseService, SectionService sectionService,
                           FloorService floorService) {
        this.meterService = meterService;
        this.flatService = flatService;
        this.houseService = houseService;
        this.sectionService = sectionService;
        this.floorService = floorService;
    }

    @GetMapping("")
    @PreAuthorize("hasAuthority('user:read')")
    public String listOfMeters(Model model) {
        List<Meter> meters = meterService.findAllSorted();
        model.addAttribute("meters", meters);
        List<Flat> flats = flatService.findAll();
        model.addAttribute("flats", flats);
        return "new/meter";
    }

//    @GetMapping("/filter")
//    public List<Meter> filterFlatNumber( List<Meter> meters, Integer flatNum){
//        return meters.stream()
//                .filter(meter -> meter.getFlat().getFlatNumber().equals(flatNum))
//                .collect(Collectors.toList());
//    }

    @GetMapping("/create")
    @PreAuthorize("hasAuthority('admin:write')")
    public String createMetersForm(@ModelAttribute("meter") Meter meter,
                                   @ModelAttribute("date") String date,
                                   Model model){
        List<Flat> flats = flatService.findAll();
        model.addAttribute("flats", flats);
        return "meter/create";
    }

    @PostMapping()
    @PreAuthorize("hasAuthority('admin:write')")
    public String createMeter(@ModelAttribute("meter") @Valid Meter meter,
                              @ModelAttribute("date") String date,
                             BindingResult bindingResult){


        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        meter.setData(LocalDate.parse(date, dateTimeFormatter));
        meter.setFlat(flatService.findBySectionAndHouseAndFloor(
                houseService.findByAddress(meter.getFlat().getHouse().getAddress()),
                sectionService.findBySectionNumber(meter.getFlat().getSection().getSectionNumber()),
                floorService.findByfloor(meter.getFlat().getFloor().getFloorNumber())));
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


