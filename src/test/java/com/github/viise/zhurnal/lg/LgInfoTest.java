package com.github.viise.zhurnal.lg;

import com.github.viise.zhurnal.HttpMethod;
import com.github.viise.zhurnal.HttpStatus;
import com.github.viise.zhurnal.Template;
import com.github.viise.zhurnal.tml.*;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class LgInfoTest {

    @Test
    public void print() {
        String actual = new LgInfo(
                LgInfoTest.class,
                new Template[] {
                        new TmlHttp(HttpMethod.GET, "/log/1", HttpStatus.OK),
                        new TmlDuration(300L, TimeUnit.MILLISECONDS)
                }
        ).print();

        assertTrue(
                actual.matches(
                "\\[INFO] \\[.*] \\[LgInfoTest] \\[HTTP <METHOD:GET> <ENDPOINT:/log/1> <STATUS:200 OK>] \\[DURATION <VALUE:300> <UNIT:MILLISECONDS>]"
                )
        );
    }

    @Test
    public void print_ctor_1() {
        String actual = new LgInfo(
                LgInfo.class,
                false,
                new TmlDateTime(),
                new ArrayList<Template>() {{ add(new TmlMsg("Hello, log!")); }}
        ).print();
        assertTrue(actual.matches("\\[INFO] \\[.*] \\[LgInfo] \\[MESSAGE Hello, log!]"));
    }

    @Test
    public void print_ctor_1_fullName() {
        String actual = new LgInfo(
                LgInfo.class,
                true,
                new TmlDateTime(),
                new ArrayList<Template>() {{ add(new TmlMsg("Hello, log!")); }}
        ).print();
        assertTrue(actual.matches("\\[INFO] \\[.*] \\[com\\.github\\.viise\\.zhurnal\\.lg\\.LgInfo] \\[MESSAGE Hello, log!]"));
    }

    @Test
    public void print_ctor_2() {
        String actual = new LgInfo(
                LgInfo.class,
                false,
                new TmlDateTime(),
                new TmlMsg("Hello, log!")
        ).print();
        assertTrue(actual.matches("\\[INFO] \\[.*] \\[LgInfo] \\[MESSAGE Hello, log!]"));
    }

    @Test
    public void print_ctor_3() {
        String actual = new LgInfo(
                LgInfo.class,
                false,
                new TmlDateTime(),
                "Hello, log!"
        ).print();
        assertTrue(actual.matches("\\[INFO] \\[.*] \\[LgInfo] \\[MESSAGE Hello, log!]"));
    }

    @Test
    public void print_ctor_4() {
        String actual = new LgInfo(
                LgInfo.class,
                new TmlDateTime(),
                new ArrayList<Template>() {{ add(new TmlMsg("Hello, log!")); }}
        ).print();
        assertTrue(actual.matches("\\[INFO] \\[.*] \\[LgInfo] \\[MESSAGE Hello, log!]"));
    }

    @Test
    public void print_ctor_5() {
        String actual = new LgInfo(
                LgInfo.class,
                new ArrayList<Template>() {{ add(new TmlMsg("Hello, log!")); }}
        ).print();
        assertTrue(actual.matches("\\[INFO] \\[.*] \\[LgInfo] \\[MESSAGE Hello, log!]"));
    }

    @Test
    public void print_ctor_6() {
        String actual = new LgInfo(
                new ArrayList<Template>() {{ add(new TmlMsg("Hello, log!")); }}
        ).print();
        assertTrue(actual.matches("\\[INFO] \\[.*] \\[LgInfo] \\[MESSAGE Hello, log!]"));
    }

    @Test
    public void print_ctor_7() {
        String actual = new LgInfo(
                new TmlDateTime(),
                new ArrayList<Template>() {{ add(new TmlMsg("Hello, log!")); }}
        ).print();
        assertTrue(actual.matches("\\[INFO] \\[.*] \\[LgInfo] \\[MESSAGE Hello, log!]"));
    }

    @Test
    public void print_ctor_8() {
        String actual = new LgInfo(
                LgInfo.class,
                new TmlDateTime(),
                new Template[] { new TmlMsg("Hello, log!") }
        ).print();
        assertTrue(actual.matches("\\[INFO] \\[.*] \\[LgInfo] \\[MESSAGE Hello, log!]"));
    }

    @Test
    public void print_ctor_9() {
        String actual = new LgInfo(
                LgInfo.class,
                new Template[] { new TmlMsg("Hello, log!") }
        ).print();
        assertTrue(actual.matches("\\[INFO] \\[.*] \\[LgInfo] \\[MESSAGE Hello, log!]"));
    }

    @Test
    public void print_ctor_10() {
        String actual = new LgInfo(
                new TmlDateTime(),
                new Template[] { new TmlMsg("Hello, log!") }
        ).print();
        assertTrue(actual.matches("\\[INFO] \\[.*] \\[LgInfo] \\[MESSAGE Hello, log!]"));
    }

    @Test
    public void print_ctor_11() {
        String actual = new LgInfo(
                new Template[] { new TmlMsg("Hello, log!") }
        ).print();
        assertTrue(actual.matches("\\[INFO] \\[.*] \\[LgInfo] \\[MESSAGE Hello, log!]"));
    }

    @Test
    public void print_ctor_12() {
        String actual = new LgInfo(
                LgInfo.class,
                new TmlDateTime(),
                "Hello, log!"
        ).print();
        assertTrue(actual.matches("\\[INFO] \\[.*] \\[LgInfo] \\[MESSAGE Hello, log!]"));
    }

    @Test
    public void print_ctor_13() {
        String actual = new LgInfo(
                LgInfo.class,
                "Hello, log!"
        ).print();
        assertTrue(actual.matches("\\[INFO] \\[.*] \\[LgInfo] \\[MESSAGE Hello, log!]"));
    }

    @Test
    public void print_ctor_14() {
        String actual = new LgInfo(
                new TmlDateTime(),
                "Hello, log!"
        ).print();
        assertTrue(actual.matches("\\[INFO] \\[.*] \\[LgInfo] \\[MESSAGE Hello, log!]"));
    }

    @Test
    public void print_ctor_15() {
        String actual = new LgInfo("Hello, log!").print();
        assertTrue(actual.matches("\\[INFO] \\[.*] \\[LgInfo] \\[MESSAGE Hello, log!]"));
    }

    @Test
    public void print_ctor_16() {
        String actual = new LgInfo(
                LgInfo.class,
                new TmlDateTime(),
                "Hello, {}!",
                new ArrayList<Object>() {{ add("log"); }}
        ).print();
        assertTrue(actual.matches("\\[INFO] \\[.*] \\[LgInfo] \\[MESSAGE Hello, log!]"));
    }

    @Test
    public void print_ctor_17() {
        String actual = new LgInfo(
                LgInfo.class,
                "Hello, {}!",
                new ArrayList<Object>() {{ add("log"); }}
        ).print();
        assertTrue(actual.matches("\\[INFO] \\[.*] \\[LgInfo] \\[MESSAGE Hello, log!]"));
    }

    @Test
    public void print_ctor_18() {
        String actual = new LgInfo(
                "Hello, {}!",
                new ArrayList<Object>() {{ add("log"); }}
        ).print();
        assertTrue(actual.matches("\\[INFO] \\[.*] \\[LgInfo] \\[MESSAGE Hello, log!]"));
    }

    @Test
    public void print_ctor_19() {
        String actual = new LgInfo(
                LgInfo.class,
                new TmlDateTime(),
                "Hello, {}!",
                new ArrayList<Object>() {{ add("log"); }}
        ).print();
        assertTrue(actual.matches("\\[INFO] \\[.*] \\[LgInfo] \\[MESSAGE Hello, log!]"));
    }

    @Test
    public void print_ctor_20() {
        String actual = new LgInfo(
                LgInfo.class,
                "Hello, {}!",
                "log"
        ).print();
        assertTrue(actual.matches("\\[INFO] \\[.*] \\[LgInfo] \\[MESSAGE Hello, log!]"));
    }

    @Test
    public void print_ctor_21() {
        String actual = new LgInfo(
                new TmlDateTime(),
                "Hello, {}!",
                "log"
        ).print();
        assertTrue(actual.matches("\\[INFO] \\[.*] \\[LgInfo] \\[MESSAGE Hello, log!]"));
    }

    @Test
    public void print_ctor_22() {
        String actual = new LgInfo(
                "Hello, {}!",
                "log"
        ).print();
        assertTrue(actual.matches("\\[INFO] \\[.*] \\[LgInfo] \\[MESSAGE Hello, log!]"));
    }
}
