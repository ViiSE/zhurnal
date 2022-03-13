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

import com.github.viise.zhurnal.Level;
import com.github.viise.zhurnal.TemplateNamed;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class TmlLevelTest {

    @Test
    public void create() {
        String actual = new TmlLevel(Level.INFO).create();
        assertEquals(actual, "[LEVEL INFO]");
    }

    @Test
    public void create_nullLevel() {
        String actual = new TmlLevel(null).create();
        assertEquals(actual, "[LEVEL null]");
    }

    @Test
    public void name() {
        TemplateNamed tml = new TmlLevel(Level.INFO);
        assertEquals(tml.name(), "LEVEL");
    }
}
