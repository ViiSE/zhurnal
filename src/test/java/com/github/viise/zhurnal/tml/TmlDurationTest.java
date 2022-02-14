package com.github.viise.zhurnal.tml;

import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;

public class TmlDurationTest {

    @Test
    public void create() {
        String actual = new TmlDuration(200L).create();
        assertEquals(actual, "[DURATION <VALUE:200> <UNIT:MILLISECONDS>]");
    }

    @Test
    public void create_customUnit() {
        String actual = new TmlDuration(2L, TimeUnit.SECONDS).create();
        assertEquals(actual, "[DURATION <VALUE:2> <UNIT:SECONDS>]");
    }

    @Test
    public void create_nullUnit() {
        String actual = new TmlDuration(2L, null).create();
        assertEquals(actual, "[DURATION <VALUE:2> <UNIT:null>]");
    }

    @Test
    public void create_nullDuration() {
        String actual = new TmlDuration(null).create();
        assertEquals(actual, "[DURATION <VALUE:null> <UNIT:MILLISECONDS>]");
    }
}
