package pages.logIn;

import dataInfo.ConfigurationsProperties;
import mainFunction.MainFunction;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import static org.junit.Assert.*;

/**
 * @author by Ivan Korol on 5/30/2017.
 */
public class LogIn extends MainFunction {
    public LogIn(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @Override
    public void open() {
        driver.get(ConfigurationsProperties.getProperty("configurations", "url"));
    }

    public void logInAsValidUser(){
        open();
        System.out.println("Step 1: Open link: " +
                ConfigurationsProperties.getProperty("configuration", "url"));
        sleepSecond(2);

        assertTrue(isElementPresent(By.xpath(ConfigurationsProperties.getProperty("elements",
                "userName"))));
        System.out.println("Step 2: Enter valid user name / email");
        driver.findElement(By.xpath(ConfigurationsProperties.getProperty("elements",
                "userName"))).sendKeys(ConfigurationsProperties.getProperty("configuration", "validUserName"));

        assertTrue(isElementPresent(By.xpath(ConfigurationsProperties.getProperty("elements",
                "pass"))));
        System.out.println("Step 3: Enter valid password");
        driver.findElement(By.xpath(ConfigurationsProperties.getProperty("elements",
                "pass"))).sendKeys(ConfigurationsProperties.getProperty("configuration", "validPass"));

        assertTrue(isElementPresent(By.xpath(ConfigurationsProperties.getProperty("elements",
                "submit"))));
        System.out.println("Step 4: Click on submit button");
        driver.findElement(By.xpath(ConfigurationsProperties.getProperty("elements",
                "submit"))).click();

        assertEquals(ConfigurationsProperties.getProperty("assert.properties",
                "url_dashboard"), driver.getCurrentUrl());

    }

    public void logOut() {
        System.out.println("Step 5: Click on LogOut button");
        driver.findElement(By.xpath(ConfigurationsProperties.getProperty("elements", "logOut"))).click();
        sleepSecond(2);
        assertEquals(ConfigurationsProperties.getProperty("assert", "url_logIn"), driver.getCurrentUrl());
    }

    public void logInWithInvalidData() {
        open();
        System.out.println("Step 1: Open link: " +
                ConfigurationsProperties.getProperty("configuration", "url"));
        sleepSecond(2);

        assertTrue(isElementPresent(By.xpath(ConfigurationsProperties.getProperty("elements",
                "userName"))));
        System.out.println("Step 2: Enter invalid user name / email");
        driver.findElement(By.xpath(ConfigurationsProperties.getProperty("elements",
                "userName"))).sendKeys(ConfigurationsProperties.getProperty("configuration", "invalidUserName"));

        assertTrue(isElementPresent(By.xpath(ConfigurationsProperties.getProperty("elements",
                "pass"))));
        System.out.println("Step 3: Enter invalid password");
        driver.findElement(By.xpath(ConfigurationsProperties.getProperty("elements",
                "pass"))).sendKeys(ConfigurationsProperties.getProperty("configuration", "invalidPass"));

        assertTrue(isElementPresent(By.xpath(ConfigurationsProperties.getProperty("elements",
                "submit"))));
        System.out.println("Step 4: Click on submit button");
        driver.findElement(By.xpath(ConfigurationsProperties.getProperty("elements",
                "submit"))).click();

        //change actual result for required
        assertEquals(ConfigurationsProperties.getProperty("assert",
                "check_data"), driver.getCurrentUrl());
    }

    public void logInWithEmptyData() {
        open();
        System.out.println("Step 1: Open link: " +
                ConfigurationsProperties.getProperty("configuration", "url"));
        sleepSecond(2);

        assertTrue(isElementPresent(By.xpath(ConfigurationsProperties.getProperty("elements",
                "userName"))));

        assertTrue(isElementPresent(By.xpath(ConfigurationsProperties.getProperty("elements",
                "pass"))));

        assertTrue(isElementPresent(By.xpath(ConfigurationsProperties.getProperty("elements",
                "submit"))));
        System.out.println("Step 4: Click on submit button");
        driver.findElement(By.xpath(ConfigurationsProperties.getProperty("elements",
                "submit"))).click();

        //change actual result for required
        assertEquals(ConfigurationsProperties.getProperty("assert",
                "check_data"), driver.getCurrentUrl());
    }


}
