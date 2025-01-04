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

        var table = TestPathProvider.buildPath("pages/table.html")
        Files.exists(table)
        var value = Files.readAllLines(table).join("\n")
        value.contains("table")
    }
}
