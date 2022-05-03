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
import org.viise.zhurnal.tml.TmlInfo;
import org.viise.zhurnal.tml.TmlSql;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class TmlElkSqlTest {

    @Test
    public void create() {
        Template tmlElk = new TmlElkStd(new TmlInfo("Hello, {}!", "log"));

        String actual = new TmlElkSql(
                tmlElk,
                new TmlSql(200, "OK", "Okay")
        ).create();

        assertTrue(actual.contains("\"level\":\"INFO\""));
        assertTrue(actual.contains("\"logger_name\":\"org.viise.zhurnal.tml.TmlInfo\""));
        assertTrue(actual.contains("\"message\":\"Hello, log!\""));
        assertTrue(actual.contains("\"timestamp\""));
        assertTrue(actual.contains("\"sql_error_code\":200"));
        assertTrue(actual.contains("\"sql_state\":\"OK\""));
        assertTrue(actual.contains("\"sql_message\":\"Okay\""));

        assertFalse(actual.contains("\"thread_name\":null"));
        assertFalse(actual.contains("\"stack_trace\""));
    }

    @Test
    public void create_nullTmlElk() {
        String actual = new TmlElkSql(
                null,
                new TmlSql(200, "OK", "Okay")
        ).create();

        assertEquals(actual, "{}");
    }

    @Test
    public void create_nullTml() {
        Template tmlElk = new TmlElkStd(new TmlInfo("Hello, {}!", "log"));

        String actual = new TmlElkSql(
                tmlElk,
                null
        ).create();

        assertTrue(actual.contains("\"level\":\"INFO\""));
        assertTrue(actual.contains("\"logger_name\":\"org.viise.zhurnal.tml.TmlInfo\""));
        assertTrue(actual.contains("\"message\":\"Hello, log!\""));
        assertTrue(actual.contains("\"timestamp\""));
        assertTrue(actual.contains("\"sql_error_code\":null"));
        assertTrue(actual.contains("\"sql_state\":null"));
        assertTrue(actual.contains("\"sql_message\":null"));

        assertFalse(actual.contains("\"thread_name\":null"));
        assertFalse(actual.contains("\"stack_trace\""));
    }

    @Test
    public void create_allNull() {
        String actual = new TmlElkSql(
                null,
                null
        ).create();

        assertEquals(actual, "{}");
    }
}
