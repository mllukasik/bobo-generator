package com.mllukasik.fragment.markdown;

import com.mllukasik.fragment.Fragment;

import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

class MarkdownFragment implements Fragment {

    private final String content;
    private final Map<String, List<String>> metaData;
    private final String id;
    private final Path path;

    MarkdownFragment(String content, Map<String, List<String>> metaData, Path path, String id) {
        this.content = content;
        this.metaData = metaData;
        this.path = path;
        this.id = id;
    }

    @Override
    public String asParagraph() {
        return "<a id=\"" + id + "\">" + content + "</a>";
    }

    @Override
    public String id() {
        return id;
    }

    @Override
    public Path path() {
        return path;
    }

    @Override
    public Map<String, Object> variables() {
        return parseMetaData();
    }

    private Map<String, Object> parseMetaData() {
        var map = new LinkedHashMap<String, Object>();
        for (var entry : metaData.entrySet()) {
            if (isSingleElement(entry.getValue())) {
                map.put(entry.getKey(), entry.getValue().get(0));
                continue;
            }
            map.put(entry.getKey(), entry.getValue());
        }
        return map;
    }

    private boolean isSingleElement(List<String> value) {
        return value != null && value.size() == 1;
    }
}
