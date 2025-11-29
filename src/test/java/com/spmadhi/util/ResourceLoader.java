package com.spmadhi.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

    /*
        A simple utility to read file.
        first, we check the classpath, if found, it is used.
        if not, then we check the file system.
     */
public class ResourceLoader {
    private static Logger log = LoggerFactory.getLogger(ResourceLoader.class);
    public static InputStream getResource(String path) throws Exception{
        log.info("reading resource from location: {}", path);
        InputStream stream = ResourceLoader.class.getClassLoader().getResourceAsStream(path);
        //Refer section 87 to clarify
        if(Objects.nonNull(stream)){
            return stream;
        }
        return Files.newInputStream(Path.of(path));
    }
}
