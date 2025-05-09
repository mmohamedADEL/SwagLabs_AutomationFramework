package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage {
    private final WebDriver driver;
    private final By firstNameField = By.id("first-name");
    private final By lastNameField = By.id("last-name");
    private final By postalCodeField = By.id("postal-code");
    private final By continueButton = By.xpath("//input[@type='submit']");
    private final By cancelButton = By.xpath("//a[text()='CANCEL']");

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
    }
    public CheckoutPage enterFirstName(String firstName) {
        driver.findElement(firstNameField).sendKeys(firstName);
        return this;
    }
    public CheckoutPage enterLastName(String lastName) {
        driver.findElement(lastNameField).sendKeys(lastName);
        return this;
    }
    public CheckoutPage enterPostalCode(String postalCode) {
        driver.findElement(postalCodeField).sendKeys(postalCode);
        return this;
    }
    public OverviewPage clickOnContinueButton() {
        driver.findElement(continueButton).click();
        return new OverviewPage(driver);
    }
    public CheckoutPage clickOnCancelButton() {
        driver.findElement(cancelButton).click();
        return this;
    }
    public CheckoutPage fillCheckoutForm(String firstName, String lastName, String postalCode) {
        enterFirstName(firstName);
        enterLastName(lastName);
        enterPostalCode(postalCode);
        return this;
    }

}
