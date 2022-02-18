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

import com.github.viise.zhurnal.Level;
import com.github.viise.zhurnal.Log;
import com.github.viise.zhurnal.Template;
import com.github.viise.zhurnal.TemplateLeveled;

import java.util.Arrays;
import java.util.List;

public class LgLimited implements Log<TemplateLeveled> {

    private final Log<Template> lg;
    private final List<Level> allowedLvls;

    public LgLimited(Log<Template> lg, List<Level> allowedLvls) {
        this.lg = lg;
        this.allowedLvls = allowedLvls;
    }

    public LgLimited(Log<Template> lg, Level... allowedLvls) {
        this(lg, Arrays.asList(allowedLvls));
    }

    @Override
    public void print(TemplateLeveled tmlLvl) {
        if (allowedLvls.contains(tmlLvl.level())) {
            lg.print(tmlLvl);
        }
    }
}
