package com.politech.edu.diplom.model.service;

import com.politeh.edu.diplom.model.*;
import com.politeh.edu.diplom.repository.FlatRepo;
import com.politeh.edu.diplom.services.FlatService;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FlatServiceTest {

    private static final Long FLAT_ONE_ID = 1L;
    private static final Integer FLAT_ONE_NUMBER = 1;
    private static final Double FLAT_ONE_SQUARE = 56.5;
    private static final Integer FLAT_ONE_PEOPLE_COUNT = 2;
    private static final String FLAT_ONE_BANKBOOK = "Default bankbook";
    private static final LocalDateTime FLAT_ONE_CREATED_AT = LocalDateTime.of(1990, Month.JANUARY, 1, 0, 0);
    private static final LocalDateTime FLAT_ONE_UPDATED_AT = LocalDateTime.of(2005, Month.JANUARY, 1, 0, 0);

    private static final Long FLAT_ONE_HOUSE_ID = 1L;
    private static final String FLAT_ONE_HOUSE_NAME = "House name";
    private static final String FLAT_ONE_HOUSE_ADDRESS = "House address";
    private static final LocalDateTime FLAT_ONE_HOUSE_CREATED_AT = LocalDateTime.of(1991, Month.JANUARY, 1, 0, 0);
    private static final LocalDateTime FLAT_ONE_HOUSE_UPDATED_AT = LocalDateTime.of(2017, Month.JANUARY, 1, 0, 0);
    private static final Tariff FLAT_ONE_HOUSE_TARIFF_ONE = new Tariff(1L, "Service 1", "Unit 1", 25.0);
    private static final Tariff FLAT_ONE_HOUSE_TARIFF_TWO = new Tariff(2L, "Service 2", "Unit 2", 14.0);
    private static final Tariff FLAT_ONE_HOUSE_TARIFF_THREE = new Tariff(3L, "Service 3", "Unit 3", 17.75);
    private static final Tariff FLAT_ONE_HOUSE_TARIFF_FOUR = new Tariff(4L, "Service 4", "Unit 4", 88.14);

    private static final List<Tariff> FLAT_ONE_HOUSE_TARRIFS = Stream.of(
            FLAT_ONE_HOUSE_TARIFF_ONE,
            FLAT_ONE_HOUSE_TARIFF_TWO,
            FLAT_ONE_HOUSE_TARIFF_THREE,
            FLAT_ONE_HOUSE_TARIFF_FOUR
    ).collect(Collectors.toList());

    private static final House FLAT_ONE_HOUSE = new House(
            FLAT_ONE_HOUSE_ID,
            FLAT_ONE_HOUSE_NAME,
            FLAT_ONE_HOUSE_ADDRESS,
            FLAT_ONE_HOUSE_CREATED_AT,
            FLAT_ONE_HOUSE_UPDATED_AT,
            FLAT_ONE_HOUSE_TARRIFS

    );

    private static final Long FLAT_ONE_FLOOR_ID = 1L;
    private static final Integer FLAT_ONE_FLOOR_NUMBER = 1;
    private static final House FLAT_ONE_FLOOR_HOUSE = FLAT_ONE_HOUSE;
    private static final Floor FLAT_ONE_FLOOR = new Floor(FLAT_ONE_FLOOR_ID, FLAT_ONE_FLOOR_NUMBER, FLAT_ONE_FLOOR_HOUSE);

    private static final Long FLAT_ONE_USER_ID = 1L;
    private static final String FLAT_ONE_USER_PASSWORD = "1234";
    private static final String FLAT_ONE_USER_EMAIL = "user@user.com";
    private static final String FLAT_ONE_USER_FIRST_NAME = "Vasiliy";
    private static final String FLAT_ONE_USER_LAST_NAME = "Skumbrievich";
    private static final String FLAT_ONE_USER_MIDDLE_NAME = "Sidorovich";
    private static final Role FLAT_ONE_USER_ROLE = Role.USER;
    private static final Status FLAT_ONE_USER_STATUS = Status.ACTIVE;
    private static final String FLAT_ONE_USER_PHONE = "88005553535";
    private static final String FLAT_ONE_USER_VIBER = "user_viber_1";
    private static final String FLAT_ONE_USER_TELEGRAM = "user_telegram_1";
    private static final LocalDate FLAT_ONE_USER_CREATED_AT = LocalDate.of(1995, Month.JANUARY, 1);
    private static final LocalDate FLAT_ONE_USER_UPDATED_AT = LocalDate.of(2020, Month.JANUARY, 1);
    private static final User FLAT_ONE_USER = new User(
            FLAT_ONE_USER_ID,
            FLAT_ONE_USER_PASSWORD,
            FLAT_ONE_USER_EMAIL,
            FLAT_ONE_USER_FIRST_NAME,
            FLAT_ONE_USER_LAST_NAME,
            FLAT_ONE_USER_MIDDLE_NAME,
            FLAT_ONE_USER_ROLE,
            FLAT_ONE_USER_STATUS,
            FLAT_ONE_USER_PHONE,
            FLAT_ONE_USER_VIBER,
            FLAT_ONE_USER_TELEGRAM,
            FLAT_ONE_USER_CREATED_AT,
            FLAT_ONE_USER_UPDATED_AT
    );

    private static final Long FLAT_ONE_SECTION_ID = 1L;
    private static final Integer FLAT_ONE_SECTION_NUMBER = 1;
    private static final Section FLAT_ONE_SECTION = new Section(FLAT_ONE_SECTION_ID, FLAT_ONE_SECTION_NUMBER);

    private static final Flat FLAT_ONE = new Flat(
            FLAT_ONE_ID,
            FLAT_ONE_NUMBER,
            FLAT_ONE_SQUARE,
            FLAT_ONE_PEOPLE_COUNT,
            FLAT_ONE_BANKBOOK,
            FLAT_ONE_CREATED_AT,
            FLAT_ONE_UPDATED_AT,
            FLAT_ONE_HOUSE,
            FLAT_ONE_FLOOR,
            FLAT_ONE_USER,
            FLAT_ONE_SECTION
    );

    @Mock
    private FlatRepo repo;
    @InjectMocks
    private FlatService service;

    @Test
    public void testFindById() {
        when(repo.getOne(FLAT_ONE_ID)).thenReturn(FLAT_ONE);
        assertEquals(FLAT_ONE, service.findById(FLAT_ONE_ID));
    }

    @Test
    public void testFindByIdPutNegativeIdCheckNotEquals() {
        assertNotEquals(FLAT_ONE, service.findById(-FLAT_ONE_ID));
    }

    @Test
    public void testFindByIdPutNegativeIdCheckNull() {
        assertNull(service.findById(-FLAT_ONE_ID));
    }

    @Test
    public void testSaveFlat() {
        when(repo.save(FLAT_ONE)).thenReturn(FLAT_ONE);
        assertEquals(FLAT_ONE, service.saveFlat(FLAT_ONE));
    }

    @Test
    public void testSavePassNull() {
        assertNull(service.saveFlat(null));
    }
}
