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

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class TmlRootTest {

    @Test
    public void create() {
        String actual = new TmlRoot(
                "response",
                new TmlChild("status", 200),
                new TmlChild("status_text", "OK")
        ).create();
        assertEquals(actual, "[RESPONSE <STATUS:200> <STATUS_TEXT:OK>]");
    }

    @Test
    public void create_list() {
        String actual = new TmlRoot(
                "response",
                new ArrayList<Template>() {{
                    add(new TmlChild("status", 200));
                    add(new TmlChild("status_text", "OK"));
                }}
        ).create();
        assertEquals(actual, "[RESPONSE <STATUS:200> <STATUS_TEXT:OK>]");
    }

    @Test
    public void create_withoutChildren() {
        String actual = new TmlRoot("response").create();
        assertEquals(actual, "[RESPONSE]");
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    public void create_nullParams() {
        List<Template> tmls = null;
        String actual = new TmlRoot("response", tmls).create();
        assertEquals(actual, "[RESPONSE]");
    }

    @Test
    public void create_nullName() {
        String actual = new TmlRoot(null, new TmlChild("value", 1)).create();
        assertEquals(actual, "[NULL <VALUE:1>]");
    }

    @Test
    public void name() {
        TemplateNamed tml = new TmlRoot("root", new TmlChild("name", 2));
        assertEquals(tml.name(), "ROOT");
    }
}
