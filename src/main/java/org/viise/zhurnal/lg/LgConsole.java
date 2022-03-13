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

import org.viise.zhurnal.Log;
import org.viise.zhurnal.Template;

/**
 * Console Log.
 */
public final class LgConsole implements Log<Template> {

    /**
     * Printing template into standard output.
     * @param tml Template to be printed. {@code tml} must not be null.
     */
    @Override
    public void print(Template tml) {
        System.out.println(tml.create());
    }
}
