package webTest;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import webTest.Open_browsers;
import webTest.page.SPGLogin;

public class DemoTest  extends  Open_browsersTest{
    @Parameters({"SPG_URl"})
    @Test
    public void SPGLogintest(String url)  {
        Open_browsers.getDriver().get(url);
        SPGLogin spgLogin = new SPGLogin();
        //高级按钮
        spgLogin.GJbutton.click();
        //继续前往按钮
        spgLogin.JXQWbutton.click();
        //验证是否进入到登录界面
        String aqkxwgone = "安全API网关V1.0";
        String aqkxwgone_erro = spgLogin.DLJMtext.getText();
        //System.out.println(aqkxwgone);
        if(aqkxwgone.equals(aqkxwgone_erro)){
            System.out.println("成功访问到安全API网关登录界面");
        }else {
            System.out.println("访问失败");
        }


    }
}
