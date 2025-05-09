package DriverFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class DriverFactory {
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();


    public static void setDriver(String webDriver) {
        switch (webDriver.toLowerCase()){
            case "chrome":
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--start-maximized");
                driver.set(new ChromeDriver(options));
                break;
            case "firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("--start-maximized");
                driver.set(new FirefoxDriver(firefoxOptions));
                break;
            default:
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.addArguments("--start-maximized");
                driver.set(new EdgeDriver(edgeOptions));
                break;
        }
    }
    public static WebDriver getDriver() {
        return driver.get();
    }
    public static void quit() {
        driver.get().quit();
        driver.remove();
    }
}
