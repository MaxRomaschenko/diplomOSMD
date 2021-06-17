package com.politeh.edu.diplom.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="flat")
public class Flat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "flat_number")
    private Integer flatNumber;

    @Column(name = "square")
    private Double square;

    @NotEmpty(message = "Введите лицевой счёт")
    @Size(max = 20,message = "Максимум 20 символов")
    @Column(name="bank_book")
    private String bankBook;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, optional = false)
    @JoinColumn(name = "house_id",referencedColumnName  = "id")
    private House house;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, optional = false)
    @JoinColumn(name = "floor_id",referencedColumnName  = "id")
    private Floor floor;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, optional = false)
    @JoinColumn(name = "user_id",referencedColumnName  = "id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, optional = false)
    @JoinColumn(name = "section_id",referencedColumnName  = "id")
    private Section section;

    @OneToMany(cascade = CascadeType.PERSIST)
    private List<Tariff> tariffs ;
}
