package com.mllukasik.context.generate;

import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.nio.file.Path;
import java.util.concurrent.Callable;

@Command(name = "generate", version = "1.0.0")
public class GeneratorEntrypoint implements Callable<Integer> {

    @Option(names = {"-w", "--workspace"}, description = "workspace path")
    private Path workspace;

    @Option(names = {"-p", "--pages"}, description = "pages path")
    private Path pages;

    @Option(names = "--skip-pages-generation", description = "skips page generation process")
    boolean skipPagesGeneration;

    @Option(names = {"-t", "--page-template"}, description = "page template path")
    private Path pageTemplate;

    @Option(names = {"-o", "--output"}, description = "build path")
    private Path build;

    private final GeneratorService generatorService = new GeneratorService();

    @Override
    public Integer call() {
        var command = GenerateCommand.builder()
                .setWorkspace(workspace)
                .setPages(pages)
                .setSkipPagesGeneration(skipPagesGeneration)
                .setPageTemplate(pageTemplate)
                .setBuild(build)
                .build();
        generatorService.execute(command);
        return 0;
    }
}
