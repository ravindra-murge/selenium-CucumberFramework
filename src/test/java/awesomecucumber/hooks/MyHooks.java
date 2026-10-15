package awesomecucumber.hooks;

import awesomecucumber.factory.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;

public class MyHooks {

    private WebDriver driver;
    @Before
    public void before(){

       String browser = System.getProperty("browser","chrome");
        driver = DriverFactory.initializeDriver(browser);
    }

    @After
    public void tearDown(){

        driver.quit();
    }
}
