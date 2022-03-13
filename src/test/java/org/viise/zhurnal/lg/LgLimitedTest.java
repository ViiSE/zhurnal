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
import org.viise.zhurnal.tml.TmlError;
import org.viise.zhurnal.tml.TmlInfo;
import org.testng.annotations.Test;

public class LgLimitedTest {

    @Test
    public void print() {
        new LgLimited(
                new LgConsole(),
                Level.INFO
        ).print(
                new TmlInfo("Info!")
        );
    }

    @Test
    public void print_notAllowed() {
        new LgLimited(
                new LgConsole(),
                Level.INFO
        ).print(
                new TmlError("Error!")
        );
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void print_nullLog() {
        new LgLimited(
                null,
                Level.INFO
        ).print(
                new TmlInfo("Info!")
        );
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    public void print_nullLevel() {
        Level lvl = null;
        new LgLimited(
                new LgConsole(),
                lvl
        ).print(
                new TmlInfo("Info!")
        );
    }
}
