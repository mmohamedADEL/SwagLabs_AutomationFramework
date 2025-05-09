package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilities.LogUtility;
import utilities.Utility;

public class FinishPage {
    private final WebDriver driver;
    private final By confirmationMessage = By.className("complete-header");

    public FinishPage(WebDriver driver) {
        this.driver = driver;
    }
    public String getConfirmationMessage() {
        String message =  Utility.getTextFromElement(driver, confirmationMessage);
        LogUtility.info("Confirmation message: " + message);
        return message;
    }
}
