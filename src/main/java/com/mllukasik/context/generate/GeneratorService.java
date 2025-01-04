package com.mllukasik.context.generate;

import com.mllukasik.fragment.Fragment;
import com.mllukasik.fragment.FragmentParser;
import com.mllukasik.robusta.util.Paths;
import com.mllukasik.template.Template;
import com.mllukasik.template.TemplateEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

class GeneratorService {

    private static final String HTML_EXT = ".html";
    private static final Logger LOGGER = LoggerFactory.getLogger(GeneratorService.class);

    private final TemplateEngine templateEngine;
    private final FragmentParser fragmentParser;

    GeneratorService() {
        templateEngine = TemplateEngine.create();
        fragmentParser = new FragmentParser();
    }

    void execute(GenerateCommand command) {
        var fragments = new LinkedList<Fragment>();
        var visitor = new SimpleFileVisitor((f) -> fragments.add(fragmentParser.parse(f)));
        try {
            Files.walkFileTree(command.pages(), visitor);
        } catch (IOException ex) {
            LOGGER.error("Could not read page", ex);
        }
        execute(command, fragments);
    }

    private void execute(GenerateCommand command, List<Fragment> fragments) {
        var visitor = new SimpleFileVisitor(
                (f) -> handleFile(f, command, fragments),
                (f) -> !(f.getFileName().toString().startsWith("_") || command.pages().getFileName()
                        .equals(f.getFileName()))
        );
        try {
            Files.walkFileTree(command.workspace(), visitor);
        } catch (IOException ex) {
            LOGGER.error("Could not generate fragment", ex);
        }
        if (command.skipPagesGeneration()) {
            return;
        }
        for (var fragment : fragments) {
            var buildPath = command.workspace().relativize(fragment.path());
            var template = createTemplate(
                    command,
                    command.pageTemplate(),
                    Paths.changeFileExtension(buildPath, ".html"),
                    getVariables(fragments, fragment));
            processTemplate(template);
        }
    }

    private void handleFile(Path file, GenerateCommand command, List<Fragment> fragments) {
        var relativePath = command.workspace().relativize(file);
        var ext = Paths.getFileExtension(file);
        if (!HTML_EXT.equals(ext)) {
            copy(file, command.build().resolve(relativePath));
            return;
        }
        var template = createTemplate(command,
                file,
                relativePath,
                getVariables(fragments));
        processTemplate(template);
    }

    private void copy(Path path, Path destination) {
        try {
            Paths.ensureDirectoryExists(destination.getParent());
            Files.copy(path, destination);
        } catch (IOException ex) {
            LOGGER.error("Could not copy file", ex);
        }
    }

    private void processTemplate(Template template) {
        try {
            templateEngine.process(template);
        } catch (IOException ex) {
            LOGGER.error("Could not process template", ex);
        }

    }

    private Template createTemplate(GenerateCommand command, Path template, Path path, Map<String, Object> variables) {
        return Template.builder()
                .setTemplatePath(template)
                .setBuildPath(command.build().resolve(path))
                .setVariables(variables)
                .build();

    }

    private Map<String, Object> getVariables(List<Fragment> fragments) {
        var map = new HashMap<String, Object>();
        map.put("fragments", fragments);
        return map;
    }

    private Map<String, Object> getVariables(List<Fragment> fragments, Fragment fragment) {
        var map = getVariables(fragments);
        map.put("metadata", fragment.metadata());
        map.put("fragment_content", fragment.content());
        return map;
    }
}
