package Listeners;

import org.testng.IInvokedMethodListener;
import org.testng.ITestContext;
import org.testng.ITestResult;

import static DriverFactory.DriverFactory.getDriver;
import static utilities.Utility.takeScreenshot;

public class InvokedMethodListenersClass implements IInvokedMethodListener {


    @Override
    public void afterInvocation(org.testng.IInvokedMethod method, org.testng.ITestResult testResult , ITestContext context) {
        if(testResult.getStatus() == ITestResult.FAILURE){
            takeScreenshot(getDriver(),testResult.getName());
        }
    }

}
