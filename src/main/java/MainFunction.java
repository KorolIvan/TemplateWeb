import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author by Ivan Korol on 5/29/2017.
 */
public abstract class MainFunction {

    protected WebDriver driver;

    public MainFunction(WebDriver driver) {
        this.driver = driver;
        new WebDriverWait(this.driver, 10);
        PageFactory.initElements(this.driver, this);
    }

    public abstract void open();

    protected void type(WebElement element, String txt) {
        element.clear();
        element.sendKeys(txt);
    }

    protected boolean isElementPresent(By locator) {

        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        List<WebElement> list = driver.findElements(locator);
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        if (list.size() == 0) {
            return false;
        } else {
            return list.get(0).isDisplayed();
        }

    }

    protected final void takeScreenShot(String fileName) {

        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            org.apache.commons.io.FileUtils.copyFile(src, new File("./Results/" + fileName + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void sleepSecond(int second) {
        try {
            Thread.sleep(second * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    protected void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            System.out.println("cannot scroll to element");
        }

    }

    protected String getTextFromWebElement(WebElement element) {
        return element.getText();
    }



}
