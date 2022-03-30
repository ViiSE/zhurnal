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
import org.viise.zhurnal.tml.TmlThreadJson;
import org.json.JSONObject;

/**
 * Thread ELK Template.
 * Added in main ELK log ({@link #tmlElk}) :
 * <pre> {@code
 * {
 *   "thread_id": 1,
 *   "thread_name": "main",
 *   "thread_is_alive": true,
 *   "thread_is_interrupted": false
 * }
 * } </pre>
 */
public final class TmlElkThread implements Template {

    private final Template tmlElk;
    private final Template tml;

    /**
     * Ctor.
     *
     * @param tmlElk The main template, which will be supplemented by {@link #tml}. {@link #tmlElk} is immutable.
     * @param tml    Template to be used to generate Thread ELK log. It's recommended to use
     *               {@link org.viise.zhurnal.tml.TmlThread} implementations.
     */
    public TmlElkThread(Template tmlElk, Template tml) {
        this.tmlElk = tmlElk;
        this.tml = tml;
    }

    /**
     * Creating ELK log with Thread information. For example:
     * <pre> {@code
     * Template tmlElk = new TmlElkStd(new TmlInfo("Hello, {}!", "log"));
     *
     * String actual = new TmlElkThread(
     *         tmlElk,
     *         new TmlThread()
     * ).create();
     * } </pre>
     * Or:
     * <pre> {@code
     * Template tmlElk = new TmlElkStd(new TmlInfo("Hello, {}!", "log"));
     * String actual = new TmlElkThread(
     *         new TmlElkDuration(
     *                 tmlElk,
     *                 new TmlDuration(200L)
     *         ),
     *         new TmlThread()
     * ).create();
     * } </pre>
     *
     * If {@link #tmlElk} is null, then {@link #tmlElk} creating empty JSON object.
     * If {@link #tml} is null, then the following will be added to the created {@link #tmlElk}:
     * <pre> {@code
     * {
     *   "thread_id": null,
     *   "thread_name": null,
     *   "thread_is_alive": null,
     *   "thread_is_interrupted": null
     * }
     * } </pre>
     *
     * @return ELK log with Thread information.
     */
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
