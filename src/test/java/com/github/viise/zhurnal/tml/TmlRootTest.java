package com.github.viise.zhurnal.tml;

import com.github.viise.zhurnal.Template;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class TmlRootTest {

    @Test
    public void create() {
        String actual = new TmlRoot(
                "response",
                new TmlChild("status", 200),
                new TmlChild("status_text", "OK")
        ).create();
        assertEquals(actual, "[RESPONSE <STATUS:200> <STATUS_TEXT:OK>]");
    }

    @Test
    public void create_list() {
        String actual = new TmlRoot(
                "response",
                new ArrayList<Template>() {{
                    add(new TmlChild("status", 200));
                    add(new TmlChild("status_text", "OK"));
                }}
        ).create();
        assertEquals(actual, "[RESPONSE <STATUS:200> <STATUS_TEXT:OK>]");
    }

    @Test
    public void create_withoutChildren() {
        String actual = new TmlRoot("response").create();
        assertEquals(actual, "[RESPONSE]");
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    public void create_nullParams() {
        List<Template> tmls = null;
        String actual = new TmlRoot("response", tmls).create();
        assertEquals(actual, "[RESPONSE]");
    }

    @Test
    public void create_nullName() {
        String actual = new TmlRoot(null, new TmlChild("value", 1)).create();
        assertEquals(actual, "[NULL <VALUE:1>]");
    }
}
