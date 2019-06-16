package korol.ivan.util.locators;

/**
 * @author Ivan Korol on 7/24/2018
 */
/**
 * this class will contains usual locators xPaths
 * */
public class LocatorsXPaths {
    private LocatorsXPaths(){}

    public final static String BUTTON_XPATH = "//input[@type='button'] " +
            "| //button[@value = '%s'] " +
            "| //div[contains(@Class, 'button')] " +
            " | //input[contains(@value, '%s')]";

}
