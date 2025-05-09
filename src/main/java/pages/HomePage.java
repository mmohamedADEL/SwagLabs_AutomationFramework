package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utilities.LogUtility;
import utilities.Utility;

import java.util.List;
import java.util.Set;

public class HomePage {


    public static float totalPrice ;


    private final WebDriver driver;

    private final By AddToCartButtonGeneral = By.xpath("//button[contains(@class, 'btn_primary')]");
    private final By cartLink =By.className("shopping_cart_link");
    private final By cartItemCount = By.className("shopping_cart_badge");
    private final By removeButton = By.xpath("//button[contains(@class, 'btn_secondary')]");
    private final By PricesProductOfSelectedProducts = By.xpath("//button[text()='REMOVE']/preceding-sibling::div[@class='inventory_item_price']");

    private static List<WebElement>  allProducts ;
    private static List<WebElement> SelectedProducts ;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }
    public HomePage AddAllProductsToCart() {
        allProducts = driver.findElements(AddToCartButtonGeneral);
        LogUtility.info("number of products: "+allProducts.size());
        for(int i=1; i<=allProducts.size(); i++){
            Utility.clickOnElement(driver, By.xpath("(//button[@class])["+i+"]"));
        }
        return this;
    }
    public String getNumberOfItemsInCart() {
        return Utility.getTextFromElement(driver, cartItemCount);

    }
    public String getNumberOfSelectedProducts() {
        SelectedProducts = driver.findElements(removeButton);
        LogUtility.info("number of selected products: "+SelectedProducts.size());
        return String.valueOf(SelectedProducts.size());
    }
    public boolean compareSelectedProductsAndCartProducts() {
        return getNumberOfItemsInCart().equals(getNumberOfSelectedProducts());
    }
    public HomePage addRandomProductToCart(int numberOfProductToSelect, int NumberOfAllProducts ) {
        Set<Integer> uniqueRandomNumbers = Utility
                .generateUniqueRandomNumbers(numberOfProductToSelect, NumberOfAllProducts);
        for (int randomNumber : uniqueRandomNumbers) {
            Utility.clickOnElement(driver, By.xpath("(//button[@class])[" + randomNumber + "]"));
            LogUtility.info("Random product added to cart: " + randomNumber);

        }
        return this;

    }
    public CartPage clickOnCartLink() {
        Utility.clickOnElement(driver, cartLink);
        return new CartPage(driver);
    }
    public String getTotalPriceOfSelectedProducts() {
        try {
            LogUtility.info("number of selected "+driver.findElements(PricesProductOfSelectedProducts).size());
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
