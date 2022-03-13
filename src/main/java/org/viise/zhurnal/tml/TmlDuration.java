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

import java.util.concurrent.TimeUnit;

/**
 * Duration template.
 */
public final class TmlDuration implements TemplateNamed {

    private final Long duration;
    private final TimeUnit unit;

    /**
     * Ctor.
     * @param duration Duration value.
     * @param unit {@link TimeUnit}.
     */
    public TmlDuration(Long duration, TimeUnit unit) {
        this.duration = duration;
        this.unit = unit;
    }

    /**
     * Ctor.
     * @param duration Duration value.
     */
    public TmlDuration(Long duration) {
        this(duration, TimeUnit.MILLISECONDS);
    }

    /**
     * Creating template {@code "[DURATION <VALUE:duration_value> <UNIT:unit_name>]"}, where {@code duration_value} -
     * {@link #duration}, {@code unit_name} - {@link #unit} name.
     * Example:
     * <pre> {@code
     * String tml = new TmlDuration(200L, TimeUnit.MILLISECOND).create();
     * // This code creating template:
     * // [DURATION <VALUE:200> <UNIT:MILLISECONDS>]
     * } </pre>
     * @return template as a String.
     */
    @Override
    public String create() {
        return new TmlRoot(
                "duration",
                new TmlChild(
                        "value",
                        duration
                ),
                new TmlChild(
                        "unit",
                        unit != null ? unit.name() : "null"
                )
        ).create();
    }

    /**
     * Name of duration template.
     * @return {@code "DURATION"}.
     */
    @Override
    public String name() {
        return "DURATION";
    }
}
