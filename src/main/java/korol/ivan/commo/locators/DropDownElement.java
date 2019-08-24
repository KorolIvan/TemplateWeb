package korol.ivan.commo.locators;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class DropDownElement extends LocatorsController {
    private static final String OPTION_ITEMS_LIST_XPATH = "";
    private static final String SEARCH_FIELD_INSIDE_OPTION_LIST = "";

    private WebDriver driver;
    private DropDownElement dropDownElement;

    private DropDownElement(WebDriver driver) {
        this.driver = driver;
    }

    public DropDownElement getInstance() {
        if(dropDownElement == null) {
            dropDownElement = new DropDownElement(driver);
        }
        return dropDownElement;
    }

    public void selectItemFromDropdown(String dropdownLabel, String itemName) {

    }

    public void multiselectionFromDropdown(String dropdownLabel, String items) {

    }

    public void multiselectionFromDropdown(String dropdownLabel, List<String> items) {

    }

    public void selectItemFromDropDownWithSearchFunction(String dropdownLabel, String items) {

    }

    public void multiselectionFromDropDownWithSearchFunction(String dropdownLabel, String items) {

    }

    public void selectItemFromDropDownWithSearchFieldInsaidOptionList(String dropdownLabel, String itemName) {

    }

    public WebElement getElement(String elementName) {
        return null;
    }
}
