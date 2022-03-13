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

package org.viise.zhurnal.lg;

import org.viise.zhurnal.Level;
import org.viise.zhurnal.Log;
import org.viise.zhurnal.Template;
import org.viise.zhurnal.TemplateLeveled;

import java.util.Arrays;
import java.util.List;

/**
 * Limited log.
 * Implementation limits log entries by level.
 *
 * @see Level
 */
public final class LgLimited implements Log<TemplateLeveled> {

    private final Log<Template> lg;
    private final List<Level> allowedLvls;

    /**
     * Ctor.
     * @param lg Log.
     * @param allowedLvls List of allowed levels. If {@code TemplateLeveled} implementation have a level that is
     *                    contained in this list, then they will be printed, otherwise - not.
     */
    public LgLimited(Log<Template> lg, List<Level> allowedLvls) {
        this.lg = lg;
        this.allowedLvls = allowedLvls;
    }

    /**
     * Ctor.
     * @param lg Log.
     * @param allowedLvls Array of allowed levels. If {@code TemplateLeveled} implementation have a level that is
     *                    contained in this array, then they will be printed, otherwise - not.
     */
    public LgLimited(Log<Template> lg, Level... allowedLvls) {
        this(lg, Arrays.asList(allowedLvls));
    }

    /**
     * Printing leveled template. If {@code tmlLvl} have a level that is contained in {@code allowedLvls}, then they
     * will be printed, otherwise - not.
     * @param tmlLvl Implementation of {@link TemplateLeveled} interface. {@code tmlLvl} must not be null.
     */
    @Override
    public void print(TemplateLeveled tmlLvl) {
        if (allowedLvls.contains(tmlLvl.level())) {
            lg.print(tmlLvl);
        }
    }
}
