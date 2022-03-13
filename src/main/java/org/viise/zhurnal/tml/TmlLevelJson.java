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

import org.viise.zhurnal.Level;
import org.viise.zhurnal.Template;
import org.viise.zhurnal.TemplateNamed;
import org.json.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Level template as a JSON.
 */
public final class TmlLevelJson implements TemplateNamed {

    private final Pattern pattern;
    private final String tmlStr;

    /**
     * Ctor.
     *
     * @param pattern Pattern for parsing {@link #tmlStr}. {@code pattern} must not be null.
     * @param tmlStr  Template as a String. To create it's recommended to use {@link TmlLevel} implementation. If
     *                {@code tmlStr} is null, then default value is empty.
     */
    public TmlLevelJson(Pattern pattern, String tmlStr) {
        this.pattern = pattern;
        this.tmlStr = tmlStr != null ? tmlStr : "";
    }

    /**
     * Ctor.
     *
     * @param pattern Pattern for parsing {@link #tmlStr}. {@code pattern} must not be null.
     * @param tml     Template implementation. It's recommended to use {@link TmlLevel} implementation. If
     *                {@code tmlStr} is null, then default value is empty.
     */
    public TmlLevelJson(Pattern pattern, Template tml) {
        this(pattern, tml != null ? tml.create() : "");
    }

    /**
     * Ctor.
     *
     * @param tmlStr Template as a String. To create it's recommended to use {@link TmlLevel} implementation. If
     *               {@code tmlStr} is null, then default value is empty.
     */
    public TmlLevelJson(String tmlStr) {
        this(null, tmlStr);
    }

    /**
     * Ctor.
     *
     * @param tml Template as a String. To create it's recommended to use {@link TmlLevel} implementation. If
     *            {@code tml} is null, then default value for {@link #tmlStr} is empty.
     */
    public TmlLevelJson(Template tml) {
        this(tml != null ? tml.create() : "");
    }

    /**
     * Creating template {@code {"level":"level_value"}}, where {@code level_value} - level value from {@link #tmlStr}.
     * Example:
     * <pre> {@code
     * String tml = new TmlLevelJson(new TmlLevel(Level.INFO)).create();
     * // This code creating template:
     * // {"level":"INFO"}
     * } </pre>
     * If {@link #pattern} is null, then {@link #pattern} is generated like this:
     * <pre>
     *     1. Get {@link Level#names} and added in Level group. Example:{@code [LEVEL (INFO|WARN|ERROR|TRACE|DEBUG)]}.
     *     The resulting pattern is:
     * {@code [LEVEL (INFO|WARN|ERROR|TRACE|DEBUG)]}
     * </pre>
     *
     * If {@link #tmlStr} does not match {@link #pattern}, then method return {@code {"level":null}}.
     *
     * @return template as a JSON String.
     */
    @Override
    public String create() {
        Matcher matcher;
        if (pattern != null) {
            matcher = pattern.matcher(tmlStr);
        } else {
            StringBuilder sb = new StringBuilder("\\[LEVEL (");
            Level.names.forEach(lvlName -> sb.append(lvlName).append("|"));
            sb.setLength(sb.length() - 1);
            sb.append(")]");
            Pattern pattern = Pattern.compile(sb.toString());

            matcher = pattern.matcher(tmlStr);
        }
        JSONObject jObj = new JSONObject();
        if (matcher.find()) {
            jObj.put("level", matcher.group(1));
        } else {
            jObj.put("level", JSONObject.NULL);
        }

        return jObj.toString();
    }

    /**
     * Name of class template.
     * @return {@code "level"}.
     */
    @Override
    public String name() {
        return "level";
    }
}
