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
 * Class template as a JSON.
 */
public final class TmlClassJson implements TemplateNamed {

    private final Pattern pattern;
    private final String tmlStr;

    /**
     * Ctor.
     *
     * @param pattern Pattern for parsing {@link #tmlStr}. {@code pattern} must not be null.
     * @param tmlStr  Template as a String. To create it's recommended to use {@link TmlClass} implementation. If
     *                {@code tmlStr} is null, then default value is empty.
     */
    public TmlClassJson(Pattern pattern, String tmlStr) {
        this.pattern = pattern;
        this.tmlStr = tmlStr != null ? tmlStr : "";
    }

    /**
     * Ctor.
     *
     * @param pattern Pattern for parsing {@link #tmlStr}. {@code pattern} must not be null.
     * @param tml     Template implementation. It's recommended to use {@link TmlClass} implementation. If
     *                {@code tmlStr} is null, then default value is empty.
     */
    public TmlClassJson(Pattern pattern, Template tml) {
        this(pattern, tml != null ? tml.create() : "");
    }

    /**
     * Ctor. {@link #pattern} value is {@code "\[CLASS ((?:[a-zA-Z0-9-]+\.)+[A-Za-z0-9$]+|[A-Za-z0-9$]+)]"}.
     *
     * @param tmlStr Template as a String. To create it's recommended to use {@link TmlClass} implementation. If
     *               {@code tmlStr} is null, then default value is empty.
     */
    public TmlClassJson(String tmlStr) {
        this(Pattern.compile("\\[CLASS ((?:[a-zA-Z0-9-]+\\.)+[A-Za-z0-9$]+|[A-Za-z0-9$]+)]"), tmlStr);
    }

    /**
     * Ctor. {@link #pattern} value is {@code "\[CLASS ((?:[a-zA-Z0-9-]+\.)+[A-Za-z0-9$]+|[A-Za-z0-9$]+)]"}.
     *
     * @param tml Template as a String. To create it's recommended to use {@link TmlClass} implementation. If
     *            {@code tml} is null, then default value for {@link #tmlStr} is empty.
     */
    public TmlClassJson(Template tml) {
        this(tml != null ? tml.create() : "");
    }

    /**
     * Creating template {@code {"class":"className"}}, where className - class name from {@link #tmlStr}.
     * Example:
     * <pre> {@code
     * String tml = new TmlClassJson(new TmlClass(Main.class)).create();
     * // This code creating template:
     * // {"class":"org.viise.zhurnal.tml.Main"}
     * } </pre>
     * @return template as a JSON String.
     */
    @Override
    public String create() {
        JSONObject jObj = new JSONObject();
        Matcher matcher = pattern.matcher(tmlStr);
        if (matcher.find()) {
            jObj.put("class", matcher.group(1));
        } else {
            jObj.put("class", JSONObject.NULL);
        }

        return jObj.toString();
    }

    /**
     * Name of class template.
     * @return {@code "class"}.
     */
    @Override
    public String name() {
        return "class";
    }
}
