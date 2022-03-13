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

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;

public class TmlDurationTest {

    @Test
    public void create() {
        String actual = new TmlDuration(200L).create();
        assertEquals(actual, "[DURATION <VALUE:200> <UNIT:MILLISECONDS>]");
    }

    @Test
    public void create_customUnit() {
        String actual = new TmlDuration(2L, TimeUnit.SECONDS).create();
        assertEquals(actual, "[DURATION <VALUE:2> <UNIT:SECONDS>]");
    }

    @Test
    public void create_nullUnit() {
        String actual = new TmlDuration(2L, null).create();
        assertEquals(actual, "[DURATION <VALUE:2> <UNIT:null>]");
    }

    @Test
    public void create_nullDuration() {
        String actual = new TmlDuration(null).create();
        assertEquals(actual, "[DURATION <VALUE:null> <UNIT:MILLISECONDS>]");
    }

    @Test
    public void name() {
        TemplateNamed tml = new TmlDuration(1L);
        assertEquals(tml.name(), "DURATION");
    }
}
