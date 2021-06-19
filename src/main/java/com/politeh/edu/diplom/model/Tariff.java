package com.politeh.edu.diplom.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="tariff")
public class Tariff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @NotEmpty(message = "Введите название услуги")
//    @Column(name ="name")
//    @Size(min = 2,max= 70)
    private String name;


//    @NotEmpty(message = "Введите изм.вел.")
//    @Column(name ="unit")
//    @Size(min = 1,max= 10)
    private String unit;

//    @NotNull(message = "Введите цену")
//    @Column(name ="to_pay")
//    @Min(0)
    private Double toPay;

}
