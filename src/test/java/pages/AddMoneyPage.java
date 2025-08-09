package pages;

import com.codeborne.selenide.Selenide;
import dto.User;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.testng.Assert;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selectors.byCssSelector;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;

@Log4j2
public class AddMoneyPage extends BasePage{

    private final By PUSH_TO_API_BUTTON = withText("PUSH"); // локатор кнопки PUSH TO API
    private final By INPUT_USERID = byId("id_send");
    private final By INPUT_MONEY = byId("money_send");
    private final By BUTTON_STATUS = byCssSelector(".status"); // локатор ввода днег
    private final By BUTTON_MONEY = byCssSelector(".money"); // локатор ввода днег


    @Override
    @Step("Открытие страницы Add Money")
    public AddMoneyPage open() {
        Selenide.open(BASE_URL+"#/update/users/plusMoney");
        return this;
    }

    @Override
    public AddMoneyPage isPageOpened() {
        try {
            $(PUSH_TO_API_BUTTON).shouldBe(visible);
            log.info("AddMoneyPage is opened");
        } catch (Exception e) {
            log.error("Page isn't opened: {}", e.getMessage());
            Assert.fail("Page isn't opened: " + e.getMessage());
        }
        return this;
    }

    @Step
    public AddMoneyPage addMoney (User user,double money){
        if(user.getId()!=0){
            $(INPUT_USERID).sendKeys(Integer.toString(user.getId()));
            log.info("Entering a Id: {}",user.getId());
        }
        if(money!=0){
        $(INPUT_MONEY).sendKeys(String.valueOf(money));
            log.info("Entering a money: {}",money);
    }
        $(PUSH_TO_API_BUTTON).click();
        log.info("Click to PUSH_TO_API_BUTTON");
        sleep(1000);
        addStatus(user);
        addNewMoney(user);
        return this;
    }

    @Step("Запись статуса")
    private void addStatus(User user){
        user.setStatus($(BUTTON_STATUS).text());
        log.info("add Status: {}",user.getStatus());
    }

    @Step("Запись нового значения Money")
    private void addNewMoney(User user){
        String status = user.getStatus();
        if(status.equals("Status: Successfully pushed, code: 200")) {
            String money = $(BUTTON_MONEY).text();
            user.setMoney(Double.parseDouble(money));
            log.info("add New Money: {}", user.getMoney());
        }
    }
}