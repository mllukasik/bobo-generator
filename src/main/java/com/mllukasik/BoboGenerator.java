package com.mllukasik;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class BoboGenerator {

    private static final Logger LOGGER = LogManager.getLogger(BoboGenerator.class);

    private BoboGenerator() {
    }

    public static void main(String[] args) {
        LOGGER.info("---BoboGenerator---");
    }
}
