package korol.ivan.util.browserset;

/**
 * @author by Ivan Korol on 11/8/2017.
 */
public class OSSystem {

    private static String os;

    public static String getOs() {
        os = System.getProperty("os.name").toLowerCase();
        if(os.contains("win")){
            os = "windows";
        } else if (os.contains("nux") || os.contains("nix")){
            os =  "linux";
        } else if (os.contains("mac")) {
            os = "mac";
        }else {
            os = "Other";
        }
        return os;
    }
}
