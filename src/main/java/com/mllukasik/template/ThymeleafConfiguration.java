package com.mllukasik.template;

import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.FileTemplateResolver;

public final class ThymeleafConfiguration {

    ThymeleafConfiguration() {
    }

    public org.thymeleaf.TemplateEngine templateEngine() {
        var resolver = new FileTemplateResolver();
        resolver.setTemplateMode(TemplateMode.HTML);
        resolver.setCharacterEncoding("UTF-8");
        resolver.setSuffix(".html");
        var templateEngine = new org.thymeleaf.TemplateEngine();
        templateEngine.setTemplateResolver(resolver);
        return templateEngine;
    }

    public TemplateEngine getInstance() {
        ensureBuildPathExists();
        return new ThymeleafTemplateEngine(templateEngine());
    }

    private void ensureBuildPathExists() {

    }
}
