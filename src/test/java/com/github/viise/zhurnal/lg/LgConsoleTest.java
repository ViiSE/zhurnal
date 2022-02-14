package com.github.viise.zhurnal.lg;

import org.testng.annotations.Test;

public class LgConsoleTest {

    @Test
    public void print() {
        new LgConsole(new LgInfo("Hello, {}!", "log")).print();
    }
}
