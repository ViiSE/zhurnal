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

package com.github.viise.zhurnal.tml.elk;

import com.github.viise.zhurnal.Template;
import com.github.viise.zhurnal.tml.TmlThreadJson;
import org.json.JSONObject;

public final class TmlElkThread implements Template {

    private final Template tmlElk;
    private final Template tml;

    public TmlElkThread(Template tmlElk, Template tml) {
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
            jObj.put("thread_id", JSONObject.NULL);
            jObj.put("thread_is_alive", JSONObject.NULL);
            jObj.put("thread_is_interrupted", JSONObject.NULL);
        } else {
            String tmlStr = tml.create();
            String threadJson = new TmlThreadJson(tmlStr).create();
            JSONObject jThreadObj = new JSONObject(threadJson);
            jObj.put("thread_id", jThreadObj.getJSONObject("thread").get("id"));
            jObj.put("thread_name", jThreadObj.getJSONObject("thread").get("name"));
            jObj.put("thread_is_alive", jThreadObj.getJSONObject("thread").get("is_alive"));
            jObj.put("thread_is_interrupted", jThreadObj.getJSONObject("thread").get("is_interrupted"));
        }

        return jObj.toString();
    }
}
