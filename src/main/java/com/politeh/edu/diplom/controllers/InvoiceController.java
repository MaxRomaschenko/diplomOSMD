package com.politeh.edu.diplom.controllers;



import com.politeh.edu.diplom.model.Flat;
import com.politeh.edu.diplom.model.Invoice;
import com.politeh.edu.diplom.model.PayStatus;
import com.politeh.edu.diplom.services.FlatService;
import com.politeh.edu.diplom.services.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/invoice")
public class InvoiceController {
    private final InvoiceService invoiceService;
    private final FlatService flatService;

    @Autowired
    public InvoiceController(InvoiceService invoiceService, FlatService flatService) {
        this.invoiceService = invoiceService;
        this.flatService = flatService;
    }

    @GetMapping("")
    @PreAuthorize("hasAuthority('user:read')")
    public String listOfInvoices(Model model) {
        List<Invoice> invoices = invoiceService.findAll();
        model.addAttribute("invoices", invoices);
        List<Flat> flats = flatService.findAll();
        model.addAttribute("flats",flats);
        return "new/invoice";
    }

    @GetMapping("/create")
    @PreAuthorize("hasAuthority('admin:write')")
    public String createInvoiceForm(@ModelAttribute("invoice") Invoice invoice,
                                  @ModelAttribute("date_start") String dateStart,
                                  @ModelAttribute("date_end") String dateEnd,
                                  @ModelAttribute("stringStatus") String stringStatus,
                                  Model model){
        List<Flat> flats = flatService.findAll();
        model.addAttribute("flats",flats);

        return "invoice/create";
    }

    @PostMapping()
    @PreAuthorize("hasAuthority('admin:write')")
    public String createInvoice(@ModelAttribute("invoice") Invoice invoice,
                              @ModelAttribute("date_start") String dateStart,
                              @ModelAttribute("date_end") String dateEnd,
                              @ModelAttribute("stringStatus") String stringStatus,
                              BindingResult bindingResult){

        invoice.setStatus(PayStatus.valueOf(stringStatus.toUpperCase(Locale.ROOT)));
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        invoice.setPeriodStart(LocalDate.parse(dateStart, dateTimeFormatter));
        invoice.setPeriodEnd(LocalDate.parse(dateEnd, dateTimeFormatter));
        invoice.setFlat(flatService.findByBankBook(invoice.getFlat().getBankBook()));

     //   Flat flat1 = flatService
        if (bindingResult.hasErrors()  ) {
            return "invoice/create";
        }


        invoiceService.saveInvoice(invoice);
        invoice.setInvoiceNumber(346716823L + invoice.getId());
        invoiceService.saveInvoice(invoice);
        return "redirect:/invoice";
    }

    @GetMapping("/{id}/edit")
    @PreAuthorize("hasAuthority('admin:write')")
    public String edit(@ModelAttribute("invoice") Invoice invoice) {
        return "invoice/edit";
    }

    @PostMapping("/{id}/edit")
    @PreAuthorize("hasAuthority('admin:write')")
    public String update(@ModelAttribute("invoice") @Valid Invoice floor,
                         BindingResult bindingResult) {

        if (bindingResult.hasErrors() ) {
            return "invoice/edit";
        }


        invoiceService.saveInvoice(floor);
        return "redirect:/invoice";
    }

    @PostMapping("/{id}")
    @PreAuthorize("hasAuthority('admin:write')")
    public String delete(@PathVariable("id") Long id) {
        invoiceService.deleteById(id);
        return "redirect:/invoice";
    }

}
