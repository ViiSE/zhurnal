package com.github.viise.zhurnal.tml;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class TmlInfoTest {

    @Test
    public void create() {
        String actual = new TmlInfo().create();
        assertEquals(actual, "[INFO]");
    }
}
