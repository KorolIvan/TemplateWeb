import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

import java.io.File;

/**
 * @author by Ivan Korol on 5/29/2017.
 */
public class BasicTest {

    private static File pathBinary = new File("C:\\Program Files\\Mozilla Firefox\\firefox.exe");
    //private static File pathBinary = new File("/usr/lib/firefox/firefox.sh");
    private static FirefoxBinary firefoxBinary = new FirefoxBinary(pathBinary);
    private static FirefoxProfile firefoxProfile = new FirefoxProfile();

    private static ThreadLocal<WebDriver> threadLocal;

    @BeforeClass
    public void browserStartUp() {
        threadLocal = new ThreadLocal<>();
        threadLocal.set(new FirefoxDriver(firefoxBinary, firefoxProfile));
    }

    @Before
    public void setUp() {
        // creating all required objects

    }

    @AfterClass
    public void browserTearDown() {
        if(threadLocal != null) {
            threadLocal.get().close();
            threadLocal.get().quit();
        }
    }


    private WebDriver getWebDriver(){
        return threadLocal.get();
    }
}
