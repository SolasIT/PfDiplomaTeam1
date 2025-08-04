package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BasePage {

    static WebDriver driver;
    protected final String BASE_URL = "http://82.142.167.37:4881/";
    static WebDriverWait wait;

    public BasePage(WebDriver driver) {
        BasePage.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public abstract BasePage open();

    public abstract BasePage isPageOpened();
}
