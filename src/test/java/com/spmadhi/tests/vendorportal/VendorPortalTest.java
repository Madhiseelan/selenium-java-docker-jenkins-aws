package com.spmadhi.tests.vendorportal;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.spmadhi.pages.vendorportal.DashboardPage;
import com.spmadhi.pages.vendorportal.LoginPage;
import com.spmadhi.tests.BaseTest;
import io.github.bonigarcia.wdm.WebDriverManager;
import net.bytebuddy.asm.Advice;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class VendorPortalTest extends BaseTest {
    private LoginPage loginPage;
    private DashboardPage dashboardPage;

    @BeforeTest
    public void setupTest(){
        this.loginPage = new LoginPage(driver);
        this.dashboardPage = new DashboardPage(driver);
    }
    @Test
    public void loginTest(){
        loginPage.goTo("https://d1uh9e7cu07ukd.cloudfront.net/selenium-docker/vendor-app/index.html");
        Assert.assertTrue(loginPage.isAt());
        loginPage.login("sam", "sam");
    }

    @Test(dependsOnMethods = "loginTest")
    public void dashboardTest(){
        Assert.assertTrue(dashboardPage.isAt());

        //Finance Metrics
        Assert.assertEquals(dashboardPage.getMonthlyEarning(), "$40,000");
        Assert.assertEquals(dashboardPage.getAnnualEarning(), "$215,000");
        Assert.assertEquals(dashboardPage.getProfitMargin(), "50%");
        Assert.assertEquals(dashboardPage.getAvailableInventory(), "18");

        //Order History Search
        dashboardPage.searchOrderHistoryBy("adams");
        Assert.assertEquals(dashboardPage.getSearchResultsCount(), 8);
    }
    @Test(dependsOnMethods = "dashboardTest")
    public void logoutTest(){
        dashboardPage.logOut();
        Assert.assertTrue(loginPage.isAt());
    }

}
