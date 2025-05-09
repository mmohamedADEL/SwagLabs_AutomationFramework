package Tests;

import DriverFactory.DriverFactory;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
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
public class CheckOutTest {
    @BeforeTest
    public void setUp() throws IOException {
        setDriver(DataUtility.getPropertyValue("Environment" , "Browser"));
        LogUtility.info("Browser is launched");
        getDriver().get(DataUtility.getPropertyValue("Environment" , "BaseUrl"));
        LogUtility.info("Redirected to the URL");
        getDriver().manage()
                .timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void redirectToOverviewPage() throws IOException {
        new LoginPage(getDriver())
                .enterUsername(DataUtility.getJsonData("LoginData" , "validLogin" , "username"))
                .enterPassword(DataUtility.getJsonData("LoginData" , "validLogin" , "password"))
                .clickOnLoginButton()
                .addRandomProductToCart(3 , 6)
                .clickOnCartLink()
                .clickOnCheckoutButton()
                .fillCheckoutForm(DataUtility.getJsonData("CheckOutData" ,"validData", "FirstName"),
                        DataUtility.getJsonData("CheckOutData" ,"validData", "LastName"),
                        DataUtility.getJsonData("CheckOutData" ,"validData", "postalCode"))
                .clickOnContinueButton();
        LogUtility.info("Data entered in the fields");
        Assert.assertEquals(getDriver().getCurrentUrl() , DataUtility.getPropertyValue("Environment" , "OverviewPageURL"));
    }


    @AfterTest
    public void tearDown() {
        DriverFactory.quit();
        LogUtility.info("Browser is closed");
    }
}
