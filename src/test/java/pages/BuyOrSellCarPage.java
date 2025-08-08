package pages;

import com.codeborne.selenide.Selenide;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.testng.Assert;
import wrappers.Input;

import java.util.Objects;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;

@Log4j2
public class BuyOrSellCarPage extends BasePage {

    private final String ENDPOINT_URL = "#/update/users/buyCar"; // endpoint страницы Buy or sell car
    private final By PUSH_TO_API_BUTTON = withText("PUSH"); // локатор кнопки PUSH TO API

    @Override
    public BuyOrSellCarPage open() {
        log.info("Opening BuyOrSellCarPage");
        Selenide.open(ENDPOINT_URL); // открытие страницы Buy or sell car по прямой ссылке
        return this;
    }

    @Override
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

    public BuyOrSellCarPage ChangeFieldValueByKeys(String field_id, boolean isIncreasing, String newValue) {
        log.info("Changing value of field \"{}\"", field_id);
        if (isIncreasing) {
            new Input(field_id).increaseValue();
        } else {
            new Input(field_id).decreaseValue();
        }
        if (Objects.equals(getFieldValue(field_id), newValue)) {
            log.info("The value of field \"{}\" has been successfully changed to {}", field_id, newValue);
        } else {
            log.error("The value of field \"{}\" hasn't been changed to {}", field_id, newValue);
        }
        return this;
    }

    public String getFieldValue(String field_id) {
        log.info("Getting value of field \"{}\"", field_id);
        return $(By.id(String.format("%s", field_id))).getValue();
    }
}
