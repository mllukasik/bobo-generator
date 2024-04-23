package com.mllukasik.tool

import java.nio.file.Path

final class TemplatePathProvider {

    private TemplatePathProvider() {

    }

    private static Path WORKSPACE_PATH = Path.of("workspace")
    private static Path TEMPLATE_PATH = WORKSPACE_PATH.resolve("template")
    private static Path BUILD_PATH = Path.of("build")

    static String resolveAsString(String other) {
        TEMPLATE_PATH.resolve(other)
    }

    static Path buildPath(String templateName) {
        BUILD_PATH.resolve(templateName)
    }
}
