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
import ru.viise.zhurnal.tml.TmlDurationJson;
import org.json.JSONObject;

/**
 * Duration ELK Template.
 * Added in main ELK log ({@link #tmlElk}) :
 * <pre> {@code
 * {
 *   "duration_unit": "MILLISECONDS",
 *   "duration_value": 200
 * }
 * } </pre>
 */
public final class TmlElkDuration implements Template {

    private final Template tmlElk;
    private final Template tml;

    /**
     * Ctor.
     *
     * @param tmlElk The main template, which will be supplemented by {@link #tml}. {@link #tmlElk} is immutable.
     * @param tml    Template to be used to generate Duration ELK log. It's recommended to use
     *               {@link ru.viise.zhurnal.tml.TmlDuration} implementations.
     */
    public TmlElkDuration(Template tmlElk, Template tml) {
        this.tmlElk = tmlElk;
        this.tml = tml;
    }

    /**
     * Creating ELK log with Duration information. For example:
     * <pre> {@code
     * Template tmlElk = new TmlElkStd(new TmlInfo("Hello, {}!", "log"));
     *
     * String actual = new TmlElkDuration(
     *         tmlElk,
     *         new TmlDuration(200L)
     * ).create();
     * } </pre>
     * Or:
     * <pre> {@code
     * Template tmlElk = new TmlElkStd(new TmlInfo("Hello, {}!", "log"));
     * String actual = new TmlElkDuration(
     *         new TmlElkThread(
     *                 tmlElk,
     *                 new TmlThread()
     *         ),
     *         new TmlDuration(200L)
     * ).create();
     * } </pre>
     *
     * If {@link #tmlElk} is null, then {@link #tmlElk} creating empty JSON object.
     * If {@link #tml} is null, then the following will be added to the created {@link #tmlElk}:
     * <pre> {@code
     * {
     *   "duration_unit": null,
     *   "duration_value": null
     * }
     * } </pre>
     *
     * @return ELK log with Duration information.
     */
    @Override
    public String create() {
        if (tmlElk == null) {
            return new JSONObject().toString();
        }

        String tmlJson = tmlElk.create();
        JSONObject jObj = new JSONObject(tmlJson);

        if (tml == null) {
            jObj.put("duration_value", JSONObject.NULL);
            jObj.put("duration_unit", JSONObject.NULL);
        } else {
            String tmlStr = tml.create();
            JSONObject jDurationObj = new JSONObject(new TmlDurationJson(tmlStr).create());
            jObj.put("duration_value", jDurationObj.getJSONObject("duration").get("value"));
            jObj.put("duration_unit", jDurationObj.getJSONObject("duration").get("unit"));
        }

        return jObj.toString();
    }
}
