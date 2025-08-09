package pages;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.testng.Assert;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;

@Log4j2
public class LoginPage extends BasePage{

    private final By INPUT_EMAIL = By.name ("email");
    private final By INPUT_PASSWORD = By.name ("password");
    private final By BUTTON_GO = By.cssSelector (".btn-primary");

    @Override
    @Step("Открытие страницы логина")
    public LoginPage open() {
        Selenide.open(BASE_URL);
        log.info("Open Login Page");
        return this;
    }

    @Override
    @Step("Проверка открытия страницы логина")
    public LoginPage isPageOpened() {
        try {
            $(BUTTON_GO).shouldBe(visible);
            log.info("LoginPage is opened");
        } catch (Exception e) {
            log.error("Page isn't opened: {}", e.getMessage());
            Assert.fail("Page isn't opened: " + e.getMessage());
        }
        return this;
    }

    @Step("Вход в систему с именем пользователя: {email} и паролем: {password}")
    public MainPage login (String email,String password){
        $(INPUT_EMAIL).sendKeys(email);
        log.info("Entering a email: {}",email);
        $(INPUT_PASSWORD).sendKeys(password);
        log.info("Entering a password: {}",password);
        $(BUTTON_GO).click();
        log.info("Click to GO");
        sleep(2000);
        return new MainPage();
    }
}