package com.mllukasik.tool

import java.nio.file.Path

final class TestPathProvider {

    private TestPathProvider() {

    }

    private static Path WORKSPACE_PATH = Path.of("workspace")
    private static Path TEMPLATE_PATH = WORKSPACE_PATH.resolve("template")
    private static Path PAGES_PATH = WORKSPACE_PATH.resolve("pages")
    private static Path BUILD_PATH = Path.of("build")

    static Path workspacePath() {
        return WORKSPACE_PATH
    }

    static Path workspacePath(String path) {
        return WORKSPACE_PATH.resolve(path)
    }

    static Path templatePath(String template) {
        TEMPLATE_PATH.resolve(template)
    }

    static Path pagePath(String pageName) {
        PAGES_PATH.resolve(pageName)
    }

    static Path buildPath() {
        BUILD_PATH
    }

    static Path buildPath(String template) {
        BUILD_PATH.resolve(template)
    }
}
