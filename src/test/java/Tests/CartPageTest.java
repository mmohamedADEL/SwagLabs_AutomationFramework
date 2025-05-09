package Tests;

import DriverFactory.DriverFactory;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.CartPage;
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
public class CartPageTest {
    @BeforeMethod
    public void setUp() throws IOException {
        setDriver(DataUtility.getPropertyValue("Environment" , "Browser"));
        LogUtility.info("Browser is launched");
        getDriver().get(DataUtility.getPropertyValue("Environment" , "BaseUrl"));
        LogUtility.info("Redirected to the URL");
        getDriver().manage()
                .timeouts().implicitlyWait(Duration.ofSeconds(10));
    }


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
        Assert.assertEquals(CartPage.totalPrice,HomePage.totalPrice,"7amada" );
    }
    @Test
    public void redirectToCheckOutPage() throws IOException {
        new LoginPage(getDriver())
                .enterUsername(DataUtility.getJsonData("LoginData" , "validLogin" , "username"))
                .enterPassword(DataUtility.getJsonData("LoginData" , "validLogin" , "password"))
                .clickOnLoginButton()
                .addRandomProductToCart(3 , 6)
                .clickOnCartLink()
                .clickOnCheckoutButton();
        Assert.assertEquals(getDriver().getCurrentUrl() , DataUtility.getPropertyValue("Environment" , "CheckOutPageUrl"));
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quit();
        LogUtility.info("Browser is closed");
    }
}
