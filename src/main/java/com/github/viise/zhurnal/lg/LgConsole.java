package com.github.viise.zhurnal.lg;

import com.github.viise.zhurnal.Log;

public final class LgConsole implements Log<Void> {

    private final Log<String> lg;

    public LgConsole(Log<String> lg) {
        this.lg = lg;
    }

    @Override
    public Void print() {
        System.out.println(lg.print());
        return null;
    }
}
