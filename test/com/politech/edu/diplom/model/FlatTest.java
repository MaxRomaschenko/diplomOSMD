package com.politech.edu.diplom.model;

import com.politeh.edu.diplom.exception.DeepPastDateException;
import com.politeh.edu.diplom.exception.FutureDateException;
import com.politeh.edu.diplom.model.Flat;
import com.politeh.edu.diplom.model.Floor;
import com.politeh.edu.diplom.model.House;
import com.politeh.edu.diplom.model.Section;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.Month;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class FlatTest {

    private static Flat flat;

    @BeforeClass
    public static void setUp() throws Exception {
        flat = new Flat();
    }

    // flatNumber attribute

    @Test
    public void testSetFlatNumberPassValidValue() {
        Integer flatNumber = 145;
        flat.setFlatNumber(flatNumber);

        assertEquals(flatNumber, flat.getFlatNumber());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetFlatNumberPassInvalidValue() {
        Integer flatNumber = -145;
        flat.setFlatNumber(flatNumber);

        assertNotEquals(flatNumber, flat.getFlatNumber());
    }

    @Test(expected = NullPointerException.class)
    public void testSetFlatNumberPassNullValue() {
        Integer flatNumber = null;
        flat.setFlatNumber(flatNumber);

        assertNotEquals(flatNumber, flat.getFlatNumber());
    }

    // square attribute

    @Test
    public void testSetSquarePassValidValue() {
        Double square = 15.5;
        flat.setSquare(square);

        assertEquals(square, flat.getSquare());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetSquarePassInvalidValue() {
        double square = -15.5;
        flat.setSquare(square);

        assertNotEquals(square, flat.getSquare(), 1);
    }

    @Test(expected = NullPointerException.class)
    public void testSetSquarePassNullValue() {
        Double square = null;
        flat.setSquare(square);

        assertNotEquals(square, flat.getSquare(), 1);
    }

    // bankBook attribute

    @Test
    public void testSetBankBookPassValidValue() {
        String bankBook = "default_bankbook";
        flat.setBankBook(bankBook);

        assertEquals(bankBook, flat.getBankBook());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetBankBookPassEmptyBankBook() {
        String bankBook = "";
        flat.setBankBook(bankBook);

        assertNotEquals(bankBook, flat.getBankBook());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetBankBookPassNonCapableString() {
        String bankBook = "default_bankbook_default_bankbook_2_default_bankbook_3";
        flat.setBankBook(bankBook);

        assertNotEquals(bankBook, flat.getBankBook());
    }

    @Test(expected = NullPointerException.class)
    public void testSetBankBookPassNullValue() {
        String bankBook = null;
        flat.setBankBook(bankBook);

        assertNotEquals(bankBook, flat.getBankBook());
    }

    // created_at attribute

    @Test
    public void testSetCreatedAtPassValidValue() {
        LocalDateTime time = LocalDateTime.of(2015, Month.JANUARY, 1, 0, 0);
        flat.setCreated_at(time);

        assertEquals(time, flat.getCreated_at());
    }

    // updated_at attribute

    @Test
    public void testSetUpdatedAtPassValidValue() {
        LocalDateTime time = LocalDateTime.of(2015, Month.JANUARY, 2, 0, 0);
        flat.setUpdated_at(time);

        assertEquals(time, flat.getUpdated_at());
    }

    //house attribute

    @Test
    public void testSetHousePassValidValue() {
        House house = new House();
        house.setAddress("Some address");
        house.setHouseName("Some name");

        flat.setHouse(house);

        assertEquals(house, flat.getHouse());
    }

    @Test(expected = NullPointerException.class)
    public void testSetHousePassNull() {
        House house = null;
        flat.setHouse(house);

        assertNotEquals(house, flat.getHouse());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetHousePassEmptyValue() {
        House house = new House();
        flat.setHouse(house);

        assertEquals(house, flat.getHouse());
    }

    //floor attribute

    @Test
    public void testSetFloorPassValidValue() {
        Floor floor = new Floor();
        floor.setFloorNumber(5);

        flat.setFloor(floor);

        assertEquals(floor, flat.getFloor());
    }

    @Test(expected = NullPointerException.class)
    public void testSetFloorPassNull() {
        Floor floor = null;
        flat.setFloor(floor);

        assertNotEquals(floor, flat.getFloor());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetFloorPassEmptyValue() {
        Floor floor = new Floor();
        flat.setFloor(floor);

        assertEquals(floor, flat.getFloor());
    }

    //section attribute

    @Test
    public void testSetSectionPassValidValue() {
        Section section = new Section();
        section.setSectionNumber(1);

        flat.setSection(section);

        assertEquals(section, flat.getSection());
    }

    @Test(expected = NullPointerException.class)
    public void testSetSectionPassNull() {
        Section section = null;
        flat.setSection(section);

        assertNotEquals(section, flat.getSection());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetSectionPassEmptyValue() {
        Section section = new Section();
        flat.setSection(section);

        assertNotEquals(section, flat.getSection());
    }
}
