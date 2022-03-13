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

import org.viise.zhurnal.Template;

/**
 * Basic template.
 */
public final class TmlBasic implements Template {

    private final Object value;

    /**
     * Ctor.
     * @param value template value.
     */
    public TmlBasic(Object value) {
        this.value = value;
    }

    /**
     * Creating template.
     * @return {@code value} as string. If {@code value} is null, then creating {@code "null"} string.
     */
    @Override
    public String create() {
        return value == null ? "null" : value.toString();
    }
}
