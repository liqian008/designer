package com.bruce.designer.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil {

    private static Properties properties = new Properties();
    
    static{
        InputStream inputStream = PropertiesUtil.class.getClassLoader().getResourceAsStream("/conf/config.properties");
        try {
            properties.load(inputStream);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
    
    public static String getString(String key){
        return properties.get(key).toString();
    }
    

    // public final static Object initLock = new Object();
    //
    // private final static String BUNDLE_FILE_NAME = "config";
    //
    // private static ResourceBundle bundle = null;
    //
    // static {
    // try {
    // if (bundle == null) {
    // synchronized (initLock) {
    // if (bundle == null)
    // bundle = ResourceBundle.getBundle(BUNDLE_FILE_NAME);
    // }
    // }
    // } catch (Exception e) {
    // System.out.println("读取资源文件config.properties失败!");
    // }
    // }
    //
    // public static String getString(String key){
    // return bundle.getString(key);
    // }

}