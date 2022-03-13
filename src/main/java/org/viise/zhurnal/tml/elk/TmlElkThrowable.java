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
import org.viise.zhurnal.tml.TmlThrowableJson;
import org.json.JSONObject;

public final class TmlElkThrowable implements Template {

    private final Template tmlElk;
    private final Template tml;

    public TmlElkThrowable(Template tmlElk, Template tml) {
        this.tmlElk = tmlElk;
        this.tml = tml;
    }

    @Override
    public String create() {
        if (tmlElk == null) {
            return new JSONObject().toString();
        }

        String tmlJson = tmlElk.create();
        JSONObject jObj = new JSONObject(tmlJson);

        if (tml == null) {
            jObj.put("throwable_message", JSONObject.NULL);
            jObj.put("stack_trace", JSONObject.NULL);
        } else {
            String tmlStr = tml.create();
            String throwableJson = new TmlThrowableJson(tmlStr).create();
            JSONObject jThrowableObj = new JSONObject(throwableJson);
            jObj.put("throwable_message", jThrowableObj.getJSONObject("throwable").get("message"));
            jObj.put("stack_trace", jThrowableObj.getJSONObject("throwable").get("stack_trace"));
        }

        return jObj.toString();
    }
}
