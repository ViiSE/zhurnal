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

import ru.viise.zhurnal.Log;
import ru.viise.zhurnal.Template;
import ru.viise.zhurnal.Log;
import ru.viise.zhurnal.Template;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * File log.
 */
public final class LgFile implements Log<Template> {

    private final String fileName;

    /**
     * Ctor.
     * @param fileName Name of the log file. Name of the log file must not be empty.
     */
    public LgFile(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Printing template into file. File will be created if it doesn't exist.
     * @param tml Template that has been saved to a file. {@code tml} must not be null.
     */
    @Override
    public void print(Template tml) {
        try {
            Files.write(
                    Paths.get(fileName),
                    String.format("%s%s", tml.create(), System.lineSeparator()).getBytes(),
                    StandardOpenOption.CREATE, StandardOpenOption.APPEND
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
