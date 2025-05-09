package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilities.LogUtility;

public class OverviewPage {
    private final WebDriver driver;
    private final By finishButton = By.xpath("//a[text()='FINISH']");
    private final By cancelButton = By.xpath("//a[text()='CANCEL']");
    private final By subtotalPrice = By.className("summary_subtotal_label");
    private final By taxPrice = By.className("summary_tax_label");
    private final By totalPrice = By.className("summary_total_label");
    public OverviewPage(WebDriver driver) {
        this.driver = driver;
    }
    public FinishPage clickOnFinishButton() {
        driver.findElement(finishButton).click();
        return new FinishPage(driver);
    }
    public HomePage clickOnCancelButton() {
        driver.findElement(cancelButton).click();
        return new HomePage(driver);
    }
    public float getSubtotalPrice() {
        float subTotalPrice = Float.parseFloat(driver.findElement(subtotalPrice).getText().replace("Item total: $", ""));
        LogUtility.info("Subtotal price: " + subTotalPrice);
        return subTotalPrice;
    }
    public float getTaxPrice() {
        float taxPriceValue = Float.parseFloat(driver.findElement(taxPrice).getText().replace("Tax: $", ""));
        LogUtility.info("Tax price: " + taxPriceValue);
        return taxPriceValue;
    }
    public float getTotalPrice() {
        float totalPriceValue = Float.parseFloat(driver.findElement(totalPrice).getText().replace("Total: $", ""));
        LogUtility.info("Total price: " + totalPriceValue);
        return totalPriceValue;
    }
    //compare subtotal and total price
    public boolean compareSubtotalAndTotalPrice() {
        float subtotal = getSubtotalPrice();
        float tax = getTaxPrice();
        float total = getTotalPrice();
        return (subtotal + tax) == total;
    }
}
