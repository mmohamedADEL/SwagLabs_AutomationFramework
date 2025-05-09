package Tests;

import DriverFactory.DriverFactory;
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
public class LoginTest {
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
    public void validLoginTest() throws IOException {
         new LoginPage(getDriver())
                .enterUsername(DataUtility.getJsonData("LoginData" , "validLogin" , "username"))
                .enterPassword(DataUtility.getJsonData("LoginData" , "validLogin" , "password"))
                .clickOnLoginButton();
        LogUtility.info("Data entered in the fields");
        LogUtility.info("Login button clicked");
        new LoginPage(getDriver()).assertHomeUrl(DataUtility.getPropertyValue("Environment" , "HomePageURL"));
    }

    @AfterTest
    public void tearDown() {
        DriverFactory.quit();
        LogUtility.info("Browser is closed");
    }
}
