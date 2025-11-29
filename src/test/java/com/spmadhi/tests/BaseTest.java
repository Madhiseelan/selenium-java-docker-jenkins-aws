package com.spmadhi.tests;

import com.spmadhi.pages.vendorportal.DashboardPage;
import com.spmadhi.pages.vendorportal.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public abstract class BaseTest {

    protected WebDriver driver;

    @BeforeTest
    public void serDriver(){
        WebDriverManager.chromedriver().setup();
        this.driver = new ChromeDriver();

    }

    @AfterTest
    public void quickDriver(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        this.driver.quit();
    }


}
