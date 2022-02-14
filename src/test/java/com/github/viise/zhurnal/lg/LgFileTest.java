package com.github.viise.zhurnal.lg;

import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.testng.Assert.assertTrue;

public class LgFileTest {

    private final String fName = System.getProperty("user.dir") + File.separator
            + "src" + File.separator
            + "test" + File.separator
            + "resources" + File.separator
            + "lg.log";

    @Test
    public void print() throws IOException {
        new LgFile(
                fName,
                new LgInfo(LgFileTest.class, "Hello, {}!", "log")
        ).print();

        String actual = Files.readAllLines(Paths.get(fName)).get(0);
        assertTrue(actual.matches("\\[INFO] \\[.*] \\[LgFileTest] \\[MESSAGE Hello, log!]"));
    }

    @AfterTest
    public void clearUp() throws IOException {
        Files.delete(Paths.get(fName));
    }
}
