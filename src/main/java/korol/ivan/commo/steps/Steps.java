package korol.ivan.commo.steps;

import org.openqa.selenium.WebElement;

/**
 * @author Ivan Korol on 7/18/2018
 */
public interface Steps {

    void type(WebElement element, String txt);

    void hoverCursor(WebElement elementForHover);

    void scrollToElement(WebElement element);

    void switchToTab(int tab);

    void closeTab();

    void refreshPage();

    void clickOnElement(WebElement element);
}
