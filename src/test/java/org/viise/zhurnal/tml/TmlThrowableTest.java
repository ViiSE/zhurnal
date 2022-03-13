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

import org.viise.zhurnal.TemplateNamed;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class TmlThrowableTest {

    @SuppressWarnings({"NumericOverflow", "divzero", "unused"})
    @Test
    public void create_full() {
        try {
            int a = 1 / 0;
        } catch (Exception e) {
            String actual = new TmlThrowable(e, "Exception in test", true).create();
            assertTrue(actual.contains("[THROWABLE <MESSAGE:Exception in test:/ by zero> <STACK_TRACE:java.lang.ArithmeticException: / by zero"));
        }
    }

    @SuppressWarnings({"NumericOverflow", "divzero", "unused"})
    @Test
    public void create_printThrMsg() {
        try {
            int a = 1 / 0;
        } catch (Exception e) {
            String actual = new TmlThrowable(e, "Exception in test").create();
            assertTrue(actual.contains("[THROWABLE <MESSAGE:Exception in test:/ by zero> <STACK_TRACE:java.lang.ArithmeticException: / by zero"));
        }
    }

    @SuppressWarnings({"NumericOverflow", "divzero", "unused"})
    @Test
    public void create_no_printThrMsg() {
        try {
            int a = 1 / 0;
        } catch (Exception e) {
            String actual = new TmlThrowable(e, "Exception in test", false).create();
            assertTrue(actual.contains("[THROWABLE <MESSAGE:Exception in test> <STACK_TRACE:java.lang.ArithmeticException: / by zero"));
        }
    }

    @SuppressWarnings({"NumericOverflow", "divzero", "unused"})
    @Test
    public void create_onlyThr() {
        try {
            int a = 1 / 0;
        } catch (Exception e) {
            String actual = new TmlThrowable(e).create();
            assertTrue(actual.contains("[THROWABLE <MESSAGE:/ by zero> <STACK_TRACE:java.lang.ArithmeticException: / by zero"));
        }
    }

    @Test
    public void create_printThrMsg_null() {
        String actual = new TmlThrowable(new Throwable("Test"), "Error", null).create();
        assertTrue(actual.contains("[THROWABLE <MESSAGE:Error:Test> <STACK_TRACE:java.lang.Throwable: Test"));
    }

    @Test
    public void create_errorMsg_null() {
        String actual = new TmlThrowable(new Throwable("Test"), null).create();
        assertTrue(actual.contains("[THROWABLE <MESSAGE:null:Test> <STACK_TRACE:java.lang.Throwable: Test"));
    }

    @Test
    public void create_thr_null() {
        String actual = new TmlThrowable(null).create();
        assertEquals(actual, "[THROWABLE <MESSAGE:null> <STACK_TRACE:null>]");
    }

    @Test
    public void create_thr_null_2() {
        String actual = new TmlThrowable(null, "Error").create();
        assertEquals(actual, "[THROWABLE <MESSAGE:Error:null> <STACK_TRACE:null>]");
    }

    @Test
    public void create_thr_null_3() {
        String actual = new TmlThrowable(null, "Error", false).create();
        assertEquals(actual, "[THROWABLE <MESSAGE:Error> <STACK_TRACE:null>]");
    }

    @Test
    public void create_thr_null_4() {
        String actual = new TmlThrowable(null, null, false).create();
        assertEquals(actual, "[THROWABLE <MESSAGE:null> <STACK_TRACE:null>]");
    }

    @Test
    public void create_thr_null_5() {
        String actual = new TmlThrowable(null, null).create();
        assertEquals(actual, "[THROWABLE <MESSAGE:null> <STACK_TRACE:null>]");
    }

    @Test
    public void name() {
        TemplateNamed tml = new TmlThrowable(new Throwable());
        assertEquals(tml.name(), "THROWABLE");
    }
}
