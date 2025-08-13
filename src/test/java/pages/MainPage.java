package pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.testng.Assert;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

@Log4j2
public class MainPage extends BasePage {

    private final SelenideElement userDropdown = $x("//a[@class='dropdown-toggle nav-link' and text()='Users']");
    private final SelenideElement carsDropdown = $x("//a[@class='dropdown-toggle nav-link' and text()='Cars']");
    private final SelenideElement carsReadAll = $x("//a[@href='#/read/cars' and text()='Read all']");
    private final SelenideElement carsCreateNew = $x("//a[@href='#/create/cars' and text()='Create new']");
    private final SelenideElement carsBuyOrSellCar = $x("//a[@href='#/update/users/buyCar' and text()='Buy or sell car']");
    private final SelenideElement housesDropdown = $x("//a[@class='dropdown-toggle nav-link' and text()='Houses']");
    private final SelenideElement allPostButton = $x("//a[@class='nav-link' and text()='All POST']");
    private final SelenideElement allDeleteButton = $x("//a[@class='nav-link' and text()='All DELETE']");
    private final String BUTTON = "//a[text()='%s']";
    private final SelenideElement LOGIN_FIELD = $("[placeholder='Enter your email...']");
    private final SelenideElement PASSWORD_FIELD = $("[placeholder='Enter your password...']");
    private final SelenideElement AUTH_BUTTON_GO = $(".btn-primary");

    @Step("Открытие страницы сайта")
    @Override
    public MainPage open() {
        log.info("Open main page");
        Selenide.open(BASE_URL);
        return this;
    }

    @Override
    @Step("Проверка открытия страницы сайта")
    public MainPage isPageOpened() {
        try {
            userDropdown.shouldBe(visible);
        } catch (Exception e) {
            log.error("Page isn't opened: {}", e.getMessage());
            Assert.fail("Page isn't opened: " + e.getMessage());
        }
        return this;
    }

    @Step("Открытие раздела Cars: Read All")
    public CarsReadAllPage openCarsReadAll() {
        log.info("Open Cars: Read All");
        $(carsDropdown).click();
        $(carsReadAll).click();
        return new CarsReadAllPage();
    }

    @Step("Открытие раздела Cars: CreateNew")
    public CarsCreateNewPage openCarsCreateNew() {
        log.info("Opening Cars dropdown");
        $(carsDropdown).shouldBe(visible, Duration.ofSeconds(10)).click();
        log.info("Clicking Create new link");
        $(carsCreateNew).shouldBe(visible, Duration.ofSeconds(10)).click();
        log.info("Creating new CarsCreateNewPage instance");
        return new CarsCreateNewPage();
    }

    @Step("Клик по кнопкe: {buttonname}")
    public MainPage clickUserButton(String buttonname) {
        userDropdown.click();
        $x(String.format(BUTTON, buttonname)).click();
        log.info("Click to button {}", buttonname);
        return this;
    }

    @Step("Клик по кнопкe: All DELETE")
    public AllDeletePage clickAllDelete(){
        allDeleteButton.click();
        switchTo().window(1);
        log.info("Click All DELETE");
        return new AllDeletePage();
    }

    @Step("Авторизация email: {email}, password {password}")
    public MainPage auth(String email, String password) {
        log.info("Authentication");
        log.info("Filling field \"Enter your email...\"");
        LOGIN_FIELD.val(email);
        log.info("Field email is filled: {}",email);
        log.info("Filling field \"Enter your password...\"");
        PASSWORD_FIELD.val(password);
        log.info("Field password is filled: {}",password);
        log.info("Pushing button \"GO\"");
        AUTH_BUTTON_GO.click();
        log.info("Button is pushed");
        sleep(1500); // согласовано
        return this;
    }
}