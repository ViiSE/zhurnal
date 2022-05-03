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
import ru.viise.zhurnal.tml.TmlThrowableJson;
import org.json.JSONObject;
import ru.viise.zhurnal.Template;
import ru.viise.zhurnal.tml.TmlThrowable;

/**
 * Throwable ELK Template.
 * Added in main ELK log ({@link #tmlElk}) :
 * <pre> {@code
 * {
 *   "stack_trace": "stack trace here",
 *   "throwable_message": "throwable message here"
 * }
 * } </pre>
 */
public final class TmlElkThrowable implements Template {

    private final Template tmlElk;
    private final Template tml;

    /**
     * Ctor.
     *
     * @param tmlElk The main template, which will be supplemented by {@link #tml}. {@link #tmlElk} is immutable.
     * @param tml    Template to be used to generate Throwable ELK log. It's recommended to use
     *               {@link TmlThrowable} implementations.
     */
    public TmlElkThrowable(Template tmlElk, Template tml) {
        this.tmlElk = tmlElk;
        this.tml = tml;
    }

    /**
     * Creating ELK log with Duration information. For example:
     * <pre> {@code
     * Template tmlElk = new TmlElkStd(new TmlInfo("Hello, {}!", "log"));
     *
     * String actual = new TmlElkThrowable(
     *         tmlElk,
     *         new TmlThrowable(new Throwable())
     * ).create();
     * } </pre>
     * Or:
     * <pre> {@code
     * Template tmlElk = new TmlElkStd(new TmlInfo("Hello, {}!", "log"));
     * String actual = new TmlElkThrowable(
     *         new TmlElkThread(
     *                 tmlElk,
     *                 new TmlThread()
     *         ),
     *         new TmlThrowable(new Throwable())
     * ).create();
     * } </pre>
     *
     * If {@link #tmlElk} is null, then {@link #tmlElk} creating empty JSON object.
     * If {@link #tml} is null, then the following will be added to the created {@link #tmlElk}:
     * <pre> {@code
     * {
     *   "stack_trace": null,
     *   "throwable_message": null
     * }
     * } </pre>
     *
     * @return ELK log with Throwable information.
     */
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
