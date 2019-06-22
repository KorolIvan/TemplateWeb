package korol.ivan.commo.locators;

import korol.ivan.util.locators.LocatorsXPaths;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class ButtonElement extends LocatorsController implements Clickable {
    private WebDriver driver;

    private static ButtonElement buttonElement;

    private ButtonElement(WebDriver driver){
        this.driver = driver;
    }

    public static ButtonElement getInstance(WebDriver driver) {
        if(buttonElement == null) {
            buttonElement = new ButtonElement(driver);
        }
        return buttonElement;
    }

    @Override
    public WebElement getElement(String elementName) {
        List<String> temp = getFormattedXPath(LocatorsXPaths.BUTTON_XPATH, elementName);
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
