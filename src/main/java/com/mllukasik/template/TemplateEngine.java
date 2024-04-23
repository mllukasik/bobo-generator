package com.mllukasik.template;

import java.io.IOException;

public interface TemplateEngine {

    void process(Template template) throws IOException;

    static TemplateEngine create(TemplateEngineConfiguration configuration) {
        return new ThymeleafEngineFactory(configuration).create();
    }

    static TemplateEngine create() {
        return create(new TemplateEngineConfiguration());
    }
}
