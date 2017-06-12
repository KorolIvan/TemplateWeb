package pages.registration;

import dataInfo.ConfigurationsProperties;
import mainFunction.MainFunction;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * @author by Ivan Korol on 6/12/2017.
 */
public class Registration extends MainFunction {
    public Registration(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @Override
    public void open() {
        driver.get(ConfigurationsProperties.getProperty("configurations", "url"));
    }

    public void submitWithOutData() {

    }

    public void submitWithWrongData() {

    }

    public void submitWithValidData() {

    }
}
