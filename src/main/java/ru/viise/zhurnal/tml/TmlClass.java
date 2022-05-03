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
 * Class template.
 */
public final class TmlClass implements TemplateNamed {

    private final Class<?> tmlClass;
    private final Boolean useFullName;

    /**
     * Ctor.
     * @param tmlClass Class whose name will be used in the template as a string.
     * @param useFullName Whether to use the fully qualified class name in the resulting string template or not.
     */
    public TmlClass(Class<?> tmlClass, Boolean useFullName) {
        this.tmlClass = tmlClass;
        this.useFullName = useFullName;
    }

    /**
     * Ctor.
     * @param tmlClass Class whose name will be used in the template as a string.
     */
    public TmlClass(Class<?> tmlClass) {
        this(tmlClass, true);
    }

    /**
     * Creating template {@code "[CLASS className]"}, where className - class name from {@code tmlClass}.
     * Example:
     * <pre> {@code
     * String tml = new TmlClass(Main.class).create();
     * // This code creating template:
     * // [CLASS ru.viise.zhurnal.tml.Main]
     * } </pre>
     * @return template as a String.
     */
    @Override
    public String create() {
        String className = tmlClass == null ? "null" : useFullName ? tmlClass.getName() : tmlClass.getSimpleName();
        return String.format("[CLASS %s]", className);
    }

    /**
     * Name of class template.
     * @return {@code "CLASS"}.
     */
    @Override
    public String name() {
        return "CLASS";
    }
}
