package com.spmadhi.tests.vendorportal;

import com.spmadhi.pages.vendorportal.DashboardPage;
import com.spmadhi.pages.vendorportal.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import net.bytebuddy.asm.Advice;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class VendorPortalTest {

    private WebDriver driver;

    @BeforeTest
    public void serDriver(){
        WebDriverManager.chromedriver().setup();
        this.driver = new ChromeDriver();
    }
    @Test
    public void loginTest(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.goTo("https://d1uh9e7cu07ukd.cloudfront.net/selenium-docker/vendor-app/index.html");
        Assert.assertTrue(loginPage.isAt());
        loginPage.login("sam", "sam");
    }

    @Test(dependsOnMethods = "loginTest")
    public void dashboardTest(){
        DashboardPage dashboardPage = new DashboardPage(driver);
        Assert.assertTrue(dashboardPage.isAt());

        //Finance Metrics
        Assert.assertEquals(dashboardPage.getMonthlyEarning(), "$40,000");
        Assert.assertEquals(dashboardPage.getAnnualEarning(), "$215,000");
        Assert.assertEquals(dashboardPage.getProfitMargin(), "50%");
        Assert.assertEquals(dashboardPage.getAvailableInventory(), "18");

        //Order History Search
        dashboardPage.searchOrderHistoryBy("adams");
        Assert.assertEquals(dashboardPage.getSearchResultsCount(), 8);

        //Logout
        dashboardPage.logOut();
    }

    @AfterTest
    public void quickDriver(){
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        this.driver.quit();
    }
}
