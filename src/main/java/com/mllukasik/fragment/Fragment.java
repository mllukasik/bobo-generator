package com.mllukasik.fragment;

import java.util.Map;

public interface Fragment {

    String asParagraph();

    String id();

    Map<String, Object> variables();
}
