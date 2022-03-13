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

public class TmlThreadJsonTest {

    @Test
    public void create() {
        String actual = new TmlThreadJson(new TmlThread()).create();
        assertEquals(
                actual,
                "{\"thread\":{\"is_interrupted\":false,\"is_alive\":true,\"name\":\"main\",\"id\":1}}"
        );
    }

    @Test
    public void create_str() {
        String actual = new TmlThreadJson(
                "[THREAD <ID:1> <NAME:main> <IS_ALIVE:true> <IS_INTERRUPTED:false>]"
        ).create();
        assertEquals(
                actual,
                "{\"thread\":{\"is_interrupted\":false,\"is_alive\":true,\"name\":\"main\",\"id\":1}}"
        );
    }

    @Test
    public void create_withPattern() {
        String actual = new TmlThreadJson(
                Pattern.compile("THREAD id:(-?[\\d]+) name:(.*) is_alive:(true|false) is_interrupted:(true|false)"),
                "THREAD id:1 name:main is_alive:true is_interrupted:false"
        ).create();
        assertEquals(
                actual,
                "{\"thread\":{\"is_interrupted\":false,\"is_alive\":true,\"name\":\"main\",\"id\":1}}"
        );
    }

    @Test
    public void create_withPatternAndTml() {
        String actual = new TmlThreadJson(
                Pattern.compile("\\[THREAD <ID:([\\d]+)> <NAME:((?:[a-zA-Z0-9-]+\\.)+[A-Za-z0-9$]+|[A-Za-z0-9$]+)> <IS_ALIVE:(true|false)> <IS_INTERRUPTED:(true|false)>]"),
                new TmlThread()
        ).create();
        assertEquals(
                actual,
                "{\"thread\":{\"is_interrupted\":false,\"is_alive\":true,\"name\":\"main\",\"id\":1}}"
        );
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    public void create_nullStr() {
        String str = null;
        String actual = new TmlThreadJson(str).create();
        assertEquals(
                actual,
                "{\"thread\":{\"is_interrupted\":null,\"is_alive\":null,\"name\":null,\"id\":null}}"
        );
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    public void create_nullTml() {
        Template tml = null;
        String actual = new TmlThreadJson(tml).create();
        assertEquals(
                actual,
                "{\"thread\":{\"is_interrupted\":null,\"is_alive\":null,\"name\":null,\"id\":null}}"
        );
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    public void create_nullStrWithPattern() {
        String tmlStr = null;
        String actual = new TmlThreadJson(
                Pattern.compile("\\[THREAD <ID:([\\d]+)> <NAME:((?:[a-zA-Z0-9-]+\\.)+[A-Za-z0-9$]+|[A-Za-z0-9$]+)> <IS_ALIVE:(true|false)> <IS_INTERRUPTED:(true|false)>]"),
                tmlStr
        ).create();
        assertEquals(
                actual,
                "{\"thread\":{\"is_interrupted\":null,\"is_alive\":null,\"name\":null,\"id\":null}}"
        );
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    public void create_nullTmlWithPattern() {
        Template tml = null;
        String actual = new TmlThreadJson(
                Pattern.compile("\\[THREAD <ID:([\\d]+)> <NAME:((?:[a-zA-Z0-9-]+\\.)+[A-Za-z0-9$]+|[A-Za-z0-9$]+)> <IS_ALIVE:(true|false)> <IS_INTERRUPTED:(true|false)>]"),
                tml
        ).create();
        assertEquals(
                actual,
                "{\"thread\":{\"is_interrupted\":null,\"is_alive\":null,\"name\":null,\"id\":null}}"
        );
    }

    @Test
    public void name() {
        TemplateNamed tml = new TmlThreadJson("");
        assertEquals(tml.name(), "thread");
    }
}
