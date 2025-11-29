package com.spmadhi.util;

import com.spmadhi.tests.vendorportal.model.VendorPortalTestData;

public class demojson {
    public static void main(String[] args) {

        VendorPortalTestData testData = JsonUtil.getTestData("test-data/vendor-portal/mike.json");

        System.out.println(testData.getSearchResultsCount());
    }
}
