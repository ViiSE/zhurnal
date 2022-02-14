package com.github.viise.zhurnal.tml;

import com.github.viise.zhurnal.Template;

public final class TmlError implements Template {

    @Override
    public String create() {
        return "[ERROR]";
    }
}
