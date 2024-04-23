package com.mllukasik.template;

import com.mllukasik.template.exception.TemplateEngineException;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.FileTemplateResolver;

import java.io.IOException;
import java.nio.file.Files;

final class ThymeleafEngineFactory {

    private final TemplateEngineConfiguration configuration;

    ThymeleafEngineFactory(TemplateEngineConfiguration configuration) {
        this.configuration = configuration;
    }

    public org.thymeleaf.TemplateEngine templateEngine() {
        var resolver = new FileTemplateResolver();
        resolver.setTemplateMode(TemplateMode.HTML);
        resolver.setCharacterEncoding("UTF-8");
        var templateEngine = new org.thymeleaf.TemplateEngine();
        templateEngine.setTemplateResolver(resolver);
        return templateEngine;
    }

    public TemplateEngine create() {
        try {
            ensureBuildPathExists();
        } catch (IOException exception) {
            throw new TemplateEngineException(exception);
        }
        return new ThymeleafTemplateEngine(templateEngine(), configuration);
    }

    private void ensureBuildPathExists() throws IOException {
        final var buildPath = configuration.buildPath();
        if (Files.isDirectory(buildPath)) {
            return;
        }
        if (Files.exists(buildPath)) {
            throw new IllegalStateException("Build path exists but is not directory path");
        }
        Files.createDirectories(buildPath);
    }
}
