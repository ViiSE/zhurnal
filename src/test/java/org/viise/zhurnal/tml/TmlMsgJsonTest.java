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

public class TmlMsgJsonTest {

    @Test
    public void create() {
        String actual = new TmlMsgJson(new TmlMsg("Hello, {}!", "log")).create();
        assertEquals(actual, "{\"message\":\"Hello, log!\"}");
    }

    @Test
    public void create_str() {
        String actual = new TmlMsgJson("[MESSAGE Hello, log!]").create();
        assertEquals(actual, "{\"message\":\"Hello, log!\"}");
    }

    @Test
    public void create_withPattern() {
        String actual = new TmlMsgJson(
                Pattern.compile("MSG (.*)"),
                "MSG Hello, log!"
        ).create();
        assertEquals(actual, "{\"message\":\"Hello, log!\"}");
    }

    @Test
    public void create_withPatternAndTml() {
        String actual = new TmlMsgJson(
                Pattern.compile("\\[MESSAGE (.*)]"),
                new TmlMsg("Hello, {}!", "log")
        ).create();
        assertEquals(actual, "{\"message\":\"Hello, log!\"}");
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    public void create_nullStr() {
        String str = null;
        String actual = new TmlMsgJson(str).create();
        assertEquals(actual, "{\"message\":null}");
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    public void create_nullTml() {
        Template tml = null;
        String actual = new TmlMsgJson(tml).create();
        assertEquals(actual, "{\"message\":null}");
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    public void create_nullStrWithPattern() {
        String tmlStr = null;
        String actual = new TmlMsgJson(Pattern.compile("\\[MESSAGE (.*)]"), tmlStr).create();
        assertEquals(actual, "{\"message\":null}");
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    public void create_nullTmlWithPattern() {
        Template tml = null;
        String actual = new TmlMsgJson(Pattern.compile("\\[MESSAGE (.*)]"), tml).create();
        assertEquals(actual, "{\"message\":null}");
    }

    @Test
    public void name() {
        TemplateNamed tml = new TmlMsgJson("");
        assertEquals(tml.name(), "message");
    }
}
