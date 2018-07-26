package korol.ivan.util.browserset.simple;

import korol.ivan.util.browserset.OSSystem;
import korol.ivan.util.logger.EventHandler;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author by Ivan Korol on 11/8/2017.
 */
public class UseBrowser {

    private WebDriver driver;
    private EventFiringWebDriver wrappedDriver;

    //path for windows os
    private static final String CHROME_DRIVER_PATH = "src/main/resources/drivers/win/chromedriver.exe";
    private static final String IE_DRIVER_PATH = "src/main/resources/drivers/win/IEDriverServer.exe";
    private static final String FIREFOX_DRIVER_PATH = "src/main/resources/drivers/win/geckodriver_winX64.exe";

    //path for linux os
    private static final String FIREFOX_DRIVER_PATH_LINUX = "src/main/resources/drivers /linux/geckodriver";
    private static final String CHROME_DRIVER_PATH_LINUX = "src/main/resources/drivers/linux/chromedriver";
    //private static final String CHROME_DRIVER_PATH_LINUX = "/var/lib/jenkins/drivers/chromedriver";


    //path for mac os
    //с сафари немного не удобно так как надо ставить отдельно расширение в сам браузер чтоб запускать тесты
    //private static final String SAFARY_DRIVER_PATH_MAC = "src/main/resources/driver/";
    private static final String CHROME_DRIVER_PATH_MAC = "src/main/resources/drivers/mac/chromedriver";

    private String operationSystem() {
        return new OSSystem().getOs();
    }

    //public WebDriver getDriver() throws IOException, InterruptedException {
    public EventFiringWebDriver getDriver(String browserName) throws IOException, InterruptedException {
        switch (operationSystem()) {
            case "windows":
                //switch (getBrowser()) {
                switch (browserName) {
                    case "firefox":
                        System.setProperty("webdriver.gecko.driver", FIREFOX_DRIVER_PATH);
                        driver = new FirefoxDriver();
                        wrappedDriver = new EventFiringWebDriver(driver);
                        wrappedDriver.register(new EventHandler());
                        break;
                    case "chrome":
                        System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH);
                        driver = new ChromeDriver();
                        driver.manage().window().maximize();
//                driver.manage().window().setSize(new Dimension(1024, 758));
                        wrappedDriver = new EventFiringWebDriver(driver);
                        wrappedDriver.register(new EventHandler());
                        break;
                    case "ie":
                        System.setProperty("webdriver.ie.driver", IE_DRIVER_PATH);
                        DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
                        caps.setCapability(InternetExplorerDriver.NATIVE_EVENTS, false);
                        caps.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
                        caps.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
                        caps.setCapability("allow-blocked-content", true);
                        caps.setCapability("allowBlockedContent", true);
                        driver = new InternetExplorerDriver();
                        wrappedDriver = new EventFiringWebDriver(driver);
                        wrappedDriver.register(new EventHandler());
                        break;
                    default:
                        throw new IllegalArgumentException("check browser name");
//
                }
                break;
            case "linux":
                switch (browserName) {
                    case "firefox":
                        System.setProperty("webdriver.gecko.driver", FIREFOX_DRIVER_PATH_LINUX);
                        driver = new FirefoxDriver();
                        wrappedDriver = new EventFiringWebDriver(driver);
                        wrappedDriver.register(new EventHandler());
                        break;
                    case "chrome":
                        System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH_LINUX);
                        driver = new ChromeDriver();
//                        driver.manage().window().maximize();
                        driver.manage().window().setSize(new Dimension(1024, 758));
                        wrappedDriver = new EventFiringWebDriver(driver);
                        wrappedDriver.register(new EventHandler());
                        break;
                    default:
                        throw new IllegalArgumentException("check browser name");
                }

            case "mac":
//                switch (getBrowser()) {
                switch (browserName) {
                    case "firefox":
                        System.setProperty("webdriver.gecko.driver", FIREFOX_DRIVER_PATH);
                        driver = new FirefoxDriver();
                        wrappedDriver = new EventFiringWebDriver(driver);
                        wrappedDriver.register(new EventHandler());
                        break;
                    case "chrome":
                        System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH_MAC);
                        driver = new ChromeDriver();
                        //driver.manage().window().maximize();
                        driver.manage().window().setSize(new Dimension(1024, 758));
                        wrappedDriver = new EventFiringWebDriver(driver);
                        wrappedDriver.register(new EventHandler());
                        break;
                    case "safari":
                        driver = new SafariDriver();
                        wrappedDriver = new EventFiringWebDriver(driver);
                        wrappedDriver.register(new EventHandler());
                        break;
                    default:
                        throw new IllegalArgumentException("check browser name");

                }

            default:
                driver = null;
                throw new IllegalArgumentException("unknown operation system name");
        }
        return wrappedDriver;
    }

}