package com.github.viise.zhurnal.tml;

import com.github.viise.zhurnal.Template;

public final class TmlChild implements Template {

    private final String name;
    private final Object value;

    public TmlChild(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public String create() {
        return String.format("<%s:%s>", new TmlBasic(name).create().toUpperCase(), new TmlBasic(value).create());
    }
}
