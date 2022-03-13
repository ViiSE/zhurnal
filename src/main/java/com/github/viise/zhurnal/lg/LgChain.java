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

package com.github.viise.zhurnal.lg;

import com.github.viise.zhurnal.Log;
import com.github.viise.zhurnal.Template;

import java.util.Arrays;
import java.util.List;

/**
 * Chain of logs.
 * Each log interface will print the template in order in which they were specified.
 */
public final class LgChain implements Log<Template> {

    private final List<Log<Template>> lgs;

    /**
     * Ctor.
     * @param lgs List of logs. Each log interface will print the template in order in which they were specified.
     *            List must not be null.
     */
    public LgChain(List<Log<Template>> lgs) {
        this.lgs = lgs;
    }

    /**
     * Ctor.
     * @param lgs Arrays of logs. Each log interface will print the template in order in which they were specified.
     *            Array must not be null.
     */
    @SafeVarargs
    public LgChain(Log<Template>... lgs) {
        this(Arrays.asList(lgs));
    }

    /**
     * Printing template for each log specified in {@code lgs} list.
     * @param tml Template to be printing. {@code tml} must not be null.
     */
    @Override
    public void print(Template tml) {
        lgs.forEach(lg -> lg.print(tml));
    }
}
