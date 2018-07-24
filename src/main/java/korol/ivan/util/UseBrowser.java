package korol.ivan.util;

import korol.ivan.util.logger.EventHandler;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
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

    //commands to cmd for linux
    private static final String CHECK_CHROME_LINUX = "dpkg -s chromium-browser";
    private static final String CHECK_FIREFOX_LINUX = "dpkg -s firefox";

    //commands to cmd for windows
    private static final String COMMAND_CHECK_ALL_EXIT_PROGRAM = "powershell.exe \"Get-ItemProperty HKLM:\\Software\\Microsoft\\Windows\\CurrentVersion\\Uninstall\\* | Select-Object DisplayName, InstallDate | Format-Table –AutoSize\"";
    //todo commands to get installed browsers
    //commands to cmd for mac
    private static final String CHECK_CHROME_MAC = "";
    private static final String CHECK_FIREFOX_MAC = "";

    //path for windows os
    private static final String CHROME_DRIVER_PATH = "src/main/resources/driver/windows/chromedriver.exe";
    private static final String IE_DRIVER_PATH = "src/main/resources/driver//windows/IEDriverServer.exe";
    private static final String FIREFOX_DRIVER_PATH = "src/main/resources/driver/windows/geckodriver_winX64.exe";

    //path for linux os
    private static final String FIREFOX_DRIVER_PATH_LINUX = "src/main/resources/driver/linux/geckodriver";
    //private static final String CHROME_DRIVER_PATH_LINUX = "src/main/resources/driver/linux/chromedriver";
    private static final String CHROME_DRIVER_PATH_LINUX = "/var/lib/jenkins/drivers/chromedriver";


    //path for mac os
    //с сафари немного не удобно так как надо ставить отдельно расширение в сам браузер чтоб запускать тесты
    //private static final String SAFARY_DRIVER_PATH_MAC = "src/main/resources/driver/";
    private static final String CHROME_DRIVER_PATH_MAC = "src/main/resources/driver/mac/chromedriver";

    private String operationSystem() {
        return new OSSystem().getOs();
    }

    //public WebDriver getDriver() throws IOException, InterruptedException {
    public EventFiringWebDriver getDriver() throws IOException, InterruptedException {
        switch (operationSystem()){
            case "windows" :
//                switch (getBrowser()) {
//                    case "firefox":
//                        System.setProperty("webdriver.gecko.driver", FIREFOX_DRIVER_PATH);
//                        driver = new FirefoxDriver();
//                        break;
//                    case "chrom":
                System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH);
                driver = new ChromeDriver();
                driver.manage().window().maximize();
//                driver.manage().window().setSize(new Dimension(1024, 758));
                wrappedDriver = new EventFiringWebDriver(driver);
                wrappedDriver.register(new EventHandler());
                break;
//                    case "ie":
//                        System.setProperty("webdriver.ie.driver", IE_DRIVER_PATH);
////                        DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
////                        caps.setCapability("ignoreZoomSetting", true);
//                        driver = new InternetExplorerDriver();
//                        break;
//
//                }


            case "linux":
//                switch (getBrowser()) {
//                    case "firefox":
//                        System.setProperty("webdriver.gecko.driver", FIREFOX_DRIVER_PATH_LINUX);
//                        driver = new FirefoxDriver();
//                        break;
//                    case "chrom":
                System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH_LINUX);
                driver = new ChromeDriver();
//                        driver.manage().window().maximize();
                driver.manage().window().setSize(new Dimension(1024, 758));
                wrappedDriver = new EventFiringWebDriver(driver);
                wrappedDriver.register(new EventHandler());
                break;
//                }

            case "mac":
//                switch (getBrowser()) {
//                    case "firefox":
//                        System.setProperty("webdriver.gecko.driver", FIREFOX_DRIVER_PATH);
//                        driver = new FirefoxDriver();
//                        break;
//                    case "chrome":
                System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH);
                driver = new ChromeDriver();
                //driver.manage().window().maximize();
                driver.manage().window().setSize(new Dimension(1024, 758));
                wrappedDriver = new EventFiringWebDriver(driver);
                wrappedDriver.register(new EventHandler());
                break;
//                    case "safari":
//                        System.setProperty("webdriver.safari.driver", IE_DRIVER_PATH);
//                        driver = new SafariDriver();
//                        break;
//
//                }

//            default:
//                System.out.println("other");
//                driver = null;
//                break;
        }
        //return driver;
        return wrappedDriver;
    }

    //todo executable browser driver


    private String getBrowser () throws InterruptedException, IOException {
        StringBuilder builder = new StringBuilder();
        String command;
        String line;
        String existBrowser = null;
        String[] browsers = {"chrom", "firefox", "internet explorer", "ie", "safari"};
        first: for (int i = 0; i < getCommand().size(); i++) {
            command = getCommand().get(i);
            Process process = Runtime.getRuntime().exec(command);
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            while ((line = reader.readLine()) != null) {
                builder.append(line + "\n");

                for(int j = 0; j < browsers.length; j++){
                    if (operationSystem().equals("linux")) {
                        if (line.toLowerCase().contains(browsers[j]) && line.toLowerCase().contains("installed")) {
                            //System.out.println(browsers[j]);
                            existBrowser = browsers[j];
                            break first;
                        }
                    } else if (operationSystem().equals("windows")) {
                        if (line.toLowerCase().contains(browsers[j])) {
                            //System.out.println(browsers[j]);
                            existBrowser = browsers[j];
                            break first;
                        }
                    }

                }
            }
            //line = builder.toString();
            //System.out.println(line);

            process.waitFor();
        }
        return existBrowser;
    }

    private List<String> getCommand () {
        List<String> comands = new ArrayList<String>();

        switch (operationSystem()) {
            case "windows":
                comands.add(COMMAND_CHECK_ALL_EXIT_PROGRAM);
            case "linux":
                comands.add(CHECK_CHROME_LINUX);
                comands.add(CHECK_FIREFOX_LINUX);
                break;

            case "mac":
                //comands.add("");
            default:
                return null;
        }

        return comands;
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println(new UseBrowser().getBrowser());
    }
}