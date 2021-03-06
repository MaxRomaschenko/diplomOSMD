package com.politeh.edu.diplom.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="invoice")
public class  Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "invoice_number")
    private Long invoiceNumber;

    @Column(name = "period_start")
    private LocalDate periodStart;
    @Column(name = "period_end")
    private LocalDate periodEnd;


    @NotNull(message = "Выберите статус пользователя")
    @Enumerated(EnumType.STRING)
    @Column(name="status")
    private PayStatus status;
    @Column(name = "to_pay")
    private BigDecimal toPay;
    @Column(name="created_at")
    private LocalDateTime created_at;

    @Column(name="updated_at")
    private LocalDateTime updated_at;

    @NonNull
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, optional = false)
    @JoinColumn(name = "flat_id",referencedColumnName  = "id")
    private Flat flat;

}
