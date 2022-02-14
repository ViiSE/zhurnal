package com.github.viise.zhurnal.tml;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class TmlErrorTest {

    @Test
    public void create() {
        String actual = new TmlError().create();
        assertEquals(actual, "[ERROR]");
    }
}
