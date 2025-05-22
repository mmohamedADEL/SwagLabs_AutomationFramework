package Tests;

import DriverFactory.DriverFactory;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.CartPage;
import pages.CheckoutPage;
import pages.HomePage;
import pages.LoginPage;
import utilities.DataUtility;
import utilities.LogUtility;
import Listeners.ITestListenerClass;
import Listeners.InvokedMethodListenersClass;

import java.io.IOException;
import java.time.Duration;

import static DriverFactory.DriverFactory.getDriver;
import static DriverFactory.DriverFactory.setDriver;

@Listeners({ITestListenerClass.class, InvokedMethodListenersClass.class})
public class OverviewTest {
    @Parameters("browser")
    @BeforeMethod
    public void setUp(@Optional String browser) throws IOException {
        if (browser == null || browser.isEmpty()) {

            browser = DataUtility.getPropertyValue("Environment", "Browser");
        }

        setDriver(browser);
        LogUtility.info(browser + " browser is launched");

        String baseUrl = DataUtility.getPropertyValue("Environment", "BaseUrl");
        getDriver().get(baseUrl);
        LogUtility.info("Redirected to the URL: " + baseUrl);

        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Description("Test to check if the total price is equal to subtotal + tax")
    @Owner("Adel")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void checkTotalEqualSubPlusTax() throws IOException {
       boolean x = new LoginPage(getDriver())
                .enterUsername(DataUtility.getJsonData("LoginData" , "validLogin" , "username"))
                .enterPassword(DataUtility.getJsonData("LoginData" , "validLogin" , "password"))
                .clickOnLoginButton()
                .addRandomProductToCart(3 , 6)
                .clickOnCartLink()
                .clickOnCheckoutButton()
                .fillCheckoutForm(DataUtility.getJsonData("CheckOutData" ,"validData", "FirstName"),
                        DataUtility.getJsonData("CheckOutData" ,"validData", "LastName"),
                        DataUtility.getJsonData("CheckOutData" ,"validData", "postalCode"))
                .clickOnContinueButton()
                .compareSubtotalAndTotalPrice();
        Assert.assertTrue(x);
        LogUtility.info("Total price is equal to subtotal + tax");
    }
    @Description("Test to check if the user is redirected to the Finish page")
    @Owner("Adel")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void redirectToFinishPage()throws IOException{
        String message = new LoginPage(getDriver())
                .enterUsername(DataUtility.getJsonData("LoginData" , "validLogin" , "username"))
                .enterPassword(DataUtility.getJsonData("LoginData" , "validLogin" , "password"))
                .clickOnLoginButton()
                .addRandomProductToCart(3 , 6)
                .clickOnCartLink()
                .clickOnCheckoutButton()
                .fillCheckoutForm(DataUtility.getJsonData("CheckOutData" ,"validData", "FirstName"),
                        DataUtility.getJsonData("CheckOutData" ,"validData", "LastName"),
                        DataUtility.getJsonData("CheckOutData" ,"validData", "postalCode"))
                .clickOnContinueButton()
                .clickOnFinishButton()
                .getConfirmationMessage();
        Assert.assertEquals(getDriver().getCurrentUrl() , DataUtility.getPropertyValue("Environment" , "FinishPageURL"));
        Assert.assertEquals(message , DataUtility.getJsonData("confirmation_msg" ,"confirmationMassage"));
    }
    @Description("Test to check if the user is redirected to the Overview page")
    @Owner("Adel")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void checkoutWithEmptyCart() throws IOException {
        new LoginPage(getDriver())
                .enterUsername(DataUtility.getJsonData("LoginData" , "validLogin" , "username"))
                .enterPassword(DataUtility.getJsonData("LoginData" , "validLogin" , "password"))
                .clickOnLoginButton()
                .clickOnCartLink()
                .clickOnCheckoutButton();
        LogUtility.info("current url is :"+ getDriver().getCurrentUrl());
        Assert.assertNotEquals(getDriver().getCurrentUrl() , DataUtility.getPropertyValue("Environment" , "CheckoutPageURL"));
    }
    @Description("Test to check if the user is redirected to the Overview page")
    @Owner("Adel")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void checkoutWithEmptyFields() throws IOException {
       new LoginPage(getDriver())
                .enterUsername(DataUtility.getJsonData("LoginData" , "validLogin" , "username"))
                .enterPassword(DataUtility.getJsonData("LoginData" , "validLogin" , "password"))
                .clickOnLoginButton()
                .addRandomProductToCart(3 , 6)
                .clickOnCartLink()
                .clickOnCheckoutButton()
                .fillCheckoutForm("", "", "")
                .clickOnContinueButton();
        LogUtility.info("current url is :"+ getDriver().getCurrentUrl());
        String message = new CheckoutPage(getDriver())
                .waitForErrorMessage()
                        .getErrorMessage();
        LogUtility.info("Error message is: " + message);
        Assert.assertEquals(message , DataUtility.getJsonData("error_msg" ,"errorMessage"));
    }
    @Description("Test to check if the postalCode field accept special characters")
    @Owner("adel")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void validatePostalCodeNotAcceptString() throws IOException {
        new LoginPage(getDriver())
                .enterUsername(DataUtility.getJsonData("LoginData" , "validLogin" , "username"))
                .enterPassword(DataUtility.getJsonData("LoginData" , "validLogin" , "password"))
                .clickOnLoginButton()
                .addRandomProductToCart(3 , 6)
                .clickOnCartLink()
                .clickOnCheckoutButton()
                .fillCheckoutForm(DataUtility.getJsonData("CheckOutData" ,"validData", "FirstName"),
                        DataUtility.getJsonData("CheckOutData" ,"validData", "LastName"),
                        ("string"))
                .clickOnContinueButton();
        //check for error message is appearing
        LogUtility.info("current url is :"+ getDriver().getCurrentUrl());
        Assert.assertNotEquals(getDriver().getCurrentUrl() , DataUtility.getPropertyValue("Environment" , "OverviewPageURL"));
    }
    @Description("compare total price in home and cart")
    @Owner("adel")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void compareTotalPriceInHomeAndCart() throws IOException {
        new LoginPage(getDriver())
                .enterUsername(DataUtility.getJsonData("LoginData" , "validLogin" , "username"))
                .enterPassword(DataUtility.getJsonData("LoginData" , "validLogin" , "password"))
                .clickOnLoginButton()
                .addRandomProductToCart(3 , 6)
                .getTotalPriceOfSelectedProducts();
        new HomePage(getDriver()).clickOnCartLink();
        Assert.assertEquals(getDriver().getCurrentUrl() , DataUtility.getPropertyValue("Environment" , "CartPageURL"));
        new CartPage(getDriver())
                .getTotalPriceOfSelectedProducts();
        Assert.assertEquals(CartPage.totalPrice,HomePage.totalPrice,"ERROR total price is not correct" );
    }
    //compare total price in overview and cart

    @AfterMethod
    public void tearDown() {
        DriverFactory.quit();
        LogUtility.info("Browser is closed");
    }
}
