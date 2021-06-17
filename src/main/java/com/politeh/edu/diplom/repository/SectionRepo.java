package com.politeh.edu.diplom.repository;


import com.politeh.edu.diplom.model.Section;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SectionRepo extends JpaRepository<Section,Long> {
    Section findBySectionNumber(Integer number);
}
