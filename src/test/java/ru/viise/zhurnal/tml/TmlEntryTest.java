/*
 * Copyright 2022 ViiSE
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ru.viise.zhurnal.tml;

import ru.viise.zhurnal.Level;
import ru.viise.zhurnal.Template;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class TmlEntryTest {

    @Test
    public void create_ctor_1() {
        String actual = new TmlEntry(
                TmlEntry.class,
                false,
                Level.INFO,
                new TmlTimestamp(),
                new ArrayList<Template>() {{ add(new TmlMsg("Hello, log!")); }}
        ).create();
        assertTrue(
                actual.matches(
                        "\\[LEVEL INFO] \\[TIMESTAMP .*] \\[CLASS TmlEntry] \\[MESSAGE Hello, log!]"
                )
        );
    }

    @Test
    public void create_ctor_1_fullName() {
        String actual = new TmlEntry(
                TmlEntry.class,
                true,
                Level.INFO,
                new TmlTimestamp(),
                new ArrayList<Template>() {{ add(new TmlMsg("Hello, log!")); }}
        ).create();
        assertTrue(
                actual.matches(
                        "\\[LEVEL INFO] \\[TIMESTAMP .*] \\[CLASS ru\\.viise\\.zhurnal\\.tml\\.TmlEntry] \\[MESSAGE Hello, log!]"
                )
        );
    }

    @Test
    public void create_ctor_2() {
        String actual = new TmlEntry(
                TmlEntry.class,
                false,
                Level.INFO,
                new TmlTimestamp(),
                new TmlMsg("Hello, log!")
        ).create();
        assertTrue(
                actual.matches(
                        "\\[LEVEL INFO] \\[TIMESTAMP .*] \\[CLASS TmlEntry] \\[MESSAGE Hello, log!]")
        );
    }

    @Test
    public void create_ctor_3() {
        String actual = new TmlEntry(
                TmlEntry.class,
                false,
                Level.INFO,
                new TmlTimestamp(),
                "Hello, log!"
        ).create();
        assertTrue(
                actual.matches(
                        "\\[LEVEL INFO] \\[TIMESTAMP .*] \\[CLASS TmlEntry] \\[MESSAGE Hello, log!]"
                )
        );
    }

    @Test
    public void create_ctor_4() {
        String actual = new TmlEntry(
                TmlEntry.class,
                Level.INFO,
                new TmlTimestamp(),
                new ArrayList<Template>() {{ add(new TmlMsg("Hello, log!")); }}
        ).create();
        assertTrue(
                actual.matches(
                        "\\[LEVEL INFO] \\[TIMESTAMP .*] \\[CLASS ru\\.viise\\.zhurnal\\.tml\\.TmlEntry] \\[MESSAGE Hello, log!]"
                )
        );
    }

    @Test
    public void create_ctor_5() {
        String actual = new TmlEntry(
                TmlEntry.class,
                Level.INFO,
                new ArrayList<Template>() {{ add(new TmlMsg("Hello, log!")); }}
        ).create();
        assertTrue(
                actual.matches(
                        "\\[LEVEL INFO] \\[TIMESTAMP .*] \\[CLASS ru\\.viise\\.zhurnal\\.tml\\.TmlEntry] \\[MESSAGE Hello, log!]"
                )
        );
    }

    @Test
    public void create_ctor_6() {
        String actual = new TmlEntry(
                Level.INFO,
                new ArrayList<Template>() {{ add(new TmlMsg("Hello, log!")); }}
        ).create();
        assertTrue(
                actual.matches(
                        "\\[LEVEL INFO] \\[TIMESTAMP .*] \\[CLASS ru\\.viise\\.zhurnal\\.tml\\.TmlEntry] \\[MESSAGE Hello, log!]"
                )
        );
    }

    @Test
    public void create_ctor_7() {
        String actual = new TmlEntry(
                Level.INFO,
                new TmlTimestamp(),
                new ArrayList<Template>() {{ add(new TmlMsg("Hello, log!")); }}
        ).create();
        assertTrue(
                actual.matches(
                        "\\[LEVEL INFO] \\[TIMESTAMP .*] \\[CLASS ru\\.viise\\.zhurnal\\.tml\\.TmlEntry] \\[MESSAGE Hello, log!]"
                )
        );
    }

    @Test
    public void create_ctor_8() {
        String actual = new TmlEntry(
                TmlEntry.class,
                Level.INFO,
                new TmlTimestamp(),
                new Template[] { new TmlMsg("Hello, log!") }
        ).create();
        assertTrue(
                actual.matches(
                        "\\[LEVEL INFO] \\[TIMESTAMP .*] \\[CLASS ru\\.viise\\.zhurnal\\.tml\\.TmlEntry] \\[MESSAGE Hello, log!]"
                )
        );
    }

    @Test
    public void create_ctor_9() {
        String actual = new TmlEntry(
                TmlEntry.class,
                Level.INFO,
                new Template[] { new TmlMsg("Hello, log!") }
        ).create();
        assertTrue(
                actual.matches(
                        "\\[LEVEL INFO] \\[TIMESTAMP .*] \\[CLASS ru\\.viise\\.zhurnal\\.tml\\.TmlEntry] \\[MESSAGE Hello, log!]"
                )
        );
    }

    @Test
    public void create_ctor_10() {
        String actual = new TmlEntry(
                Level.INFO,
                new TmlTimestamp(),
                new Template[] { new TmlMsg("Hello, log!") }
        ).create();
        assertTrue(
                actual.matches(
                        "\\[LEVEL INFO] \\[TIMESTAMP .*] \\[CLASS ru\\.viise\\.zhurnal\\.tml\\.TmlEntry] \\[MESSAGE Hello, log!]"
                )
        );
    }

    @Test
    public void create_ctor_11() {
        String actual = new TmlEntry(
                Level.INFO,
                new Template[] { new TmlMsg("Hello, log!") }
        ).create();
        assertTrue(
                actual.matches(
                        "\\[LEVEL INFO] \\[TIMESTAMP .*] \\[CLASS ru\\.viise\\.zhurnal\\.tml\\.TmlEntry] \\[MESSAGE Hello, log!]"
                )
        );
    }

    @Test
    public void create_ctor_12() {
        String actual = new TmlEntry(
                TmlEntry.class,
                Level.INFO,
                new TmlTimestamp(),
                "Hello, log!"
        ).create();
        assertTrue(
                actual.matches(
                        "\\[LEVEL INFO] \\[TIMESTAMP .*] \\[CLASS ru\\.viise\\.zhurnal\\.tml\\.TmlEntry] \\[MESSAGE Hello, log!]"
                )
        );
    }

    @Test
    public void create_ctor_13() {
        String actual = new TmlEntry(
                TmlEntry.class,
                Level.INFO,
                "Hello, log!"
        ).create();
        assertTrue(
                actual.matches(
                        "\\[LEVEL INFO] \\[TIMESTAMP .*] \\[CLASS ru\\.viise\\.zhurnal\\.tml\\.TmlEntry] \\[MESSAGE Hello, log!]"
                )
        );
    }

    @Test
    public void create_ctor_14() {
        String actual = new TmlEntry(
                Level.INFO,
                new TmlTimestamp(),
                "Hello, log!"
        ).create();
        assertTrue(
                actual.matches(
                        "\\[LEVEL INFO] \\[TIMESTAMP .*] \\[CLASS ru\\.viise\\.zhurnal\\.tml\\.TmlEntry] \\[MESSAGE Hello, log!]"
                )
        );
    }

    @Test
    public void create_ctor_15() {
        String actual = new TmlEntry(
                Level.INFO,
                "Hello, log!"
        ).create();
        assertTrue(
                actual.matches(
                        "\\[LEVEL INFO] \\[TIMESTAMP .*] \\[CLASS ru\\.viise\\.zhurnal\\.tml\\.TmlEntry] \\[MESSAGE Hello, log!]"
                )
        );
    }

    @Test
    public void create_ctor_16() {
        String actual = new TmlEntry(
                TmlEntry.class,
                Level.INFO,
                new TmlTimestamp(),
                "Hello, {}!",
                new ArrayList<Object>() {{ add("log"); }}
        ).create();
        assertTrue(
                actual.matches(
                        "\\[LEVEL INFO] \\[TIMESTAMP .*] \\[CLASS ru\\.viise\\.zhurnal\\.tml\\.TmlEntry] \\[MESSAGE Hello, log!]"
                )
        );
    }

    @Test
    public void create_ctor_17() {
        String actual = new TmlEntry(
                TmlEntry.class,
                Level.INFO,
                "Hello, {}!",
                new ArrayList<Object>() {{ add("log"); }}
        ).create();
        assertTrue(
                actual.matches(
                        "\\[LEVEL INFO] \\[TIMESTAMP .*] \\[CLASS ru\\.viise\\.zhurnal\\.tml\\.TmlEntry] \\[MESSAGE Hello, log!]"
                )
        );
    }

    @Test
    public void create_ctor_18() {
        String actual = new TmlEntry(
                Level.INFO,
                "Hello, {}!",
                new ArrayList<Object>() {{ add("log"); }}
        ).create();
        assertTrue(
                actual.matches("\\[LEVEL INFO] \\[TIMESTAMP .*] \\[CLASS ru\\.viise\\.zhurnal\\.tml\\.TmlEntry] \\[MESSAGE Hello, log!]"
                )
        );
    }

    @Test
    public void create_ctor_19() {
        String actual = new TmlEntry(
                TmlEntry.class,
                Level.INFO,
                new TmlTimestamp(),
                "Hello, {}!",
                "log"
        ).create();
        assertTrue(
                actual.matches(
                        "\\[LEVEL INFO] \\[TIMESTAMP .*] \\[CLASS ru\\.viise\\.zhurnal\\.tml\\.TmlEntry] \\[MESSAGE Hello, log!]"
                )
        );
    }

    @Test
    public void create_ctor_20() {
        String actual = new TmlEntry(
                TmlEntry.class,
                Level.INFO,
                "Hello, {}!",
                "log"
        ).create();
        assertTrue(
                actual.matches(
                        "\\[LEVEL INFO] \\[TIMESTAMP .*] \\[CLASS ru\\.viise\\.zhurnal\\.tml\\.TmlEntry] \\[MESSAGE Hello, log!]"
                )
        );
    }

    @Test
    public void create_ctor_21() {
        String actual = new TmlEntry(
                Level.INFO,
                new TmlTimestamp(),
                "Hello, {}!",
                "log"
        ).create();
        assertTrue(
                actual.matches(
                        "\\[LEVEL INFO] \\[TIMESTAMP .*] \\[CLASS ru\\.viise\\.zhurnal\\.tml\\.TmlEntry] \\[MESSAGE Hello, log!]"
                )
        );
    }

    @Test
    public void create_ctor_22() {
        String actual = new TmlEntry(
                Level.INFO,
                "Hello, {}!",
                "log"
        ).create();
        assertTrue(
                actual.matches(
                        "\\[LEVEL INFO] \\[TIMESTAMP .*] \\[CLASS ru\\.viise\\.zhurnal\\.tml\\.TmlEntry] \\[MESSAGE Hello, log!]"
                )
        );
    }

    @Test
    public void level() {
        Level lvl = new TmlEntry(Level.INFO, "Hello, {}!", "log").level();
        assertEquals(lvl, Level.INFO);
    }
}
