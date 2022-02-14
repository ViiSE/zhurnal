package com.github.viise.zhurnal.tml;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class TmlThreadTest {

    @Test
    public void create() {
        String actual = new TmlThread(1L, "Thread-1", true, false).create();
        assertEquals(actual, "[THREAD <ID:1> <NAME:Thread-1> <IS_ALIVE:true> <IS_INTERRUPTED:false>]");
    }

    @Test
    public void create_thread() {
        String actual = new TmlThread(Thread.currentThread()).create();
        assertEquals(actual, "[THREAD <ID:1> <NAME:main> <IS_ALIVE:true> <IS_INTERRUPTED:false>]");
    }

    @Test
    public void create_nullThread() {
        String actual = new TmlThread(null).create();
        assertEquals(actual, "[THREAD <ID:null> <NAME:null> <IS_ALIVE:false> <IS_INTERRUPTED:false>]");
    }
}
