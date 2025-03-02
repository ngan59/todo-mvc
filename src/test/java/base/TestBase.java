package base;

import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;

import static common.Browser.captureScreenShot;
import static common.Browser.closeBrowser;

public class TestBase {
    //chụp màn hình mỗi khi fail
    @AfterMethod(alwaysRun = true)
   protected void tearDown(ITestResult testResult) {
        String tcName = testResult.getMethod().getMethodName();
        if(!testResult.isSuccess()){
            captureScreenShot(tcName);
            }
        }
    @AfterClass
    protected void tearDown(){
        closeBrowser();
    }
}
