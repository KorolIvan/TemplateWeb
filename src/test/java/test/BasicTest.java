package test;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import korol.ivan.util.browserset.UseBrowser;

import java.io.IOException;


/**
 * @author by Ivan Korol on 5/29/2017.
 */
public class BasicTest {

    private static ThreadLocal<WebDriver> threadLocal;

    @BeforeClass
    public static void browserStartUp() throws IOException, InterruptedException {
        threadLocal = new ThreadLocal<>();
        threadLocal.set(new UseBrowser().getDriver("chrome"));
    }

    @BeforeClass
    public static void setUp() {
        // creating all required objects

    }

    @AfterClass
    public static void browserTearDown() {
        if (threadLocal != null) {
            try {
                // this command will close IE browser window
                Runtime.getRuntime().exec("taskkill /F /IM IEDriverServer.exe");
                Runtime.getRuntime().exec("taskkill /F /IM iexplore.exe");
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                threadLocal.get().quit();
            }
        }
    }


    private WebDriver getWebDriver() {
        return threadLocal.get();
    }


    @Test
    public void someTest() {
        getWebDriver().get("https://www.w3schools.com");
        Assert.assertFalse("some message", false);
    }
}
