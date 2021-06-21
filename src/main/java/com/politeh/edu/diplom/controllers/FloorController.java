package com.politeh.edu.diplom.controllers;

import com.politeh.edu.diplom.model.Floor;
import com.politeh.edu.diplom.model.House;
import com.politeh.edu.diplom.services.FloorService;
import com.politeh.edu.diplom.services.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/floor")
public class  FloorController {
    private final FloorService floorService;
    private final HouseService houseService;

    @Autowired
    public FloorController(FloorService floorService, HouseService houseService) {
        this.floorService = floorService;
        this.houseService = houseService;
    }

    @GetMapping("/list")
    @PreAuthorize("hasAuthority('user:read')")
    public String listOfFloors(Model model) {
        List<Floor> floors = floorService.findAll();
        model.addAttribute("floor", floors);
        return "floor/list";
    }

    @GetMapping("/create")
    @PreAuthorize("hasAuthority('admin:write')")
    public String createFloorForm(@ModelAttribute("floor") Floor floor,
                                  @ModelAttribute("house") House house){
        return "floor/create";
    }

    @PostMapping()
    @PreAuthorize("hasAuthority('admin:write')")
    public String createFloor(@ModelAttribute("floor") @Valid Floor floor,
                             @ModelAttribute("house") @Valid House house,
                             BindingResult bindingResult){
        House houseCheck = houseService.findByAddress(house.getAddress());
        if (bindingResult.hasErrors() || houseCheck == null ) {
            return "floor/create";
        }

        floor.setHouse(houseCheck);
        floorService.saveFloor(floor);
        return "redirect:/floor/list";
    }

    @GetMapping("/{id}/edit")
    @PreAuthorize("hasAuthority('admin:write')")
    public String edit(@ModelAttribute("floor") Floor floor,
                       @ModelAttribute("house") House house) {
        return "floor/edit";
    }

    @PostMapping("/{id}/edit")
    @PreAuthorize("hasAuthority('admin:write')")
    public String update(@ModelAttribute("floor") @Valid Floor floor,
                         @ModelAttribute("house") @Valid House house,
                         BindingResult bindingResult) {
        House houseCheck = houseService.findByAddress(house.getAddress());
        if (bindingResult.hasErrors() || houseCheck == null) {
            return "floor/edit";
        }

        floor.setHouse(houseCheck);
        floorService.saveFloor(floor);
        return "redirect:/floor/list";
    }

    @PostMapping("/{id}")
    @PreAuthorize("hasAuthority('admin:write')")
    public String delete(@PathVariable("id") Long id) {
        floorService.deleteById(id);
        return "redirect:/floor/list";
    }
}
