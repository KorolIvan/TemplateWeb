package korol.ivan.commo.locators;

import korol.ivan.util.locators.LocatorsXPaths;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class RadioButtonElement extends LocatorsController implements Selectable {
    private WebDriver driver;
    private static RadioButtonElement radioButtonElement;

    private RadioButtonElement(WebDriver driver) {
        this.driver = driver;
    }

    public static RadioButtonElement getInstance(WebDriver driver) {
        if (radioButtonElement == null) {
            radioButtonElement = new RadioButtonElement(driver);
        }
        return radioButtonElement;
    }

    @Override
    public WebElement getElement(String elementName) {
        List<String> pathes = getFormattedXPath(LocatorsXPaths.RADIO_BUTTON_XPATH, elementName);
        System.out.println(pathes.size());
        for (String s :
                pathes) {
            System.out.println(s);
            List<WebElement> elements = driver.findElements(By.xpath(s));
            System.out.println(elements.size());
            for (WebElement e :
                    elements) {
                if(e.findElement(By.xpath("//ancestor::label")).getText()
                        .equalsIgnoreCase(elementName)){
                    return e;
                }
            }
        }
        return null;
    }

    @Override
    public void selectElement(String label, boolean select) {
        if (select) {
            getElement(label).click();
        }
    }
}
