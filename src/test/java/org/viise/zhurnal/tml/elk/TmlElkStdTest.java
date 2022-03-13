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
import org.viise.zhurnal.tml.*;
import org.testng.annotations.Test;
import org.viise.zhurnal.tml.*;

import static org.testng.Assert.*;

public class TmlElkStdTest {

    @Test
    public void create() {
        String actual = new TmlElkStd(new TmlInfo("Hello, {}!", "log")).create();
        assertTrue(actual.contains("\"level\":\"INFO\""));
        assertTrue(actual.contains("\"logger_name\":\"org.viise.zhurnal.tml.TmlInfo\""));
        assertTrue(actual.contains("\"message\":\"Hello, log!\""));
        assertTrue(actual.contains("\"timestamp\""));
        assertFalse(actual.contains("\"thread_name\":null"));
        assertFalse(actual.contains("\"stack_trace\""));
    }

    @Test
    public void create_withStackTraceAndThread() {
        String actual = new TmlElkStd(
                new TmlError(
                        new Template[] {
                                new TmlThread(),
                                new TmlThrowable(new Throwable()),
                                new TmlMsg("Error!")
                        }
                )
        ).create();
        assertTrue(actual.contains("\"level\":\"ERROR\""));
        assertTrue(actual.contains("\"logger_name\":\"org.viise.zhurnal.tml.TmlError\""));
        assertTrue(actual.contains("\"message\":\"Error!\""));
        assertTrue(actual.contains("\"timestamp\""));
        assertTrue(actual.contains("\"stack_trace\""));
        assertFalse(actual.contains("\"thread_name\":null"));
    }

    @Test
    public void create_nullTml() {
        String actual = new TmlElkStd(null).create();
        assertTrue(actual.contains("\"level\":null"));
        assertTrue(actual.contains("\"logger_name\":null"));
        assertTrue(actual.contains("\"message\":null"));
        assertTrue(actual.contains("\"timestamp\":null"));
        assertTrue(actual.contains("\"stack_trace\":null"));
        assertTrue(actual.contains("\"thread_name\":null"));
    }
}
