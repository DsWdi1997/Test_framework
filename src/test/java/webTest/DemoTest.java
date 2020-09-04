package webTest;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import webTest.Open_browsers;

public class DemoTest  extends  Open_browsersTest{
    @Parameters({"baidu_url"})
    @Test
    public void VisitBaidu1(String url)  {
        Open_browsers.getDriver().get(url);
    }
}
