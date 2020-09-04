package webTest.page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import webTest.Page;

public class SPGLogin extends Page {
        //高级按钮
        @FindBy(id = "details-button")
        public WebElement GJbutton ;

        //继续前往
        @FindBy(id = "proceed-link")
        public  WebElement JXQWbutton ;

        //验证是否进入到登录界面
        @FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/form/h2")
        public  WebElement DLJMtext;
}
