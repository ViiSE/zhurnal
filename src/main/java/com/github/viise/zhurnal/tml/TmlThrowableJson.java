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

import com.github.viise.zhurnal.Template;
import com.github.viise.zhurnal.TemplateNamed;
import org.json.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Throwable template as a JSON.
 */
public final class TmlThrowableJson implements TemplateNamed {

    private final Pattern pattern;
    private final String tmlStr;

    /**
     * Ctor.
     *
     * @param pattern Pattern for parsing {@link #tmlStr}. {@code pattern} must not be null.
     * @param tmlStr  Template as a String. To create it's recommended to use {@link TmlThrowable} implementation.
     *                If {@code tmlStr} is null, then default value is empty.
     */
    public TmlThrowableJson(Pattern pattern, String tmlStr) {
        this.pattern = pattern;
        this.tmlStr = tmlStr != null ? tmlStr : "";
    }

    /**
     * Ctor. {@link #pattern} value is {@code "\[THROWABLE <MESSAGE:(.*)> <STACK_TRACE:((.*\R.*)+)>]"}.
     *
     * @param tmlStr Template as a String. To create it's recommended to use {@link TmlThrowable} implementation. If
     *               {@code tmlStr} is null, then default value is empty.
     */
    public TmlThrowableJson(String tmlStr) {
        this(Pattern.compile("\\[THROWABLE <MESSAGE:(.*)> <STACK_TRACE:((.*\\R.*)+)>]"), tmlStr);
    }

    /**
     * Ctor.
     *
     * @param pattern Pattern for parsing {@link #tmlStr}. {@code pattern} must not be null.
     * @param tml     Template implementation. It's recommended to use {@link TmlThrowable} implementation. If
     *                {@code tmlStr} is null, then default value is empty.
     */
    public TmlThrowableJson(Pattern pattern, Template tml) {
        this(pattern, tml != null ? tml.create() : "");
    }

    /**
     * Ctor. {@link #pattern} value is {@code "\[THROWABLE <MESSAGE:(.*)> <STACK_TRACE:((.*\R.*)+)>]"}.
     *
     * @param tml Template as a String. To create it's recommended to use {@link TmlThrowable} implementation. If
     *            {@code tml} is null, then default value for {@link #tmlStr} is empty.
     */
    public TmlThrowableJson(Template tml) {
        this(tml != null ? tml.create() : "");
    }

    /**
     * Creating template {@code {"throwable":{"stack_trace":stack_trace_value,"message":message_value}}},
     * where {@code stack_trace_value} - stack trace value from {@link #tmlStr},{@code message_value} - message value
     * from {@link #tmlStr}.
     * Example:
     * <pre> {@code
     * String tml = new TmlThrowableJson(new TmlThrowable(new Throwable())).create();
     * // This code creating template:
     * // {"throwable":{"stack_trace":"java.lang.Throwable\n\tat com.github.viise.zhurnal.tml...","message":"null"}}
     * } </pre>
     *
     * If {@link #tmlStr} does not match {@link #pattern}, then method return
     * {@code {"throwable":{"stack_trace":null,"message":null}}}.
     *
     * @return template as a JSON String.
     */
    @Override
    public String create() {
        Matcher matcher = pattern.matcher(tmlStr);
        JSONObject jObj = new JSONObject();
        JSONObject jThrowableObj = new JSONObject();
        if (matcher.find()) {
            jThrowableObj.put("message", matcher.group(1));
            jThrowableObj.put("stack_trace", matcher.group(2));
        } else {
            jThrowableObj.put("message", JSONObject.NULL);
            jThrowableObj.put("stack_trace", JSONObject.NULL);
        }
        jObj.put("throwable", jThrowableObj);

        return jObj.toString();
    }

    /**
     * Name of class template.
     * @return {@code "throwable"}.
     */
    @Override
    public String name() {
        return "throwable";
    }
}
