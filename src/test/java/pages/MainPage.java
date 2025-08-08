package pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.testng.Assert;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

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

    @Step("Открытие раздела Cars: Read All")
    public CarsReadAllPage openCarsReadAll() {
        log.info("Open Cars: Read All");
        $(carsDropdown).click();
        $(carsReadAll).click();
        return new CarsReadAllPage();
    }

    @Step("Открытие раздела Cars: CreateNew")
    public CarsCreateNewPage openCarsCreateNew() {
        log.info("Open Cars: CreateNew");
        $(carsDropdown).click();
        $(carsCreateNew).click();
        return new CarsCreateNewPage();
    }

    @Step("Открытие раздела Cars: BuyOrSellCar")
    public CarsBuyOrSellCarPage openCarsBuyOrSellCar() {
        log.info("Open Cars: BuyOrSellCar");
        $(carsDropdown).click();
        $(carsBuyOrSellCar).click();
        return new CarsBuyOrSellCarPage();
    }
}