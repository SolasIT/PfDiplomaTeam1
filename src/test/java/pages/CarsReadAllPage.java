package pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

@Log4j2
public class CarsReadAllPage extends BasePage {
    private final By ID_COLUMN = byXpath("//th[normalize-space()='ID:']");
    private final By ID_ASC_BUTTON = byXpath("//button[contains(@class, 'btn-secondary') and contains(., 'ID')]");
    private final By MARK_COLUMN = byXpath("//th[normalize-space()='Mark:']");
    private final By MODEL_COLUMN = byXpath("//th[normalize-space()='Model:']");
    private final By PRICE_COLUMN = byXpath("//th[normalize-space()='Price:']");
    private final By RELOAD_BUTTON = byXpath("//button[contains(text(), 'Reload')]");
    private final By ID_BUTTON = byXpath("//button[contains(text(), 'ID')]");
    private final By FIRST_ID = byXpath("//tbody/tr[1]/td[1]");
    private final By SECOND_ID = byXpath("//tbody/tr[2]/td[1]");
    private final By THIRD_ID = byXpath("//tbody/tr[3]/td[1]");

    @Override
    @Step("Открытие страницы Cars: Read All")
    public CarsReadAllPage open() {
        log.info("Открытие страницы Cars Read All");
        Selenide.open(BASE_URL + "#/read/cars");
        return this;
    }

    @Override
    @Step("Проверка открытия Cars Read All Page")
    public CarsReadAllPage isPageOpened() {
        try {
            $(ID_COLUMN).shouldBe(visible);
            log.info("CarsReadAllPage is opened");
        } catch (Exception e) {
            log.error("Page isn't opened: {}", e.getMessage());
            Assert.fail("Failed to open CarsReadAllPage: " + e.getMessage());
        }
        return this;
    }

    @Step("Получение списка ID")
    public List<Integer> getIds() {
        log.info("Getting list ID");
        List<Integer> ids = new ArrayList<>();
        ids.add(Integer.parseInt($(FIRST_ID).getText().trim()));
        ids.add(Integer.parseInt($(SECOND_ID).getText().trim()));
        ids.add(Integer.parseInt($(THIRD_ID).getText().trim()));
        return ids;
    }

    @Step("Сортировка по ID по возрастанию")
    public CarsReadAllPage sortByIdAsc() {
        log.info("Sort ID Asc");
        $(ID_BUTTON).click();
        return this;
    }

    @Step("Сортировка по ID по убыванию")
    public CarsReadAllPage sortByIdDsc() {
        log.info("Sort ID Dsc");
        $(ID_ASC_BUTTON).click();
        return this;
    }

    public SelenideElement getIdColumn() {
        return $(ID_COLUMN);
    }

    public SelenideElement getMarkColumn() {
        return $(MARK_COLUMN);
    }

    public SelenideElement getModelColumn() {
        return $(MODEL_COLUMN);
    }

    public SelenideElement getPriceColumn() {
        return $(PRICE_COLUMN);
    }

    public SelenideElement getReloadButton() {
        return $(RELOAD_BUTTON);
    }
}