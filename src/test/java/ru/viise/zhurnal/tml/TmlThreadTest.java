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

import static org.testng.Assert.assertEquals;

public class TmlThreadTest {

    @Test
    public void create() {
        String actual = new TmlThread(1L, "Thread-1", true, false).create();
        assertEquals(actual, "[THREAD <ID:1> <NAME:Thread-1> <IS_ALIVE:true> <IS_INTERRUPTED:false>]");
    }

    @Test
    public void create_thread() {
        String actual = new TmlThread(Thread.currentThread()).create();
        assertEquals(actual, "[THREAD <ID:1> <NAME:main> <IS_ALIVE:true> <IS_INTERRUPTED:false>]");
    }

    @Test
    public void create_nullThread() {
        String actual = new TmlThread(null).create();
        assertEquals(actual, "[THREAD <ID:null> <NAME:null> <IS_ALIVE:null> <IS_INTERRUPTED:null>]");
    }

    @Test
    public void create_nullParams() {
        String actual = new TmlThread(null, null, null, null).create();
        assertEquals(actual, "[THREAD <ID:null> <NAME:null> <IS_ALIVE:null> <IS_INTERRUPTED:null>]");
    }

    @Test
    public void name() {
        TemplateNamed tml = new TmlThread();
        assertEquals(tml.name(), "THREAD");
    }
}
