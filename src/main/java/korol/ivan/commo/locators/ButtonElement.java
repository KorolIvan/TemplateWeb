package korol.ivan.commo.locators;

import com.google.inject.Singleton;
import korol.ivan.util.locators.LocatorsXPaths;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
@Singleton
public class ButtonElement extends LocatorsController implements Element {
    private WebDriver driver;

    public ButtonElement(WebDriver driver){
        this.driver = driver;
    }

    @Override
    public WebElement getElement(String elementName) {
        List<String> temp = formatXPath(LocatorsXPaths.BUTTON_XPATH, elementName);
        WebElement element = null;
        for (String aTemp : temp) {
            try {
                element = driver.findElement(By.xpath(aTemp));
                if (element.getText().equalsIgnoreCase(elementName)
                        || element.getAttribute("value").equalsIgnoreCase(elementName)) {
                    break;
                }
            } finally {
                continue;
            }
        }
        return element;
    }
}
