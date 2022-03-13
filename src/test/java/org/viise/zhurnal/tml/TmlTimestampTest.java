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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.testng.Assert.assertEquals;

public class TmlTimestampTest {

    @Test
    public void create() {
        String actual = new TmlTimestamp().create();
        DateTimeFormatter dtf = DateTimeFormatter.ISO_DATE_TIME;
        dtf.parse(actual.replace("[TIMESTAMP ", "").replace("]", ""));
    }

    @Test
    public void create_customDateTime() {
        LocalDateTime ldt = LocalDateTime.now();
        String actual = new TmlTimestamp(ldt).create();
        assertEquals(actual, String.format("[TIMESTAMP %s]", DateTimeFormatter.ISO_DATE_TIME.format(ldt)));
    }

    @Test
    public void create_customPattern() {
        LocalDate ld = LocalDate.now();
        String actual = new TmlTimestamp(LocalDateTime.now(), "yyyy-MM-dd").create();
        assertEquals(actual, String.format("[TIMESTAMP %s]", ld));
    }

    @Test
    public void create_customPattern_2() {
        LocalDateTime ldt = LocalDateTime.now();
        String actual = new TmlTimestamp(ldt, DateTimeFormatter.ISO_DATE_TIME).create();
        assertEquals(actual, String.format("[TIMESTAMP %s]", DateTimeFormatter.ISO_DATE_TIME.format(ldt)));
    }

    @Test
    public void create_nullDateTime() {
        String actual = new TmlTimestamp(null).create();
        assertEquals(actual, "[TIMESTAMP null]");
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    public void create_nullDateTimeFormatter() {
        DateTimeFormatter dtf = null;
        LocalDateTime ldt = LocalDateTime.now();
        String actual = new TmlTimestamp(ldt, dtf).create();
        assertEquals(actual, String.format("[TIMESTAMP %s]", DateTimeFormatter.ISO_DATE_TIME.format(ldt)));
    }

    @Test
    public void name() {
        TemplateNamed tml = new TmlTimestamp();
        assertEquals(tml.name(), "TIMESTAMP");
    }
}
