package com.mllukasik.fragment.markdown;

import com.mllukasik.fragment.Fragment;
import com.mllukasik.fragment.markdown.exception.PathMarkdownProviderException;
import com.mllukasik.robusta.util.Paths;

import java.io.IOException;
import java.nio.file.Path;

public class PathMarkdownProvider extends BasicMarkdownProvider {

    Fragment fragment(Path path) {
        return createFragment(
                readContent(path),
                idFromPath(path)
        );
    }

    private String idFromPath(Path path) {
       var fileName = path.getFileName().toString();
       var dotIndex = fileName.indexOf('.');
       if(dotIndex == -1) {
           return fileName;
       }
       return fileName.substring(0, dotIndex);
    }

    private String readContent(Path path) {
        try {
            return Paths.readLines(path);
        } catch (IOException exception) {
            throw new PathMarkdownProviderException(exception);
        }
    }
}
