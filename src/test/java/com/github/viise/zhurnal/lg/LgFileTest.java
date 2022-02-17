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

import com.github.viise.zhurnal.tml.TmlInfo;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.testng.Assert.assertTrue;

public class LgFileTest {

    private final String fName = System.getProperty("user.dir") + File.separator
            + "src" + File.separator
            + "test" + File.separator
            + "resources" + File.separator
            + "lg.log";

    @Test
    public void print() throws IOException {
        new LgFile(fName).print(new TmlInfo(LgFileTest.class, "Hello, {}!", "log"));

        String actual = Files.readAllLines(Paths.get(fName)).get(0);
        assertTrue(actual.matches("\\[INFO] \\[.*] \\[LgFileTest] \\[MESSAGE Hello, log!]"));
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void print_fileNotFound() throws IOException {
        new LgFile("not-found/this-file-does-not-exists.log").print(
                new TmlInfo(LgFileTest.class, "Hello, {}!", "log")
        );

        String actual = Files.readAllLines(Paths.get(fName)).get(0);
        assertTrue(actual.matches("\\[INFO] \\[.*] \\[LgFileTest] \\[MESSAGE Hello, log!]"));
    }

    @AfterTest
    public void clearUp() {
        try {
            Files.delete(Paths.get(fName));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
