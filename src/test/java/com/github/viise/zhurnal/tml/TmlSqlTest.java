package com.github.viise.zhurnal.tml;

import org.testng.annotations.Test;

import java.sql.SQLException;

import static org.testng.Assert.assertEquals;

public class TmlSqlTest {

    @Test
    public void create() {
        String actual = new TmlSql(-100, "FAILED", "SELECT failed").create();
        assertEquals(actual, "[SQL <ERROR_CODE:-100> <SQL_STATE:FAILED> <MESSAGE:SELECT failed>]");
    }

    @Test
    public void create_fromException() {
        SQLException sqlEx = new SQLException("SELECT failed", "FAILED", -100, new Throwable("SELECT failed"));
        String actual = new TmlSql(sqlEx).create();
        assertEquals(actual, "[SQL <ERROR_CODE:-100> <SQL_STATE:FAILED> <MESSAGE:SELECT failed>]");
    }

    @Test
    public void create_allNull() {
        String actual = new TmlSql(null, null, null).create();
        assertEquals(actual, "[SQL <ERROR_CODE:null> <SQL_STATE:null> <MESSAGE:null>]");
    }
}
