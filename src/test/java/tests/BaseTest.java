package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import pages.*;
import utils.TestListener;

import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Selenide.closeWebDriver;

@Listeners(TestListener.class)
public class BaseTest {
    SoftAssert softAssert;
    MainPage mainPage;
    CarsCreateNewPage carsCreateNewPage;
    CarsReadAllPage carsReadAllPage;

    String email = System.getProperty("email");
    String password = System.getProperty("password");

    @BeforeMethod
    public void setup() {
        // Настройки Chrome
        ChromeOptions options = new ChromeOptions();
        Map<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("credentials_enable_service", false);
        chromePrefs.put("profile.password_manager_enabled", false);
        options.setExperimentalOption("prefs", chromePrefs);
        //options.addArguments("--incognito");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-infobars");
        options.addArguments(("--headless"));
        Configuration.browser = "chrome";
        Configuration.browserCapabilities = options;
        Configuration.baseUrl = "http://82.142.167.37:4881/";
        Configuration.clickViaJs = true;
        Configuration.timeout = 10000;
        Configuration.pageLoadTimeout = 30000;
        Configuration.screenshots = true;
        Configuration.savePageSource = false;

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .screenshots(true)
                .savePageSource(true));

        softAssert = new SoftAssert();

        // Инициализация страниц
        mainPage = new MainPage();
        carsCreateNewPage = new CarsCreateNewPage();
        carsReadAllPage = new CarsReadAllPage();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        closeWebDriver();
    }
}