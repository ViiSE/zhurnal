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

package com.github.viise.zhurnal.tml;

import com.github.viise.zhurnal.Template;

import java.io.PrintWriter;
import java.io.StringWriter;

public class TmlThrowable implements Template {

    private final Throwable thr;
    private final String errorMsg;
    private final Boolean printThrMsg;

    public TmlThrowable(Throwable thr, String errorMsg, Boolean printThrMsg) {
        this.thr = thr;
        this.errorMsg = errorMsg;
        this.printThrMsg = printThrMsg != null ? printThrMsg : true;
    }

    public TmlThrowable(Throwable thr, String errorMsg) {
        this(thr, errorMsg, true);
    }

    public TmlThrowable(Throwable thr) {
        this(thr, "", true);
    }

    @Override
    public String create() {
        String stackTrace = null;
        String msg = null;
        if (thr != null) {
            StringWriter sw = new StringWriter();
            try (PrintWriter pw = new PrintWriter(sw)) {
                thr.printStackTrace(pw);
                stackTrace = sw.toString();
                msg = printThrMsg
                        ? (new TmlBasic(thr.getMessage()).create() + new TmlBasic(errorMsg).create())
                        : new TmlBasic(errorMsg).create();
            }
        }

        return new TmlRoot(
                "throwable",
                new TmlChild(
                        "message",
                        new TmlBasic(msg).create()
                ),
                new TmlChild(
                        "stack_trace",
                        new TmlBasic(stackTrace).create()
                )
        ).create();
    }
}
