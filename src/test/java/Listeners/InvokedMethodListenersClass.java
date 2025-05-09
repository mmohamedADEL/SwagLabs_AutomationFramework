package Listeners;

import io.qameta.allure.Allure;
import org.testng.IInvokedMethodListener;
import org.testng.ITestContext;
import org.testng.ITestResult;
import utilities.LogUtility;
import utilities.Utility;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static DriverFactory.DriverFactory.getDriver;
import static utilities.Utility.takeScreenshot;

public class InvokedMethodListenersClass implements IInvokedMethodListener {

    @Override
    public void afterInvocation(org.testng.IInvokedMethod method, org.testng.ITestResult testResult , ITestContext context) {

        if(testResult.getStatus() == ITestResult.FAILURE){
            LogUtility.error("Test failed: " + testResult.getName());
            takeScreenshot(getDriver(),testResult.getName());
        }
        try {
            File logFile = Utility.getLatestFile(LogUtility.LOGS_PATH);
            assert logFile != null;
            Allure.addAttachment("logs.log", Files.readString(Path.of(logFile.getPath())));
        } catch (IOException e) {
            LogUtility.error(e.getMessage());
        }
    }

}
