package com.spmadhi.pages.flightreservation;

import com.spmadhi.pages.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.v135.page.Page;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RegistrationConfirmationPage extends AbstractPage {
    private WebDriver driver;
    @FindBy(id = "go-to-flights-search")
    private WebElement goToFlightsSearchButton;

    public RegistrationConfirmationPage(WebDriver driver){
       super(driver);
    }

    /*
     * Before you interact with any method on the new page, first check if you are on that page.
     * Check if the page is loaded successfully or not.
     */
    @Override
    public boolean isAt() {
        this.wait.until(ExpectedConditions.visibilityOf(this.goToFlightsSearchButton));
        return this.goToFlightsSearchButton.isDisplayed();
    }

    public void gotToFlightsSearch(){
        this.goToFlightsSearchButton.click();
    }


}
