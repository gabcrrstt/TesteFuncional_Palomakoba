package com.example.webservice.util;

public class ExemploConsultaCEP {

    private AndroidDriver driver;

    @Before
    public void setUp() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("deviceName", "emulator-5554");
        desiredCapabilities.setCapability("automationName", "uiautomator2");
        desiredCapabilities.setCapability("appPackage", "com.example.webservice");
        desiredCapabilities.setCapability("appActivity", "com.example.webservice.MainActivity");

        URL remoteUrl = new URL("http://localhost:4723/wd/hub");

        driver = new AndroidDriver(remoteUrl, desiredCapabilities);
    }

    @Test
    public void testarCEP() {
        (new TouchAction(driver)).tap(777, 168).perform();
        (new TouchAction(driver)).tap(710, 42).perform();
        (new TouchAction(driver)).tap(601, 731).perform();
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}

