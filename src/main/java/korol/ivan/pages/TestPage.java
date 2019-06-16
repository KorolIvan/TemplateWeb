package korol.ivan.pages;

import korol.ivan.commo.MainFunction;
import org.openqa.selenium.WebDriver;

public class TestPage extends MainFunction {

    public TestPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void open() {
        driver.get("https://www.zillow.com/");
    }
}
