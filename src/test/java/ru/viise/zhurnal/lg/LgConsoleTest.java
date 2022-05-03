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

package ru.viise.zhurnal.lg;

import ru.viise.zhurnal.tml.TmlInfo;
import org.testng.annotations.Test;
import ru.viise.zhurnal.tml.TmlInfo;

public class LgConsoleTest {

    @Test
    public void print() {
        new LgConsole().print(new TmlInfo("Hello, {}!", "log"));
    }
}
