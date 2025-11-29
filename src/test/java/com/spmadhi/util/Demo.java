package com.spmadhi.util;

import org.apache.commons.io.IOUtils;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class Demo {
    //This class was only created to check the Resource loaded is working or not.
    //Used the txt file for example.

    public static void main(String[] args) throws Exception{
        InputStream stream = ResourceLoader.getResource("test-suites/a/dummy.txt");
        String content = IOUtils.toString(stream, StandardCharsets.UTF_8);

        System.out.println((content));
    }
}
