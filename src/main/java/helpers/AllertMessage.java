package helpers;

/**
 * @author by Ivan Korol on 6/12/2017.
 */
public class AllertMessage {
    private String screenName;
    private String fileName;
    private String assertName;

    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getAssertName() {
        return assertName;
    }

    public void setAssertName(String assertName) {
        this.assertName = assertName;
    }

    public AllertMessage(String screenName, String fileName, String assertName) {
        this.screenName = screenName;
        this.fileName = fileName;
        this.assertName = assertName;
    }


}
