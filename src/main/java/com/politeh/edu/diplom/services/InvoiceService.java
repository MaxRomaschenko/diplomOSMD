package com.politeh.edu.diplom.services;

import com.politeh.edu.diplom.model.Invoice;
import com.politeh.edu.diplom.model.Meter;
import com.politeh.edu.diplom.repository.InvoiceRepo;
import com.politeh.edu.diplom.repository.MeterRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceService {

    private final InvoiceRepo invoiceRepo;

    @Autowired
    public InvoiceService(InvoiceRepo invoiceRepo) {
        this.invoiceRepo = invoiceRepo;
    }

    public Invoice saveInvoice(Invoice invoice){
        return invoiceRepo.save(invoice);
    }

    public Invoice findById(Long id){
        return invoiceRepo.getOne(id);
    }

    public void deleteById(Long id){
        invoiceRepo.deleteById(id);
    }

    public List<Invoice> findAll(){
        return invoiceRepo.findAll();
    }

}
