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
import ru.viise.zhurnal.TemplateNamed;

/**
 * Child template.
 */
public final class TmlChild implements TemplateNamed {

    private final String name;
    private final Object value;

    /**
     * Ctor.
     * @param name template name.
     * @param value template value.
     */
    public TmlChild(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    /**
     * Creating template {@code "<name:value>"}, where name - {@code name} in uppercase, {@code value} - value
     * from ctor. If {@code value} is null, then {@code value} is {@code "null"}.
     * Example:
     * <pre> {@code
     * String tml = new TmlChild("class", "my class").create();
     * // This code creating template:
     * // <CLASS:my class>
     * } </pre>
     * @return template as a String.
     */
    @Override
    public String create() {
        return String.format("<%s:%s>", new TmlBasic(name).create().toUpperCase(), new TmlBasic(value).create());
    }

    /**
     * Name of template.
     * @return {@code name} in uppercase.
     */
    @Override
    public String name() {
        return name.toUpperCase();
    }
}
