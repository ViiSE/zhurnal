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

import ru.viise.zhurnal.TemplateNamed;
import ru.viise.zhurnal.TemplateNamed;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Message template. This template supports parameter substitution via {@code {}}. For example:
 * <pre> {@code
 * String tmlMsg = new TmlMsg("Hello, {}!", "log").create();
 * // This code creating template:
 * // [MESSAGE Hello, log!]
 * } </pre>
 * If {@code {}} in {@code msg} more than {@code params} size, then {@code msg} will contain {@code {}}. For example:
 * <pre> {@code
 * String tmlMsg = new TmlMsg("Hello, {}! Today is {}.", "log").create();
 * // This code creating template:
 * // [MESSAGE Hello, log! Today is {}.]
 * } </pre>
 * If {@code {}} in {@code msg} less than {@code params}, then not all {@code params} will be included in {@code msg}.
 * For example:
 * <pre> {@code
 * String tmlMsg - new TmlMsg("Hello, {}!", "log", "today").create();
 * // This code creating template:
 * // [MESSAGE Hello, log!]
 * } </pre>
 */
public final class TmlMsg implements TemplateNamed {

    private final String msg;
    private final List<Object> params;

    /**
     * Ctor.
     * @param msg Message.
     */
    public TmlMsg(String msg) {
        this(msg, new ArrayList<>());
    }

    /**
     * Ctor.
     * @param msg Message.
     * @param params Params in message.
     */
    public TmlMsg(String msg, Object... params) {
        this.msg = msg;
        this.params = Arrays.asList(params);
    }

    /**
     * Ctor.
     * @param msg Message.
     * @param params Params in message.
     */
    public TmlMsg(String msg, List<Object> params) {
        this.msg = msg;
        this.params = params;
    }

    /**
     * Creating template {@code "[MESSAGE message]"}, where {@code message} - {@link #msg}.
     * Example:
     * <pre> {@code
     * String tml = new TmlMsg("Hello, {}!", "log").create();
     * // This code creating template:
     * // [MESSAGE Hello, log!]
     * } </pre>
     * @return template as a String.
     */
    @Override
    public String create() {
        if (!(params == null || params.isEmpty())) {
            if (msg != null && msg.contains("{}")) {
                String resMsg = msg;
                for (Object param: params) {
                    resMsg = resMsg.replaceFirst("\\{}", new TmlBasic(param).create());

                    if (!resMsg.contains("{}")) {
                        break;
                    }
                }

                return create(resMsg);
            }
        }

        return create(msg);
    }

    private String create(String msg) {
        return new TmlRoot(
                "MESSAGE",
                new TmlBasic(msg)
        ).create();
    }

    /**
     * Name of message template.
     * @return {@code "MESSAGE"}.
     */
    @Override
    public String name() {
        return "MESSAGE";
    }
}
