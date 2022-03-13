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
import com.github.viise.zhurnal.tml.TmlSqlJson;
import org.json.JSONObject;

public final class TmlElkSql implements Template {

    private final Template tmlElk;
    private final Template tml;

    public TmlElkSql(Template tmlElk, Template tml) {
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
            jObj.put("sql_error_code", JSONObject.NULL);
            jObj.put("sql_state", JSONObject.NULL);
            jObj.put("sql_message", JSONObject.NULL);
        } else {
            String tmlStr = tml.create();
            String sqlJson = new TmlSqlJson(tmlStr).create();
            JSONObject jSqlObj = new JSONObject(sqlJson);
            jObj.put("sql_error_code", jSqlObj.getJSONObject("sql").get("error_code"));
            jObj.put("sql_state", jSqlObj.getJSONObject("sql").get("state"));
            jObj.put("sql_message", jSqlObj.getJSONObject("sql").get("message"));
        }

        return jObj.toString();
    }
}
