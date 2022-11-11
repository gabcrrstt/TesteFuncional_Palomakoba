package com.example.webservice.util;

import java.net.MalformedURLException;

public class DriverFactory {
    private static AndroidDriver<MobileElement> driver;

    public static AndroidDriver<MobileElement> getDriver(){
        if(driver ==null){
            criaDriver();
        }
        return driver;
    }

    public static void criarDriver(){
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("deviceName", "emulator-5554");
        desiredCapabilities.setCapability("automationName", "uiautomator2");
        desiredCapabilities.setCapability("appPackage", "com.example.webservice");
        desiredCapabilities.setCapability("appActivity", "com.example.webservice.MainActivity");

        URL remoteUrl = null;
        try {
            remoteUrl = new URL("http://localhost:4723/wd/hub");
            driver = new AndroidDriver(remoteUrl, desiredCapabilities);
        }catch (MalformedURLException e){
            e.printStackTrace();
        }
    }
    public static void finalizarDriver(){
        if(driver !=null){
            driver.quit();
            driver = null;
        }
    }
}
