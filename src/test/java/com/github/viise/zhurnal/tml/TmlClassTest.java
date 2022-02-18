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

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class TmlClassTest {

    @Test
    public void create() {
        String actual = new TmlClass(TmlClassTest.class).create();
        assertEquals(actual, "[CLASS com.github.viise.zhurnal.tml.TmlClassTest]");
    }

    @Test
    public void create_simpleName() {
        String actual = new TmlClass(TmlClassTest.class, false).create();
        assertEquals(actual, "[CLASS TmlClassTest]");
    }

    @Test
    public void create_nullClass() {
        String actual = new TmlClass(null).create();
        assertEquals(actual, "[CLASS null]");
    }
}
