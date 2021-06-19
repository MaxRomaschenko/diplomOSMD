package com.politeh.edu.diplom.services;

import com.politeh.edu.diplom.model.Flat;
import com.politeh.edu.diplom.model.Tariff;
import com.politeh.edu.diplom.model.User;
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
