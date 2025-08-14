package pages;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.testng.Assert;
import wrappers.Checkbox;
import wrappers.Input;

import java.util.Objects;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;

@Log4j2
public class BuyOrSellCarPage extends BasePage {

    private final String ENDPOINT_URL = "#/update/users/buyCar"; // endpoint страницы Buy or sell car
    private final By PUSH_TO_API_BUTTON = withText("PUSH"); // локатор кнопки PUSH TO API
    private final String ID_FIELD_USER_ID = "id_send"; // Id поля User ID
    private final String ID_FIELD_CAR_ID = "car_send"; // Id поля Car Id

    @Override
    @Step("Открытие страницы Buy or sell car")
    public BuyOrSellCarPage open() {
        log.info("Opening BuyOrSellCarPage");
        Selenide.open(ENDPOINT_URL); // открытие страницы Buy or sell car по прямой ссылке
        return this;
    }

    @Override
    @Step("Проверка открытия страницы Buy or sell car")
    public BuyOrSellCarPage isPageOpened() { // проверка отображения формы для заполнения на странице Buy or sell car
        try {
            $(PUSH_TO_API_BUTTON).shouldBe(visible);
            log.info("BuyOrSellCarPage is opened");
        } catch (Exception e) {
            log.error("Page isn't opened: {}", e.getMessage());
            Assert.fail("Page isn't opened: " + e.getMessage());
        }
        return this;
    }

    @Step("Изменение значения полей ввода нажатием на стрелочки")
    public BuyOrSellCarPage changeFieldValueByKeys(String fieldId, boolean isIncreasing, String newValue) {
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

    @Step("Получение текущего значения в поле ввода")
    public String getFieldValue(String fieldId) {
        log.info("Getting value of field \"{}\"", fieldId);
        return $(By.id(String.format("%s", fieldId))).getValue();
    }

    @Step("Заполнение полей ввода")
    public BuyOrSellCarPage inputTextInField(String fieldId, String text) {
        log.info("Trying to input text \"{}\" in the field \"{}\"", text, fieldId);
        new Input(fieldId).write(text);
        return this;
    }

    @Step("Покупка или продажа автомобиля пользователем")
    public BuyOrSellCarPage buyOrSellCar(String userId, String carId, String checkboxLabel) {
        $(By.id(ID_FIELD_USER_ID)).shouldBe(visible); // поле ввода User ID должно отображаться
        log.info("Filling field \"{}\"", ID_FIELD_USER_ID);
        new Input(ID_FIELD_USER_ID).write(userId);
        log.info("Field \"{}\" is filled with value \"{}\"", ID_FIELD_USER_ID, userId);
        $(By.id(ID_FIELD_CAR_ID)).shouldBe(visible); // поле ввода Car Id должно отображаться
        log.info("Filling field \"{}\"", ID_FIELD_CAR_ID);
        new Input(ID_FIELD_CAR_ID).write(carId);
        log.info("Field \"{}\" is filled with value \"{}\"", ID_FIELD_CAR_ID, carId);
        if (!checkboxLabel.isEmpty()) {
            log.info("Activating checkbox \"{}\"", checkboxLabel);
            new Checkbox(checkboxLabel).activateCheckbox();
            log.info("Checkbox \"{}\" is activated", checkboxLabel);
        }
        log.info("Pushing button to proceeding");
        $(PUSH_TO_API_BUTTON).click();
        sleep(100); // без него как будто мимо кнопки нажимает
        log.info("Button is pushed");
        return this;
    }
}