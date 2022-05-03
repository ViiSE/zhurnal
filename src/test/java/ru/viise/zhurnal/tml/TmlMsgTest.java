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

import ru.viise.zhurnal.TemplateNamed;
import org.testng.annotations.Test;
import ru.viise.zhurnal.TemplateNamed;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class TmlMsgTest {

    @Test
    public void create() {
        String actual = new TmlMsg("Hello, {}!", "World").create();
        assertEquals(actual, "[MESSAGE Hello, World!]");
    }

    @Test
    public void create_list() {
        String actual = new TmlMsg(
                "Hello, {}! Your age is {}",
                new ArrayList<Object>()
                {{
                    add("John");
                    add(25);
                }}
        ).create();
        assertEquals(actual, "[MESSAGE Hello, John! Your age is 25]");
    }

    @Test
    public void create_tooManyParams() {
        String actual = new TmlMsg("Hello, {}!", "World", 25).create();
        assertEquals(actual, "[MESSAGE Hello, World!]");
    }

    @Test
    public void create_tooManyTemplates() {
        String actual = new TmlMsg("Hello, {}! Your age is {}", "World").create();
        assertEquals(actual, "[MESSAGE Hello, World! Your age is {}]");
    }

    @Test
    public void create_nullMsg() {
        String actual = new TmlMsg(null, "World", 25).create();
        assertEquals(actual, "[MESSAGE null]");
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    public void create_nullParams() {
        List<Object> params = null;
        String actual = new TmlMsg("Hello, {}!", params).create();
        assertEquals(actual, "[MESSAGE Hello, {}!]");
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    public void create_allNull() {
        List<Object> params = null;
        String actual = new TmlMsg(null, params).create();
        assertEquals(actual, "[MESSAGE null]");
    }

    @Test
    public void create_oneOfParamIsNull() {
        String actual = new TmlMsg("Hello, {}! Your age is {}.", "John", null).create();
        assertEquals(actual, "[MESSAGE Hello, John! Your age is null.]");
    }

    @Test
    public void create_withoutParams() {
        String actual = new TmlMsg("Hello, World!").create();
        assertEquals(actual, "[MESSAGE Hello, World!]");
    }

    @Test
    public void name() {
        TemplateNamed tml = new TmlMsg("");
        assertEquals(tml.name(), "MESSAGE");
    }
}
