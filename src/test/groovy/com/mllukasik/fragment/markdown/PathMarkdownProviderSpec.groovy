package com.mllukasik.fragment.markdown

import com.mllukasik.tool.TestPathProvider
import spock.lang.Specification

class PathMarkdownProviderSpec extends Specification {

    private static final SIMPLE_RENDER = """<p id="simple"><h1>HEADER 1</h1>
<h2>HEADER 2</h2>
<p>text</p>
</p>"""

    private static final SIMPLE_VARIABLES = [
            "title": "Simple page",
            "test": ["A", "B", "C"]
    ]

    private PathMarkdownFragmentParser pathMarkdownProvider

    def setup() {
        pathMarkdownProvider = new PathMarkdownFragmentParser()
    }

    def "get simple fragment, expect correct data"() {
        when:
        var fragment = pathMarkdownProvider.parse(TestPathProvider.pagePath("simple.md"))
        then:
        fragment.id() == "simple"
        fragment.asParagraph() == SIMPLE_RENDER
        fragment.metadata() == SIMPLE_VARIABLES
    }
}
