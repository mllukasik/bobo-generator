package com.mllukasik.template;

import java.nio.file.Path;

public record TemplateEngineConfiguration(

        Path buildPath
) {
    public TemplateEngineConfiguration() {
        this(Path.of("build"));
    }
}
