package test;

import org.openqa.selenium.WebDriver;
import korol.ivan.util.browserset.UseBrowser;
import org.testng.Assert;
import org.testng.annotations.*;
import ru.yandex.qatools.allure.annotations.Step;

import java.io.IOException;


/**
 * @author by Ivan Korol on 5/29/2017.
 */
public class BasicTest {

    private static ThreadLocal<WebDriver> threadLocal;

    @BeforeTest
    @Parameters({"browserName"})
    public static void browserStartUp(String browserName) throws IOException, InterruptedException {
        threadLocal = new ThreadLocal<>();
        threadLocal.set(new UseBrowser().getDriver(browserName));
    }

    @BeforeClass
    public void setUp() {
        // creating all required objects

    }

    @AfterTest
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


    @Test(description = "open w3school site on the browser")
    public void someTest() {
        getWebDriver().get("https://www.w3schools.com");
        Assert.assertFalse(false , "some message");
    }
}
