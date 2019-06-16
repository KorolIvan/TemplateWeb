package korol.ivan.commo.locators;

import org.openqa.selenium.WebDriver;

public class LocatorsManager {

    private WebDriver driver;

    public LocatorsManager(WebDriver driver) {
        this.driver = driver;
    }

    public LocatorsManager() {
    }

    public static void main(String[] args) {
        LocatorsManager lm = new LocatorsManager();
        Element element1 = lm.getElement(ElementType.LINK);
        Element element2 = lm.getElement(ElementType.LINK);
        System.out.println(element1);
        System.out.println(element2);
    }

    public Element getElement(ElementType elementType) {
        switch (elementType) {
            case BUTTON:
                return ButtonElement.getInstance(driver);
            case LINK:
                return LinkElement.getInstance(driver);
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
