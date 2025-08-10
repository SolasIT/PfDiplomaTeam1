package pages;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.testng.Assert;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

@Log4j2
public class AllDeletePage extends BasePage {

    private final String BUTTON_DELETE = "//button[@value = '%s']";//Локатор кнопки удаления
    private final String INPUT_DELETE = "//button[@value = '%s']/ancestor::div[@role='group']//input";//Локатор ввода ID
    private final String STATUS_DELETE = "//button[@value = '%s']/ancestor::div[@role='group']//button[@class='status btn btn-secondary']";//Локатор Статуса

    @Override
    @Step("Открытие страницы All Delete")
    public AllDeletePage open() {
        Selenide.open(BASE_URL + "#/delete/all");
        return this;
    }

    @Override
    @Step("Проверка открытия страницы All Delete")
    public AllDeletePage isPageOpened() {
        try {
            $x(String.format(BUTTON_DELETE, "user")).shouldBe(visible);
            log.info("AllDeletePage is opened");
        } catch (Exception e) {
            log.error("Page isn't opened: {}", e.getMessage());
            Assert.fail("Page isn't opened: " + e.getMessage());
        }
        return this;
    }

    @Step("Нажатие кнопки 'DELETE' {buttonname}")
    public AllDeletePage clickDelete(String buttonname) {
        $x(String.format(BUTTON_DELETE, buttonname)).click();
        log.info("Click Delete {}", buttonname);
        return this;
    }

    @Step("Ввод {buttonname} ID : {value}")
    public AllDeletePage inputID(String buttonname, int id) {
        $x(String.format(INPUT_DELETE, buttonname)).sendKeys(String.valueOf(id));
        log.info("Input {} value {}", buttonname, id);
        return this;
    }

    @Step("Получения статуса {buttonname}")
    public String getStatus(String buttonname) {
        String status = $x(String.format(STATUS_DELETE, buttonname)).text();
        log.info("Get status {} : {}", buttonname, status);
        return status;
    }
}