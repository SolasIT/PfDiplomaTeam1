package pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.testng.Assert;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

@Log4j2
public class MainPage extends BasePage {

    private final SelenideElement userDropdown = $x("//a[@class='dropdown-toggle nav-link' and text()='Users']");
    private final SelenideElement carsDropdown = $x("//a[@class='dropdown-toggle nav-link' and text()='Cars']");
    private final SelenideElement housesDropdown = $x("//a[@class='dropdown-toggle nav-link' and text()='Houses']");
    private final SelenideElement allPostButton = $x("//a[@class='nav-link' and text()='All POST']");
    private final SelenideElement allDeleteButton = $x("//a[@class='nav-link' and text()='All DELETE']");
    private final SelenideElement LOGIN_FIELD = $("[placeholder='Enter your email...']");
    private final SelenideElement PASSWORD_FIELD = $("[placeholder='Enter your password...']");
    private final SelenideElement AUTH_BUTTON_GO = $(withText("GO"));

    @Step("Открытие страницы сайта")
    @Override
    public MainPage open() {
        log.info("Open main page");
        Selenide.open(BASE_URL);
        return this;
    }

    @Override
    public MainPage isPageOpened() {
        try {
            userDropdown.shouldBe(visible);
        } catch (Exception e) {
            log.error("Page isn't opened: {}", e.getMessage());
            Assert.fail("Page isn't opened: " + e.getMessage());
        }
        return this;
    }

    public MainPage auth() {
        log.info("Authentication");
        log.info("Filling field \"Enter your email...\"");
        LOGIN_FIELD.val("user@pflb.ru");
        log.info("Field is filled");
        log.info("Filling field \"Enter your password...\"");
        PASSWORD_FIELD.val("user");
        log.info("Field is filled");
        log.info("Pushing button \"GO\"");
        AUTH_BUTTON_GO.click();
        log.info("Button is pushed");
        sleep(200); // согласовано
        return this;
    }
}