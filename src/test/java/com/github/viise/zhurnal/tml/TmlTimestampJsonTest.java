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

package com.github.viise.zhurnal.tml;

import com.github.viise.zhurnal.Template;
import com.github.viise.zhurnal.TemplateNamed;
import org.testng.annotations.Test;

import java.util.regex.Pattern;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class TmlTimestampJsonTest {

    @Test
    public void create() {
        String actual = new TmlTimestampJson(new TmlTimestamp()).create();
        assertTrue(actual.contains("\"timestamp\""));
    }

    @Test
    public void create_str() {
        String actual = new TmlTimestampJson(
                "[TIMESTAMP 2022-02-24T00:38:13.573]"
        ).create();
        assertEquals(actual, "{\"timestamp\":\"2022-02-24T00:38:13.573\"}");
    }

    @Test
    public void create_withPattern() {
        String actual = new TmlTimestampJson(
                Pattern.compile("TIMESTAMP (.*)"),
                "TIMESTAMP 2022-02-24T00:38:13.573"
        ).create();
        assertEquals(actual, "{\"timestamp\":\"2022-02-24T00:38:13.573\"}");
    }

    @Test
    public void create_withPatternAndTml() {
        String actual = new TmlTimestampJson(
                Pattern.compile("\\[TIMESTAMP (.*)]"),
                new TmlTimestamp()
        ).create();
        assertTrue(actual.contains("\"timestamp\""));
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    public void create_nullStr() {
        String str = null;
        String actual = new TmlTimestampJson(str).create();
        assertEquals(
                actual,
                "{\"timestamp\":null}"
        );
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    public void create_nullTml() {
        Template tml = null;
        String actual = new TmlTimestampJson(tml).create();
        assertEquals(
                actual,
                "{\"timestamp\":null}"
        );
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    public void create_nullStrWithPattern() {
        String tmlStr = null;
        String actual = new TmlTimestampJson(
                Pattern.compile("\\[TIMESTAMP (.*)]"),
                tmlStr
        ).create();
        assertEquals(
                actual,
                "{\"timestamp\":null}"
        );
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    public void create_nullTmlWithPattern() {
        Template tml = null;
        String actual = new TmlTimestampJson(
                Pattern.compile("\\[TIMESTAMP (.*)]"),
                tml
        ).create();
        assertEquals(
                actual,
                "{\"timestamp\":null}"
        );
    }

    @Test
    public void name() {
        TemplateNamed tml = new TmlTimestampJson("");
        assertEquals(tml.name(), "timestamp");
    }
}
