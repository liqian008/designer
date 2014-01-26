package com.bruce.designer.util;

import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.Set;

public class ConfigUtil {

    private static final String BUNDLE_NAME = "conf/site_config";

    private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);

    public static String getString(String key) {
        try {
            return RESOURCE_BUNDLE.getString(key);
        } catch (MissingResourceException e) {
            return key;
        }
    }

    public static Set<String> getKeySet() {
        return RESOURCE_BUNDLE.keySet();
    }

}
