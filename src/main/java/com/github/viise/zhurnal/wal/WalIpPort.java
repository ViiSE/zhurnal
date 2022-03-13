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

package com.github.viise.zhurnal.wal;

import com.github.viise.stena.ProtectException;
import com.github.viise.stena.Wall;
import com.github.viise.stena.wal.WalPositiveInt;

/**
 * IP Port protection.
 */
public final class WalIpPort implements Wall<Integer> {

    /**
     * Protect IP Port.
     * @param name Port name
     * @param port Port value
     * @throws ProtectException {@code port} is less than zero or more than 65535
     */
    @Override
    public void protect(String name, Integer port) throws ProtectException {
        String pName = name == null ? "ipPort" : name;
        new WalPositiveInt().protect(pName, port);
        if (port > 65535) {
            throw new ProtectException(String.format("%s could not be more than 65535", pName));
        }
    }

    /**
     * Protect IP Port. {@code name} is "ipPort".
     * @param port Port value
     * @throws ProtectException {@code port} is less than zero or more than 65535
     */
    @Override
    public void protect(Integer port) throws ProtectException {
        protect("ipPort", port);
    }
}
