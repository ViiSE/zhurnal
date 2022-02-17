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

package com.github.viise.zhurnal;

/**
 * Template interface.
 * Implementations of the {@code Log} interface must use {@code Templates} to ensure that logs conform to the same
 * standard. Logs of one standard are easier to parse using Grok or Regex and so on.
 *
 * For custom {@code Log} implementation it's necessary to check constructor parameters for null. For this you can use
 * implementation of {@code Template} interface - {@link com.github.viise.zhurnal.tml.TmlBasic}.
 *
 * @param <T> Some returned value. Log implementations that writing log externally (for example, in the console output
 *           or to a file) usually returning {@code Void}. Log implementations that writing log internally usually
 *           returning {@code String}.
 * @see com.github.viise.zhurnal.Template
 * @see com.github.viise.zhurnal.tml.TmlBasic
 * @see <a href="https://www.elastic.co/guide/en/logstash/current/plugins-filters-grok.html">Grok</a>
 */
public interface Template {
    String create();
}
