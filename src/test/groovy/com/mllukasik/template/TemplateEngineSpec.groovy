package com.mllukasik.template

import spock.lang.Specification

class TemplateEngineSpec extends Specification{

    private TemplateEngine templateEngine

    def setup() {
        templateEngine = TemplateEngine.getInstance()
    }

    def "simple test"() {
        var template = Template.fromTemplatePath("workspace/template/index.html")
        when:
        templateEngine.process(template)
        then:
        noExceptionThrown()
    }
}
