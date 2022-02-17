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

import java.util.Arrays;
import java.util.List;

public final class TmlRoot implements Template {

    private final String name;
    private final List<Template> children;

    public TmlRoot(String name, Template... children) {
        this(name, Arrays.asList(children));
    }

    public TmlRoot(String name, List<Template> children) {
        this.name = name;
        this.children = children;
    }

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
}
