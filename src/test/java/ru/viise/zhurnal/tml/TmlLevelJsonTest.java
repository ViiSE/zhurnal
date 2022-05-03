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

import ru.viise.zhurnal.Level;
import ru.viise.zhurnal.Template;
import ru.viise.zhurnal.TemplateNamed;
import org.testng.annotations.Test;
import ru.viise.zhurnal.Level;
import ru.viise.zhurnal.Template;
import ru.viise.zhurnal.TemplateNamed;

import java.util.regex.Pattern;

import static org.testng.Assert.assertEquals;

public class TmlLevelJsonTest {

    @Test
    public void create() {
        String actual = new TmlLevelJson(new TmlLevel(Level.INFO)).create();
        assertEquals(actual, "{\"level\":\"INFO\"}");
    }

    @Test
    public void create_str() {
        String actual = new TmlLevelJson("[LEVEL INFO]").create();
        assertEquals(actual, "{\"level\":\"INFO\"}");
    }

    @Test
    public void create_withPattern() {
        String actual = new TmlLevelJson(
                Pattern.compile("LVL (INFO|WARN|ERROR)"),
                "LVL INFO"
        ).create();
        assertEquals(actual, "{\"level\":\"INFO\"}");
    }

    @Test
    public void create_withPatternAndTml() {
        String actual = new TmlLevelJson(
                Pattern.compile("\\[LEVEL (INFO|WARN|ERROR)]"),
                new TmlLevel(Level.INFO)
        ).create();
        assertEquals(actual, "{\"level\":\"INFO\"}");
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    public void create_nullStr() {
        String str = null;
        String actual = new TmlLevelJson(str).create();
        assertEquals(actual, "{\"level\":null}");
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    public void create_nullTml() {
        Template tml = null;
        String actual = new TmlLevelJson(tml).create();
        assertEquals(actual, "{\"level\":null}");
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    public void create_nullStrWithPattern() {
        String tmlStr = null;
        String actual = new TmlLevelJson(Pattern.compile("\\[LEVEL (INFO|WARN|ERROR)]"), tmlStr).create();
        assertEquals(actual, "{\"level\":null}");
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    public void create_nullTmlWithPattern() {
        Template tml = null;
        String actual = new TmlLevelJson(Pattern.compile("\\[LEVEL (INFO|WARN|ERROR)]"), tml).create();
        assertEquals(actual, "{\"level\":null}");
    }

    @Test
    public void name() {
        TemplateNamed tml = new TmlLevelJson("");
        assertEquals(tml.name(), "level");
    }
}
