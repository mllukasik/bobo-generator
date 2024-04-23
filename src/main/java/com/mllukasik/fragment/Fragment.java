package com.mllukasik.fragment;

import java.nio.file.Path;
import java.util.Map;

public interface Fragment {

    String asParagraph();

    String id();

    Path path();

    Map<String, Object> variables();
}
