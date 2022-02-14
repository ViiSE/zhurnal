package com.github.viise.zhurnal.tml;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class TmlChildTest {

    @Test
    public void create() {
        String actual = new TmlChild("value", 22).create();
        assertEquals(actual, "<VALUE:22>");
    }

    @Test
    public void create_nullName() {
        String actual = new TmlChild(null, 22).create();
        assertEquals(actual, "<NULL:22>");
    }

    @Test
    public void create_nullValue() {
        String actual = new TmlChild("value", null).create();
        assertEquals(actual, "<VALUE:null>");
    }
}
