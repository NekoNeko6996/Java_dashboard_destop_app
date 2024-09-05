package com.application.main;

import java.io.InputStream;

public class ResourceLoader {

    public static InputStream getResourceAsStream(String path) {
        return ResourceLoader.class.getResourceAsStream(path);
    }
}
