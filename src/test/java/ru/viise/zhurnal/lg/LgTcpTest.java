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

package ru.viise.zhurnal.lg;

import org.testng.annotations.Ignore;
import ru.viise.zhurnal.tml.TmlInfo;
import ru.viise.zhurnal.tml.elk.TmlElkStd;
import org.json.JSONObject;
import org.testng.annotations.Test;
import ru.viise.zhurnal.tml.TmlInfo;
import ru.viise.zhurnal.tml.elk.TmlElkStd;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import static org.testng.Assert.assertNotNull;

public class LgTcpTest {

    private Thread startServer() {
        Thread thr = new Thread(() -> {
            try (
                    ServerSocket serverSock = new ServerSocket(10000);
                    Socket clientSock = serverSock.accept()
            ) {
                try (
                        BufferedReader br = new BufferedReader(
                                new InputStreamReader(clientSock.getInputStream())
                        )
                ) {
                    String log = br.readLine();
                    JSONObject jObj = new JSONObject(log);
                    assertNotNull(jObj.get("timestamp"));
                    assertNotNull(jObj.get("level"));
                    assertNotNull(jObj.get("logger_name"));
                    assertNotNull(jObj.get("thread_name"));
                    assertNotNull(jObj.get("message"));
                }
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

    @Ignore
    @Test
    public void print() {
        Thread serverThr = startServer();

        new LgTcp(
                "localhost",
                10000
        ).print(
                new TmlElkStd(
                        new TmlInfo("Hello, {}!", "log")
                )
        );

        stopServer(serverThr);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void print_serverNotFound() {
        new LgTcp(
                "localhost",
                8888
        ).print(
                new TmlElkStd(
                        new TmlInfo("Hello, {}!", "log")
                )
        );
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void print_wrongPort() {
        new LgTcp(
                "localhost",
                99999
        ).print(
                new TmlElkStd(
                        new TmlInfo("Hello, {}!", "log")
                )
        );
    }
}
