package com.mllukasik.fragment.markdown;

import com.mllukasik.fragment.FragmentsProvider;
import org.commonmark.ext.front.matter.YamlFrontMatterExtension;
import org.commonmark.ext.front.matter.YamlFrontMatterVisitor;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;

import java.util.List;
import java.util.Map;


public class BasicMarkdownProvider implements FragmentsProvider {

    private final Parser parser;
    private final HtmlRenderer renderer;

    public BasicMarkdownProvider() {
        parser = Parser.builder().extensions(List.of(YamlFrontMatterExtension.create())).build();
        renderer = HtmlRenderer.builder().build();
    }

    MarkdownFragment createFragment(String content, String id) {
        var node = parseContent(content);
        var metaData = getMetaData(node);
        var renderedContent = render(node);
        return new MarkdownFragment(renderedContent, metaData, id);
    }

    Node parseContent(String content) {
        return parser.parse(content);
    }

    String render(Node node) {
        return renderer.render(node);
    }

    Map<String, List<String>> getMetaData(Node node) {
        var visitor = new YamlFrontMatterVisitor();
        node.accept(visitor);
        return visitor.getData();
    }
}
