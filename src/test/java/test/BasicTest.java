package test;

import korol.ivan.commo.locators.ElementType;
import korol.ivan.pages.TestPage;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import korol.ivan.util.browserset.UseBrowser;

import java.io.IOException;

/**
 * @author by Ivan Korol on 5/29/2017.
 */
public class BasicTest {

    private static ThreadLocal<WebDriver> threadLocal;

    protected TestPage testPage = null;

    @BeforeClass
    public static void setUp() throws IOException, InterruptedException {
        threadLocal = new ThreadLocal<>();
        threadLocal.set(new UseBrowser().getDriver("chrome"));
    }

    @Before
    public void setAllObjects() {
        testPage = new TestPage(getWebDriver());
    }

    @AfterClass
    public static void tearDown() {
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
        testPage.open();
        testPage.selectElementWithLabel(ElementType.CHECKBOX, "onions", true);
        try{
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertFalse("some message", false);
    }
}
