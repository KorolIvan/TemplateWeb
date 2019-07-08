package korol.ivan.commo.locators;

import korol.ivan.util.locators.LocatorsXPaths;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CheckBoxElement extends LocatorsController implements Selectable {
    private WebDriver driver;
    private static CheckBoxElement checkBoxElement;

    private CheckBoxElement(WebDriver driver) {
        this.driver = driver;
    }

    public static CheckBoxElement getInstance(WebDriver webDriver) {
        if(checkBoxElement == null) {
            checkBoxElement = new CheckBoxElement(webDriver);
        }
        return checkBoxElement;
    }

    @Override
    public void selectElement(String label, boolean select) {
        if(select) {
            getElement(label).click();
        }
    }

    @Override
    public WebElement getElement(String elementName) {
        List<String> pathes = getFormattedXPath(LocatorsXPaths.CHECK_BOX_XPATH, elementName);
        for (String s :
                pathes) {
            if(driver.findElement(By.xpath(s)) != null) {
                return driver.findElement(By.xpath(s));
            }
        }
        return null;
    }
}
