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

import com.github.viise.zhurnal.Template;
import com.github.viise.zhurnal.tml.TmlInfo;
import com.github.viise.zhurnal.tml.TmlSql;
import com.github.viise.zhurnal.tml.TmlThread;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class TmlElkThreadTest {

    @Test
    public void create() {
        Template tmlElk = new TmlElkStd(new TmlInfo("Hello, {}!", "log"));

        String actual = new TmlElkThread(
                tmlElk,
                new TmlThread(1L, "main", true, false)
        ).create();

        System.out.println(actual);

        assertTrue(actual.contains("\"level\":\"INFO\""));
        assertTrue(actual.contains("\"logger_name\":\"com.github.viise.zhurnal.tml.TmlInfo\""));
        assertTrue(actual.contains("\"message\":\"Hello, log!\""));
        assertTrue(actual.contains("\"timestamp\""));

        assertTrue(actual.contains("\"thread_id\":1"));
        assertTrue(actual.contains("\"thread_name\":\"main\""));
        assertTrue(actual.contains("\"thread_is_alive\":true"));
        assertTrue(actual.contains("\"thread_is_interrupted\":false"));

        assertFalse(actual.contains("\"stack_trace\""));
    }

    @Test
    public void create_nullTmlElk() {
        String actual = new TmlElkThread(
                null,
                new TmlThread(1L, "main", true, false)
        ).create();

        assertEquals(actual, "{}");
    }

    @Test
    public void create_nullTml() {
        Template tmlElk = new TmlElkStd(new TmlInfo("Hello, {}!", "log"));

        String actual = new TmlElkThread(
                tmlElk,
                null
        ).create();

        assertTrue(actual.contains("\"level\":\"INFO\""));
        assertTrue(actual.contains("\"logger_name\":\"com.github.viise.zhurnal.tml.TmlInfo\""));
        assertTrue(actual.contains("\"message\":\"Hello, log!\""));
        assertTrue(actual.contains("\"timestamp\""));

        assertTrue(actual.contains("\"thread_id\":null"));
        assertTrue(actual.contains("\"thread_is_alive\":null"));
        assertTrue(actual.contains("\"thread_is_interrupted\":null"));

        assertFalse(actual.contains("\"thread_name\":null"));
        assertFalse(actual.contains("\"stack_trace\""));
    }

    @Test
    public void create_allNull() {
        String actual = new TmlElkThread(
                null,
                null
        ).create();

        assertEquals(actual, "{}");
    }
}
