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

package ru.viise.zhurnal.tml.elk;

import ru.viise.zhurnal.Template;
import ru.viise.zhurnal.tml.*;
import org.json.JSONObject;
import ru.viise.zhurnal.tml.*;
import ru.viise.zhurnal.Template;
import ru.viise.zhurnal.TemplateLeveled;
import ru.viise.zhurnal.tml.*;

/**
 * Standard ELK Template.
 * Standard ELK log:
 * <pre> {@code
 * {
 *   "level": "INFO",
 *   "thread_name": "main",
 *   "logger_name": "ru.viise.zhurnal.tml.TmlInfo",
 *   "message": "Hello, log!",
 *   "timestamp": "2022-03-14T02:08:59.137"
 * }
 * } </pre>
 */
public final class TmlElkStd implements Template {

    private final Template tml;

    /**
     * Ctor.
     * @param tml Template to be used to generate standard ELK log. It's recommended to use
     * {@link TemplateLeveled} implementations.
     */
    public TmlElkStd(Template tml) {
        this.tml = tml;
    }

    /**
     * Creating standard ELK log. If {@code "thread_name"} is null, then the name of the current thread is taken.
     * If {@code "stack_trace"} is not presented, then this field will not be presented in resulting string. If
     * {@link #tml} is null, then method return
     * {@code {"level":null,"thread_name":null,"logger_name":null,"stack_trace":null,"message":null,"timestamp":null}}.
     *
     * @return Standard ELK log.
     */
    @Override
    public String create() {
        JSONObject jObj = new JSONObject();

        if (tml == null) {
            jObj.put("level", JSONObject.NULL);
            jObj.put("timestamp", JSONObject.NULL);
            jObj.put("logger_name", JSONObject.NULL);
            jObj.put("thread_name", JSONObject.NULL);
            jObj.put("stack_trace", JSONObject.NULL);
            jObj.put("message", JSONObject.NULL);
        } else {
            String tmlStr = tml.create();

            jObj.put(
                    "level",
                    new JSONObject(
                            new TmlLevelJson(tmlStr).create()
                    ).get("level")
            );
            jObj.put(
                    "timestamp",
                    new JSONObject(
                            new TmlTimestampJson(tmlStr).create()
                    ).get("timestamp")
            );
            jObj.put(
                    "logger_name",
                    new JSONObject(
                            new TmlClassJson(tmlStr).create()
                    ).get("class")
            );
            jObj.put(
                    "thread_name",
                    new JSONObject(
                            new TmlThreadJson(tmlStr).create()
                    ).getJSONObject("thread").get("name")
            );
            if (jObj.isNull("thread_name")) {
                jObj.put("thread_name", Thread.currentThread().getName());
            }

            jObj.put(
                    "stack_trace",
                    new JSONObject(
                            new TmlThrowableJson(tmlStr).create()
                    ).getJSONObject("throwable").get("stack_trace")
            );
            if (jObj.isNull("stack_trace")) {
                jObj.remove("stack_trace");
            }

            jObj.put(
                    "message",
                    new JSONObject(
                            new TmlMsgJson(tmlStr).create()
                    ).get("message")
            );
        }

        return jObj.toString();
    }
}
