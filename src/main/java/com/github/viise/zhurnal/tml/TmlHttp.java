package com.github.viise.zhurnal.tml;

import com.github.viise.zhurnal.HttpMethod;
import com.github.viise.zhurnal.HttpStatus;
import com.github.viise.zhurnal.Template;

public final class TmlHttp implements Template {

    private final HttpMethod method;
    private final String endpoint;
    private final HttpStatus httpStatus;

    public TmlHttp(HttpMethod method, String endpoint, HttpStatus httpStatus) {
        this.method = method;
        this.endpoint = endpoint;
        this.httpStatus = httpStatus;
    }

    @Override
    public String create() {
        return new TmlRoot(
                "http",
                new TmlChild(
                        "method",
                        method != null ? method.name() : "null"
                ),
                new TmlChild(
                        "endpoint",
                        endpoint
                ),
                new TmlChild(
                        "status",
                        httpStatus != null ? httpStatus.toString() : "null"
                )
        ).create();
    }
}
