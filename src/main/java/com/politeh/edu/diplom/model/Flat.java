package com.politeh.edu.diplom.model;

import com.politeh.edu.diplom.exception.DeepPastDateException;
import com.politeh.edu.diplom.exception.FutureDateException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="flat")
public class Flat {

    private static final Integer BANKBOOK_MAX_LENGTH = 20;
    private static final LocalDateTime MIN_DATE
            = LocalDateTime.of(1900, Month.JANUARY, 1, 0, 0);


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

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, optional = false)
    @JoinColumn(name = "user_id",referencedColumnName  = "id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, optional = false)
    @JoinColumn(name = "section_id",referencedColumnName  = "id")
    private Section section;

    public void setFlatNumber(Integer flatNumber) {
        if (flatNumber == null) {
            String message = "The flat number has not been initialized";
            throw new NullPointerException(message);
        }
        if (flatNumber < 0) {
            String message = "The flat number should be a positive integer number";
            throw new IllegalArgumentException(message);
        }

        this.flatNumber = flatNumber;
    }

    public void setSquare(Double square) {
        if (square == null) {
            String message = "The square value has not been initialized";
            throw new NullPointerException(message);
        }
        if (square < 0) {
            String message = "The square size should be a positive";
            throw new IllegalArgumentException(message);
        }

        this.square = square;
    }

    public void setBankBook(String bankBook) {
        if (bankBook == null) {
            String message = "The bank book has not been initialized";
            throw new NullPointerException(message);
        }
        if (bankBook.isEmpty()) {
            String message = "The bank book is empty";
            throw new IllegalArgumentException(message);
        }
        if (bankBook.length() > BANKBOOK_MAX_LENGTH) {
            String message = "The bank book size is higher than " + BANKBOOK_MAX_LENGTH;
            throw new IllegalArgumentException(message);
        }

        this.bankBook = bankBook;
    }

    public void setCreatedAt(LocalDateTime created_at) throws DeepPastDateException, FutureDateException {
        if (created_at == null) {
            String message = "The created date has not been initialized";
            throw new NullPointerException(message);
        }
        if (created_at.isBefore(MIN_DATE)) {
            String message = "The proposed date is too far from the current date";
            throw new DeepPastDateException(message);
        }
        if (created_at.isAfter(LocalDateTime.now())) {
            String message = "The proposed date has not become";
            throw new FutureDateException(message);
        }

        this.created_at = created_at;
    }

    public void setUpdatedAt(LocalDateTime updated_at) throws DeepPastDateException, FutureDateException {
        if (updated_at == null) {
            String message = "The updated date has not been initialized";
            throw new NullPointerException(message);
        }
        if (updated_at.isBefore(MIN_DATE)) {
            String message = "The proposed date is too far from the current date";
            throw new DeepPastDateException(message);
        }
        if (updated_at.isAfter(LocalDateTime.now())) {
            String message = "The proposed date has not become";
            throw new FutureDateException(message);
        }

        if (updated_at.isBefore(getCreated_at())) {
            String message = "The update date is preceding the created date";
            throw new IllegalArgumentException(message);
        }

        this.updated_at = updated_at;
    }

    public void setHouse(House house) {
        if (house == null) {
            String message = "The house value has not been initialized";
            throw new NullPointerException(message);
        }
//        if (house.getHouseName() == null || house.getAddress() == null) {
//            String message = "The house attribute has no the main information about either the name or address";
//            throw new IllegalArgumentException(message);
//        }

        this.house = house;
    }

    public void setFloor(Floor floor) {
        if (floor == null) {
            String message = "The floor value has not been initialized";
            throw new NullPointerException(message);
        }
//        if (floor.getFloorNumber() == null) {
//            String message = "The floor value is less than zero";
//            throw new IllegalArgumentException(message);
//        }

        this.floor = floor;
    }

    public void setSection(Section section) {
        if (section == null) {
            String message = "The section value has not been initialized";
            throw new NullPointerException(message);
        }
//        if (section.getSectionNumber() == null) {
//            String message = "The section number is less than zero";
//            throw new IllegalArgumentException(message);
//        }

        this.section = section;
    }



    public static class Builder{
        private Flat flat;

        public Builder() {
            flat = new Flat();
        }

        public Builder(Flat flat){
            this.flat = flat;
        }

        public Builder setFlatNumber(Integer flatNumber){
            flat.setFlatNumber(flatNumber);
            return this;
        }
        public Builder setSquare(Double square){
            flat.setSquare(square);
            return this;
        }
        public Builder setBankBook(String bankBook){
            flat.setBankBook(bankBook);
            return this;
        }
        public Builder setHouse(House house){
            flat.setHouse(house);
            return this;
        }
        public Builder setFloor(Floor floor){
            flat.setFloor(floor);
            return this;
        }

        public Builder setUser(User user){
            flat.setUser(user);
            return this;
        }
        public Builder setSection(Section section){
            flat.setSection(section);
            return this;
        }

        public Builder setCreatedAT(LocalDateTime createdAT){
            flat.setCreated_at(createdAT);
            return this;
        }
        public Builder setUpdatedAT(LocalDateTime updatedAT){
            flat.setUpdated_at(updatedAT);
            return this;
        }

        public Flat build(){
            return flat;
        }

    }

}
