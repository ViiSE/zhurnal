package com.github.viise.zhurnal.tml;

import com.github.viise.zhurnal.Template;

public final class TmlClass implements Template {

    private final Class<?> tmlClass;
    private final Boolean useFullName;

    public TmlClass(Class<?> tmlClass, Boolean useFullName) {
        this.tmlClass = tmlClass;
        this.useFullName = useFullName;
    }

    public TmlClass(Class<?> tmlClass) {
        this(tmlClass, true);
    }

    @Override
    public String create() {
        String className = tmlClass == null ? "null" : useFullName ? tmlClass.getName() : tmlClass.getSimpleName();
        return String.format("[%s]", className);
    }
}
