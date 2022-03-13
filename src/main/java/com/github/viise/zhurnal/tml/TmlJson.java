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

import java.util.Arrays;
import java.util.List;

public final class TmlJson implements Template {

    private final List<TemplateNamed> tmls;

    public TmlJson(List<TemplateNamed> tmls) {
        this.tmls = tmls;
    }

    public TmlJson(TemplateNamed... tmls) {
        this(tmls != null ? Arrays.asList(tmls) : null);
    }

    @Override
    public String create() {
        if (tmls == null || tmls.isEmpty()) {
            return new JSONObject().toString();
        } else {
            JSONObject jObj = new JSONObject();
            tmls.forEach(tml -> jObj.put(tml.name(), new JSONObject(tml.create()).get(tml.name())));
            return jObj.toString();
        }
    }
}
