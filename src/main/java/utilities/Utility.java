package utilities;

import io.qameta.allure.Allure;
import org.aspectj.util.FileUtil;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.util.*;

public class Utility {
    private static final String SCREENSHOTPATH = "Test-output/screenshots/";
    // Clicking on element
    public static void clickOnElement(WebDriver driver, By locator)
    {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(locator));
        driver.findElement(locator).click();

    }
    // Sending date to elements
    public static void sendData(WebDriver driver,By locator,String text)
    {
        new WebDriverWait(driver,Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
        driver.findElement(locator).sendKeys(text);
    }
    // generate visibility wait
    public static void waitForVisibility(WebDriver driver, By locator) {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static void waitForElementToBeClickable(WebDriver driver, By cartButton) {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(cartButton));
    }

    // get alert message

    public static String getAlertMessageThenAccept(WebDriver driver){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.alertIsPresent());
        String alertText = driver.switchTo().alert().getText();
        driver.switchTo().alert().accept();
        return alertText;
    }

    //Method  to accept the alert
    public static void acceptAlert(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
    }

    //Method to get text from element
    public static String getTextFromElement(WebDriver driver, By locator) {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
        return driver.findElement(locator).getText();
    }

    // Method to general wait
    public static void waitForElement(WebDriver driver, By locator) {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    //method of scrolling
    public static void scrollToElement(WebDriver driver, By locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(locator));
    }
    //Method to take screenshot
    public static void takeScreenshot(WebDriver driver, String screenShotName) {
        try {
            File screenshotSrc = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            File screenshot = new File(SCREENSHOTPATH + screenShotName+ "-" + getCurrentTime() + ".png");
            FileUtil.copyFile(screenshotSrc, screenshot);

            Allure.addAttachment(screenShotName, Files.newInputStream(Path.of(screenshot.getPath())));
        }
        catch (Exception e) {
           e.printStackTrace();
        }
    }
    // create method to get current date
    public static String getCurrentTime() {
        java.util.Date date = new java.util.Date();
        return date.toString().replace(":", "-").replace(" ", "_");
    }
    // method to select from dropdown
    public static void selectFromDropdown(WebDriver driver, By locator, String value) {
        WebElement dropdown = driver.findElement(locator);
        dropdown.click();
        WebElement option = dropdown.findElement(By.xpath("//option[text()='" + value + "']"));
        option.click();
    }
    public static File getLatestFile(String folderPath) {
        File folder = new File(folderPath);
        File[] files = folder.listFiles();
        assert files != null;
        if (files.length == 0)
            return null;
        Arrays.sort(files, Comparator.comparingLong(File::lastModified).reversed());

        return files[0];
    }
    public static int generateRandomNumber(int upper){
        return new Random().nextInt(upper)+1;
    }
    //Generate set with unique random numbers
    public static Set<Integer> generateUniqueRandomNumbers(int numberOfProductToSelect, int NumberOfAllProducts) {
        Set<Integer> uniqueNumbers = new HashSet<>();
        while (uniqueNumbers.size() < numberOfProductToSelect) {
            int randomNumber = generateRandomNumber(NumberOfAllProducts) ;
            uniqueNumbers.add(randomNumber);
        }
        return uniqueNumbers;
    }
}
