package com.mllukasik.fragment.markdown

import com.mllukasik.tool.TestPathProvider
import spock.lang.Specification

class PathMarkdownProviderSpec extends Specification {

    private static final SIMPLE_RENDER = """<a id="simple"><h1>HEADER 1</h1>
<h2>HEADER 2</h2>
<p>text</p>
</a>"""

    private static final SIMPLE_VARIABLES = [
            "title": "Simple page",
            "test": ["A", "B", "C"]
    ]

    private PathMarkdownProvider pathMarkdownProvider

    def setup() {
        pathMarkdownProvider = new PathMarkdownProvider()
    }

    def "get simple fragment, expect correct data"() {
        when:
        var fragment = pathMarkdownProvider.fragment(TestPathProvider.pagePath("simple.md"))
        then:
        fragment.id() == "simple"
        fragment.asParagraph() == SIMPLE_RENDER
        fragment.variables() == SIMPLE_VARIABLES
    }
}
