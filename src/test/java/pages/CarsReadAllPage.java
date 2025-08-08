package pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.testng.asserts.SoftAssert;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

@Log4j2
public class CarsReadAllPage extends BasePage {
    private static final String PAGE_URL = "http://82.142.167.37:4881/#/read/cars";
    private final SelenideElement idColumn = $x("//th[normalize-space()='ID:']");
    private final SelenideElement idAscButton = $x("//button[contains(@class, 'btn-secondary') and contains(., 'ID')]");
    private final SelenideElement engineTypeColumn = $x("//th[normalize-space()='Engine Type:']");
    private final SelenideElement markColumn = $x("//th[normalize-space()='Mark:']");
    private final SelenideElement modelColumn = $x("//th[normalize-space()='Model:']");
    private final SelenideElement priceColumn = $x("//th[normalize-space()='Price:']");
    private final SelenideElement reloadButton = $x("//button[contains(text(), 'Reload')]");
    private final SelenideElement idButton = $x("//button[contains(text(), 'ID')]");
    private final SelenideElement firstId = $x("//tbody/tr[1]/td[1]");
    private final SelenideElement secondId = $x("//tbody/tr[2]/td[1]");
    private final SelenideElement thirdId = $x("//tbody/tr[3]/td[1]");

    @Override
    @Step("Открытие страницы Cars: Read All")
    public CarsReadAllPage open() {
        log.info("Открытие страницы Cars Read All");
        Selenide.open(PAGE_URL);
        return this;
    }

    @Override
    @Step("Проверка открытия страницы")
    public CarsReadAllPage isPageOpened() {
        log.info("Page is opened");
        idColumn.shouldBe(visible);
        return this;
    }

    @Step("Сортировка по IDe по возрастанию")
    public CarsReadAllPage sortByIdAsc() {
        log.info("Sort ID Asc");
        idButton.click();
        return this;
    }

    @Step("Сортировка по ID")
    public CarsReadAllPage sortByIdDsc() {
        log.info("Sort ID Dsc");
        idAscButton.click();
        return this;
    }

    @Step("Проверка порядка ID")
    public CarsReadAllPage verifyIdOrder(boolean ascending, SoftAssert softAssert) {
        int id1 = Integer.parseInt(firstId.getText().trim());
        int id2 = Integer.parseInt(secondId.getText().trim());
        int id3 = Integer.parseInt(thirdId.getText().trim());

        if (ascending) {
            softAssert.assertTrue(id1 < id2,
                    String.format("Первый ID (%d) должен быть меньше второго (%d)", id1, id2));
            softAssert.assertTrue(id2 < id3,
                    String.format("Второй ID (%d) должен быть меньше третьего (%d)", id2, id3));
        } else {
            softAssert.assertTrue(id1 > id2,
                    String.format("Первый ID (%d) должен быть больше второго (%d)", id1, id2));
            softAssert.assertTrue(id2 > id3,
                    String.format("Второй ID (%d) должен быть больше третьего (%d)", id2, id3));
        }
        return this;
    }

    @Step("Проверка видимости основных элементов")
    public void checkBasicElementsVisible() {
        idColumn.shouldBe(visible);
        //idButton.shouldBe(visible);
        reloadButton.shouldBe(visible);
    }

    @Step("Проверка текста заголовков")
    public void checkHeadersText() {
        idColumn.shouldHave(exactText("ID:"));
        //engineTypeColumn.shouldHave(exactText("Engine Type:"));
        markColumn.shouldHave(exactText("Mark:"));
        modelColumn.shouldHave(exactText("Model:"));
        priceColumn.shouldHave(exactText("Price:"));
    }
}