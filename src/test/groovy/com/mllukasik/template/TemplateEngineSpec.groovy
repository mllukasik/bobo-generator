package com.mllukasik.template

import com.mllukasik.tool.TestPathProvider
import spock.lang.Specification

import java.nio.file.Files

class TemplateEngineSpec extends Specification {

    private TemplateEngine templateEngine

    def setup() {
        templateEngine = TemplateEngine.create()
    }

    def "process template, with existing template, expect generated file"() {
        var templateName = "simple.html"
        var template = Template.builder()
                .setTemplatePath(TestPathProvider.resolveTemplate(templateName))
                .build()
        when:
        templateEngine.process(template)
        then:
        Files.exists(TestPathProvider.buildPath(templateName))
    }

    def "process template, with variable map, expect generated file"() {
        var templateName = "variable_test.html"
        var testValue = "test_text_GW*Xhc@a98ngL"

        var variables = [
                "testObject": [
                        "variable1": [
                                "innerVariable": "$testValue"
                        ]
                ]
        ]
        var template = Template.builder()
                .setTemplatePath(TestPathProvider.resolveTemplate(templateName))
                .setVariables(variables)
                .build()
        when:
        templateEngine.process(template)
        then:
        Files.exists(TestPathProvider.buildPath(templateName))
        TestPathProvider.buildPath(templateName).toFile().getText().contains(testValue)
    }
}
