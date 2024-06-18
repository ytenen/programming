package managers;

import java.util.Locale;
import java.util.ResourceBundle;

public class ResourceBundleManager {
    private ResourceBundle resourceBundle;

    public ResourceBundleManager(Locale locale) {
        resourceBundle = ResourceBundle.getBundle("resources", locale);
    }

    public String getString(String key) {
        return resourceBundle.getString(key);
    }
}