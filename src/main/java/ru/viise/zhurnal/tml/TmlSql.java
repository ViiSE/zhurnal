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

package ru.viise.zhurnal.tml;

import ru.viise.zhurnal.TemplateNamed;
import ru.viise.zhurnal.TemplateNamed;

import java.sql.SQLException;

/**
 * SQL template.
 */
public final class TmlSql implements TemplateNamed {

    private final Integer errorCode;
    private final String sqlState;
    private final String message;

    /**
     * Ctor.
     * @param errorCode SQL error code.
     * @param sqlState SQL state.
     * @param message SQL message.
     */
    public TmlSql(Integer errorCode, String sqlState, String message) {
        this.errorCode = errorCode;
        this.sqlState = sqlState;
        this.message = message;
    }

    /**
     * Ctor.
     * @param sqlEx SQLException.
     */
    public TmlSql(SQLException sqlEx) {
        this(sqlEx.getErrorCode(), sqlEx.getSQLState(), sqlEx.getMessage());
    }

    /**
     * Creating template {@code "[SQL <ERROR_CODE:err_code_val> <SQL_STATE:sql_state_val> <MESSAGE:msg>]"}, where
     * {@code err_code_val} - error code value, {@code sql_state_val} - SQL state value, {@code message} - SQL message.
     * Example:
     * <pre> {@code
     * String tml = new TmlSql(100, "OK", "Okay").create();
     * // This code creating template:
     * // [SQL <ERROR_CODE:100> <SQL_STATE:OK> <MESSAGE:Okay>]
     * } </pre>
     * @return template as a String.
     */
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

    /**
     * Name of SQL template.
     * @return {@code "SQL"}.
     */
    @Override
    public String name() {
        return "SQL";
    }
}
