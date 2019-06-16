package korol.ivan.commo.steps;

import korol.ivan.commo.locators.Element;
import korol.ivan.commo.locators.ElementType;
import korol.ivan.commo.locators.LocatorsManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import ru.yandex.qatools.allure.annotations.Step;

import java.util.ArrayList;

/**
 * @author Ivan Korol on 7/18/2018
 */
public class StepsImpl extends LocatorsManager implements Steps {

    private WebDriver driver;

    public StepsImpl(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    @Step(value = "enter text - \"{1}\" to input field")
    @Override
    public void type(WebElement element, String txt) {
        try {
            element.clear();
        }catch (NullPointerException e) {
            System.out.println("element is empty");
        }
        element.sendKeys(txt);
    }

    @Step(value = "hover cursor on the element {0}")
    @Override
    public void hoverCursor(WebElement elementForHover) {
        Actions action = new Actions(driver);
        action.moveToElement(elementForHover).build().perform();
    }

    @Step(value = "scroll to element {0}")
    @Override
    public void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            System.out.println("cannot scroll to element");
        }
    }

    @Step(value = "switch to new opened tab")
    @Override
    public void switchToTab(int tab) {
        ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tab));
    }

    @Step(value = "close opened tab")
    @Override
    public void closeTab() {
        driver.close();
    }

    @Step(value = "refresh page")
    @Override
    public void refreshPage() {
        try {
            driver.navigate().refresh();
            Thread.sleep(2000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    @Step(value = "click on {0} element")
    @Override
    public void clickOnElement(WebElement element) {
        element.click();
    }

    @Step(value = "click on {0} with name '{1}'")
    public void clickElement(ElementType type, String elementName) {
        Element element = getElement(type);
        element.getElement(elementName).click();
    }
}
