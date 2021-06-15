package com.politeh.edu.diplom.services;

import com.politeh.edu.diplom.model.Section;
import com.politeh.edu.diplom.repository.SectionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SectionService {

    private final SectionRepo sectionRepo;

    @Autowired
    public SectionService(SectionRepo sectionRepo) {
        this.sectionRepo = sectionRepo;
    }

    public Section saveSection(Section section){
        return sectionRepo.save(section);
    }

    public Section findById(Long id){
        return sectionRepo.getOne(id);
    }

    public void deleteById(Long id){
        sectionRepo.deleteById(id);
    }


    public List<Section> findAll(){
        return sectionRepo.findAll();
    }

}

