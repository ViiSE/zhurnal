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

import ru.viise.zhurnal.HttpMethod;
import ru.viise.zhurnal.HttpStatus;
import ru.viise.zhurnal.TemplateNamed;
import ru.viise.zhurnal.HttpStatus;
import ru.viise.zhurnal.TemplateNamed;

/**
 * HTTP template.
 */
public final class TmlHttp implements TemplateNamed {

    private final HttpMethod method;
    private final String endpoint;
    private final HttpStatus httpStatus;

    /**
     * Ctor.
     * @param method {@link HttpMethod}.
     * @param endpoint HTTP endpoint.
     * @param httpStatus {@link HttpStatus}.
     */
    public TmlHttp(HttpMethod method, String endpoint, HttpStatus httpStatus) {
        this.method = method;
        this.endpoint = endpoint;
        this.httpStatus = httpStatus;
    }

    /**
     * Creating template {@code "[HTTP <METHOD:method> <ENDPOINT:endpoint> <STATUS:status>]"}, where {@code method} -
     * {@link HttpMethod} name, {@code endpoint} - HTTP endpoint, {@code status} - {@link HttpStatus} name.
     * Example:
     * <pre> {@code
     * String tml = new TmlHttp(HttpMethod.GET, "/users/1", HttpStatus.OK).create();
     * // This code creating template:
     * // [HTTP <METHOD:GET> <ENDPOINT:/users/1> <STATUS:200 OK>]
     * } </pre>
     * @return template as a String.
     */
    @Override
    public String create() {
        return new TmlRoot(
                "http",
                new TmlChild(
                        "method",
                        method != null ? method.name() : "null"
                ),
                new TmlChild(
                        "endpoint",
                        endpoint
                ),
                new TmlChild(
                        "status",
                        httpStatus != null ? httpStatus.toString() : "null"
                )
        ).create();
    }

    /**
     * Name of HTTP template.
     * @return {@code "HTTP"}.
     */
    @Override
    public String name() {
        return "HTTP";
    }
}
