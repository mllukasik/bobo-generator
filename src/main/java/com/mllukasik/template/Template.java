package com.mllukasik.template;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Path;

public record Template(

        String templatePath,
        Writer writer
) {

    public static Template fromTemplatePath(String templatePath) throws IOException {
        var fileName = Path.of(templatePath).getFileName().toString();
        var buildPath = Path.of("build", fileName);
        return new Template(templatePath, new FileWriter(buildPath.toFile()));
    }
}
