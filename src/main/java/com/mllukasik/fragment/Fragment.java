package com.mllukasik.fragment;

import java.nio.file.Path;
import java.util.Map;

public interface Fragment {

    String content();

    String id();

    Path path();

    Map<String, Object> metadata();
}
