import korol.ivan.util.browserset.cross.SetBrowsers;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import korol.ivan.util.browserset.simple.UseBrowser;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @author by Ivan Korol on 5/29/2017.
 */
public class BasicTest {

    private static ThreadLocal<WebDriver> threadLocal;

    /**
     * method getDriver waiting for argument as expected browser name:
     * windows OS:
     * - firefox
     * - chrome
     * - ie
     * linux OS:
     * - chrome
     * - firefox
     * mac OS:
     * - chrome
     * - safari (make sure that Allow Remote Automation selected under Develop menu item)
     */
    @BeforeClass
    public static void browserStartUp() throws IOException, InterruptedException {
        threadLocal = new ThreadLocal<>();
        threadLocal.set(new UseBrowser().getDriver("chrome"));
        /*ArrayList<String> browsersList = new ArrayList<>();
        browsersList.add("chrome");
        browsersList.add("firefox");
        for (int i = 0; i < browsersList.size(); i++) {

        }*/

    }

    @Before
    public void setUp() {
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
            }

            threadLocal.get().quit();
        }
    }


    private WebDriver getWebDriver() {
        return threadLocal.get();
    }

    @Test
    public void someTest() {
        getWebDriver().get("https://www.w3schools.com");
    }
}
