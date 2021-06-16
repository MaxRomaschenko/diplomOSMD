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

    @NotEmpty(message = "Введите номер квартиры")
    @Min(0)
    @Column(name = "flat_number")
    private Integer flatNumber;
    @NotEmpty(message = "Введите размер квартиры м2")
    @Min(0)
    @Column(name = "square")
    private Double square;

    @NotEmpty(message = "Введите лицевой счёт")
    @Size(max = 20,message = "Максимум 20 символов")
    @Column(name="bank_book")
    private String bankBook;

    @Column(name="created_at")
    private LocalDateTime created_at;
    @Column(name="updated_at")
    private LocalDateTime updated_at;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, optional = false)
    @JoinColumn(name = "house_id",referencedColumnName  = "id")
    private House house;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, optional = false)
    @JoinColumn(name = "floor_id",referencedColumnName  = "id")
    private Floor floor;
    @OneToOne
    private User user;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, optional = false)
    @JoinColumn(name = "section_id",referencedColumnName  = "id")
    private Section section;
    @OneToMany(cascade = CascadeType.PERSIST)
    private List<Tariff> tariffs = new ArrayList<>();
}
