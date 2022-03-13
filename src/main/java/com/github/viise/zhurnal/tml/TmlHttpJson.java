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

package com.github.viise.zhurnal.tml;

import com.github.viise.zhurnal.HttpMethod;
import com.github.viise.zhurnal.Template;
import com.github.viise.zhurnal.TemplateNamed;
import org.json.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * HTTP template as a JSON.
 */
public final class TmlHttpJson implements TemplateNamed {

    private final Pattern pattern;
    private final String tmlStr;

    /**
     * Ctor.
     *
     * @param pattern Pattern for parsing {@link #tmlStr}. {@code pattern} must not be null.
     * @param tmlStr  Template as a String. To create it's recommended to use {@link TmlHttpJson} implementation. If
     *                {@code tmlStr} is null, then default value is empty.
     */
    public TmlHttpJson(Pattern pattern, String tmlStr) {
        this.pattern = pattern;
        this.tmlStr = tmlStr != null ? tmlStr : "";
    }

    /**
     * Ctor.
     *
     * @param pattern Pattern for parsing {@link #tmlStr}. {@code pattern} must not be null.
     * @param tml     Template implementation. It's recommended to use {@link TmlHttpJson} implementation. If
     *                {@code tmlStr} is null, then default value is empty.
     */
    public TmlHttpJson(Pattern pattern, Template tml) {
        this(pattern, tml != null ? tml.create() : "");
    }

    /**
     * Ctor.
     *
     * @param tmlStr Template as a String. To create it's recommended to use {@link TmlHttpJson} implementation. If
     *               {@code tmlStr} is null, then default value is empty.
     */
    public TmlHttpJson(String tmlStr) {
        this(null, tmlStr);
    }

    /**
     * Ctor.
     *
     * @param tml Template as a String. To create it's recommended to use {@link TmlHttpJson} implementation. If
     *            {@code tml} is null, then default value for {@link #tmlStr} is empty.
     */
    public TmlHttpJson(Template tml) {
        this(tml != null ? tml.create() : "");
    }

    /**
     * Creating template {@code {"http":{"endpoint":"endpoint_value","method":"method_value","status":"status_value"}}},
     * where {@code endpoint_value} - endpoint value from {@link #tmlStr}, {@code method_value} - method value from
     * {@link #tmlStr}, {@code status_value} - status value from {@link #tmlStr}.
     * Example:
     * <pre> {@code
     * String tml = new TmlHttpJson(new TmlHttp(HttpMethod.GET, "/users/1", HttpStatus.OK)).create();
     * // This code creating template:
     * // {"http":{"endpoint":"/users/1","method":"GET","status":"200 OK"}}
     * } </pre>
     * If {@link #pattern} is null, then {@link #pattern} is generated like this:
     * <pre>
     *     1. Get {@link HttpMethod#names} and added in Method group. Example:{@code [HTTP <METHOD:(GET|PUT|POST|DELETE)>}.
     *     2. Added in pattern endpoint group:{@code <ENDPOINT:((?:/?[A-Za-z0-9$.+!*'(){},~:;=@#%_\-]*)+)>}
     *     3. Added in pattern status group:{@code <STATUS:([\d]+ [A-Za-z\- ]+)>]}.
     *     The resulting pattern is something like this ("something like this" because all HTTP methods for readability
     *     are not listed):
     * {@code [HTTP <METHOD:(GET|PUT|POST|DELETE)> <ENDPOINT:((?:/?[A-Za-z0-9$.+!*'(){},~:;=@#%_\-]*)+)> <STATUS:([\d]+ [A-Za-z\- ]+)>]}
     * </pre>
     *
     * If {@link #tmlStr} does not match {@link #pattern}, then method return
     * {@code {"http":{"endpoint":null,"method":null,"status":null}}}.
     *
     * @return template as a JSON String.
     */
    @Override
    public String create() {
        Matcher matcher;

        if (pattern != null) {
            matcher = pattern.matcher(tmlStr);
        } else {
            StringBuilder sb = new StringBuilder("\\[HTTP <METHOD:(");
            HttpMethod.names.forEach(methodName -> sb.append(methodName).append("|"));
            sb.setLength(sb.length() - 1);
            sb.append(")> ")
                    .append("<ENDPOINT:((?:/?[A-Za-z0-9$.+!*'(){},~:;=@#%_\\-]*)+)> ")
                    .append("<STATUS:([\\d]+ [A-Za-z\\- ]+)>]");
            matcher = Pattern.compile(sb.toString()).matcher(tmlStr);
        }

        JSONObject jObj = new JSONObject();
        JSONObject jHttpObj = new JSONObject();
        if (matcher.find()) {
            jHttpObj.put("method", matcher.group(1));
            jHttpObj.put("endpoint", matcher.group(2));
            jHttpObj.put("status", matcher.group(3));
        } else {
            jHttpObj.put("method", JSONObject.NULL);
            jHttpObj.put("endpoint", JSONObject.NULL);
            jHttpObj.put("status", JSONObject.NULL);
        }

        jObj.put("http", jHttpObj);

        return jObj.toString();
    }

    /**
     * Name of class template.
     * @return {@code "http"}.
     */
    @Override
    public String name() {
        return "http";
    }
}
