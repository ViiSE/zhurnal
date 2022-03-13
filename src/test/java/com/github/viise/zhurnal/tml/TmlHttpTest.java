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

import com.github.viise.zhurnal.HttpMethod;
import com.github.viise.zhurnal.HttpStatus;
import com.github.viise.zhurnal.TemplateNamed;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class TmlHttpTest {

    @Test
    public void create() {
        String actual = new TmlHttp(HttpMethod.GET, "/cats/1", HttpStatus.OK).create();
        assertEquals(actual, "[HTTP <METHOD:GET> <ENDPOINT:/cats/1> <STATUS:200 OK>]");
    }

    @Test
    public void create_nullMethod() {
        String actual = new TmlHttp(null, "/cats/1", HttpStatus.OK).create();
        assertEquals(actual, "[HTTP <METHOD:null> <ENDPOINT:/cats/1> <STATUS:200 OK>]");
    }

    @Test
    public void create_nullEndpoint() {
        String actual = new TmlHttp(HttpMethod.GET, null, HttpStatus.OK).create();
        assertEquals(actual, "[HTTP <METHOD:GET> <ENDPOINT:null> <STATUS:200 OK>]");
    }

    @Test
    public void create_nullStatus() {
        String actual = new TmlHttp(HttpMethod.GET, "/cats/1", null).create();
        assertEquals(actual, "[HTTP <METHOD:GET> <ENDPOINT:/cats/1> <STATUS:null>]");
    }

    @Test
    public void name() {
        TemplateNamed tml = new TmlHttp(HttpMethod.GET, "users", HttpStatus.OK);
        assertEquals(tml.name(), "HTTP");
    }
}
