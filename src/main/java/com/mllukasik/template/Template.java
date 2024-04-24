package com.mllukasik.template;

import java.nio.file.Path;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;

public final class Template {

    private final Path templatePath;
    private final Path buildPath;
    private final Map<String, Object> variables;

    private Template(Path templatePath, Path buildPath, Map<String, Object> variables) {
        this.templatePath = templatePath;
        this.buildPath = buildPath;
        this.variables = variables;
    }

    public Path templatePath() {
        return templatePath;
    }

    public Path buildPath() {
        return buildPath;
    }

    public Map<String, Object> variables() {
        return Optional.ofNullable(variables).orElseGet(Collections::emptyMap);
    }

    public static TemplateBuilder builder() {
        return new TemplateBuilder();
    }

    public static class TemplateBuilder {
        private Path templatePath;
        private Map<String, Object> variables;
        private Path buildPath;

        public TemplateBuilder setTemplatePath(Path templatePath) {
            this.templatePath = templatePath;
            return this;
        }

        public TemplateBuilder setVariables(Map<String, Object> variables) {
            this.variables = variables;
            return this;
        }

        public TemplateBuilder setBuildPath(Path buildPath) {
            this.buildPath = buildPath;
            return this;
        }

        public Template build() {
            return new Template(templatePath, buildPath, variables);
        }
    }
}
