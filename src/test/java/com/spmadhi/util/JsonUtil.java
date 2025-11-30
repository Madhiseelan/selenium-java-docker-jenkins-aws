package com.spmadhi.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spmadhi.tests.vendorportal.model.VendorPortalTestData;
import org.openqa.selenium.bidi.module.Input;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;

public class JsonUtil {

    private static final Logger log = LoggerFactory.getLogger(JsonUtil.class);
    //ObjectMapper is responsible for converting the input stream into a java object
    private static final ObjectMapper mapper = new ObjectMapper();

    /* This is useful when you have one page of Test data to be passed to tests to run
    public static VendorPortalTestData getTestData(String path){
        try(InputStream stream = ResourceLoader.getResource(path)){
            return mapper.readValue(stream, VendorPortalTestData.class);
        }catch(Exception e){
            log.error("unable to read test data file..! {}", path, e);
        }
        return null;
        }

        // Below demo code is to call the above method from demojson class to get data from json file.
        // Temp check code.
       public class demojson {
            public static void main(String[] args) {
                VendorPortalTestData testData = JsonUtil.getTestData("test-data/vendor-portal/mike.json");
                System.out.println(testData.getSearchResultsCount());
            }
        } */

    public static <T> T getTestData(String path, Class<T> type){
        try(InputStream stream = ResourceLoader.getResource(path)){
            return mapper.readValue(stream, type);
        }catch(Exception e){
            log.error("unable to read test data file..! {}", path, e);
        }
        return null;
    }

}
