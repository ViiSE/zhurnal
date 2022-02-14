package com.github.viise.zhurnal.tml;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class TmlTraceTest {

    @Test
    public void create() {
        String actual = new TmlTrace().create();
        assertEquals(actual, "[TRACE]");
    }
}
