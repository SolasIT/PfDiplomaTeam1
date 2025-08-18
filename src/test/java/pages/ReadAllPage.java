package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import lombok.extern.log4j.Log4j2;

import static com.codeborne.selenide.Selenide.$;

@Log4j2
public class ReadAllPage extends BasePage {

    private final By BUTTON_RELOAD = Selectors.byText("Reload");
    private final By ID_TABLE = Selectors.withText("ID");
    private final By FLOOR_COUNT_TABLE = Selectors.withText("Floor Count");
    private final By PRICE_TABLE = Selectors.withText("Price");
    private final By PARKING_PLACES_TABLE = Selectors.withText("Parking Places");
    private final By LODGERS_TABLE = Selectors.withText("Lodgers");

    @Step("Нажатие кнопки Reload")
    public ReadAllPage clickReload(){
        log.info("Click Reaload button");
        $(BUTTON_RELOAD).click();
        return this;
    }

    @Override
    @Step("Открытие страницы Read All")
    public ReadAllPage open() {
        log.info("Opening Read All Page");
        Selenide.open(BASE_URL + "/#/read/houses");
        return this;
    }

    @Override
    @Step("Проверка открытия страницы Read All")
    public ReadAllPage isPageOpened() {
        log.info("Read All page is open");
        $(FLOOR_COUNT_TABLE).shouldBe(Condition.visible);
        return this;
    }
}
