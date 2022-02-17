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

public final class TmlThread implements Template {

    private final Long id;
    private final String name;
    private final Boolean isAlive;
    private final Boolean isInterrupted;

    public TmlThread(Long id, String name, Boolean isAlive, Boolean isInterrupted) {
        this.id = id;
        this.name = name;
        this.isAlive = isAlive;
        this.isInterrupted = isInterrupted;
    }

    public TmlThread(Thread thd) {
        this(
                thd != null ? thd.getId() : null,
                thd != null ? thd.getName() : null,
                thd != null ? thd.isAlive() : null,
                thd != null ? thd.isInterrupted() : null
        );

    }

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
}
