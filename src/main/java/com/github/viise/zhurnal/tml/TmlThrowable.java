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

import com.github.viise.zhurnal.TemplateNamed;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Throwable template.
 */
public final class TmlThrowable implements TemplateNamed {

    private final Throwable thr;
    private final String errorMsg;
    private final Boolean printThrMsg;

    /**
     * Ctor.
     * @param thr Throwable.
     * @param errorMsg Throwable error message.
     * @param printThrMsg Printing throwable message or not.
     */
    public TmlThrowable(Throwable thr, String errorMsg, Boolean printThrMsg) {
        this.thr = thr;
        this.errorMsg = errorMsg;
        this.printThrMsg = printThrMsg != null ? printThrMsg : true;
    }

    /**
     * Ctor.
     * @param thr Throwable.
     * @param errorMsg Throwable error message.
     */
    public TmlThrowable(Throwable thr, String errorMsg) {
        this(thr, errorMsg, true);
    }

    /**
     * Ctor.
     * @param thr Throwable.
     */
    public TmlThrowable(Throwable thr) {
        this(thr, "", true);
    }

    /**
     * Creating template
     * {@code "[THROWABLE <MESSAGE:thr_msg> <STACK_TRACE:thr_stack_trace>]"}, where {@code thr_msg} - throwable message
     * from {@link #thr}, {@code thr_stack_trace} - throwable stack trace from {@link #thr}.
     * Example:
     * <pre> {@code
     * String tml = new TmlThrowable(new Throwable(), "error").create();
     * // This code creating template:
     * // [THROWABLE <MESSAGE:error> <STACK_TRACE:stack_trace_here>]
     * } </pre>
     * @return template as a String.
     */
    @Override
    public String create() {
        String stackTrace = null;
        String msg;
        if (thr != null) {
            StringWriter sw = new StringWriter();
            try (PrintWriter pw = new PrintWriter(sw)) {
                thr.printStackTrace(pw);
                stackTrace = sw.toString();
                String format = errorMsg != null ? (errorMsg.isEmpty() ? "%s%s" : "%s:%s") : "%s:%s";
                msg = printThrMsg
                        ? String.format(
                                format,
                                new TmlBasic(errorMsg).create(),
                                new TmlBasic(thr.getMessage()).create()
                        )
                        : new TmlBasic(errorMsg).create();
            }
        } else {
            String format = errorMsg != null ? (errorMsg.isEmpty() ? "null" : "%s:null") : "null";
            msg = printThrMsg
                    ? String.format(format, new TmlBasic(errorMsg).create())
                    : String.format("%s", new TmlBasic(errorMsg).create());
        }

        return new TmlRoot(
                "throwable",
                new TmlChild(
                        "message",
                        msg
                ),
                new TmlChild(
                        "stack_trace",
                        new TmlBasic(stackTrace).create()
                )
        ).create();
    }

    /**
     * Name of throwable template.
     * @return {@code "THROWABLE"}.
     */
    @Override
    public String name() {
        return "THROWABLE";
    }
}
