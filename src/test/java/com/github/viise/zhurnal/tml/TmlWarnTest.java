package com.github.viise.zhurnal.tml;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class TmlWarnTest {

    @Test
    public void create() {
        String actual = new TmlWarn().create();
        assertEquals(actual, "[WARN]");
    }
}
