package com.politeh.edu.diplom.repository;

import com.politeh.edu.diplom.model.Invoice;
import com.politeh.edu.diplom.model.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceRepo extends JpaRepository<Invoice,Long> {

}