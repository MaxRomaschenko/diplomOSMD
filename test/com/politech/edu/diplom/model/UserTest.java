package com.politech.edu.diplom.model;

import com.politeh.edu.diplom.model.Flat;
import com.politeh.edu.diplom.model.Role;
import com.politeh.edu.diplom.model.Status;
import com.politeh.edu.diplom.model.User;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class UserTest {
    private static User user;

    @BeforeClass
    public static void setUp() throws Exception {
        user = new User();
    }

    @Test
    public void testSetUserPasswordValidValue(){
        String password = "admin";
        user.setPassword(password);

        assertEquals(password, user.getPassword());
    }

    @Test
    public void testSetUserfirstNameValidValue(){
        String firstName = "oleg";
        user.setFirstName(firstName);

        assertEquals(firstName, user.getFirstName());
    }

    @Test
    public void testSetUserlastNameValidValue(){
        String lastName = "popov";
        user.setLastName(lastName);

        assertEquals(lastName, user.getLastName());
    }

    @Test
    public void testSetUsermiddleNameValidValue(){
        String middlename = "alex";
        user.setMiddleName(middlename);

        assertEquals(middlename, user.getMiddleName());
    }

    @Test
    public void testSetUserToleValidValue(){
        Role role = Role.ADMIN;
        user.setRole(role);

        assertEquals(role, user.getRole());
    }

    @Test
    public void testSetStatusToleValidValue(){
        Status status = Status.ACTIVE;
        user.setStatus(status);

        assertEquals(status, user.getStatus());
    }

    @Test
    public void testSetPhoneToleValidValue(){
        String phone = "111111";
        user.setPhone(phone);

        assertEquals(phone, user.getPhone());
    }

    @Test
    public void testSetViberToleValidValue(){
        String viber = "111111";
        user.setViber(viber);

        assertEquals(viber, user.getViber());
    }

    @Test
    public void testSetTelegramToleValidValue(){
        String telegram = "111111";
        user.setTelegram(telegram);

        assertEquals(telegram, user.getTelegram());
    }

    @Test
    public void testSetCToleValidValue(){
        String telegram = "111111";
        user.setTelegram(telegram);

        assertEquals(telegram, user.getTelegram());
    }

    @Test
    public void testSetCreatedAtPassValidValue() {
        LocalDate time = LocalDate.of(2015, Month.JANUARY, 1);
        user.setCreated_at(time);

        assertEquals(time, user.getCreated_at());
    }

    // updated_at attribute

    @Test
    public void testSetUpdatedAtPassValidValue() {
        LocalDate time = LocalDate.of(2015, Month.JANUARY, 2);
        user.setUpdated_at(time);

        assertEquals(time, user.getUpdated_at());
    }

    //house attribute
}
