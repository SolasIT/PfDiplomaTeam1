package tests.ui;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import pages.*;
import utils.PropertyReader;
import utils.TestListener;

import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Selenide.closeWebDriver;

@Listeners(TestListener.class)
public class BaseTest {

    MainPage mainPage;
    CreateUserPage createUserPage;
    AddMoneyPage addMoneyPage;
    BuyOrSellCarPage buyOrSellCarPage;
    AllDeletePage allDeletePage;

    String email = System.getProperty("email", PropertyReader.getProperty("email"));
    String password = System.getProperty("password", PropertyReader.getProperty("password"));

    @BeforeMethod (alwaysRun = true)
    public void setup() {
        // Настройки Chrome
        ChromeOptions options = new ChromeOptions();
        Map<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("credentials_enable_service", false);
        chromePrefs.put("profile.password_manager_enabled", false);
        options.setExperimentalOption("prefs", chromePrefs);
        //  options.addArguments("--incognito"); // в этом режиме ругается на незащищённое подключение
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-infobars");
        if (System.getProperty("email")!=null) {
            options.addArguments(("--headless"));
        }
        Configuration.browser = "chrome";
        Configuration.browserCapabilities = options;
        Configuration.baseUrl = "http://82.142.167.37:4881/";
        Configuration.clickViaJs = true;
        Configuration.timeout = 5000;
        Configuration.pageLoadTimeout = 30000;
        Configuration.screenshots = true;
        Configuration.savePageSource = true;

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .screenshots(true)
                .savePageSource(true));

        // Инициализация страниц
        mainPage = new MainPage();
        createUserPage = new CreateUserPage();
        addMoneyPage = new AddMoneyPage();
        buyOrSellCarPage = new BuyOrSellCarPage();
        allDeletePage = new AllDeletePage();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        closeWebDriver();
    }
}