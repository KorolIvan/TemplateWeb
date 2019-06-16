package korol.ivan.commo.locators;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LocatorsController {

    private List<String> getXPaths(String xPath) {
        return Arrays.asList(xPath.split("\\|"));
    }

    public List<String> formatXPath(String xpath, String name) {
        List<String> temp = new ArrayList<>();
        for (String xpathe : getXPaths(xpath)) {
            temp.add(String.format(xpathe, name));
        }
        return temp;
    }
}
