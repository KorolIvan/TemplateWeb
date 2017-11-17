package korol.ivan.util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

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

    //commands to cmd for linux
    private static final String CHECK_CHROME_LINUX = "dpkg -s chromium-browser";
    private static final String CHECK_FIREFOX_LINUX = "dpkg -s firefox";
    //todo add required to the system win and mac commands
    //commands to cmd for windows
    private static final String CHECK_CHROME_WINDOWS = "";
    private static final String CHECK_FIREFOX_WINDOWS = "";

    //commands to cmd for mac
    private static final String CHECK_CHROME_MAC = "";
    private static final String CHECK_FIREFOX_MAC = "";

    //path for windows os
    private static final String CHROME_DRIVER_PATH = "src/main/resources/driver/win/chromedriver.exe";
    private static final String IE_DRIVER_PATH = "src/main/resources/driver/win/IEDriverServer.exe";
    private static final String FIREFOX_DRIVER_PATH = "src/main/resources/driver/win/geckodriver_winX64.exe";

    //path for linux os
    private static final String FIREFOX_DRIVER_PATH_LINUX = "src/main/resources/driver/linux/geckodriver";
    private static final String CHROME_DRIVER_PATH_LINUX = "src/main/resources/driver/linux/chromedriver";

    //todo add to resources and write over here
    //path for mac os
    //private static final String SAFARY_DRIVER_PATH_MAC = "src/main/resources/driver/";
    private static final String CHROME_DRIVER_PATH_MAC = "src/main/resources/driver/mac/chromedriver";

    //todo call console check the browsers and return the current browser
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
            }
            line = builder.toString();
            //System.out.println(line);
            for(int j = 0; j < browsers.length; j++){
                if (line.contains(browsers[j]) && line.contains("installed")) {
                    //System.out.println(browsers[j]);
                    existBrowser = browsers[j];
                    break first;
                }
            }
            process.waitFor();
        }
        return existBrowser;
    }

    private String operationSystem() {
        return new OSSystem().getOs();
    }

    public WebDriver getDriver() throws IOException, InterruptedException {
        switch (operationSystem()) {
            case "windows":
                switch (getBrowser()) {
                    case "firefox":
                        System.setProperty("webdriver.gecko.driver", FIREFOX_DRIVER_PATH);
                        driver = new FirefoxDriver();
                        break;
                    case "chrom":
                        System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH);
                        driver = new ChromeDriver();
                        driver.manage().window().maximize();
                        break;
                    case "ie":
                        System.setProperty("webdriver.ie.driver", IE_DRIVER_PATH);
//                        DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
//                        caps.setCapability("ignoreZoomSetting", true);
                        driver = new InternetExplorerDriver();
                        break;

                }


            case "linux":
                switch (getBrowser()) {
                    case "firefox":
                        System.setProperty("webdriver.gecko.driver", FIREFOX_DRIVER_PATH_LINUX);
                        driver = new FirefoxDriver();
                        break;
                    case "chrom":
                        System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH_LINUX);
                        driver = new ChromeDriver();
                        driver.manage().window().maximize();
                        break;
                }
//
//            case "mac":
//                switch (browser) {
//                    case "firefox":
//                        System.setProperty("webdriver.gecko.driver", FIREFOX_DRIVER_PATH);
//                        driver = new FirefoxDriver();
//                        break;
//                    case "chrome":
//                        System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH);
//                        driver = new ChromeDriver();
//                        driver.manage().window().maximize();
//                        break;
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
        return driver;
    }
    //todo set value which will contain the browser name
    private List<String> getCommand () {
        List<String> comands = new ArrayList<String>();

        switch (operationSystem()) {
            case "windows":

            case "linux":
                comands.add(CHECK_CHROME_LINUX);
                comands.add(CHECK_FIREFOX_LINUX);
                break;

            case "mac":

                default:
                    return null;
        }

        return comands;
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println(new UseBrowser().getBrowser());
    }
}