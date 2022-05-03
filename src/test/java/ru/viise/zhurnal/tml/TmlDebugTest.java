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

import ru.viise.zhurnal.HttpMethod;
import ru.viise.zhurnal.HttpStatus;
import ru.viise.zhurnal.Level;
import ru.viise.zhurnal.Template;
import org.testng.annotations.Test;
import ru.viise.zhurnal.Level;
import ru.viise.zhurnal.Template;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class TmlDebugTest {

    @Test
    public void create() {
        String actual = new TmlDebug(
                TmlDebugTest.class,
                new Template[] {
                        new TmlHttp(HttpMethod.GET, "/log/1", HttpStatus.OK),
                        new TmlDuration(300L, TimeUnit.MILLISECONDS)
                }
        ).create();

        assertTrue(
                actual.matches(
                        "\\[LEVEL DEBUG] \\[TIMESTAMP .*] \\[CLASS ru\\.viise\\.zhurnal\\.tml\\.TmlDebugTest] \\[HTTP <METHOD:GET> <ENDPOINT:/log/1> <STATUS:200 OK>] \\[DURATION <VALUE:300> <UNIT:MILLISECONDS>]"
                )
        );
    }

    @Test
    public void create_ctor_1() {
        String actual = new TmlDebug(
                TmlDebug.class,
                false,
                new TmlTimestamp(),
                new ArrayList<Template>() {{ add(new TmlMsg("Hello, log!")); }}
        ).create();
        assertTrue(
                actual.matches(
                        "\\[LEVEL DEBUG] \\[TIMESTAMP .*] \\[CLASS TmlDebug] \\[MESSAGE Hello, log!]"
                )
        );
    }

    @Test
    public void create_ctor_1_fullName() {
        String actual = new TmlDebug(
                TmlDebug.class,
                true,
                new TmlTimestamp(),
                new ArrayList<Template>() {{ add(new TmlMsg("Hello, log!")); }}
        ).create();
        assertTrue(
                actual.matches(
                        "\\[LEVEL DEBUG] \\[TIMESTAMP .*] \\[CLASS ru\\.viise\\.zhurnal\\.tml\\.TmlDebug] \\[MESSAGE Hello, log!]"
                )
        );
    }

    @Test
    public void create_ctor_2() {
        String actual = new TmlDebug(
                TmlDebug.class,
                false,
                new TmlTimestamp(),
                new TmlMsg("Hello, log!")
        ).create();
        assertTrue(
                actual.matches(
                        "\\[LEVEL DEBUG] \\[TIMESTAMP .*] \\[CLASS TmlDebug] \\[MESSAGE Hello, log!]"
                )
        );
    }

    @Test
    public void create_ctor_3() {
        String actual = new TmlDebug(
                TmlDebug.class,
                false,
                new TmlTimestamp(),
                "Hello, log!"
        ).create();
        assertTrue(
                actual.matches(
                        "\\[LEVEL DEBUG] \\[TIMESTAMP .*] \\[CLASS TmlDebug] \\[MESSAGE Hello, log!]"
                )
        );
    }

    @Test
    public void create_ctor_4() {
        String actual = new TmlDebug(
                TmlDebugTest.class,
                false,
                "Hello, log!"
        ).create();
        assertTrue(
                actual.matches(
                        "\\[LEVEL DEBUG] \\[TIMESTAMP .*] \\[CLASS TmlDebugTest] \\[MESSAGE Hello, log!]"
                )
        );
    }

    @Test
    public void create_ctor_5() {
        String actual = new TmlDebug(
                false,
                "Hello, log!"
        ).create();
        assertTrue(
                actual.matches(
                        "\\[LEVEL DEBUG] \\[TIMESTAMP .*] \\[CLASS TmlDebug] \\[MESSAGE Hello, log!]"
                )
        );
    }

    @Test
    public void create_ctor_6() {
        String actual = new TmlDebug(
                TmlDebugTest.class,
                false,
                "Hello, {}!",
                new ArrayList<Object>() {{ add("log"); }}
        ).create();
        assertTrue(
                actual.matches(
                        "\\[LEVEL DEBUG] \\[TIMESTAMP .*] \\[CLASS TmlDebugTest] \\[MESSAGE Hello, log!]"
                )
        );
    }

    @Test
    public void create_ctor_7() {
        String actual = new TmlDebug(
                TmlDebugTest.class,
                false,
                "Hello, {}!",
                "log"
        ).create();
        assertTrue(
                actual.matches(
                        "\\[LEVEL DEBUG] \\[TIMESTAMP .*] \\[CLASS TmlDebugTest] \\[MESSAGE Hello, log!]"
                )
        );
    }

    @Test
    public void create_ctor_8() {
        String actual = new TmlDebug(
                false,
                "Hello, {}!",
                new ArrayList<Object>() {{ add("log"); }}
        ).create();
        assertTrue(
                actual.matches(
                        "\\[LEVEL DEBUG] \\[TIMESTAMP .*] \\[CLASS TmlDebug] \\[MESSAGE Hello, log!]"
                )
        );
    }

    @Test
    public void create_ctor_9() {
        String actual = new TmlDebug(
                false,
                "Hello, {}!",
                "log"
        ).create();
        assertTrue(
                actual.matches(
                        "\\[LEVEL DEBUG] \\[TIMESTAMP .*] \\[CLASS TmlDebug] \\[MESSAGE Hello, log!]"
                )
        );
    }
    
    @Test
    public void create_ctor_10() {
        String actual = new TmlDebug(
                TmlDebug.class,
                new TmlTimestamp(),
                new ArrayList<Template>() {{ add(new TmlMsg("Hello, log!")); }}
        ).create();
        assertTrue(
                actual.matches(
                        "\\[LEVEL DEBUG] \\[TIMESTAMP .*] \\[CLASS ru\\.viise\\.zhurnal\\.tml\\.TmlDebug] \\[MESSAGE Hello, log!]"
                )
        );
    }

    @Test
    public void create_ctor_11() {
        String actual = new TmlDebug(
                TmlDebug.class,
                new ArrayList<Template>() {{ add(new TmlMsg("Hello, log!")); }}
        ).create();
        assertTrue(
                actual.matches(
                        "\\[LEVEL DEBUG] \\[TIMESTAMP .*] \\[CLASS ru\\.viise\\.zhurnal\\.tml\\.TmlDebug] \\[MESSAGE Hello, log!]"
                )
        );
    }

    @Test
    public void create_ctor_12() {
        String actual = new TmlDebug(
                new ArrayList<Template>() {{ add(new TmlMsg("Hello, log!")); }}
        ).create();
        assertTrue(
                actual.matches(
                        "\\[LEVEL DEBUG] \\[TIMESTAMP .*] \\[CLASS ru\\.viise\\.zhurnal\\.tml\\.TmlDebug] \\[MESSAGE Hello, log!]"
                )
        );
    }

    @Test
    public void create_ctor_13() {
        String actual = new TmlDebug(
                new TmlTimestamp(),
                new ArrayList<Template>() {{ add(new TmlMsg("Hello, log!")); }}
        ).create();
        assertTrue(
                actual.matches(
                        "\\[LEVEL DEBUG] \\[TIMESTAMP .*] \\[CLASS ru\\.viise\\.zhurnal\\.tml\\.TmlDebug] \\[MESSAGE Hello, log!]"
                )
        );
    }

    @Test
    public void create_ctor_14() {
        String actual = new TmlDebug(
                TmlDebug.class,
                new TmlTimestamp(),
                new Template[] { new TmlMsg("Hello, log!") }
        ).create();
        assertTrue(
                actual.matches(
                        "\\[LEVEL DEBUG] \\[TIMESTAMP .*] \\[CLASS ru\\.viise\\.zhurnal\\.tml\\.TmlDebug] \\[MESSAGE Hello, log!]"
                )
        );
    }

    @Test
    public void create_ctor_15() {
        String actual = new TmlDebug(
                TmlDebug.class,
                new Template[] { new TmlMsg("Hello, log!") }
        ).create();
        assertTrue(
                actual.matches(
                        "\\[LEVEL DEBUG] \\[TIMESTAMP .*] \\[CLASS ru\\.viise\\.zhurnal\\.tml\\.TmlDebug] \\[MESSAGE Hello, log!]"
                )
        );
    }

    @Test
    public void create_ctor_16() {
        String actual = new TmlDebug(
                new TmlTimestamp(),
                new Template[] { new TmlMsg("Hello, log!") }
        ).create();
        assertTrue(
                actual.matches(
                        "\\[LEVEL DEBUG] \\[TIMESTAMP .*] \\[CLASS ru\\.viise\\.zhurnal\\.tml\\.TmlDebug] \\[MESSAGE Hello, log!]"
                )
        );
    }

    @Test
    public void create_ctor_17() {
        String actual = new TmlDebug(
                new Template[] { new TmlMsg("Hello, log!") }
        ).create();
        assertTrue(
                actual.matches(
                        "\\[LEVEL DEBUG] \\[TIMESTAMP .*] \\[CLASS ru\\.viise\\.zhurnal\\.tml\\.TmlDebug] \\[MESSAGE Hello, log!]"
                )
        );
    }

    @Test
    public void create_ctor_18() {
        String actual = new TmlDebug(
                TmlDebug.class,
                new TmlTimestamp(),
                "Hello, log!"
        ).create();
        assertTrue(
                actual.matches(
                        "\\[LEVEL DEBUG] \\[TIMESTAMP .*] \\[CLASS ru\\.viise\\.zhurnal\\.tml\\.TmlDebug] \\[MESSAGE Hello, log!]"
                )
        );
    }

    @Test
    public void create_ctor_19() {
        String actual = new TmlDebug(
                TmlDebug.class,
                "Hello, log!"
        ).create();
        assertTrue(
                actual.matches(
                        "\\[LEVEL DEBUG] \\[TIMESTAMP .*] \\[CLASS ru\\.viise\\.zhurnal\\.tml\\.TmlDebug] \\[MESSAGE Hello, log!]"
                )
        );
    }

    @Test
    public void create_ctor_20() {
        String actual = new TmlDebug(
                new TmlTimestamp(),
                "Hello, log!"
        ).create();
        assertTrue(
                actual.matches(
                        "\\[LEVEL DEBUG] \\[TIMESTAMP .*] \\[CLASS ru\\.viise\\.zhurnal\\.tml\\.TmlDebug] \\[MESSAGE Hello, log!]"
                )
        );
    }

    @Test
    public void create_ctor_21() {
        String actual = new TmlDebug("Hello, log!").create();
        assertTrue(
                actual.matches(
                        "\\[LEVEL DEBUG] \\[TIMESTAMP .*] \\[CLASS ru\\.viise\\.zhurnal\\.tml\\.TmlDebug] \\[MESSAGE Hello, log!]"
                )
        );
    }

    @Test
    public void create_ctor_22() {
        String actual = new TmlDebug(
                TmlDebug.class,
                new TmlTimestamp(),
                "Hello, {}!",
                new ArrayList<Object>() {{ add("log"); }}
        ).create();
        assertTrue(
                actual.matches(
                        "\\[LEVEL DEBUG] \\[TIMESTAMP .*] \\[CLASS ru\\.viise\\.zhurnal\\.tml\\.TmlDebug] \\[MESSAGE Hello, log!]"
                )
        );
    }

    @Test
    public void create_ctor_23() {
        String actual = new TmlDebug(
                TmlDebug.class,
                "Hello, {}!",
                new ArrayList<Object>() {{ add("log"); }}
        ).create();
        assertTrue(
                actual.matches(
                        "\\[LEVEL DEBUG] \\[TIMESTAMP .*] \\[CLASS ru\\.viise\\.zhurnal\\.tml\\.TmlDebug] \\[MESSAGE Hello, log!]"
                )
        );
    }

    @Test
    public void create_ctor_24() {
        String actual = new TmlDebug(
                "Hello, {}!",
                new ArrayList<Object>() {{ add("log"); }}
        ).create();
        assertTrue(
                actual.matches(
                        "\\[LEVEL DEBUG] \\[TIMESTAMP .*] \\[CLASS ru\\.viise\\.zhurnal\\.tml\\.TmlDebug] \\[MESSAGE Hello, log!]"
                )
        );
    }

    @Test
    public void create_ctor_25() {
        String actual = new TmlDebug(
                TmlDebug.class,
                new TmlTimestamp(),
                "Hello, {}!",
                "log"
        ).create();
        assertTrue(
                actual.matches(
                        "\\[LEVEL DEBUG] \\[TIMESTAMP .*] \\[CLASS ru\\.viise\\.zhurnal\\.tml\\.TmlDebug] \\[MESSAGE Hello, log!]"
                )
        );
    }

    @Test
    public void create_ctor_26() {
        String actual = new TmlDebug(
                TmlDebug.class,
                "Hello, {}!",
                "log"
        ).create();
        assertTrue(
                actual.matches(
                        "\\[LEVEL DEBUG] \\[TIMESTAMP .*] \\[CLASS ru\\.viise\\.zhurnal\\.tml\\.TmlDebug] \\[MESSAGE Hello, log!]"
                )
        );
    }

    @Test
    public void create_ctor_27() {
        String actual = new TmlDebug(
                new TmlTimestamp(),
                "Hello, {}!",
                "log"
        ).create();
        assertTrue(
                actual.matches(
                        "\\[LEVEL DEBUG] \\[TIMESTAMP .*] \\[CLASS ru\\.viise\\.zhurnal\\.tml\\.TmlDebug] \\[MESSAGE Hello, log!]"
                )
        );
    }

    @Test
    public void create_ctor_28() {
        String actual = new TmlDebug(
                "Hello, {}!",
                "log"
        ).create();
        assertTrue(
                actual.matches(
                        "\\[LEVEL DEBUG] \\[TIMESTAMP .*] \\[CLASS ru\\.viise\\.zhurnal\\.tml\\.TmlDebug] \\[MESSAGE Hello, log!]"
                )
        );
    }

    @Test
    public void level() {
        Level lvl = new TmlDebug("Debug").level();
        assertEquals(lvl, Level.DEBUG);
    }
}
