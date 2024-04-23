package com.mllukasik.fragment;

import com.mllukasik.fragment.exception.FragmentParserException;
import com.mllukasik.fragment.markdown.PathMarkdownFragmentParser;
import com.mllukasik.robusta.util.Paths;

import java.nio.file.Path;

public final class FragmentParser {

    private final PathMarkdownFragmentParser markdownParser;

    public FragmentParser() {
        markdownParser = new PathMarkdownFragmentParser();
    }

    public Fragment parse(Path path) {
        var fileExtension = Paths.getFileExtension(path);
        return switch (fileExtension) {
            case ".md", ".markdown" -> handleMarkdown(path);
            default -> throw new FragmentParserException("Unsupported format " + fileExtension);
        };
    }

    private Fragment handleMarkdown(Path path) {
        return markdownParser.parse(path);
    }
}
