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

package org.viise.zhurnal.tml;

import org.viise.zhurnal.Template;
import org.viise.zhurnal.TemplateNamed;
import org.json.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Message template as a JSON.
 */
public final class TmlMsgJson implements TemplateNamed {

    private final Pattern pattern;
    private final String tmlStr;

    /**
     * Ctor.
     *
     * @param pattern Pattern for parsing {@link #tmlStr}. {@code pattern} must not be null.
     * @param tmlStr  Template as a String. To create it's recommended to use {@link TmlMsg} implementation. If
     *                {@code tmlStr} is null, then default value is empty.
     */
    public TmlMsgJson(Pattern pattern, String tmlStr) {
        this.pattern = pattern;
        this.tmlStr = tmlStr != null ? tmlStr : "";
    }

    /**
     * Ctor.
     *
     * @param pattern Pattern for parsing {@link #tmlStr}. {@code pattern} must not be null.
     * @param tml     Template implementation. It's recommended to use {@link TmlMsg} implementation. If {@code tmlStr}
     *                is null, then default value is empty.
     */
    public TmlMsgJson(Pattern pattern, Template tml) {
        this(pattern, tml != null ? tml.create() : "");
    }

    /**
     * Ctor. {@link #pattern} value is {@code "\[MESSAGE (.*)]"}.
     *
     * @param tml Template as a String. To create it's recommended to use {@link TmlMsg} implementation. If {@code tml}
     *            is null, then default value for {@link #tmlStr} is empty.
     */
    public TmlMsgJson(Template tml) {
        this(tml != null ? tml.create() : "");
    }

    /**
     * Ctor. {@link #pattern} value is {@code "\[MESSAGE (.*)]"}.
     *
     * @param tmlStr Template as a String. To create it's recommended to use {@link TmlMsg} implementation. If
     *               {@code tmlStr} is null, then default value is empty.
     */
    public TmlMsgJson(String tmlStr) {
        this(Pattern.compile("\\[MESSAGE (.*)]"), tmlStr);
    }

    /**
     * Creating template {@code {"message":"message_value"}}, where {@code message_value} - message value from
     * {@link #tmlStr}.
     * Example:
     * <pre> {@code
     * String tml = new TmlMsgJson(new TmlMsg("Hello, {}!", "log")).create();
     * // This code creating template:
     * // {"message":"Hello, log!"}
     * } </pre>
     *
     * If {@link #tmlStr} does not match {@link #pattern}, then method return {@code {"message":null}}.
     *
     * @return template as a JSON String.
     */
    @Override
    public String create() {
        Matcher matcher = pattern.matcher(tmlStr);
        JSONObject jObj = new JSONObject();
        if (matcher.find()) {
            jObj.put("message", matcher.group(1));
        } else {
            jObj.put("message", JSONObject.NULL);
        }

        return jObj.toString();
    }

    /**
     * Name of class template.
     * @return {@code "message"}.
     */
    @Override
    public String name() {
        return "message";
    }
}
