package com.mllukasik;

import com.mllukasik.context.generate.GeneratorEntrypoint;
import picocli.CommandLine;

public final class BoboGenerator {

    private BoboGenerator() {
    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new GeneratorEntrypoint())
                .execute(args);
        System.exit(exitCode);
    }
}
