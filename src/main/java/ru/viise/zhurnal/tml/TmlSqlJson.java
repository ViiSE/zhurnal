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
import ru.viise.zhurnal.TemplateNamed;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * SQL template as a JSON.
 */
public final class TmlSqlJson implements TemplateNamed {

    private final Pattern pattern;
    private final String tmlStr;

    /**
     * Ctor.
     *
     * @param pattern Pattern for parsing {@link #tmlStr}. {@code pattern} must not be null.
     * @param tmlStr  Template as a String. To create it's recommended to use {@link TmlSql} implementation. If
     *                {@code tmlStr} is null, then default value is empty.
     */
    public TmlSqlJson(Pattern pattern, String tmlStr) {
        this.pattern = pattern;
        this.tmlStr = tmlStr != null ? tmlStr : "";
    }

    /**
     * Ctor.
     *
     * @param pattern Pattern for parsing {@link #tmlStr}. {@code pattern} must not be null.
     * @param tml     Template implementation. It's recommended to use {@link TmlSql} implementation. If {@code tmlStr}
     *                is null, then default value is empty.
     */
    public TmlSqlJson(Pattern pattern, Template tml) {
        this(pattern, tml != null ? tml.create() : "");
    }

    /**
     * Ctor. {@link #pattern} value is {@code "\[MESSAGE (.*)]"}.
     *
     * @param tml Template as a String. To create it's recommended to use {@link TmlSql} implementation. If {@code tml}
     *            is null, then default value for {@link #tmlStr} is empty.
     */
    public TmlSqlJson(Template tml) {
        this(tml != null ? tml.create() : "");
    }

    /**
     * Ctor. {@link #pattern} value is {@code "\[SQL <ERROR_CODE:(-?[\d]+)> <SQL_STATE:(.*)> <MESSAGE:(.*)>]"}.
     *
     * @param tmlStr Template as a String. To create it's recommended to use {@link TmlSql} implementation. If
     *               {@code tmlStr} is null, then default value is empty.
     */
    public TmlSqlJson(String tmlStr) {
        this(Pattern.compile("\\[SQL <ERROR_CODE:(-?[\\d]+)> <SQL_STATE:(.*)> <MESSAGE:(.*)>]"), tmlStr);
    }

    /**
     * Creating template {@code {"sql":{"error_code":error_code_value,"state":"state_value","message":"message_value"}}},
     * where {@code error_code_value} - error code value from {@link #tmlStr}, {@code state_value} - state value from
     * {@link #tmlStr}, {@code message_value} message value from {@link #tmlStr}.
     * Example:
     * <pre> {@code
     * String tml = new TmlSqlJson(new TmlSql(100, "OK", "Okay")).create();
     * // This code creating template:
     * // {"sql":{"error_code":100,"state":"OK","message":"Okay"}}
     * } </pre>
     *
     * If {@link #tmlStr} does not match {@link #pattern}, then method return
     * {@code {"sql":{"error_code":null,"state":null,"message":null}}}.
     *
     * @return template as a JSON String.
     */
    @Override
    public String create() {
        Matcher matcher = pattern.matcher(tmlStr);
        JSONObject jObj = new JSONObject();
        JSONObject jSqlObj = new JSONObject();
        if (matcher.find()) {
            jSqlObj.put("error_code", Integer.parseInt(matcher.group(1)));
            jSqlObj.put("state", matcher.group(2));
            jSqlObj.put("message", matcher.group(3));
        } else {
            jSqlObj.put("error_code", JSONObject.NULL);
            jSqlObj.put("state", JSONObject.NULL);
            jSqlObj.put("message", JSONObject.NULL);
        }
        jObj.put("sql", jSqlObj);

        return jObj.toString();
    }

    /**
     * Name of class template.
     * @return {@code "sql"}.
     */
    @Override
    public String name() {
        return "sql";
    }
}
