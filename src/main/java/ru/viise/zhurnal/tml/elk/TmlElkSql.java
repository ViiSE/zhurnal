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
import ru.viise.zhurnal.tml.TmlSqlJson;
import org.json.JSONObject;
import ru.viise.zhurnal.Template;
import ru.viise.zhurnal.tml.TmlSql;
import ru.viise.zhurnal.tml.TmlSqlJson;

/**
 * SQL ELK Template.
 * Added in main ELK log ({@link #tmlElk}) :
 * <pre> {@code
 * {
 *   "sql_error_code": "200",
 *   "sql_state": "OK",
 *   "sql_message": "Okay"
 * }
 * } </pre>
 */
public final class TmlElkSql implements Template {

    private final Template tmlElk;
    private final Template tml;

    /**
     * Ctor.
     *
     * @param tmlElk The main template, which will be supplemented by {@link #tml}. {@link #tmlElk} is immutable.
     * @param tml    Template to be used to generate SQL ELK log. It's recommended to use
     *               {@link TmlSql} implementations.
     */
    public TmlElkSql(Template tmlElk, Template tml) {
        this.tmlElk = tmlElk;
        this.tml = tml;
    }

    /**
     * Creating ELK log with SQL information. For example:
     * <pre> {@code
     * Template tmlElk = new TmlElkStd(new TmlInfo("Hello, {}!", "log"));
     *
     * String actual = new TmlElkSql(
     *         tmlElk,
     *         new TmlSql(200, "OK", "Okay")
     * ).create();
     * } </pre>
     * Or:
     * <pre> {@code
     * Template tmlElk = new TmlElkStd(new TmlInfo("Hello, {}!", "log"));
     * String actual = new TmlElkSql(
     *         new TmlElkThread(
     *                 tmlElk,
     *                 new TmlThread()
     *         ),
     *         new TmlSql(200, "OK", "Okay")
     * ).create();
     * } </pre>
     *
     * If {@link #tmlElk} is null, then {@link #tmlElk} creating empty JSON object.
     * If {@link #tml} is null, then the following will be added to the created {@link #tmlElk}:
     * <pre> {@code
     * {
     *   "sql_error_code": null,
     *   "sql_state": null,
     *   "sql_message": null
     * }
     * } </pre>
     *
     * @return ELK log with SQL information.
     */
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
