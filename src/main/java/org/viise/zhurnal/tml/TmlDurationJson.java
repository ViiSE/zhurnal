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
 * Duration template as a JSON.
 */
public final class TmlDurationJson implements TemplateNamed {

    private final Pattern pattern;
    private final String tmlStr;

    /**
     * Ctor.
     *
     * @param pattern Pattern for parsing {@link #tmlStr}. {@code pattern} must not be null.
     * @param tmlStr  Template as a String. To create it's recommended to use {@link TmlDuration} implementation. If
     *                {@code tmlStr} is null, then default value is empty.
     */
    public TmlDurationJson(Pattern pattern, String tmlStr) {
        this.pattern = pattern;
        this.tmlStr = tmlStr != null ? tmlStr : "";
    }

    /**
     * Ctor.
     *
     * @param pattern Pattern for parsing {@link #tmlStr}. {@code pattern} must not be null.
     * @param tml     Template implementation. It's recommended to use {@link TmlDuration} implementation. If
     *                {@code tmlStr} is null, then default value is empty.
     */
    public TmlDurationJson(Pattern pattern, Template tml) {
        this(pattern, tml != null ? tml.create() : "");
    }

    /**
     * Ctor. {@link #pattern} value is {@code "\[DURATION <VALUE:([\d]+)> <UNIT:([A-Za-z$]+)>]"}.
     *
     * @param tmlStr Template as a String. To create it's recommended to use {@link TmlDuration} implementation. If
     *               {@code tmlStr} is null, then default value is empty.
     */
    public TmlDurationJson(String tmlStr) {
        this(Pattern.compile("\\[DURATION <VALUE:([\\d]+)> <UNIT:([A-Za-z$]+)>]"), tmlStr);
    }

    /**
     * Ctor. {@link #pattern} value is {@code "\[DURATION <VALUE:([\d]+)> <UNIT:([A-Za-z$]+)>]"}.
     *
     * @param tml Template as a String. To create it's recommended to use {@link TmlDuration} implementation. If
     *            {@code tml} is null, then default value for {@link #tmlStr} is empty.
     */
    public TmlDurationJson(Template tml) {
        this(tml != null ? tml.create() : "");
    }

    /**
     * Creating template {@code {"duration":{"unit":"unit_value","value":value}}}, where unit_value - unitValue from
     * {@link #tmlStr}, {@code value} - value as a number from {@link #tmlStr}.
     * Example:
     * <pre> {@code
     * String tml = new TmlDurationJson(new TmlDuration(200L)).create();
     * // This code creating template:
     * // {"duration":{"unit":"MILLISECONDS","value":200}}
     * } </pre>
     * @return template as a JSON String.
     */
    @Override
    public String create() {
        JSONObject jObj = new JSONObject();
        JSONObject jDurationObj = new JSONObject();
        Matcher matcher = pattern.matcher(tmlStr);
        if (matcher.find()) {
            jDurationObj.put("value", Long.parseLong(matcher.group(1)));
            jDurationObj.put("unit", matcher.group(2));
        } else {
            jDurationObj.put("value", JSONObject.NULL);
            jDurationObj.put("unit", JSONObject.NULL);
        }
        jObj.put("duration", jDurationObj);

        return jObj.toString();
    }

    /**
     * Name of class template.
     * @return {@code "duration"}.
     */
    @Override
    public String name() {
        return "duration";
    }
}
