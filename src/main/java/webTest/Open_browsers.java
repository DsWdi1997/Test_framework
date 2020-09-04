package webTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import webTest.listener.LogEventListener;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Open_browsers  {
    /*
        所有TestNG TestCase都继承这个类。 这个类的功能是：
        1，让testNG可以设置测试执行的浏览器类型
     */

        /**
         * 每个DriverManager只管理一个driver，所以他是static的，但是我引入ThreadLocal来处理多线程
         */
        public static ThreadLocal<WebDriver> ThreadDriver = new ThreadLocal<WebDriver>();

        /**
         * 当TestCase要设置浏览器类型时其实只是设置了browserType这个字段。
         * 而真正的新建driver是在Page类需要用到driver时
         * Page的构造方法里调用getDriver，然后getDriver检测发现当前线程没有driver时才会真正新建一个driver.
         */
        public static String browserType = null;
        /**
         * 如果当前进程没有绑定driver，创建一个然后绑定上，如果已经有了就直接返回
         */
        private static WebDriver driver = Open_browsers.ThreadDriver.get();

        public static WebDriver getDriver () {
        if (driver == null) {
            switch (browserType) {
                case "ie":
                    driver = new EventFiringWebDriver(new InternetExplorerDriver()).register(new LogEventListener());
                    ThreadDriver.set(driver);
                    break;
                case "chrome":
                    driver = new EventFiringWebDriver(new ChromeDriver()).register(new LogEventListener());
                    ThreadDriver.set(driver);
                    break;
                case "firefox":
                    driver = new EventFiringWebDriver(new FirefoxDriver()).register(new LogEventListener());
                    ThreadDriver.set(driver);
                    break;
            }
            // 找东西前等3秒
            Open_browsers.getDriver().manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        }

        return driver;
    }

        /**
         * 根据TestCase的要求来指定浏览器类型但不创建他
         *
         * @param driverName    浏览器名字
         * @param chromrPath    chromeDriver目录
         * @param iePath    ieServerDriver目录
         * @param firefoxPath    firefox.exe目录
         */
        public static void setupDriver (String driverName, String chromrPath, String iePath, String firefoxPath){
        browserType = driverName;
            switch (browserType) {
                case "ie":
                    System.out.println("IE浏览器Iedriver驱动地址:" + iePath);
                    System.setProperty("webdriver." + browserType + ".driver", iePath);
                    break;
                case "chrome":
                    System.out.println("谷歌ChromeDriver驱动地址:" + chromrPath);
                    System.setProperty("webdriver." + browserType + ".driver", chromrPath);
                    break;
                case "firefox":
                    if (!firefoxPath.equals("moren")) {
                        System.setProperty("webdriver" + browserType + ".bin", firefoxPath);
                    }
                    break;
        }
    }

        /**
         * 关浏览器，Windows上需要在这里杀进程的步骤
         */
        public static void quitDriver () {
        getDriver().quit();
        Open_browsers.ThreadDriver.set(null);
    }
    }
