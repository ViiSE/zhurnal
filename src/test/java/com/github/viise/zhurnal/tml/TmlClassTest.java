package com.github.viise.zhurnal.tml;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class TmlClassTest {

    @Test
    public void create() {
        String actual = new TmlClass(TmlClassTest.class).create();
        assertEquals(actual, "[com.github.viise.zhurnal.tml.TmlClassTest]");
    }

    @Test
    public void create_simpleName() {
        String actual = new TmlClass(TmlClassTest.class, false).create();
        assertEquals(actual, "[TmlClassTest]");
    }

    @Test
    public void create_nullClass() {
        String actual = new TmlClass(null).create();
        assertEquals(actual, "[null]");
    }
}
