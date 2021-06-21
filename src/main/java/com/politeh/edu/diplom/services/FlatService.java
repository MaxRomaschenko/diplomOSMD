package com.politeh.edu.diplom.services;

import com.politeh.edu.diplom.model.*;
import com.politeh.edu.diplom.repository.FlatRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlatService {

    private final FlatRepo flatRepo;

    @Autowired
    public FlatService(FlatRepo flatRepo) {
        this.flatRepo = flatRepo;
    }

    public Flat findByHouseAndSectionAndFloor(Long houseId, Long sectionId,  Long floorId){
        return  flatRepo.findByHouseIdAndSectionIdAndFloorId(houseId,sectionId,floorId);
    }
    public Flat findByHouseAndSectionAndFloor(House house, Section section,  Floor floor){
        return  flatRepo.findByHouseAndSectionAndFloor(house,section,floor);
    }
    public Flat findByFlatNumber(Integer flatNum){
        return flatRepo.findByFlatNumber(flatNum);
    }

    public Flat findByBankBook(String bankBook) {
        return flatRepo.findByBankBook(bankBook);
    }

    public Flat saveFlat(Flat flat){
        return flatRepo.save(flat);
    }

    public Flat findById(Long id){
        return flatRepo.getOne(id);
    }

    public void deleteById(Long id){
        flatRepo.deleteById(id);
    }

    public List<Flat> findAll(){
        return flatRepo.findAll();
    }
}
