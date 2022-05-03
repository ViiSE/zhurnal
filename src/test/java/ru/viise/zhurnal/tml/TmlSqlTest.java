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

import java.sql.SQLException;

import static org.testng.Assert.assertEquals;

public class TmlSqlTest {

    @Test
    public void create() {
        String actual = new TmlSql(-100, "FAILED", "SELECT failed").create();
        assertEquals(actual, "[SQL <ERROR_CODE:-100> <SQL_STATE:FAILED> <MESSAGE:SELECT failed>]");
    }

    @Test
    public void create_fromException() {
        SQLException sqlEx = new SQLException("SELECT failed", "FAILED", -100, new Throwable("SELECT failed"));
        String actual = new TmlSql(sqlEx).create();
        assertEquals(actual, "[SQL <ERROR_CODE:-100> <SQL_STATE:FAILED> <MESSAGE:SELECT failed>]");
    }

    @Test
    public void create_allNull() {
        String actual = new TmlSql(null, null, null).create();
        assertEquals(actual, "[SQL <ERROR_CODE:null> <SQL_STATE:null> <MESSAGE:null>]");
    }

    @Test
    public void name() {
        TemplateNamed tml = new TmlSql(new SQLException());
        assertEquals(tml.name(), "SQL");
    }
}
