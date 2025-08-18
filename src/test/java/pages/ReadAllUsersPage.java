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
public class ReadAllUsersPage extends BasePage {

    private final By RELOAD_BUTTON = byXpath("//button[text() = 'Reload']"); //локатор кнопки Reload
    private final By ID_BUTTON = byXpath("//button[contains(text(), 'ID')]"); // локатор кнопки ID
    private final By ID_BUTTON_UP = byXpath("//button[contains(@class, 'btn-secondary') and contains(., 'ID')]");//локатор кнопки ID по возростанию
    private final By AGE_COLUMN = byXpath("//th[normalize-space()='Age:']"); //локатор столбца Age
    private final By SEX_COLUMN = byXpath("//th[normalize-space()='Sex:']"); //локатор столбца Sex Name
    private final By MONEY_COLUMN = byXpath("//th[normalize-space()='Money:']"); //локатор столбца Money
    private final By ID_COLUMN = byXpath("//th[normalize-space()='ID:']");//локатор столбца ID
    private final By SECOND_ID = byXpath("//tbody/tr[2]/td[1]"); // Локатор второго ID
    private final By THIRD_ID = byXpath("//tbody/tr[3]/td[1]"); // Локатор третьего ID
    private final By FOURTH_ID = byXpath("//tbody/tr[4]/td[1]"); // Локатор четвертого ID

    @Override
    @Step("Открытие страницы Read All")
    public ReadAllUsersPage open() {
            log.info("Open User Read All Page");
            Selenide.open(BASE_URL + "#/read/users");
            return this;
        }

    @Override
    public BasePage isPageOpened() {
        try {
            $(ID_COLUMN).shouldBe(visible);
            log.info("UsersReadAllPage is opened");
        } catch (Exception e) {
            log.error("Page isn't opened: {}", e.getMessage());
            Assert.fail("Failed to open UsersReadAllPage: " + e.getMessage());
        }
        return this;
    }


    @Step("Сортировка по ID по возрастанию")
    public ReadAllUsersPage sortByIdUp() {
        log.info("Sort ID Up");
        $(ID_BUTTON).click();
        return this;
    }

    @Step("Сортировка по ID по убыванию")
    public ReadAllUsersPage sortByIdDown() {
        log.info("Sort ID Down");
        $(ID_BUTTON_UP).click();
        return this;
    }

    @Step("Получение списка ID")
    public List<Integer> getIds() {
        log.info("Getting list ID");
        List<Integer> ids = new ArrayList<>();
        ids.add(Integer.parseInt($(SECOND_ID).getText().trim()));
        ids.add(Integer.parseInt($(THIRD_ID).getText().trim()));
        ids.add(Integer.parseInt($(FOURTH_ID).getText().trim()));
        return ids;
    }

    public SelenideElement getReloadButton() {
        return $(RELOAD_BUTTON);
    }

    public SelenideElement getIdColumn() {
        return $(ID_COLUMN);
    }

    public SelenideElement getAgeColumn() {
        return $(AGE_COLUMN);
    }

    public SelenideElement getSexColumn() {
        return $(SEX_COLUMN);
    }

    public SelenideElement getMoneyColumn() {
        return $(MONEY_COLUMN);
    }


}
