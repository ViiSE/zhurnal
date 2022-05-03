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

import ru.viise.zhurnal.Template;
import ru.viise.zhurnal.TemplateNamed;
import org.json.JSONObject;
import ru.viise.zhurnal.Template;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Thread template as a JSON.
 */
public final class TmlThreadJson implements TemplateNamed {

    private final Pattern pattern;
    private final String tmlStr;

    /**
     * Ctor.
     *
     * @param pattern Pattern for parsing {@link #tmlStr}. {@code pattern} must not be null.
     * @param tmlStr  Template as a String. To create it's recommended to use {@link TmlThread} implementation. If
     *                {@code tmlStr} is null, then default value is empty.
     */
    public TmlThreadJson(Pattern pattern, String tmlStr) {
        this.pattern = pattern;
        this.tmlStr = tmlStr != null ? tmlStr : "";
    }

    /**
     * Ctor. {@link #pattern} value is {@code "\[THREAD <ID:([\d]+)> <NAME:((?:[a-zA-Z0-9-]+\.)+[A-Za-z0-9$]+|[A-Za-z0-9$]+)> <IS_ALIVE:(true|false)> <IS_INTERRUPTED:(true|false)>]"}.
     *
     * @param tmlStr Template as a String. To create it's recommended to use {@link TmlThread} implementation. If
     *               {@code tmlStr} is null, then default value is empty.
     */
    public TmlThreadJson(String tmlStr) {
        this(
                Pattern.compile(
                        "\\[THREAD <ID:([\\d]+)> <NAME:((?:[a-zA-Z0-9-]+\\.)+[A-Za-z0-9$]+|[A-Za-z0-9$]+)> <IS_ALIVE:(true|false)> <IS_INTERRUPTED:(true|false)>]"
                ),
                tmlStr
        );
    }

    /**
     * Ctor.
     *
     * @param pattern Pattern for parsing {@link #tmlStr}. {@code pattern} must not be null.
     * @param tml     Template implementation. It's recommended to use {@link TmlThread} implementation. If
     *                {@code tmlStr} is null, then default value is empty.
     */
    public TmlThreadJson(Pattern pattern, Template tml) {
        this(pattern, tml != null ? tml.create() : "");
    }

    /**
     * Ctor. {@link #pattern} value is {@code "\[THREAD <ID:([\d]+)> <NAME:((?:[a-zA-Z0-9-]+\.)+[A-Za-z0-9$]+|[A-Za-z0-9$]+)> <IS_ALIVE:(true|false)> <IS_INTERRUPTED:(true|false)>]"}.
     *
     * @param tml Template as a String. To create it's recommended to use {@link TmlThread} implementation. If
     *            {@code tml} is null, then default value for {@link #tmlStr} is empty.
     */
    public TmlThreadJson(Template tml) {
        this(tml != null ? tml.create() : "");
    }

    /**
     * Creating template {@code {"thread":{"is_interrupted":is_interrupted_value,"is_alive":is_alive_value,"name":"name_value","id":id_value}}},
     * where {@code is_interrupted_value} - is interrupted value from {@link #tmlStr} ({@code true}or{@code false}),
     * {@code is_alive_value} - alive value from {@link #tmlStr} ({@code true}or{@code false}), {@code name_value} -
     * name value from {@link #tmlStr}, {@code id_value} - id value from {@link #tmlStr}.
     * Example:
     * <pre> {@code
     * String tml = new TmlThreadJson(new TmlThread()).create();
     * // This code creating template:
     * // {"thread":{"is_interrupted":false,"is_alive":true,"name":"main","id":1}}
     * } </pre>
     *
     * If {@link #tmlStr} does not match {@link #pattern}, then method return
     * {@code {"thread":{"is_interrupted":null,"is_alive":null,"name":null,"id":null}}}.
     *
     * @return template as a JSON String.
     */
    @Override
    public String create() {
        Matcher matcher = pattern.matcher(tmlStr);
        JSONObject jObj = new JSONObject();
        JSONObject jThreadObj = new JSONObject();
        if (matcher.find()) {
            jThreadObj.put("id", Long.parseLong(matcher.group(1)));
            jThreadObj.put("name", matcher.group(2));
            jThreadObj.put("is_alive", Boolean.parseBoolean(matcher.group(3)));
            jThreadObj.put("is_interrupted", Boolean.parseBoolean(matcher.group(4)));
        } else {
            jThreadObj.put("id", JSONObject.NULL);
            jThreadObj.put("name", JSONObject.NULL);
            jThreadObj.put("is_alive", JSONObject.NULL);
            jThreadObj.put("is_interrupted", JSONObject.NULL);
        }
        jObj.put("thread", jThreadObj);

        return jObj.toString();
    }

    /**
     * Name of class template.
     * @return {@code "thread"}.
     */
    @Override
    public String name() {
        return "thread";
    }
}
