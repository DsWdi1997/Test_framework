package webTest;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import static org.testng.Assert.*;
/**

 */

public class Open_browsersTest  {
    Open_browsers open_browsers = new Open_browsers();
    @BeforeMethod(alwaysRun = true)
    @Parameters({"DriverName", "ChromeDriverPath", "IeDriverPath", "FirefoxDriverPath"})
    public void setUp(@Optional("firefox") String driverName,
                      @Optional("chrome") String chromrPath,
                      @Optional("IE") String iePath,
                      @Optional("moren") String firefoxPath) {
        open_browsers.setupDriver(driverName, chromrPath, iePath, firefoxPath);

    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() throws InterruptedException {
        //Thread.sleep(10000);
        //open_browsers.quitDriver();
    }

}