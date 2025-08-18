package pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import dto.ui.User;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.testng.Assert;
import wrappers.Input;

import java.util.Objects;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;

@Log4j2
public class ReadUserWithCarsPage extends BasePage{

    private final By BUTTON_STATUS = byXpath("//button[contains(@class, 'status')]");
    private final By BUTTON_RELOAD = byXpath("//*[@id='root']/div/section/div/div/button[2]");

    @Override
    @Step("Открытие страницы Read User With Cars")
    public BasePage open() {
        log.info("Открытие страницы User Read All");
        Selenide.open(BASE_URL + "#/read/userInfo");
        return this;
    }

    @Override
    public BasePage isPageOpened() {
        try {
            $(BUTTON_RELOAD).shouldBe(visible);
            log.info("ReadUserWithCarsPage is opened");
        } catch (Exception e) {
            log.error("Page isn't opened: {}", e.getMessage());
            Assert.fail("Failed to open ReadUserWithCarsPage " + e.getMessage());
        }
        return this;
    }

    @Step("Запись статуса")
    private void addStatus(User user) {
        user.setStatus($(BUTTON_STATUS).text());
        log.info("add Status: {}", user.getStatus());
    }

    @Step("Заполнение поля ввода")
    public ReadUserWithCarsPage inputTextInField(String fieldId, String text) {
        log.info("Trying to input text \"{}\" in the field \"{}\"", text, fieldId);
        new Input(fieldId).write(text);
        return this;
    }

    @Step("Получение текущего значения в поле ввода")
    public String getFieldValue(String fieldId) {
        log.info("Getting value of field \"{}\"", fieldId);
        return $(By.id(String.format("%s", fieldId))).getValue();
    }

    @Step("Изменение значения полей ввода нажатием на стрелочки")
    public ReadUserWithCarsPage changeValueByRead(String fieldId, boolean isIncreasing, String newValue) {
        log.info("Changing value of field \"{}\"", fieldId);
        if (isIncreasing) {
            new Input(fieldId).increaseValue();
            log.info("Value of the field \"{}\" was increased", fieldId);
        } else {
            new Input(fieldId).decreaseValue();
            log.info("Value of the field \"{}\" was decreased", fieldId);
        }
        if (Objects.equals(getFieldValue(fieldId), newValue)) {
            log.info("The value of field \"{}\" has been successfully changed to {}", fieldId, newValue);
        } else {
            log.error("The value of field \"{}\" hasn't been changed to {}", fieldId, newValue);
        }
        return this;
    }
}
