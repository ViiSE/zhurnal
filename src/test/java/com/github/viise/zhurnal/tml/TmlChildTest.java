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

import com.github.viise.zhurnal.TemplateNamed;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class TmlChildTest {

    @Test
    public void create() {
        String actual = new TmlChild("value", 22).create();
        assertEquals(actual, "<VALUE:22>");
    }

    @Test
    public void create_nullName() {
        String actual = new TmlChild(null, 22).create();
        assertEquals(actual, "<NULL:22>");
    }

    @Test
    public void create_nullValue() {
        String actual = new TmlChild("value", null).create();
        assertEquals(actual, "<VALUE:null>");
    }

    @Test
    public void name() {
        TemplateNamed tmlChild = new TmlChild("value", 10);
        assertEquals(tmlChild.name(), "VALUE");
    }
}
