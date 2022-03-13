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

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * UDP Log. This implementation sends log over UDP.
 */
public final class LgUdp implements Log<Template> {

    private final String hostname;
    private final Integer port;

    /**
     * Ctor.
     * @param hostname Hostname of UDP server where log will be sent. Hostname must be available and not null.
     * @param port IP port of UDP server where log will be sent. IP port must be valid (0 to 65535) and not null.
     */
    public LgUdp(String hostname, Integer port) {
        this.hostname = hostname;
        this.port = port;
    }

    /**
     * Printing template in UDP socket. If {@code port} is not valid, {@link RuntimeException} will be thrown.
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

        try (DatagramSocket sock = new DatagramSocket()) {
            byte[] recordBytes = tml.create().getBytes();
            DatagramPacket packet = new DatagramPacket(
                    recordBytes,
                    recordBytes.length,
                    InetAddress.getByName(hostname),
                    port
            );
            sock.send(packet);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
