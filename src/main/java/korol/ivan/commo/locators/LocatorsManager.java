package korol.ivan.commo.locators;

import org.openqa.selenium.WebDriver;

public class LocatorsManager {

    private WebDriver driver;

    public LocatorsManager(WebDriver driver) {
        this.driver = driver;
    }

    public Element getElement(ElementType elementType) {
        switch (elementType) {
            case BUTTON:
                return new ButtonElement(driver);
            case LINK:
                return null;
            case TABLE:
                return null;
            case CHECKBOX:
                return null;
            case DROPDOWN:
                return null;
            case TEXTAREA:
                return null;
            case TEXTFIELD:
                return null;
            case RADIOBUTTON:
                return null;
            default:
                throw new NullPointerException("Element type not fount");
        }
    }
}
