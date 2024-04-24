package com.mllukasik.context.generate;

import java.nio.file.Path;
import java.util.Optional;

public record GenerateCommand(

        Path workspace,
        Path pages,
        boolean skipPagesGeneration,
        Path pageTemplate,
        Path build
) {

    public GenerateCommand {
        workspace = Optional.ofNullable(workspace).orElse(Path.of("."));
        pages = Optional.ofNullable(pages).orElse(workspace.resolve("pages"));
        pageTemplate = Optional.ofNullable(pageTemplate).orElse(workspace.resolve("_page_template.html"));
        build = Optional.ofNullable(build).orElse(Path.of("build"));
    }

    public static GenerateCommandBuilder builder() {
        return new GenerateCommandBuilder();
    }

    public static class GenerateCommandBuilder {
        private Path workspace;
        private Path pages;
        boolean skipPagesGeneration;
        private Path pageTemplate;
        private Path build;

        private GenerateCommandBuilder() {
        }

        public GenerateCommandBuilder setWorkspace(Path workspace) {
            this.workspace = workspace;
            return this;
        }

        public GenerateCommandBuilder setPages(Path pages) {
            this.pages = pages;
            return this;
        }

        public GenerateCommandBuilder setSkipPagesGeneration(boolean skipPagesGeneration) {
            this.skipPagesGeneration = skipPagesGeneration;
            return this;
        }

        public GenerateCommandBuilder setPageTemplate(Path pageTemplate) {
            this.pageTemplate = pageTemplate;
            return this;
        }

        public GenerateCommandBuilder setBuild(Path build) {
            this.build = build;
            return this;
        }

        public GenerateCommand build() {
            return new GenerateCommand(workspace, pages, skipPagesGeneration,
                    pageTemplate, build);
        }
    }
}
