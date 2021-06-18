package com.politeh.edu.diplom.services;

import com.politeh.edu.diplom.model.Meter;
import com.politeh.edu.diplom.repository.MeterRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MeterService {
    private final MeterRepo meterRepo;

    @Autowired
    public MeterService(MeterRepo meterRepo) {
        this.meterRepo = meterRepo;
    }

    public Meter saveMeter(Meter meter){
        return meterRepo.save(meter);
    }

    public Meter findById(Long id){
        return meterRepo.getOne(id);
    }

    public void deleteById(Long id){
        meterRepo.deleteById(id);
    }

    public List<Meter> findAll(){
        return meterRepo.findAll();
    }
}
