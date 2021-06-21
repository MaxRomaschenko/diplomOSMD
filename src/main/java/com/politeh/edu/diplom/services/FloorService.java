package com.politeh.edu.diplom.services;

import com.politeh.edu.diplom.model.Floor;
import com.politeh.edu.diplom.repository.FloorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FloorService {
    private final FloorRepo floorRepo;

    @Autowired
    public FloorService(FloorRepo floorRepo) {
        this.floorRepo = floorRepo;
    }


    public Floor saveFloor(Floor floor){
        return floorRepo.save(floor);
    }

    public Floor findById(Long id){
        return floorRepo.getOne(id);
    }

    public void deleteById(Long id){
        floorRepo.deleteById(id);
    }

    public Floor findByfloorNum(Integer floorNum){
        return floorRepo.findByFloorNumber(floorNum);
    }

    public List<Floor> findAll(){
        return floorRepo.findAll();
    }


}
