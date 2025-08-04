package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

@Log4j2
public class MainPage extends BasePage {

    private final By userDropdown = By.xpath("//a[@class='dropdown-toggle nav-link' and text()='Users']");
    private final By carsDropdown = By.xpath("//a[@class='dropdown-toggle nav-link' and text()='Cars']");
    private final By housesDropdown = By.xpath("//a[@class='dropdown-toggle nav-link' and text()='Houses']");
    private final By allPostButton = By.xpath("//a[@class='nav-link' and text()='All POST']");
    private final By allDeleteButton = By.xpath("//a[@class='nav-link' and text()='All DELETE']");

    public MainPage(WebDriver driver) {
        super(driver);
    }

    @Step("Открытие страницы сайта")
    @Override
    public MainPage open() {
        log.info("Open main page");
        driver.get(BASE_URL);
        return this;
    }

    @Override
    public MainPage isPageOpened() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(userDropdown));
        } catch (TimeoutException e) {
            log.error(e.getMessage());
            Assert.fail("Page isn't opened");
        }
        return this;
    }
}
