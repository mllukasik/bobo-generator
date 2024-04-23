package com.mllukasik.template;

import org.thymeleaf.context.Context;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Path;

final class ThymeleafTemplateEngine implements TemplateEngine {

    private final org.thymeleaf.TemplateEngine templateEngine;
    private final TemplateEngineConfiguration configuration;

    public ThymeleafTemplateEngine(org.thymeleaf.TemplateEngine templateEngine,
                                   TemplateEngineConfiguration configuration) {
        this.templateEngine = templateEngine;
        this.configuration = configuration;
    }

    @Override
    public void process(Template template) throws IOException {
        templateEngine.process(template.templatePath(),
                contextForTemplate(template),
                writerForTemplate(template));
    }

    private Context contextForTemplate(Template template) {
        final var context = new Context();
        var entries = template.variables().entrySet();
        for(var entry : entries) {
            context.setVariable(entry.getKey(), entry.getValue());
        }
        return context;
    }

    private Writer writerForTemplate(Template template) throws IOException {
        var fileName = Path.of(template.templatePath()).getFileName().toString();
        var buildPath = configuration.buildPath().resolve(fileName);
        return new FileWriter(buildPath.toFile());
    }
}
