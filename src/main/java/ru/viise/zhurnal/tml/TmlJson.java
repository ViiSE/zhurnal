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

package ru.viise.zhurnal.tml;

import org.json.JSONObject;
import ru.viise.zhurnal.Template;
import ru.viise.zhurnal.TemplateNamed;
import ru.viise.zhurnal.Template;

import java.util.Arrays;
import java.util.List;

/**
 * JSON Template.
 * This implementation uses a list of {@link TemplateNamed} implementations and added them in root JSON. For achieve
 * that, {@link TemplateNamed} implementation must create JSON string.
 */
public final class TmlJson implements Template {

    private final List<TemplateNamed> tmls;

    /**
     * Ctor.
     * @param tmls List of {@link TemplateNamed} implementations. {@link TemplateNamed} implementations must create JSON
     *            string.
     */
    public TmlJson(List<TemplateNamed> tmls) {
        this.tmls = tmls;
    }

    /**
     * Ctor.
     * @param tmls Array of {@link TemplateNamed} implementations. {@link TemplateNamed} implementations must create
     *             JSON string.
     */
    public TmlJson(TemplateNamed... tmls) {
        this(tmls != null ? Arrays.asList(tmls) : null);
    }

    /**
     * Creating JSON template. Example:
     * <pre> {@code
     * String tmlStr = new TmlInfo(
     *         new Template[] {
     *                 new TmlDuration(200L, TimeUnit.MILLISECONDS),
     *                 new TmlMsg("Hello, {}!", "log")
     *         }
     * ).create();
     *
     * String actual = new TmlJson(
     *         new TmlDurationJson(tmlStr),
     *         new TmlMsgJson(tmlStr)
     * ).create();
     * // This code creating template:
     * // {"duration":{"unit":"MILLISECONDS","value":200},"message":"Hello, log!"}
     * } </pre>
     * If {@link #tmls} null or empty, then method return {@code {}}.
     *
     * @return template as a JSON String.
     */
    @Override
    public String create() {
        if (tmls == null || tmls.isEmpty()) {
            return new JSONObject().toString();
        } else {
            JSONObject jObj = new JSONObject();
            tmls.forEach(tml -> jObj.put(tml.name(), new JSONObject(tml.create()).get(tml.name())));
            return jObj.toString();
        }
    }
}
