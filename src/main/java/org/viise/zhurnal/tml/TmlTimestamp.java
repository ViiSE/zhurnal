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

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Throwable template.
 */
public final class TmlTimestamp implements TemplateNamed {

    private final LocalDateTime localDateTime;
    private final DateTimeFormatter dtf;

    /**
     * Ctor.
     * @param localDateTime Local date time.
     * @param dtf {@link DateTimeFormatter}. If {@code dtf} is {@code null}, then
     * {@link DateTimeFormatter#ISO_DATE_TIME} will be used.
     */
    public TmlTimestamp(LocalDateTime localDateTime, DateTimeFormatter dtf) {
        this.localDateTime = localDateTime;
        this.dtf = dtf == null ? DateTimeFormatter.ISO_DATE_TIME : dtf;
    }

    /**
     * Ctor.
     * @param localDateTime Local date time.
     * @param pattern DateTime pattern. For example, {@code "dd.MM.yyyy"}.
     */
    public TmlTimestamp(LocalDateTime localDateTime, String pattern) {
        this(localDateTime, DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * Ctor. Pattern of {@link #localDateTime} is {@link DateTimeFormatter#ISO_DATE_TIME}.
     * @param localDateTime Local date time.
     */
    public TmlTimestamp(LocalDateTime localDateTime) {
        this(localDateTime, DateTimeFormatter.ISO_DATE_TIME);
    }

    /**
     * Ctor. {@link #localDateTime} is {@link LocalDateTime#now()}.
     */
    public TmlTimestamp() {
        this(LocalDateTime.now());
    }

    /**
     * Creating template
     * {@code "[TIMESTAMP dateTime]"}, where {@code dateTime} - {@link #localDateTime} in format provided {@link #dtf}.
     * Example:
     * <pre> {@code
     * String tml = new TmlTimestamp().create();
     * // This code creating template:
     * // [TIMESTAMP 2022-02-28T02:10:00.000]
     * } </pre>
     * @return template as a String.
     */
    @Override
    public String create() {
        return String.format(
                "[TIMESTAMP %s]",
                new TmlBasic(localDateTime != null ? dtf.format(localDateTime) : null).create()
        );
    }

    /**
     * Name of timestamp template.
     * @return {@code "TIMESTAMP"}.
     */
    @Override
    public String name() {
        return "TIMESTAMP";
    }
}
