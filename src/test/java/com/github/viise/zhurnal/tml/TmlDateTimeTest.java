package com.github.viise.zhurnal.tml;

import org.testng.annotations.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.testng.Assert.assertEquals;

public class TmlDateTimeTest {

    @Test
    public void create() {
        String actual = new TmlDateTime().create();
        DateTimeFormatter dtf = DateTimeFormatter.ISO_DATE_TIME;
        dtf.parse(actual.replace("[", "").replace("]", ""));
    }

    @Test
    public void create_customDateTime() {
        LocalDateTime ldt = LocalDateTime.now();
        String actual = new TmlDateTime(ldt).create();
        assertEquals(actual, String.format("[%s]", DateTimeFormatter.ISO_DATE_TIME.format(ldt)));
    }

    @Test
    public void create_customPattern() {
        LocalDate ld = LocalDate.now();
        String actual = new TmlDateTime(LocalDateTime.now(), "yyyy-MM-dd").create();
        assertEquals(actual, String.format("[%s]", ld));
    }

    @Test
    public void create_customPattern_2() {
        LocalDateTime ldt = LocalDateTime.now();
        String actual = new TmlDateTime(ldt, DateTimeFormatter.ISO_DATE_TIME).create();
        assertEquals(actual, String.format("[%s]", DateTimeFormatter.ISO_DATE_TIME.format(ldt)));
    }

    @Test
    public void create_nullDateTime() {
        String actual = new TmlDateTime(null).create();
        assertEquals(actual, "[null]");
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    public void create_nullDateTimeFormatter() {
        DateTimeFormatter dtf = null;
        LocalDateTime ldt = LocalDateTime.now();
        String actual = new TmlDateTime(ldt, dtf).create();
        assertEquals(actual, String.format("[%s]", DateTimeFormatter.ISO_DATE_TIME.format(ldt)));
    }
}
