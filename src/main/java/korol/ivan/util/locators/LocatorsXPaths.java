package korol.ivan.util.locators;

/**
 * @author Ivan Korol on 7/24/2018
 */
/**
 * this class will contains usual locators xPaths
 * */
public class LocatorsXPaths {
    private LocatorsXPaths(){}

    public static final String BUTTON_XPATH = "//input[@type='button'] " +
            "| //button[@value = '%s'] " +
            "| //div[contains(@Class, 'button')] " +
            " | //input[contains(@value, '%s')]";

    public static final String LINK_XPATH = "//li//a[contains(@title, %s)]";

    public static final String RADIO_BUTTON_XPATH = "//form//label[text()=' %s']/input[@type='radio']";

    public static final String CHECK_BOX_XPATH = "//form//label[text()=' %s']/input[@type='checkbox']";

}
