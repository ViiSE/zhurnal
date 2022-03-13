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

package org.viise.zhurnal.lg;

import com.github.viise.stena.ProtectException;
import org.viise.zhurnal.Log;
import org.viise.zhurnal.Template;
import org.viise.zhurnal.wal.WalIpPort;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * TCP Log. This implementation sends log over TCP.
 */
public final class LgTcp implements Log<Template> {

    private final String hostname;
    private final Integer port;

    /**
     * Ctor.
     * @param hostname Hostname of TCP server where log will be sent. Hostname must be available and not null.
     * @param port IP port of TCP server where log will be sent. IP port must be valid (0 to 65535) and not null.
     */
    public LgTcp(String hostname, Integer port) {
        this.hostname = hostname;
        this.port = port;
    }

    /**
     * Printing template in TCP socket. If {@code port} is not valid, {@link RuntimeException} will be thrown.
     * If {@code hostname} is not available, {@link RuntimeException} will be thrown.
     * @param tml Printing Template. {@code tml} must not be null.
     */
    @Override
    public void print(Template tml) {
        try {
            new WalIpPort().protect(port);
        } catch (ProtectException e) {
            throw new RuntimeException(e);
        }

        try (
                Socket sock = new Socket(hostname, port);
                DataOutputStream dos = new DataOutputStream(
                        new BufferedOutputStream(sock.getOutputStream())
                )
        ) {
            dos.writeBytes(tml.create());
            dos.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
