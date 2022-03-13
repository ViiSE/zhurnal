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

package org.viise.zhurnal.tml.elk;

import org.viise.zhurnal.Template;
import org.viise.zhurnal.tml.*;
import org.json.JSONObject;
import org.viise.zhurnal.tml.*;

public final class TmlElkStd implements Template {

    private final Template tml;

    public TmlElkStd(Template tml) {
        this.tml = tml;
    }

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
