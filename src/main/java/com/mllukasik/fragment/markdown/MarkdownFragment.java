package com.mllukasik.fragment.markdown;

import com.mllukasik.fragment.Fragment;

import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

class MarkdownFragment implements Fragment {

    private final String content;
    private final Map<String, Object> metaData;
    private final String id;
    private final Path path;

    MarkdownFragment(String content, Map<String, List<String>> metaData, Path path, String id) {
        this.content = content;
        this.metaData = parseMetaData(metaData);
        this.path = path;
        this.id = id;
    }

    @Override
    public String content() {
        return "<div id=\"" + id + "\">" + content + "</div>";
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
    public Map<String, Object> metadata() {
        return metaData;
    }

    private Map<String, Object> parseMetaData(Map<String, List<String>> metaData) {
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
