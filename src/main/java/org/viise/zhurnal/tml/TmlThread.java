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

import org.viise.zhurnal.TemplateNamed;

/**
 * Thread template.
 */
public final class TmlThread implements TemplateNamed {

    private final Long id;
    private final String name;
    private final Boolean isAlive;
    private final Boolean isInterrupted;

    /**
     * Ctor.
     * @param id Thread ID.
     * @param name Thread name.
     * @param isAlive Thread isAlive.
     * @param isInterrupted Thread isInterrupted.
     */
    public TmlThread(Long id, String name, Boolean isAlive, Boolean isInterrupted) {
        this.id = id;
        this.name = name;
        this.isAlive = isAlive;
        this.isInterrupted = isInterrupted;
    }

    /**
     * Ctor.
     * @param thd Thread.
     */
    public TmlThread(Thread thd) {
        this(
                thd != null ? thd.getId() : null,
                thd != null ? thd.getName() : null,
                thd != null ? thd.isAlive() : null,
                thd != null ? thd.isInterrupted() : null
        );

    }

    /**
     * Ctor.
     * Take the current thread.
     */
    public TmlThread() {
        this(Thread.currentThread());
    }

    /**
     * Creating template
     * {@code "[THREAD <ID:thd_id> <NAME:thd_name> <IS_ALIVE:thd_is_alive> <IS_INTERRUPTED:thd_is_interrupted>]"}, where
     * {@code thd_id} - {@link #id}, {@code thd_name} - {@link #name}, {@code thd_is_alive} - {@link #isAlive},
     * {@code thd_is_interrupted} - {@link #isInterrupted}.
     * Example:
     * <pre> {@code
     * String tml = new TmlThread().create();
     * // This code creating template:
     * // [THREAD <ID:1> <NAME:main> <IS_ALIVE:true> <IS_INTERRUPTED:false>]
     * } </pre>
     * @return template as a String.
     */
    @Override
    public String create() {
        return new TmlRoot(
                "thread",
                new TmlChild(
                        "id",
                        id
                ),
                new TmlChild(
                        "name",
                        name
                ),
                new TmlChild(
                        "is_alive",
                        isAlive
                ),
                new TmlChild(
                        "is_interrupted",
                        isInterrupted
                )
        ).create();
    }

    /**
     * Name of thread template.
     * @return {@code "THREAD"}.
     */
    @Override
    public String name() {
        return "THREAD";
    }
}
