package com.github.olegshishkin.masker.json;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Files;
import java.nio.file.Paths;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MaskerTest {

    private String input;
    private String output;

    @BeforeEach
    void setUp() throws Exception {
        input = new String(Files.readAllBytes(Paths.get("src/test/resources/input.txt")));
        output = new String(Files.readAllBytes(Paths.get("src/test/resources/output.txt")));
    }

    @Test
    void mask() {
        assertEquals(output, Masker.mask(input));
    }
}