package com.politeh.edu.diplom.services;

import com.politeh.edu.diplom.model.Tariff;
import com.politeh.edu.diplom.model.User;
import com.politeh.edu.diplom.repository.TariffRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TariffService {
    private final TariffRepo tariffRepo;

    @Autowired
    public TariffService(TariffRepo tariffRepo) {
        this.tariffRepo = tariffRepo;
    }

    public Tariff saveTariff(Tariff tariff){
        return tariffRepo.save(tariff);
    }

    public Tariff findById(Long id){
        return tariffRepo.getOne(id);
    }

    public void deleteById(Long id){
        tariffRepo.deleteById(id);
    }


    public List<Tariff> findAll(){
        return tariffRepo.findAll();
    }


}
