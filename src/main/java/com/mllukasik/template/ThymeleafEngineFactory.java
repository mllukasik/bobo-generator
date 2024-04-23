package com.mllukasik.template;

import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.FileTemplateResolver;

final class ThymeleafEngineFactory {

    public org.thymeleaf.TemplateEngine templateEngine() {
        var resolver = new FileTemplateResolver();
        resolver.setTemplateMode(TemplateMode.HTML);
        resolver.setCharacterEncoding("UTF-8");
        var templateEngine = new org.thymeleaf.TemplateEngine();
        templateEngine.setTemplateResolver(resolver);
        return templateEngine;
    }

    public TemplateEngine create() {
        return new ThymeleafTemplateEngine(templateEngine());
    }
}
