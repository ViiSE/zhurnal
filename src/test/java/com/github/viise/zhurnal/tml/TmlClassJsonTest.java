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

public class TmlClassJsonTest {

    @Test
    public void create() {
        String actual = new TmlClassJson(new TmlClass(TmlClassJsonTest.class)).create();
        assertEquals(actual, "{\"class\":\"com.github.viise.zhurnal.tml.TmlClassJsonTest\"}");
    }

    @Test
    public void create_withPattern() {
        String actual = new TmlClassJson(Pattern.compile("CLASS (.*)"), "CLASS TmlClassJsonTest").create();
        assertEquals(actual, "{\"class\":\"TmlClassJsonTest\"}");
    }

    @Test
    public void create_withPatternAndTml() {
        String actual = new TmlClassJson(
                Pattern.compile("\\[CLASS ((?:[a-zA-Z0-9-]+\\.)+[A-Za-z0-9$]+|[A-Za-z0-9$]+)]"),
                new TmlClass(TmlClassJsonTest.class, false)
        ).create();
        assertEquals(actual, "{\"class\":\"TmlClassJsonTest\"}");
    }

    @Test
    public void create_str() {
        String actual = new TmlClassJson("[CLASS TmlClassJsonTest]").create();
        assertEquals(actual, "{\"class\":\"TmlClassJsonTest\"}");
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    public void create_nullStr() {
        String str = null;
        String actual = new TmlClassJson(str).create();
        assertEquals(actual, "{\"class\":null}");
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    public void create_nullTml() {
        Template tml = null;
        String actual = new TmlClassJson(tml).create();
        assertEquals(actual, "{\"class\":null}");
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    public void create_nullStrWithPattern() {
        String tmlStr = null;
        String actual = new TmlClassJson(Pattern.compile("CLASS (.*)"), tmlStr).create();
        assertEquals(actual, "{\"class\":null}");
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    public void create_nullTmlWithPattern() {
        Template tml = null;
        String actual = new TmlClassJson(Pattern.compile("CLASS (.*)"), tml).create();
        assertEquals(actual, "{\"class\":null}");
    }

    @Test
    public void name() {
        TemplateNamed tml = new TmlClassJson(new TmlClass(TmlClassJsonTest.class));
        assertEquals(tml.name(), "class");
    }
}
