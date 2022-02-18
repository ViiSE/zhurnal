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

public final class LgChain implements Log<Template> {

    private final List<Log<Template>> lgs;

    public LgChain(List<Log<Template>> lgs) {
        this.lgs = lgs;
    }

    @SafeVarargs
    public LgChain(Log<Template>... lgs) {
        this(Arrays.asList(lgs));
    }

    @Override
    public void print(Template tml) {
        lgs.forEach(lg -> lg.print(tml));
    }
}
