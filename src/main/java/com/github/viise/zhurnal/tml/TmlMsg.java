package com.github.viise.zhurnal.tml;

import com.github.viise.zhurnal.Template;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class TmlMsg implements Template {

    private final String msg;
    private final List<Object> params;

    public TmlMsg(String name) {
        this(name, new ArrayList<>());
    }

    public TmlMsg(String msg, Object... params) {
        this.msg = msg;
        this.params = Arrays.asList(params);
    }

    public TmlMsg(String name, List<Object> params) {
        this.msg = name;
        this.params = params;
    }

    @Override
    public String create() {
        if (!(params == null || params.isEmpty())) {
            if (msg != null && msg.contains("{}")) {
                String resMsg = msg;
                for (Object param: params) {
                    resMsg = resMsg.replaceFirst("\\{}", new TmlBasic(param).create());

                    if (!resMsg.contains("{}")) {
                        break;
                    }
                }

                return create(resMsg);
            }
        }

        return create(msg);
    }

    private String create(String msg) {
        return new TmlRoot(
                "MESSAGE",
                new TmlBasic(msg)
        ).create();
    }
}
