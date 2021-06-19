package com.politeh.edu.diplom.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="house")
public class House {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name ="house_name")
    @Size(max= 100)
    private String houseName;

    @NotEmpty
    @Column(name ="address")
    @Size(max= 200)
    private String address;

    @Column(name="created_at")
    private LocalDateTime created_at;

    @Column(name="updated_at")
    private LocalDateTime updated_at;

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "house_id")
//    , referencedColumnName  = "id"
    private List<Tariff> tariffs ;
}
