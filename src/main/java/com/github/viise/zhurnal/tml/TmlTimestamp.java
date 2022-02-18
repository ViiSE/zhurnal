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

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class TmlTimestamp implements Template {

    private final LocalDateTime localDateTime;
    private final DateTimeFormatter dtf;

    public TmlTimestamp(LocalDateTime localDateTime, DateTimeFormatter dtf) {
        this.localDateTime = localDateTime;
        this.dtf = dtf == null ? DateTimeFormatter.ISO_DATE_TIME : dtf;
    }

    public TmlTimestamp(LocalDateTime localDateTime, String pattern) {
        this(localDateTime, DateTimeFormatter.ofPattern(pattern));
    }

    public TmlTimestamp(LocalDateTime localDateTime) {
        this(localDateTime, DateTimeFormatter.ISO_DATE_TIME);
    }

    public TmlTimestamp() {
        this(LocalDateTime.now());
    }

    @Override
    public String create() {
        return String.format(
                "[TIMESTAMP %s]",
                new TmlBasic(localDateTime != null ? dtf.format(localDateTime) : null).create()
        );
    }
}
