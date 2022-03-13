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
import org.viise.zhurnal.tml.TmlInfo;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class LgChainTest {

    private final String fName = System.getProperty("user.dir") + File.separator
            + "src" + File.separator
            + "test" + File.separator
            + "resources" + File.separator
            + "lg.log";

    @Test
    public void print() throws IOException {
        new LgChain(
                new LgConsole(),
                new LgFile(fName)
        ).print(new TmlInfo(LgChainTest.class, "Hello, {}!", "log"));

        String actual = Files.readAllLines(Paths.get(fName)).get(0);
        assertTrue(
                actual.matches(
                        "\\[LEVEL INFO] \\[TIMESTAMP .*] \\[CLASS org\\.viise\\.zhurnal\\.lg\\.LgChainTest] \\[MESSAGE Hello, log!]"
                )
        );

        try {
            Files.delete(Paths.get(fName));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void print_list() throws IOException {
        new LgChain(new ArrayList<Log<Template>>() {{
            add(new LgConsole());
            add(new LgFile(fName));
        }}).print(new TmlInfo(LgChainTest.class, "Hello, {}!", "log"));

        String actual = Files.readAllLines(Paths.get(fName)).get(0);
        assertTrue(
                actual.matches(
                        "\\[LEVEL INFO] \\[TIMESTAMP .*] \\[CLASS org\\.viise\\.zhurnal\\.lg\\.LgChainTest] \\[MESSAGE Hello, log!]"
                )
        );

        try {
            Files.delete(Paths.get(fName));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @SuppressWarnings("ConstantConditions")
    @Test(expectedExceptions = NullPointerException.class)
    public void print_null() {
        List<Log<Template>> lgs = null;
        new LgChain(lgs).print(new TmlInfo(LgChainTest.class, "Hello, {}!", "log"));
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void print_oneLogIsNull() {
        new LgChain(new LgConsole(), null).print(new TmlInfo(LgChainTest.class, "Hello, {}!", "log"));
    }
}
