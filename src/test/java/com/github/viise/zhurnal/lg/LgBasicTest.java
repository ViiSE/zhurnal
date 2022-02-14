package com.github.viise.zhurnal.lg;

import com.github.viise.zhurnal.Template;
import com.github.viise.zhurnal.tml.TmlDateTime;
import com.github.viise.zhurnal.tml.TmlInfo;
import com.github.viise.zhurnal.tml.TmlMsg;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static org.testng.Assert.assertTrue;

public class LgBasicTest {

    @Test
    public void print_ctor_1() {
        String actual = new LgBasic(
                LgBasic.class,
                false,
                new TmlInfo(),
                new TmlDateTime(),
                new ArrayList<Template>() {{ add(new TmlMsg("Hello, log!")); }}
        ).print();
        assertTrue(actual.matches("\\[INFO] \\[.*] \\[LgBasic] \\[MESSAGE Hello, log!]"));
    }

    @Test
    public void print_ctor_1_fullName() {
        String actual = new LgBasic(
                LgBasic.class,
                true,
                new TmlInfo(),
                new TmlDateTime(),
                new ArrayList<Template>() {{ add(new TmlMsg("Hello, log!")); }}
        ).print();
        assertTrue(actual.matches("\\[INFO] \\[.*] \\[com\\.github\\.viise\\.zhurnal\\.lg\\.LgBasic] \\[MESSAGE Hello, log!]"));
    }

    @Test
    public void print_ctor_2() {
        String actual = new LgBasic(
                LgBasic.class,
                false,
                new TmlInfo(),
                new TmlDateTime(),
                new TmlMsg("Hello, log!")
        ).print();
        assertTrue(actual.matches("\\[INFO] \\[.*] \\[LgBasic] \\[MESSAGE Hello, log!]"));
    }

    @Test
    public void print_ctor_3() {
        String actual = new LgBasic(
                LgBasic.class,
                false,
                new TmlInfo(),
                new TmlDateTime(),
                "Hello, log!"
        ).print();
        assertTrue(actual.matches("\\[INFO] \\[.*] \\[LgBasic] \\[MESSAGE Hello, log!]"));
    }

    @Test
    public void print_ctor_4() {
        String actual = new LgBasic(
                LgBasic.class,
                new TmlInfo(),
                new TmlDateTime(),
                new ArrayList<Template>() {{ add(new TmlMsg("Hello, log!")); }}
        ).print();
        assertTrue(actual.matches("\\[INFO] \\[.*] \\[LgBasic] \\[MESSAGE Hello, log!]"));
    }

    @Test
    public void print_ctor_5() {
        String actual = new LgBasic(
                LgBasic.class,
                new TmlInfo(),
                new ArrayList<Template>() {{ add(new TmlMsg("Hello, log!")); }}
        ).print();
        assertTrue(actual.matches("\\[INFO] \\[.*] \\[LgBasic] \\[MESSAGE Hello, log!]"));
    }

    @Test
    public void print_ctor_6() {
        String actual = new LgBasic(
                new TmlInfo(),
                new ArrayList<Template>() {{ add(new TmlMsg("Hello, log!")); }}
        ).print();
        assertTrue(actual.matches("\\[INFO] \\[.*] \\[LgBasic] \\[MESSAGE Hello, log!]"));
    }

    @Test
    public void print_ctor_7() {
        String actual = new LgBasic(
                new TmlInfo(),
                new TmlDateTime(),
                new ArrayList<Template>() {{ add(new TmlMsg("Hello, log!")); }}
        ).print();
        assertTrue(actual.matches("\\[INFO] \\[.*] \\[LgBasic] \\[MESSAGE Hello, log!]"));
    }

    @Test
    public void print_ctor_8() {
        String actual = new LgBasic(
                LgBasic.class,
                new TmlInfo(),
                new TmlDateTime(),
                new Template[] { new TmlMsg("Hello, log!") }
        ).print();
        assertTrue(actual.matches("\\[INFO] \\[.*] \\[LgBasic] \\[MESSAGE Hello, log!]"));
    }

    @Test
    public void print_ctor_9() {
        String actual = new LgBasic(
                LgBasic.class,
                new TmlInfo(),
                new Template[] { new TmlMsg("Hello, log!") }
        ).print();
        assertTrue(actual.matches("\\[INFO] \\[.*] \\[LgBasic] \\[MESSAGE Hello, log!]"));
    }

    @Test
    public void print_ctor_10() {
        String actual = new LgBasic(
                new TmlInfo(),
                new TmlDateTime(),
                new Template[] { new TmlMsg("Hello, log!") }
        ).print();
        assertTrue(actual.matches("\\[INFO] \\[.*] \\[LgBasic] \\[MESSAGE Hello, log!]"));
    }

    @Test
    public void print_ctor_11() {
        String actual = new LgBasic(
                new TmlInfo(),
                new Template[] { new TmlMsg("Hello, log!") }
        ).print();
        assertTrue(actual.matches("\\[INFO] \\[.*] \\[LgBasic] \\[MESSAGE Hello, log!]"));
    }

    @Test
    public void print_ctor_12() {
        String actual = new LgBasic(
                LgBasic.class,
                new TmlInfo(),
                new TmlDateTime(),
                "Hello, log!"
        ).print();
        assertTrue(actual.matches("\\[INFO] \\[.*] \\[LgBasic] \\[MESSAGE Hello, log!]"));
    }

    @Test
    public void print_ctor_13() {
        String actual = new LgBasic(
                LgBasic.class,
                new TmlInfo(),
                "Hello, log!"
        ).print();
        assertTrue(actual.matches("\\[INFO] \\[.*] \\[LgBasic] \\[MESSAGE Hello, log!]"));
    }

    @Test
    public void print_ctor_14() {
        String actual = new LgBasic(
                new TmlInfo(),
                new TmlDateTime(),
                "Hello, log!"
        ).print();
        assertTrue(actual.matches("\\[INFO] \\[.*] \\[LgBasic] \\[MESSAGE Hello, log!]"));
    }

    @Test
    public void print_ctor_15() {
        String actual = new LgBasic(
                new TmlInfo(),
                "Hello, log!"
        ).print();
        assertTrue(actual.matches("\\[INFO] \\[.*] \\[LgBasic] \\[MESSAGE Hello, log!]"));
    }

    @Test
    public void print_ctor_16() {
        String actual = new LgBasic(
                LgBasic.class,
                new TmlInfo(),
                new TmlDateTime(),
                "Hello, {}!",
                new ArrayList<Object>() {{ add("log"); }}
        ).print();
        assertTrue(actual.matches("\\[INFO] \\[.*] \\[LgBasic] \\[MESSAGE Hello, log!]"));
    }

    @Test
    public void print_ctor_17() {
        String actual = new LgBasic(
                LgBasic.class,
                new TmlInfo(),
                "Hello, {}!",
                new ArrayList<Object>() {{ add("log"); }}
        ).print();
        assertTrue(actual.matches("\\[INFO] \\[.*] \\[LgBasic] \\[MESSAGE Hello, log!]"));
    }

    @Test
    public void print_ctor_18() {
        String actual = new LgBasic(
                new TmlInfo(),
                "Hello, {}!",
                new ArrayList<Object>() {{ add("log"); }}
        ).print();
        assertTrue(actual.matches("\\[INFO] \\[.*] \\[LgBasic] \\[MESSAGE Hello, log!]"));
    }

    @Test
    public void print_ctor_19() {
        String actual = new LgBasic(
                LgBasic.class,
                new TmlInfo(),
                new TmlDateTime(),
                "Hello, {}!",
                new ArrayList<Object>() {{ add("log"); }}
        ).print();
        assertTrue(actual.matches("\\[INFO] \\[.*] \\[LgBasic] \\[MESSAGE Hello, log!]"));
    }

    @Test
    public void print_ctor_20() {
        String actual = new LgBasic(
                LgBasic.class,
                new TmlInfo(),
                "Hello, {}!",
                "log"
        ).print();
        assertTrue(actual.matches("\\[INFO] \\[.*] \\[LgBasic] \\[MESSAGE Hello, log!]"));
    }

    @Test
    public void print_ctor_21() {
        String actual = new LgBasic(
                new TmlInfo(),
                new TmlDateTime(),
                "Hello, {}!",
                "log"
        ).print();
        assertTrue(actual.matches("\\[INFO] \\[.*] \\[LgBasic] \\[MESSAGE Hello, log!]"));
    }

    @Test
    public void print_ctor_22() {
        String actual = new LgBasic(
                new TmlInfo(),
                "Hello, {}!",
                "log"
        ).print();
        assertTrue(actual.matches("\\[INFO] \\[.*] \\[LgBasic] \\[MESSAGE Hello, log!]"));
    }
}
