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

import java.sql.SQLException;

public final class TmlSql implements Template {

    private final Integer errorCode;
    private final String sqlState;
    private final String message;

    public TmlSql(Integer errorCode, String sqlState, String message) {
        this.errorCode = errorCode;
        this.sqlState = sqlState;
        this.message = message;
    }

    public TmlSql(SQLException slqEx) {
        this(slqEx.getErrorCode(), slqEx.getSQLState(), slqEx.getMessage());
    }

    @Override
    public String create() {
        return new TmlRoot(
                "sql",
                new TmlChild(
                        "error_code",
                        errorCode
                ),
                new TmlChild(
                        "sql_state",
                        sqlState
                ),
                new TmlChild(
                        "message",
                        message
                )
        ).create();
    }
}
