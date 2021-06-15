package com.politeh.edu.diplom.services;

import com.politeh.edu.diplom.model.House;
import com.politeh.edu.diplom.repository.HouseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HouseService {

    private final HouseRepo houseRepo;

    @Autowired
    public HouseService(HouseRepo houseRepo) {
        this.houseRepo = houseRepo;
    }

    public House saveHouse(House house){
        return houseRepo.save(house);
    }

    public House findById(Long id){
        return houseRepo.getOne(id);
    }

    public void deleteById(Long id){
        houseRepo.deleteById(id);
    }


    public List<House> findAll(){
        return houseRepo.findAll();
    }
}
