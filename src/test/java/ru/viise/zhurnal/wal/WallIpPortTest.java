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

package ru.viise.zhurnal.wal;

import com.github.viise.stena.ProtectException;
import org.testng.annotations.Test;

public class WallIpPortTest {

    @Test
    public void protect_1() throws ProtectException {
        new WalIpPort().protect(6666);
    }

    @Test
    public void protect_2() throws ProtectException {
        new WalIpPort().protect("My IP Port", 6666);
    }

    @Test
    public void protect_nameIsNull() throws ProtectException {
        new WalIpPort().protect(null, 6666);
    }

    @Test(expectedExceptions = ProtectException.class)
    public void protect_ipIsNull() throws ProtectException {
        new WalIpPort().protect(null);
    }

    @Test(expectedExceptions = ProtectException.class)
    public void protect_portIsLessThan_0() throws ProtectException {
        new WalIpPort().protect(-5);
    }

    @Test(expectedExceptions = ProtectException.class)
    public void protect_portIsMoreThan_65535() throws ProtectException {
        new WalIpPort().protect(70000);
    }
}
