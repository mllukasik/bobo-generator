package com.mllukasik.context.generate

import com.mllukasik.tool.TestPathProvider
import spock.lang.Specification

import java.nio.file.Files

class GeneratorServiceSpec extends Specification {

    private GeneratorService generatorService

    def setup() {
        generatorService = new GeneratorService()
    }

    def "execute command, expect all files processed"() {
        def command = GenerateCommand.builder()
                .setWorkspace(TestPathProvider.workspacePath("src"))
                .build()
        when:
        generatorService.execute(command)
        then:
        Files.exists(TestPathProvider.buildPath("index.html"))
        Files.exists(TestPathProvider.buildPath("pages/simple.html"))
        Files.exists(TestPathProvider.buildPath("static/style.css"))
    }
}
