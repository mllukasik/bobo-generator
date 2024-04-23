package com.mllukasik.template;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

public class Template {

    private final String templatePath;
    private final Map<String, Object> variables;

    private Template(String templatePath, Map<String, Object> variables) {
        this.templatePath = templatePath;
        this.variables = variables;
    }

    public String templatePath() {
        return templatePath;
    }

    public Map<String, Object> variables() {
        return Optional.ofNullable(variables).orElseGet(Collections::emptyMap);
    }

    public static TemplateBuilder builder() {
        return new TemplateBuilder();
    }

    public static class TemplateBuilder {
        private String templatePath;
        private Map<String, Object> variables;

        public TemplateBuilder setTemplatePath(String templatePath) {
            this.templatePath = templatePath;
            return this;
        }

        public TemplateBuilder setVariables(Map<String, Object> variables) {
            this.variables = variables;
            return this;
        }

        public Template build() {
            return new Template(templatePath, variables);
        }
    }
}
