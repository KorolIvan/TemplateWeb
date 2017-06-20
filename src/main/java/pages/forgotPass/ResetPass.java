package pages.forgotPass;

import dataInfo.ConfigurationsProperties;
import mainFunction.MainFunction;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import pages.logIn.LogIn;

import static org.junit.Assert.assertEquals;

/**
 * @author by Ivan Korol on 6/12/2017.
 */
public class ResetPass extends MainFunction {
    public ResetPass(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @Override
    public void open() {
        driver.get(ConfigurationsProperties.getProperty("configurations", "url"));

    }

    private void switchToResetPassPage() {
        new LogIn(driver).openForgotPassPage();
        assertEquals(ConfigurationsProperties.getProperty("assert", "url_forgotPass"), driver.getCurrentUrl());
    }

    // check error message while sending request without data
    public void sendRequestWithEmptyFields() {
        switchToResetPassPage();
        sleepSecond(3);
        System.out.println("Step 3: Click on Reset pass button with out data");
        driver.findElement(By.xpath(ConfigurationsProperties.getProperty("elements", "send_button"))).click();

        // проверочный метод

    }

    //check if password recovery can be sent successful to unregister user
    public void sendRequestWithInvalidData() {
        switchToResetPassPage();
        sleepSecond(3);
        System.out.println("Step 3: Enter invalid email to field");
        driver.findElement(By.xpath(ConfigurationsProperties.getProperty("elements", "email_field")))
                .sendKeys(ConfigurationsProperties.getProperty("configuratons", "invalid_email"));
        System.out.println("Step 4: Click on Reset pass button");
        driver.findElement(By.xpath(ConfigurationsProperties.getProperty("elements", "send_button"))).click();

        // create assert for error
    }

    // check of successful sending request for reset password
    public void sendRequestWithValidData() {
        switchToResetPassPage();
        sleepSecond(3);
        System.out.println("Step 3: Enter valid email to field");
        driver.findElement(By.xpath(ConfigurationsProperties.getProperty("elements", "email_field")))
                .sendKeys(ConfigurationsProperties.getProperty("configuratons", "valid_email"));
        System.out.println("Step 4: Click on Reset pass button");
        driver.findElement(By.xpath(ConfigurationsProperties.getProperty("elements", "send_button"))).click();

        // create assert for success sending
    }
}
