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

import ru.viise.zhurnal.Template;
import ru.viise.zhurnal.TemplateNamed;
import org.testng.annotations.Test;
import ru.viise.zhurnal.Template;
import ru.viise.zhurnal.TemplateNamed;

import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import static org.testng.Assert.assertEquals;

public class TmlDurationJsonTest {

    @Test
    public void create() {
        String actual = new TmlDurationJson(new TmlDuration(200L)).create();
        assertEquals(actual, "{\"duration\":{\"unit\":\"MILLISECONDS\",\"value\":200}}");
    }

    @Test
    public void create_str() {
        String actual = new TmlDurationJson("[DURATION <VALUE:200> <UNIT:MILLISECONDS>]").create();
        assertEquals(actual, "{\"duration\":{\"unit\":\"MILLISECONDS\",\"value\":200}}");
    }

    @Test
    public void create_withPattern() {
        String actual = new TmlDurationJson(
                Pattern.compile("DURATION ([\\d]+) ([A-Za-z$]+)"),
                "DURATION 200 ms"
        ).create();
        assertEquals(actual, "{\"duration\":{\"unit\":\"ms\",\"value\":200}}");
    }

    @Test
    public void create_withPatternAndTml() {
        String actual = new TmlDurationJson(
                Pattern.compile("\\[DURATION <VALUE:([\\d]+)> <UNIT:([A-Za-z$]+)>]"),
                new TmlDuration(200L, TimeUnit.MILLISECONDS)
        ).create();
        assertEquals(actual, "{\"duration\":{\"unit\":\"MILLISECONDS\",\"value\":200}}");
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    public void create_nullStr() {
        String str = null;
        String actual = new TmlDurationJson(str).create();
        assertEquals(actual, "{\"duration\":{\"unit\":null,\"value\":null}}");
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    public void create_nullTml() {
        Template tml = null;
        String actual = new TmlDurationJson(tml).create();
        assertEquals(actual, "{\"duration\":{\"unit\":null,\"value\":null}}");
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    public void create_nullStrWithPattern() {
        String tmlStr = null;
        String actual = new TmlDurationJson(Pattern.compile("DURATION ([\\d]+) ([A-Za-z$]+)"), tmlStr).create();
        assertEquals(actual, "{\"duration\":{\"unit\":null,\"value\":null}}");
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    public void create_nullTmlWithPattern() {
        Template tml = null;
        String actual = new TmlDurationJson(Pattern.compile("DURATION ([\\d]+) ([A-Za-z$]+)"), tml).create();
        assertEquals(actual, "{\"duration\":{\"unit\":null,\"value\":null}}");
    }

    @Test
    public void name() {
        TemplateNamed tml = new TmlDurationJson("");
        assertEquals(tml.name(), "duration");
    }
}
