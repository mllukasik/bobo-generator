package com.mllukasik.fragment.markdown;

import com.mllukasik.fragment.Fragment;
import com.mllukasik.fragment.exception.FragmentParserException;
import com.mllukasik.robusta.util.Paths;
import org.commonmark.ext.front.matter.YamlFrontMatterExtension;
import org.commonmark.ext.front.matter.YamlFrontMatterVisitor;
import org.commonmark.ext.gfm.tables.TablesExtension;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public class PathMarkdownFragmentParser {
    private final Parser parser;

    private final HtmlRenderer renderer;

    public PathMarkdownFragmentParser() {
        var extensions = List.of(YamlFrontMatterExtension.create(),
                TablesExtension.create());
        parser = Parser.builder().extensions(extensions).build();
        renderer = HtmlRenderer.builder().extensions(List.of(TablesExtension.create())).build();
    }

    public Fragment parse(Path path) {
        return createFragment(
                readContent(path),
                path,
                idFromPath(path)
        );
    }

    private MarkdownFragment createFragment(String content, Path path, String id) {
        var node = parseContent(content);
        var metaData = getMetaData(node);
        var renderedContent = render(node);
        return new MarkdownFragment(renderedContent, metaData, path, id);
    }

    private String readContent(Path path) {
        try {
            return Paths.readLines(path);
        } catch (IOException exception) {
            throw new FragmentParserException(exception);
        }
    }

    private String idFromPath(Path path) {
        var fileName = path.getFileName().toString();
        var dotIndex = fileName.indexOf('.');
        if (dotIndex == -1) {
            return fileName;
        }
        return fileName.substring(0, dotIndex);
    }

    private Node parseContent(String content) {
        return parser.parse(content);
    }

    private String render(Node node) {
        return renderer.render(node);
    }

    private Map<String, List<String>> getMetaData(Node node) {
        var visitor = new YamlFrontMatterVisitor();
        node.accept(visitor);
        return visitor.getData();
    }
}
