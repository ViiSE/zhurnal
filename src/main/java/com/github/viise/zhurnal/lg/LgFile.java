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

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public final class LgFile implements Log {

    private final String fileName;

    public LgFile(String fileName) {
        this.fileName = fileName;
    }

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