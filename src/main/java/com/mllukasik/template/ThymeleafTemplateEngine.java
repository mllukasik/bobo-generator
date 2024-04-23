package com.mllukasik.template;

import org.thymeleaf.context.Context;

public final class ThymeleafTemplateEngine implements TemplateEngine {

    private final org.thymeleaf.TemplateEngine templateEngine;

    public ThymeleafTemplateEngine(org.thymeleaf.TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    @Override
    public void process(Template template) {
        var context = new Context();
        templateEngine.process(template.templatePath(), context, template.writer());
    }
}
