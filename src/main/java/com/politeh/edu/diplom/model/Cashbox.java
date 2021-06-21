package com.politeh.edu.diplom.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="cashbox")
public class Cashbox {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Size(max = 20,message = "Максимум 20 символов")
    @Column(name="list_number")
    private String listNumber;

    @NotEmpty
    @Column(name="data")
    private LocalDate data;

    @NotEmpty
    @Column(name="payment_type")
    private String paymentType;

    @NotEmpty
    @Column(name="cash_transaction_type")
    private String cashTransactionType;

    @NotEmpty
    @Column(name="to_pay")
    private String toPay;

    @Size(max = 25)
    @Column(name="comment")
    private String comment;


   // @NotNull
    //private Flat flat;
}
