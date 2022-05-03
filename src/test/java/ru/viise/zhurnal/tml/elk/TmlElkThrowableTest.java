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

package ru.viise.zhurnal.tml.elk;

import ru.viise.zhurnal.Template;
import ru.viise.zhurnal.tml.TmlInfo;
import ru.viise.zhurnal.tml.TmlThrowable;
import org.testng.annotations.Test;
import ru.viise.zhurnal.Template;
import ru.viise.zhurnal.tml.TmlInfo;
import ru.viise.zhurnal.tml.TmlThrowable;

import static org.testng.Assert.*;

public class TmlElkThrowableTest {

    @Test
    public void create() {
        Template tmlElk = new TmlElkStd(new TmlInfo("Hello, {}!", "log"));

        String actual = new TmlElkThrowable(
                tmlElk,
                new TmlThrowable(new Throwable())
        ).create();

        assertTrue(actual.contains("\"level\":\"INFO\""));
        assertTrue(actual.contains("\"logger_name\":\"ru.viise.zhurnal.tml.TmlInfo\""));
        assertTrue(actual.contains("\"message\":\"Hello, log!\""));
        assertTrue(actual.contains("\"timestamp\""));
        assertTrue(actual.contains("\"stack_trace\""));
        assertTrue(actual.contains("\"throwable_message\""));

        assertFalse(actual.contains("\"thread_name\":null"));
    }

    @Test
    public void create_nullTmlElk() {
        String actual = new TmlElkThrowable(
                null,
                new TmlThrowable(new Throwable())
        ).create();

        assertEquals(actual, "{}");
    }

    @Test
    public void create_nullTml() {
        Template tmlElk = new TmlElkStd(new TmlInfo("Hello, {}!", "log"));

        String actual = new TmlElkThrowable(
                tmlElk,
                null
        ).create();

        assertTrue(actual.contains("\"level\":\"INFO\""));
        assertTrue(actual.contains("\"logger_name\":\"ru.viise.zhurnal.tml.TmlInfo\""));
        assertTrue(actual.contains("\"message\":\"Hello, log!\""));
        assertTrue(actual.contains("\"timestamp\""));
        assertTrue(actual.contains("\"stack_trace\":null"));
        assertTrue(actual.contains("\"throwable_message\":null"));

        assertFalse(actual.contains("\"thread_name\":null"));
    }

    @Test
    public void create_allNull() {
        String actual = new TmlElkThrowable(
                null,
                null
        ).create();

        assertEquals(actual, "{}");
    }
}
