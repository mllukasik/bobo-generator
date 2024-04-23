package com.mllukasik.template

import com.mllukasik.tool.TemplatePathProvider
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
                .setTemplatePath(TemplatePathProvider.resolveAsString(templateName))
                .build()
        when:
        templateEngine.process(template)
        then:
        Files.exists(TemplatePathProvider.buildPath(templateName))
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
                .setTemplatePath(TemplatePathProvider.resolveAsString(templateName))
                .setVariables(variables)
                .build()
        when:
        templateEngine.process(template)
        then:
        Files.exists(TemplatePathProvider.buildPath(templateName))
        TemplatePathProvider.buildPath(templateName).toFile().getText().contains(testValue)
    }
}
