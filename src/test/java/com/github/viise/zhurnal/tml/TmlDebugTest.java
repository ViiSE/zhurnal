package com.github.viise.zhurnal.tml;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class TmlDebugTest {

    @Test
    public void create() {
        String actual = new TmlDebug().create();
        assertEquals(actual, "[DEBUG]");
    }
}
