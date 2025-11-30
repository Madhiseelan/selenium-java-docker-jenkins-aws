package com.spmadhi.tests.flightreservation;

import com.spmadhi.pages.flightreservation.*;
import com.spmadhi.tests.BaseTest;
import com.spmadhi.tests.flightreservation.model.FlightReservationTestData;
import com.spmadhi.util.JsonUtil;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class FlightReservationTest extends BaseTest {

    private FlightReservationTestData testData;
    private RegistrationPage registrationPage;

    /* Below Example when we get the parameters from xml file.
    Other following method example to get the parameters from Json file.
    private String noOfPassengers;
    private String expectedPrice;
    @BeforeTest
    @Parameters({"noOfPassengers", "expectedPrice"})
    public void setParameters(String noOfPassengers, String expectedPrice){
        this.noOfPassengers = noOfPassengers;
        this.expectedPrice = expectedPrice;
        this.registrationPage = new RegistrationPage(driver);
    }
    */
    @BeforeTest
    @Parameters({"testDataPath"})
    public void setParameters(String testDataPath){
        this.testData = JsonUtil.getTestData(testDataPath, FlightReservationTestData.class);

    }

    @Test
    public void userRegistrationTest(){
        this.registrationPage = new RegistrationPage(driver);
        String url = "https://d1uh9e7cu07ukd.cloudfront.net/selenium-docker/reservation-app/index.html";
        registrationPage.goTo(url);
        Assert.assertTrue(registrationPage.isAt());

        registrationPage.enterUserDetails(testData.getFirstName(), testData.getLastName());
        registrationPage.userCredentials(testData.getEmail(), testData.getPassword());
        registrationPage.enterAddress(testData.getStreet(), testData.getCity(), testData.getZip());
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
        flightsSearchPage.selectPassengers(testData.getPassengersCount());
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

        Assert.assertEquals(flightConfirmationPage.getPrice(), testData.getExpectedPrice());
    }
}
