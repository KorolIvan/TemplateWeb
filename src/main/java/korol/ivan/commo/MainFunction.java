package korol.ivan.commo;

import korol.ivan.commo.steps.StepsImpl;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.qatools.allure.annotations.Attachment;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author by Ivan Korol on 11/8/2017.
 */
public abstract class MainFunction extends StepsImpl {
    protected WebDriver driver;

    public MainFunction(WebDriver driver) {
        super(driver);
        this.driver = driver;
        new WebDriverWait(this.driver, 10);
        PageFactory.initElements(this.driver, this);
    }

    public abstract void open();

    protected boolean isElementPresent(By locator) {

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        List<WebElement> list = driver.findElements(locator);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        if (list.size() == 0) {
            return false;
        } else {
            return list.get(0).isDisplayed();
        }

    }

    @Attachment(value = "attached screenshot", type = "image/png")
    private byte[] screenShotForAllure() {
        return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
    }

    protected void takeScreenShot(String folder, String fileName) {
        screenShotForAllure();
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            if (folder == null || folder.equals("")) {
                org.apache.commons.io.FileUtils.copyFile(src, new File("target/screenShots/" + fileName + ".png"));
            } else{
                org.apache.commons.io.FileUtils.copyFile(src, new File("target/screenShots/"+ folder +"/" + fileName + ".png"));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void sleepSecond(int second) {
        driver.manage().timeouts().implicitlyWait(second, TimeUnit.SECONDS);
    }

    protected String getTextFromWebElement(WebElement element) {
        return element.getText();
    }

    public void waitForElementPresent(WebElement element, int duration, By locator) {
        element = new WebDriverWait(this.driver, duration).until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public boolean isElementVisible(By locator){
        WebDriverWait wait = new WebDriverWait(this.driver, 3);
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator)).isDisplayed();
    }

    public List<WebElement> getListOfWebElements (By locator) {
        List<WebElement> listOfElements = driver.findElements(locator);
        return listOfElements;
    }
}
