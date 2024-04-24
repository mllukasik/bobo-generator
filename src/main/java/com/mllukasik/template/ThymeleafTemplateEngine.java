package com.mllukasik.template;

import com.mllukasik.robusta.util.Paths;
import org.thymeleaf.context.Context;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

final class ThymeleafTemplateEngine implements TemplateEngine {

    private final org.thymeleaf.TemplateEngine templateEngine;

    public ThymeleafTemplateEngine(org.thymeleaf.TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    @Override
    public void process(Template template) throws IOException {
        templateEngine.process(template.templatePath().toString(),
                contextForTemplate(template),
                writerForTemplate(template));
    }

    private Context contextForTemplate(Template template) {
        final var context = new Context();
        var entries = template.variables().entrySet();
        for (var entry : entries) {
            context.setVariable(entry.getKey(), entry.getValue());
        }
        return context;
    }

    private Writer writerForTemplate(Template template) throws IOException {
        var buildPath = template.buildPath();
        Paths.ensureDirectoryExists(buildPath.getParent());
        return new FileWriter(buildPath.toFile());
    }
}
