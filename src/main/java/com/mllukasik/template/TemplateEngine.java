package com.mllukasik.template;

import java.io.IOException;

public interface TemplateEngine {

    void process(Template template) throws IOException;

    static TemplateEngine create() {
        return new ThymeleafEngineFactory().create();
    }
}
