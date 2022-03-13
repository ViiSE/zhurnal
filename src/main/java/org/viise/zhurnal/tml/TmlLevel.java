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

import org.viise.zhurnal.Level;
import org.viise.zhurnal.TemplateNamed;

/**
 * Level template.
 */
public final class TmlLevel implements TemplateNamed {

    private final Level lvl;

    /**
     * Ctor.
     * @param lvl {@link Level}.
     */
    public TmlLevel(Level lvl) {
        this.lvl = lvl;
    }

    /**
     * Creating template {@code "[LEVEL level]"}, where {@code level} - {@link Level} name.
     * Example:
     * <pre> {@code
     * String tml = new TmlLevel(Level.INFO).create();
     * // This code creating template:
     * // [LEVEL INFO]
     * } </pre>
     * @return template as a String.
     */
    @Override
    public String create() {
        return String.format("[LEVEL %s]", lvl != null ? lvl.name() : "null");
    }

    /**
     * Name of level template.
     * @return {@code "LEVEL"}.
     */
    @Override
    public String name() {
        return "LEVEL";
    }
}
