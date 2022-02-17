package com.github.viise.zhurnal.tml;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class TmlMsgTest {

    @Test
    public void create() {
        String actual = new TmlMsg("Hello, {}!", "World").create();
        assertEquals(actual, "[MESSAGE Hello, World!]");
    }

    @Test
    public void create_list() {
        String actual = new TmlMsg(
                "Hello, {}! Your age is {}",
                new ArrayList<Object>()
                {{
                    add("John");
                    add(25);
                }}
        ).create();
        assertEquals(actual, "[MESSAGE Hello, John! Your age is 25]");
    }

    @Test
    public void create_tooManyParams() {
        String actual = new TmlMsg("Hello, {}!", "World", 25).create();
        assertEquals(actual, "[MESSAGE Hello, World!]");
    }

    @Test
    public void create_tooManyTemplates() {
        String actual = new TmlMsg("Hello, {}! Your age is {}", "World").create();
        assertEquals(actual, "[MESSAGE Hello, World! Your age is {}]");
    }

    @Test
    public void create_nullMsg() {
        String actual = new TmlMsg(null, "World", 25).create();
        assertEquals(actual, "[MESSAGE null]");
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    public void create_nullParams() {
        List<Object> params = null;
        String actual = new TmlMsg("Hello, {}!", params).create();
        assertEquals(actual, "[MESSAGE Hello, {}!]");
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    public void create_allNull() {
        List<Object> params = null;
        String actual = new TmlMsg(null, params).create();
        assertEquals(actual, "[MESSAGE null]");
    }

    @Test
    public void create_oneOfParamIsNull() {
        String actual = new TmlMsg("Hello, {}! Your age is {}.", "John", null).create();
        assertEquals(actual, "[MESSAGE Hello, John! Your age is null.]");
    }

    @Test
    public void create_withoutParams() {
        String actual = new TmlMsg("Hello, World!").create();
        assertEquals(actual, "[MESSAGE Hello, World!]");
    }
}
