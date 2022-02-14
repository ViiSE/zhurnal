package com.github.viise.zhurnal.tml;

import com.github.viise.zhurnal.Template;

public final class TmlBasic implements Template {

    private final Object value;

    public TmlBasic(Object value) {
        this.value = value;
    }

    @Override
    public String create() {
        return value == null ? "null" : value.toString();
    }
}
