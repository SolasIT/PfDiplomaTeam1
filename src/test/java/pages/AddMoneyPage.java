package pages;

import com.codeborne.selenide.Selenide;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;

@Log4j2
public class AddMoneyPage extends BasePage {

    private final String ENDPOINT_URL = "#/update/users/plusMoney"; // endpoint страницы Buy or sell car
    private final By PUSH_TO_API_BUTTON = withText("PUSH"); // локатор кнопки PUSH TO API

    @Override
    public AddMoneyPage open() {
        log.info("Opening AddMoneyPage");
        Selenide.open(ENDPOINT_URL); // открытие страницы Add money по прямой ссылке
        return this;
    }

    @Override
    public AddMoneyPage isPageOpened() {
        $(PUSH_TO_API_BUTTON).shouldBe(visible);
        log.info("AddMoneyPage is opened");
        return this;
    }
}
