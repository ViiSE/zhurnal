package com.github.viise.zhurnal.tml;

import com.github.viise.zhurnal.HttpMethod;
import com.github.viise.zhurnal.HttpStatus;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class TmlHttpTest {

    @Test
    public void create() {
        String actual = new TmlHttp(HttpMethod.GET, "/cats/1", HttpStatus.OK).create();
        assertEquals(actual, "[HTTP <METHOD:GET> <ENDPOINT:/cats/1> <STATUS:200 OK>]");
    }

    @Test
    public void create_nullMethod() {
        String actual = new TmlHttp(null, "/cats/1", HttpStatus.OK).create();
        assertEquals(actual, "[HTTP <METHOD:null> <ENDPOINT:/cats/1> <STATUS:200 OK>]");
    }

    @Test
    public void create_nullEndpoint() {
        String actual = new TmlHttp(HttpMethod.GET, null, HttpStatus.OK).create();
        assertEquals(actual, "[HTTP <METHOD:GET> <ENDPOINT:null> <STATUS:200 OK>]");
    }

    @Test
    public void create_nullStatus() {
        String actual = new TmlHttp(HttpMethod.GET, "/cats/1", null).create();
        assertEquals(actual, "[HTTP <METHOD:GET> <ENDPOINT:/cats/1> <STATUS:null>]");
    }
}
