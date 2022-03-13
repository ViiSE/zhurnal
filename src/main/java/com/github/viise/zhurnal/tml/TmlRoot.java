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

import java.util.Arrays;
import java.util.List;

/**
 * Root template.
 * For {@code children} templates recommended use implementation of {@link Template} interface - {@link TmlChild}.
 */
public final class TmlRoot implements TemplateNamed {

    private final String name;
    private final List<Template> children;

    /**
     * Ctor.
     * @param name template name.
     * @param children children templates.
     */
    public TmlRoot(String name, Template... children) {
        this(name, Arrays.asList(children));
    }

    /**
     * Ctor.
     * @param name template name.
     * @param children children templates.
     */
    public TmlRoot(String name, List<Template> children) {
        this.name = name;
        this.children = children;
    }

    /**
     * Creating template (as usually) {@code "[ROOT <children_name:children_value>]"}, where {@code children_name} -
     * children name in uppercase, {@code children_value} - children value from {@code params}.
     * Example:
     * <pre> {@code
     * String tml = new TmlRoot("root", new TmlChild("child_name", "child_value")).create();
     * // This code creating template:
     * // [ROOT <CHILD_NAME:child_value>]
     * } </pre>
     * @return template as a String.
     */
    @Override
    public String create() {
        StringBuilder sb = new StringBuilder("[").append(new TmlBasic(name).create().toUpperCase()).append(" ");
        if (children != null) {
            children.forEach(child -> sb.append(child.create()).append(" "));
        }
        sb.setLength(sb.length() - 1);
        sb.append("]");

        return sb.toString();
    }

    /**
     * Name of template.
     * @return {@code name} in uppercase.
     */
    @Override
    public String name() {
        return name.toUpperCase();
    }
}
