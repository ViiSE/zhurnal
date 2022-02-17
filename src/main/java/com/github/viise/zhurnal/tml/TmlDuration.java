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

import java.util.concurrent.TimeUnit;

public final class TmlDuration implements Template {

    private final Long duration;
    private final TimeUnit unit;

    public TmlDuration(Long duration, TimeUnit unit) {
        this.duration = duration;
        this.unit = unit;
    }

    public TmlDuration(Long duration) {
        this(duration, TimeUnit.MILLISECONDS);
    }

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
}
