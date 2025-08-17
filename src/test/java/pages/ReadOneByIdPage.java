package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import lombok.extern.log4j.Log4j2;

import static com.codeborne.selenide.Selenide.$;

@Log4j2
public class ReadOneByIdPage extends BasePage {

    private final By BUTTON_READ = Selectors.byText("Read");
    private final By ID_INPUT = Selectors.byId("house_input");
    private final By STATUS_TEXT = Selectors.withText("Status");

@Step("Нажатие кнопки Read")
    public ReadOneByIdPage clickReadButton(){
        log.info("Push on Read button");
        $(BUTTON_READ).shouldBe(Condition.visible, Condition.enabled).click();
        return this;
    }

    @Step("Нажатие стрелки вверх в поле ввода ID")
    public ReadOneByIdPage increaseInputNumber(){
        log.info("Click to ID input");
        $(ID_INPUT).click();
        log.info("Sent Arrow Up to input");
        $(ID_INPUT).sendKeys(Keys.ARROW_UP);
        return this;
    }

    @Step("Нажатие стрелки вниз в поле ввода ID")
    public ReadOneByIdPage decreaseInputNumber(){
        log.info("Click to ID input");
        $(ID_INPUT).click();
        log.info("Sent Arrow Down to input");
        $(ID_INPUT).sendKeys(Keys.ARROW_DOWN);
        return this;
    }

    @Step("Ввод значения ID в поле ввода")
    public ReadOneByIdPage sentInputNumber(String idNumber){
        log.info("Click to ID input");
        $(ID_INPUT).click();
        log.info("Sent ID: " + idNumber);
        $(ID_INPUT).setValue(idNumber);
        return this;
    }

    @Step("Получение статуса операции")
    public String getStatus(){
        log.info("Getting status");
        return $(STATUS_TEXT).getText();
    }

    @Override
    @Step("Открытие страницы Read one by ID")
    public ReadOneByIdPage open() {
        log.info("Opening page Read one by ID");
        Selenide.open(BASE_URL + "/#/read/house");
        return this;
    }

    @Override
    @Step("Проверка открытия страницы Read one by ID")
    public ReadOneByIdPage isPageOpened() {
        log.info("Page Read one by ID is opened");
        $(ID_INPUT).shouldBe(Condition.visible);
        return this;
    }
}
