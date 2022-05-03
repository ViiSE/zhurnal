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

package org.viise.zhurnal.tml.elk;

import org.viise.zhurnal.Template;
import org.viise.zhurnal.tml.TmlDuration;
import org.viise.zhurnal.tml.TmlInfo;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class TmlElkDurationTest {

    @Test
    public void create() {
        Template tmlElk = new TmlElkStd(new TmlInfo("Hello, {}!", "log"));

        String actual = new TmlElkDuration(
                tmlElk,
                new TmlDuration(200L)
        ).create();

        assertTrue(actual.contains("\"level\":\"INFO\""));
        assertTrue(actual.contains("\"logger_name\":\"org.viise.zhurnal.tml.TmlInfo\""));
        assertTrue(actual.contains("\"message\":\"Hello, log!\""));
        assertTrue(actual.contains("\"timestamp\""));
        assertTrue(actual.contains("\"duration_unit\":\"MILLISECONDS\""));
        assertTrue(actual.contains("\"duration_value\":200"));

        assertFalse(actual.contains("\"thread_name\":null"));
        assertFalse(actual.contains("\"stack_trace\""));
    }

    @Test
    public void create_nullTmlElk() {
        String actual = new TmlElkDuration(
                null,
                new TmlDuration(200L)
        ).create();

        assertEquals(actual, "{}");
    }

    @Test
    public void create_nullTml() {
        Template tmlElk = new TmlElkStd(new TmlInfo("Hello, {}!", "log"));

        String actual = new TmlElkDuration(
                tmlElk,
                null
        ).create();

        assertTrue(actual.contains("\"level\":\"INFO\""));
        assertTrue(actual.contains("\"logger_name\":\"org.viise.zhurnal.tml.TmlInfo\""));
        assertTrue(actual.contains("\"message\":\"Hello, log!\""));
        assertTrue(actual.contains("\"timestamp\""));
        assertTrue(actual.contains("\"duration_unit\":null"));
        assertTrue(actual.contains("\"duration_value\":null"));

        assertFalse(actual.contains("\"thread_name\":null"));
        assertFalse(actual.contains("\"stack_trace\""));
    }

    @Test
    public void create_allNull() {
        String actual = new TmlElkDuration(
                null,
                null
        ).create();

        assertEquals(actual, "{}");
    }
}
