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

import org.viise.zhurnal.HttpMethod;
import org.viise.zhurnal.HttpStatus;
import org.viise.zhurnal.Template;
import org.viise.zhurnal.TemplateNamed;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;

public class TmlJsonTest {

    @Test
    public void create() throws IOException {
        String tmlStr = new TmlInfo(
                new Template[] {
                        new TmlThread(),
                        new TmlHttp(HttpMethod.GET, "/users/1", HttpStatus.OK),
                        new TmlDuration(200L, TimeUnit.MILLISECONDS),
                        new TmlSql(200, "OK", "Okay."),
                        new TmlMsg("Hello, {}!", "log")
                }
        ).create();

        String actual = new TmlJson(
                new TmlThreadJson(tmlStr),
                new TmlSqlJson(tmlStr),
                new TmlHttpJson(tmlStr),
                new TmlMsgJson(tmlStr)
        ).create();

        assertEquals(
                actual,
                "{\"http\":{\"endpoint\":\"/users/1\",\"method\":\"GET\",\"status\":\"200 OK\"},\"thread\":{\"is_interrupted\":false,\"is_alive\":true,\"name\":\"main\",\"id\":1},\"message\":\"Hello, log!\",\"sql\":{\"error_code\":200,\"state\":\"OK\",\"message\":\"Okay.\"}}"
        );
    }

    @Test
    public void create_emptyList() {
        String actual = new TmlJson(new ArrayList<>()).create();
        assertEquals(actual, "{}");
    }

    @SuppressWarnings("RedundantArrayCreation")
    @Test
    public void create_emptyArray() {
        String actual = new TmlJson(new TemplateNamed[0]).create();
        assertEquals(actual, "{}");
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    public void create_nullList() {
        List<TemplateNamed> tmls = null;
        String actual = new TmlJson(tmls).create();
        assertEquals(actual, "{}");
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    public void create_nullArray() {
        TemplateNamed[] tmls = null;
        String actual = new TmlJson(tmls).create();
        assertEquals(actual, "{}");
    }
}
