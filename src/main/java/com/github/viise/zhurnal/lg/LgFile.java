package com.github.viise.zhurnal.lg;

import com.github.viise.zhurnal.Log;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public final class LgFile implements Log<Void> {

    private final String fileName;
    private final Log<String> lg;

    public LgFile(String fileName, Log<String> lg) {
        this.fileName = fileName;
        this.lg = lg;
    }

    @Override
    public Void print() {
        try {
            Files.write(
                    Paths.get(fileName),
                    String.format("%s%s", lg.print(), System.lineSeparator()).getBytes(),
                    StandardOpenOption.CREATE, StandardOpenOption.APPEND
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return null;
    }
}