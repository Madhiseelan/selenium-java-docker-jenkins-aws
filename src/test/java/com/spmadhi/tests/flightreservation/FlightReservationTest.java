package com.spmadhi.tests.flightreservation;

import com.spmadhi.pages.flightreservation.*;
import com.spmadhi.tests.BaseTest;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class FlightReservationTest extends BaseTest {
    private String noOfPassengers;
    private String expectedPrice;
    private RegistrationPage registrationPage;

    @BeforeTest
    @Parameters({"noOfPassengers", "expectedPrice"})
    public void setParameters(String noOfPassengers, String expectedPrice){
        this.noOfPassengers = noOfPassengers;
        this.expectedPrice = expectedPrice;
        this.registrationPage = new RegistrationPage(driver);
    }

    @Test
    public void userRegistrationTest(){
        String url = "https://d1uh9e7cu07ukd.cloudfront.net/selenium-docker/reservation-app/index.html";
        registrationPage.goTo(url);
        Assert.assertTrue(registrationPage.isAt());

        registrationPage.enterUserDetails("selenoum", "docker");
        registrationPage.userCredentials("seleciummadhi@docker.com", "test123");
        registrationPage.enterAddress("12 Talbot Street","atlanta","30002");
        registrationPage.register();
    }

    @Test(dependsOnMethods = "userRegistrationTest")
    public void registrationConfirmation(){
        RegistrationConfirmationPage registrationConfirmationPage = new RegistrationConfirmationPage(driver);
        Assert.assertTrue(registrationConfirmationPage.isAt());
        registrationConfirmationPage.gotToFlightsSearch();
    }

    @Test(dependsOnMethods = "registrationConfirmation")
    public void flightsSearchTest(){
        FlightsSearchPage flightsSearchPage = new FlightsSearchPage(driver);
        Assert.assertTrue(flightsSearchPage.isAt());
        flightsSearchPage.selectPassengers(noOfPassengers);
        flightsSearchPage.searchFlights();
    }

    @Test(dependsOnMethods = "flightsSearchTest")
    public void flightsSelectionTest(){
        FlightsSelectionPage flightsSelectionPage = new FlightsSelectionPage(driver);
        Assert.assertTrue(flightsSelectionPage.isAt());
        flightsSelectionPage.selectFlights();
    }

    @Test(dependsOnMethods = "flightsSelectionTest")
    public void flightReservationConfirmationTest(){
        FlightConfirmationPage flightConfirmationPage = new FlightConfirmationPage(driver);
        Assert.assertTrue(flightConfirmationPage.isAt());

        Assert.assertEquals(flightConfirmationPage.getPrice(), expectedPrice);
    }
}
