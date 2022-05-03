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

import java.util.regex.Pattern;

import static org.testng.Assert.assertEquals;

public class TmlSqlJsonTest {

    @Test
    public void create() {
        String actual = new TmlSqlJson(new TmlSql(100, "OK", "Okay")).create();
        assertEquals(
                actual,
                "{\"sql\":{\"error_code\":100,\"state\":\"OK\",\"message\":\"Okay\"}}"
        );
    }

    @Test
    public void create_str() {
        String actual = new TmlSqlJson("[SQL <ERROR_CODE:100> <SQL_STATE:OK> <MESSAGE:Okay>]").create();
        assertEquals(
                actual,
                "{\"sql\":{\"error_code\":100,\"state\":\"OK\",\"message\":\"Okay\"}}"
        );
    }

    @Test
    public void create_withPattern() {
        String actual = new TmlSqlJson(
                Pattern.compile("SQL err_code:(-?[\\d]+) state:(.*) msg:(.*)"),
                "SQL err_code:100 state:OK msg:Okay"
        ).create();
        assertEquals(
                actual,
                "{\"sql\":{\"error_code\":100,\"state\":\"OK\",\"message\":\"Okay\"}}"
        );
    }

    @Test
    public void create_withPatternAndTml() {
        String actual = new TmlSqlJson(
                Pattern.compile("\\[SQL <ERROR_CODE:(-?[\\d]+)> <SQL_STATE:(.*)> <MESSAGE:(.*)>]"),
                new TmlSql(100, "OK", "Okay")
        ).create();
        assertEquals(
                actual,
                "{\"sql\":{\"error_code\":100,\"state\":\"OK\",\"message\":\"Okay\"}}"
        );
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    public void create_nullStr() {
        String str = null;
        String actual = new TmlSqlJson(str).create();
        assertEquals(
                actual,
                "{\"sql\":{\"error_code\":null,\"state\":null,\"message\":null}}"
        );
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    public void create_nullTml() {
        Template tml = null;
        String actual = new TmlSqlJson(tml).create();
        assertEquals(
                actual,
                "{\"sql\":{\"error_code\":null,\"state\":null,\"message\":null}}"
        );
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    public void create_nullStrWithPattern() {
        String tmlStr = null;
        String actual = new TmlSqlJson(
                Pattern.compile("\\[SQL <ERROR_CODE:(-?[\\d]+)> <SQL_STATE:(.*)> <MESSAGE:(.*)>]"),
                tmlStr
        ).create();
        assertEquals(actual, "{\"sql\":{\"error_code\":null,\"state\":null,\"message\":null}}");
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    public void create_nullTmlWithPattern() {
        Template tml = null;
        String actual = new TmlSqlJson(
                Pattern.compile("\\[SQL <ERROR_CODE:(-?[\\d]+)> <SQL_STATE:(.*)> <MESSAGE:(.*)>]"),
                tml
        ).create();
        assertEquals(actual, "{\"sql\":{\"error_code\":null,\"state\":null,\"message\":null}}");
    }

    @Test
    public void name() {
        TemplateNamed tml = new TmlSqlJson("");
        assertEquals(tml.name(), "sql");
    }
}
