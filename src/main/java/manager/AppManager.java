package manager;

import java.lang.reflect.Method;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.WDriverListener;


public class AppManager {
    private WebDriver driver;

    public WebDriver getDriver() {
        return driver;
    }

    public Logger logger = LoggerFactory.getLogger(AppManager.class);

    @BeforeMethod
    public void setup(Method method) {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        logger.info("Start testing with method -->" + method.getName());
        WDriverListener webDriverListener = new WDriverListener();
        driver = new EventFiringDecorator<>(webDriverListener).decorate(driver);

    }

    @AfterMethod(enabled = false)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

}
