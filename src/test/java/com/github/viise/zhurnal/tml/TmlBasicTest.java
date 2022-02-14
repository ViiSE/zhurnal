package com.github.viise.zhurnal.tml;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class TmlBasicTest {

    @Test
    public void create() {
        String actual = new TmlBasic("Hello!").create();
        assertEquals(actual, "Hello!");
    }

    @Test
    public void create_null() {
        String actual = new TmlBasic(null).create();
        assertEquals(actual, "null");
    }
}
