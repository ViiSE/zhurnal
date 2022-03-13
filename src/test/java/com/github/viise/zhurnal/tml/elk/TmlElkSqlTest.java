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

package com.github.viise.zhurnal.tml.elk;

import com.github.viise.zhurnal.HttpMethod;
import com.github.viise.zhurnal.HttpStatus;
import com.github.viise.zhurnal.Template;
import com.github.viise.zhurnal.tml.TmlHttp;
import com.github.viise.zhurnal.tml.TmlInfo;
import com.github.viise.zhurnal.tml.TmlSql;
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

        System.out.println(actual);

        assertTrue(actual.contains("\"level\":\"INFO\""));
        assertTrue(actual.contains("\"logger_name\":\"com.github.viise.zhurnal.tml.TmlInfo\""));
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
        assertTrue(actual.contains("\"logger_name\":\"com.github.viise.zhurnal.tml.TmlInfo\""));
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
