package com.example.webservice.util;

public class testeCaixaPreta {

    private AndroidDriver<MovileElement> driver;

    @Before
    public void setUp(){
        driver = DriverFactory.getDriver();
    }
    @After
    public  void tearDown(){
        DriverFactory.finalizarDriver();
    }
}
