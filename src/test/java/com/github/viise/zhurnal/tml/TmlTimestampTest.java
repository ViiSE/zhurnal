package com.github.viise.zhurnal.tml;

import org.testng.annotations.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.testng.Assert.assertEquals;

public class TmlTimestampTest {

    @Test
    public void create() {
        String actual = new TmlTimestamp().create();
        DateTimeFormatter dtf = DateTimeFormatter.ISO_DATE_TIME;
        dtf.parse(actual.replace("[TIMESTAMP ", "").replace("]", ""));
    }

    @Test
    public void create_customDateTime() {
        LocalDateTime ldt = LocalDateTime.now();
        String actual = new TmlTimestamp(ldt).create();
        assertEquals(actual, String.format("[TIMESTAMP %s]", DateTimeFormatter.ISO_DATE_TIME.format(ldt)));
    }

    @Test
    public void create_customPattern() {
        LocalDate ld = LocalDate.now();
        String actual = new TmlTimestamp(LocalDateTime.now(), "yyyy-MM-dd").create();
        assertEquals(actual, String.format("[TIMESTAMP %s]", ld));
    }

    @Test
    public void create_customPattern_2() {
        LocalDateTime ldt = LocalDateTime.now();
        String actual = new TmlTimestamp(ldt, DateTimeFormatter.ISO_DATE_TIME).create();
        assertEquals(actual, String.format("[TIMESTAMP %s]", DateTimeFormatter.ISO_DATE_TIME.format(ldt)));
    }

    @Test
    public void create_nullDateTime() {
        String actual = new TmlTimestamp(null).create();
        assertEquals(actual, "[TIMESTAMP null]");
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    public void create_nullDateTimeFormatter() {
        DateTimeFormatter dtf = null;
        LocalDateTime ldt = LocalDateTime.now();
        String actual = new TmlTimestamp(ldt, dtf).create();
        assertEquals(actual, String.format("[TIMESTAMP %s]", DateTimeFormatter.ISO_DATE_TIME.format(ldt)));
    }
}
