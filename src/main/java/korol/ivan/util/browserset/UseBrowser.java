package korol.ivan.util.browserset;

import korol.ivan.util.logger.EventHandler;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.io.File;
import java.io.IOException;

/**
 * @author by Ivan Korol on 11/8/2017.
 */
public class UseBrowser {

    private WebDriver driver;
    private EventFiringWebDriver wrappedDriver;
    private Process executingPermission;
    private static String pathToDrivers = new File("src/main/resources/drivers/").getAbsolutePath();

    //path to browsers drivers for windows os
    private static final String CHROME_DRIVER_PATH = pathToDrivers + "/win/chromedriver.exe";
    private static final String IE_DRIVER_PATH = pathToDrivers + "/win/IEDriverServer.exe";
    private static final String FIREFOX_DRIVER_PATH = pathToDrivers + "/win/geckodriver.exe";

    //path to browsers drivers for linux os
    private static final String CHROME_DRIVER_PATH_LINUX = pathToDrivers + "/linux/chromedriver";
    private static final String FIREFOX_DRIVER_PATH_LINUX = pathToDrivers + "/linux/geckodriver";

    //this commands will run before set the browser to run
    private static String executePermission = "chmod +x ";

    //path to browsers drivers for mac os
    //for safari browser need more investigation on how to run the tests
    //private static final String SAFARY_DRIVER_PATH_MAC = "src/main/resources/driver/";
    private static final String CHROME_DRIVER_PATH_MAC = pathToDrivers + "/mac/chromedriver";

    private String operationSystem() {
        return new OSSystem().getOs();
    }

    public EventFiringWebDriver getDriver(String browserName) throws IOException, InterruptedException {
        switch (operationSystem()) {
            case "windows":
                getDriverForWindows(browserName);
                break;
            case "linux":
                getDriverForLinux(browserName);
                break;
            case "mac":
                getDriverForMac(browserName);
                break;
            default:
                driver = null;
                throw new IllegalArgumentException("unknown operation system name");
        }
        return wrappedDriver;
    }

    private EventFiringWebDriver getDriverForWindows(String browserName) {
        switch (browserName) {
            case "firefox":
                System.setProperty("webdriver.gecko.driver", FIREFOX_DRIVER_PATH);
                driver = new FirefoxDriver();
//        driver.manage().window().maximize();
                driver.manage().window().setSize(new Dimension(1366, 900));
                wrappedDriver = new EventFiringWebDriver(driver);
                return wrappedDriver.register(new EventHandler());
            case "chrome":
                System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH);
                driver = new ChromeDriver();
//        driver.manage().window().maximize();
                driver.manage().window().setSize(new Dimension(1366, 900));
                wrappedDriver = new EventFiringWebDriver(driver);
                return wrappedDriver.register(new EventHandler());
            case "ie":
                System.setProperty("webdriver.ie.driver", IE_DRIVER_PATH);
                DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
                caps.setCapability(InternetExplorerDriver.NATIVE_EVENTS, false);
                caps.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
                caps.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
                caps.setCapability("allow-blocked-content", true);
                caps.setCapability("allowBlockedContent", true);
                driver = new InternetExplorerDriver(caps);
                driver.manage().window().setSize(new Dimension(1366, 900));
                wrappedDriver = new EventFiringWebDriver(driver);
                return wrappedDriver.register(new EventHandler());
            default:
                throw new IllegalArgumentException("check browser name");
        }
    }

    private EventFiringWebDriver getDriverForLinux(String browserName) throws IOException {
        switch (browserName) {
            case "firefox":
                System.setProperty("webdriver.gecko.driver", FIREFOX_DRIVER_PATH_LINUX);
                executingPermission = Runtime.getRuntime().exec(executePermission + FIREFOX_DRIVER_PATH_LINUX);
                driver = new FirefoxDriver();
//        driver.manage().window().maximize();
                driver.manage().window().setSize(new Dimension(1366, 900));
                wrappedDriver = new EventFiringWebDriver(driver);
                return wrappedDriver.register(new EventHandler());
            case "chrome":
                System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH_LINUX);
                executingPermission = Runtime.getRuntime().exec(executePermission + CHROME_DRIVER_PATH_LINUX);
                driver = new ChromeDriver();
//        driver.manage().window().maximize();
                driver.manage().window().setSize(new Dimension(1366, 900));
                wrappedDriver = new EventFiringWebDriver(driver);
                return wrappedDriver.register(new EventHandler());
            default:
                throw new IllegalArgumentException("check browser name");
        }
    }

    private EventFiringWebDriver getDriverForMac(String browserName) {
        switch (browserName) {
            case "chrome":
                System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH_MAC);
                new File(CHROME_DRIVER_PATH_MAC).setExecutable(true);
                driver = new ChromeDriver();
//        driver.manage().window().maximize();
                driver.manage().window().setSize(new Dimension(1366, 900));
                wrappedDriver = new EventFiringWebDriver(driver);
                return wrappedDriver.register(new EventHandler());
            case "safari":
                driver = new SafariDriver();
                driver.manage().window().setSize(new Dimension(1366, 900));
                wrappedDriver = new EventFiringWebDriver(driver);
                return wrappedDriver.register(new EventHandler());
            default:
                throw new IllegalArgumentException("check browser name");
        }
    }
}