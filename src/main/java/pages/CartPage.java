package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utilities.LogUtility;
import utilities.Utility;

import java.util.List;

public class CartPage {

    public static  float totalPrice ;


    private final WebDriver driver;
    private final By checkoutButton = By.xpath("//a[contains(@class,'checkout_button')]");
    private final  By ContinueShopping = By.xpath("//a[contains(text(), 'Continue')]");
    private final By removeButtonGeneral = By.xpath("//button[contains(text(), 'REMOVE')]");
    private final By PricesProductOfSelectedProducts = By.xpath("//button[text()='REMOVE']/preceding-sibling::div[@class='inventory_item_price']");

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }
    public CheckoutPage clickOnCheckoutButton() {
        driver.findElement(checkoutButton).click();
        return new CheckoutPage(driver);
    }
    public HomePage clickOnContinueShopping() {
        driver.findElement(ContinueShopping).click();
        return new HomePage(driver);
    }

    public String getTotalPriceOfSelectedProducts() {
        try {
            LogUtility.info("number of selected " + driver.findElements(PricesProductOfSelectedProducts).size());
            List<WebElement> pricesOfSelectedProducts = driver.findElements(PricesProductOfSelectedProducts);
            for (int i = 1; i <= pricesOfSelectedProducts.size(); i++) {
                By elements = By.xpath("(//button[text()='REMOVE']/preceding-sibling::div[@class='inventory_item_price'])[" + i + "]");
                String fullText = Utility.getTextFromElement(driver, elements);
                LogUtility.info("Price of selected product: " + fullText);
                totalPrice += Float.parseFloat(fullText.replace("$", ""));
                LogUtility.info("Total price after adding product: " + totalPrice);
            }
            LogUtility.info("Total Price " + totalPrice);
            return String.valueOf(totalPrice);
        } catch (Exception e) {
            LogUtility.error(e.getMessage());
            return "0";
        }
    }
}
