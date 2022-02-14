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
