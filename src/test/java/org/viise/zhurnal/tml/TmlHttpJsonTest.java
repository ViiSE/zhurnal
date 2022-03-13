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

import java.util.regex.Pattern;

import static org.testng.Assert.assertEquals;

public class TmlHttpJsonTest {

    @Test
    public void create() {
        String actual = new TmlHttpJson(new TmlHttp(HttpMethod.GET, "/users/1", HttpStatus.OK)).create();
        assertEquals(
                actual,
                "{\"http\":{\"endpoint\":\"/users/1\",\"method\":\"GET\",\"status\":\"200 OK\"}}"
        );
    }

    @Test
    public void create_str() {
        String actual = new TmlHttpJson(
                "[HTTP <METHOD:GET> <ENDPOINT:/users/1> <STATUS:200 OK>]"
        ).create();
        assertEquals(
                actual,
                "{\"http\":{\"endpoint\":\"/users/1\",\"method\":\"GET\",\"status\":\"200 OK\"}}"
        );
    }

    @Test
    public void create_withPattern() {
        String actual = new TmlHttpJson(
                Pattern.compile("HTTP (GET|POST|PUT|DELETE) ([A-Za-z$]+) ([\\d]+ [A-Za-z\\- ]+)"),
                "HTTP GET users 200 OK"
        ).create();
        assertEquals(actual, "{\"http\":{\"endpoint\":\"users\",\"method\":\"GET\",\"status\":\"200 OK\"}}");
    }

    @Test
    public void create_withPatternAndTml() {
        String actual = new TmlHttpJson(
                Pattern.compile("\\[HTTP <METHOD:(GET|POST)> <ENDPOINT:([A-Za-z$]+)> <STATUS:([\\d]+ [A-Za-z\\- ]+)>]"),
                new TmlHttp(HttpMethod.GET, "users", HttpStatus.OK)
        ).create();
        assertEquals(actual, "{\"http\":{\"endpoint\":\"users\",\"method\":\"GET\",\"status\":\"200 OK\"}}");
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    public void create_nullStr() {
        String str = null;
        String actual = new TmlHttpJson(str).create();
        assertEquals(actual, "{\"http\":{\"endpoint\":null,\"method\":null,\"status\":null}}");
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    public void create_nullTml() {
        Template tml = null;
        String actual = new TmlHttpJson(tml).create();
        assertEquals(actual, "{\"http\":{\"endpoint\":null,\"method\":null,\"status\":null}}");
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    public void create_nullStrWithPattern() {
        String tmlStr = null;
        String actual = new TmlHttpJson(Pattern.compile("HTTP (GET|POST|PUT|DELETE) ([A-Za-z$]+) ([\\d]+ [A-Za-z\\- ]+)"), tmlStr).create();
        assertEquals(actual, "{\"http\":{\"endpoint\":null,\"method\":null,\"status\":null}}");
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    public void create_nullTmlWithPattern() {
        Template tml = null;
        String actual = new TmlHttpJson(Pattern.compile("HTTP (GET|POST|PUT|DELETE) ([A-Za-z$]+) ([\\d]+ [A-Za-z\\- ]+)"), tml).create();
        assertEquals(actual, "{\"http\":{\"endpoint\":null,\"method\":null,\"status\":null}}");
    }

    @Test
    public void name() {
        TemplateNamed tml = new TmlHttpJson("");
        assertEquals(tml.name(), "http");
    }
}
