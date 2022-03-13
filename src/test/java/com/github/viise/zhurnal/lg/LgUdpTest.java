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

package com.github.viise.zhurnal.lg;

import com.github.viise.zhurnal.tml.TmlInfo;
import com.github.viise.zhurnal.tml.elk.TmlElkStd;
import org.json.JSONObject;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

import static org.testng.Assert.assertNotNull;

public class LgUdpTest {

    private Thread startServer() {
        Thread thr = new Thread(() -> {
            try (
                    DatagramSocket sock = new DatagramSocket(8888);
            ) {
                DatagramPacket packet = new DatagramPacket(new byte[1024], 1024);
                sock.receive(packet);
                String log = new String(packet.getData(), 0, packet.getLength());
                JSONObject jObj = new JSONObject(log);
                assertNotNull(jObj.get("timestamp"));
                assertNotNull(jObj.get("level"));
                assertNotNull(jObj.get("logger_name"));
                assertNotNull(jObj.get("thread_name"));
                assertNotNull(jObj.get("message"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        thr.start();

        return thr;
    }

    private void stopServer(Thread thr) {
        if (thr.isAlive()) {
            try {
                thr.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void print() {
        Thread serverThr = startServer();

        new LgUdp(
                "localhost",
                8888
        ).print(
                new TmlElkStd(
                        new TmlInfo("Hello, {}!", "log")
                )
        );

        stopServer(serverThr);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void print_serverNotFound() {
        new LgUdp(
                "bla",
                8888
        ).print(
                new TmlElkStd(
                        new TmlInfo("Hello, {}!", "log")
                )
        );
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void print_wrongPort() {
        new LgUdp(
                "localhost",
                99999
        ).print(
                new TmlElkStd(
                        new TmlInfo("Hello, {}!", "log")
                )
        );
    }
}
