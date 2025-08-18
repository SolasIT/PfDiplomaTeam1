package tests.ui;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Attachment;
import io.qameta.allure.selenide.AllureSelenide;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import pages.*;
import utils.PropertyReader;
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
    CreateUserPage createUserPage;
    AddMoneyPage addMoneyPage;
    BuyOrSellCarPage buyOrSellCarPage;
    AllDeletePage allDeletePage;
    SettleToHousePage settleToHousePage;
    CreateNewPage createNewPage;
    ReadOneByIdPage readOneByIdPage;
    ReadAllPage readAllPage;

    String email = System.getProperty("email", PropertyReader.getProperty("email"));
    String password = System.getProperty("password", PropertyReader.getProperty("password"));

    @Parameters({"browser"}) // для кроссбраузерного тестирования
    @BeforeMethod(alwaysRun = true)
    public void setup(@Optional("chrome") String browser) {
        Configuration.baseUrl = "http://82.142.167.37:4881/";
        Configuration.clickViaJs = true;
        Configuration.timeout = 10000;
        Configuration.pageLoadTimeout = 30000;
        Configuration.screenshots = true;
        Configuration.savePageSource = true;
        if (browser.equalsIgnoreCase("chrome")) {
            // Настройки Chrome
            ChromeOptions options = new ChromeOptions();
            Map<String, Object> chromePrefs = new HashMap<>();
            chromePrefs.put("credentials_enable_service", false);
            chromePrefs.put("profile.password_manager_enabled", false);
            chromePrefs.put("profile.default_content_setting_values.notifications", 2);
            options.setExperimentalOption("prefs", chromePrefs);
            // options.addArguments("--incognito"); // в этом режиме ругается на незащищённое подключение
            options.addArguments("--disable-notifications");
            options.addArguments("--disable-popup-blocking");
            options.addArguments("--disable-infobars");
            if (System.getProperty("email") != null) {
                options.addArguments("--headless");
            }
            Configuration.browser = "chrome";
            Configuration.browserCapabilities = options;
        } else if (browser.equalsIgnoreCase("firefox")) {
            // Настройки Firefox
            FirefoxOptions options = new FirefoxOptions();
            // options.addArguments("--incognito");
            if (System.getProperty("email") != null) {
                options.addArguments("--headless");
            }
            Configuration.browser = "firefox";
            Configuration.browserCapabilities = options;
        }
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .screenshots(true)
                .savePageSource(true));

        softAssert = new SoftAssert();

        mainPage = new MainPage();
        carsCreateNewPage = new CarsCreateNewPage();
        carsReadAllPage = new CarsReadAllPage();
        createUserPage = new CreateUserPage();
        addMoneyPage = new AddMoneyPage();
        buyOrSellCarPage = new BuyOrSellCarPage();
        allDeletePage = new AllDeletePage();
        settleToHousePage = new SettleToHousePage();
        createNewPage = new CreateNewPage();
        readAllPage = new ReadAllPage();
        readOneByIdPage = new ReadOneByIdPage();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {
         if(ITestResult.FAILURE == result.getStatus()){
            byte[] screen = Selenide.screenshot(OutputType.BYTES);
            saveScreen("Screen",screen);
        }
        if(WebDriverRunner.getWebDriver()!=null) {
            closeWebDriver();
        }
    }
    
        @Attachment(value = "{name}", type = "image/png")
    private static byte[] saveScreen(String name,byte[] image){
        return image;
    }
}
