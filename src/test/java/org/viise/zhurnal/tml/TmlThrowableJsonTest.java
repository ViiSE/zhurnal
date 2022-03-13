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

package org.viise.zhurnal.tml;

import org.viise.zhurnal.Template;
import org.viise.zhurnal.TemplateNamed;
import org.testng.annotations.Test;

import java.util.regex.Pattern;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class TmlThrowableJsonTest {

    @Test
    public void create() {
        String actual = new TmlThrowableJson(new TmlThrowable(new Throwable())).create();
        assertTrue(actual.contains("\"message\""));
        assertTrue(actual.contains("\"stack_trace\""));
        System.out.println(actual);
    }

    @Test
    public void create_str() {
        String actual = new TmlThrowableJson(
                "[THROWABLE <MESSAGE:msg> <STACK_TRACE:st\n>]"
        ).create();
        assertTrue(actual.contains("\"message\""));
        assertTrue(actual.contains("\"stack_trace\""));
    }

    @Test
    public void create_withPattern() {
        String actual = new TmlThrowableJson(
                Pattern.compile("\\[THROWABLE <MESSAGE:(.*)> <STACK_TRACE:(.*)>]"),
                "THROWABLE MESSAGE:msg STACK_TRACE:st"
        ).create();
        assertTrue(actual.contains("\"message\""));
        assertTrue(actual.contains("\"stack_trace\""));
    }

    @Test
    public void create_withPatternAndTml() {
        String actual = new TmlThrowableJson(
                Pattern.compile("\\[THROWABLE <MESSAGE:(.*)> <STACK_TRACE:(.*)>]"),
                new TmlThrowable(new Throwable())
        ).create();
        assertTrue(actual.contains("\"message\""));
        assertTrue(actual.contains("\"stack_trace\""));
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    public void create_nullStr() {
        String str = null;
        String actual = new TmlThrowableJson(str).create();
        assertEquals(
                actual,
                "{\"throwable\":{\"stack_trace\":null,\"message\":null}}"
        );
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    public void create_nullTml() {
        Template tml = null;
        String actual = new TmlThrowableJson(tml).create();
        assertEquals(
                actual,
                "{\"throwable\":{\"stack_trace\":null,\"message\":null}}"
        );
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    public void create_nullStrWithPattern() {
        String tmlStr = null;
        String actual = new TmlThrowableJson(
                Pattern.compile("\\[THROWABLE <MESSAGE:(.*)> <STACK_TRACE:(.*)>]"),
                tmlStr
        ).create();
        assertEquals(
                actual,
                "{\"throwable\":{\"stack_trace\":null,\"message\":null}}"
        );
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    public void create_nullTmlWithPattern() {
        Template tml = null;
        String actual = new TmlThrowableJson(
                Pattern.compile("\\[THROWABLE <MESSAGE:(.*)> <STACK_TRACE:(.*)>]"),
                tml
        ).create();
        assertEquals(
                actual,
                "{\"throwable\":{\"stack_trace\":null,\"message\":null}}"
        );
    }

    @Test
    public void name() {
        TemplateNamed tml = new TmlThrowableJson("");
        assertEquals(tml.name(), "throwable");
    }
}
