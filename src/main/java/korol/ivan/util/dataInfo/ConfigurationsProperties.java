package korol.ivan.util.dataInfo;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * @author by Ivan Korol on 11/8/2017.
 */
public class ConfigurationsProperties {
    private static FileInputStream fileInputStream;
    private static Properties property;

    public static String getProperty(String fileName, String key) {
        property = new Properties();
        String value = null;
        try {
            fileInputStream = new FileInputStream("src/main/resources/" + fileName + ".properties");
            property.load(fileInputStream);
            value = property.getProperty(key);
        } catch (IOException e) {
            System.out.println("Error: file is not found!");
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    System.out.println("Error: cannot close the file stream");
                }
            }
        }
        return value;
    }
}
