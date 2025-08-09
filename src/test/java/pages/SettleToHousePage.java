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
public class SettleToHousePage extends BasePage {

    private final String ENDPOINT_URL = "#/update/houseAndUser"; // endpoint страницы Settle to house
    private final By PUSH_TO_API_BUTTON = withText("PUSH"); // локатор кнопки PUSH TO API
    private final String ID_FIELD_USER_ID = "id_send"; // Id поля User ID
    private final String ID_FIELD_HOUSE_ID = "house_send"; // Id поля House ID


    @Override
    @Step("Открытие страницы Settle to house")
    public SettleToHousePage open() {
        log.info("Opening SettleToHousePage");
        Selenide.open(ENDPOINT_URL); // открытие страницы Settle to house по прямой ссылке
        return this;
    }

    @Override
    @Step("Проверка открытия страницы Settle to house")
    public SettleToHousePage isPageOpened() { // проверка отображения формы для заполнения на странице Settle to house
        try {
            $(PUSH_TO_API_BUTTON).shouldBe(visible);
            log.info("SettleToHousePage is opened");
        } catch (Exception e) {
            log.error("Page isn't opened: {}", e.getMessage());
            Assert.fail("Page isn't opened: " + e.getMessage());
        }
        return this;
    }

    @Step("Изменение значения полей ввода нажатием на стрелочки")
    public SettleToHousePage changeFieldValueByKeys(String field_id, boolean isIncreasing, String newValue) {
        log.info("Changing value of field \"{}\"", field_id);
        if (isIncreasing) {
            new Input(field_id).increaseValue();
            log.info("Value of the field \"{}\" was increased", field_id);
        } else {
            new Input(field_id).decreaseValue();
            log.info("Value of the field \"{}\" was decreased", field_id);
        }
        if (Objects.equals(getFieldValue(field_id), newValue)) {
            log.info("The value of field \"{}\" has been successfully changed to {}", field_id, newValue);
        } else {
            log.error("The value of field \"{}\" hasn't been changed to {}", field_id, newValue);
        }
        return this;
    }

    @Step("Получение текущего значения в поле ввода")
    public String getFieldValue(String field_id) {
        log.info("Getting value of field \"{}\"", field_id);
        return $(By.id(String.format("%s", field_id))).getValue();
    }

    @Step("Заполнение полей ввода")
    public SettleToHousePage inputTextInField(String field_id, String text) {
        log.info("Trying to input text \"{}\" in the field \"{}\"", text, field_id);
        new Input(field_id).write(text);
        return this;
    }

    @Step("Заселение или выселение пользователя из дома")
    public SettleToHousePage settleToOrEvictFromHouse(String user_id, String house_id, String checkboxLabel) {
        $(By.id(ID_FIELD_USER_ID)).shouldBe(visible); // поле ввода User ID должно отображаться
        log.info("Filling field \"{}\"", ID_FIELD_USER_ID);
        new Input(ID_FIELD_USER_ID).write(user_id);
        log.info("Field \"{}\" is filled with value \"{}\"", ID_FIELD_USER_ID, user_id);
        $(By.id(ID_FIELD_HOUSE_ID)).shouldBe(visible); // поле ввода House ID должно отображаться
        log.info("Filling field \"{}\"", ID_FIELD_HOUSE_ID);
        new Input(ID_FIELD_HOUSE_ID).write(house_id);
        log.info("Field \"{}\" is filled with value \"{}\"", ID_FIELD_HOUSE_ID, house_id);
        if (!checkboxLabel.isEmpty()) {
            log.info("Activating checkbox \"{}\"", checkboxLabel);
            new Checkbox(checkboxLabel).activateCheckbox();
            log.info("Checkbox \"{}\" is activated", checkboxLabel);
        }
        log.info("Pushing button to proceeding");
        $(PUSH_TO_API_BUTTON).click();
        sleep(100);
        log.info("Button is pushed");
        return this;
    }
}
