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

package ru.viise.zhurnal;

/**
 * Log interface.
 * Implementations of the {@code Log} interface must use {@code Templates} to ensure that logs conform to the same
 * standard. Logs of one standard are easier to parse using Grok or Regex and so on.
 *
 * @see Template
 * @see <a href="https://www.elastic.co/guide/en/logstash/current/plugins-filters-grok.html">Grok</a>
 */
public interface Log<T extends Template> {

    /**
     * Printing log.
     * For custom {@code Template} implementation it's necessary to check constructor parameters for null.
     */
    void print(T tml);
}
