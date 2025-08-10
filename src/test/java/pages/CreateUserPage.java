package pages;

import com.codeborne.selenide.Selenide;
import dto.User;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

@Log4j2
public class CreateUserPage extends BasePage {

    private final By PUSH_TO_API_BUTTON = withText("PUSH"); // локатор кнопки PUSH TO API
    private final By INPUT_FIRSTNAME = byId("first_name_send");// локатор ввода имени
    private final By INPUT_LASTTNAME = byId("last_name_send");// локатор ввода фамилии
    private final By INPUT_AGE = byId("age_send");// локатор ввода возраста
    private final By INPUT_MONEY = byId("money_send");// локатор ввода днег
    private final String CHECBOX_SEX = "//input[@ID='sex_send' and @value = '%s']";//Локатор чекбокса пола
    private final By BUTTON_STATUS = byCssSelector(".status"); // локатор ввода днег
    private final By BUTTON_ID = byCssSelector(".newId"); // локатор ввода днег

    @Override
    @Step("Открытие страницы Create User Page")
    public CreateUserPage open() {
        Selenide.open(BASE_URL + "#/create/user");
        return this;
    }

    @Override
    @Step("Проверка открытия страницы Create User Page")
    public CreateUserPage isPageOpened() {
        try {
            $(PUSH_TO_API_BUTTON).shouldBe(visible);
            log.info("CreateUserPage is opened");
        } catch (Exception e) {
            log.error("Page isn't opened: {}", e.getMessage());
            Assert.fail("Page isn't opened: " + e.getMessage());
        }
        return this;
    }

    @Step("Создание User")
    public CreateUserPage createUser(User user) {
        try {
            if (user.getFirstname() != null) {
                $(INPUT_FIRSTNAME).sendKeys(user.getFirstname());
                log.info("Entering a Firstname: {}", user.getFirstname());
            }
            if (user.getLastname() != null) {
                $(INPUT_LASTTNAME).sendKeys(user.getLastname());
                log.info("Entering a Lastname: {}", user.getLastname());
            }
            if (user.getAge() != 0) {
                $(INPUT_AGE).sendKeys(Integer.toString(user.getAge()));
                log.info("Entering a Age: {}", user.getAge());
            }
            if (user.getMoney() != 0) {
                $(INPUT_MONEY).sendKeys(String.valueOf(user.getMoney()));
                log.info("Entering a Money: {}", user.getMoney());
            }
            if (!user.getSex().isEmpty()) {
                addSex(user.getSex());
            }
            $(PUSH_TO_API_BUTTON).click();
            sleep(1000);
            log.info("Click to PUSH_TO_API_BUTTON");
            addStatus(user);
            addID(user);
        } catch (Exception e) {
            log.error("Error creating user: {}", e.getMessage());
            Assert.fail("Error creating user: " + e.getMessage());
        }
        return this;
    }

    @Step("Установка чекбокса 'Пол': {sex}")
    private void addSex(String sex) {
        $x(String.format(CHECBOX_SEX, sex)).click();
        log.info("Entering a Sex: {}", sex);
    }

    @Step("Запись статуса")
    private void addStatus(User user) {
        user.setStatus($(BUTTON_STATUS).text());
        log.info("add Status: {}", user.getStatus());
    }

    @Step("Запись ID Клиента")
    private void addID(User user) {
        String status = user.getStatus();
        if (status.equals("Status: Successfully pushed, code: 201")) {
            String id = $(BUTTON_ID).text();
            user.setId(Integer.parseInt(id.replace("New user ID: ", "")));
            log.info("add User ID: {}", user.getId());
        }
    }

    @Step("Изменение значений поля {} нажатием кнопки")
    public CreateUserPage arrowClick(String fild, double value) {
        if (fild.equals("Age")) {
            if (value > 0) {
                for (double i = 0; i < value; i++) {
                    $(INPUT_AGE).sendKeys(Keys.ARROW_UP);
                }
                log.info("Entering a Age ARROW_UP " + value + " times");
            } else {
                for (double i = 0; i > value; i--) {
                    $(INPUT_AGE).sendKeys(Keys.ARROW_DOWN);
                }
                log.info("Entering a Age ARROW_DOWN " + (value * -1) + " times");
            }
        }
        if (fild.equals("Money")) {
            if (value > 0) {
                for (double i = 0; i < value; i++) {
                    $(INPUT_MONEY).sendKeys(Keys.ARROW_UP);
                }
                log.info("Entering a Money ARROW_UP " + value + " times");
            } else {
                for (double i = 0; i > value; i--) {
                    $(INPUT_MONEY).sendKeys(Keys.ARROW_DOWN);
                }
                log.info("Entering a Money ARROW_DOWN " + (value * -1) + " times");
            }
        }
        return this;
    }

    @Step("Получение значения поля 'Age'")
    public double getAge() {
        log.info("Get Value AGE: {}", $(INPUT_AGE).getValue());
        return Double.parseDouble($(INPUT_AGE).getValue());
    }

    @Step("Получение значения поля 'Money'")
    public double getMoney() {
        log.info("Get Value MONEY: {}", $(INPUT_MONEY).getValue());
        return Double.parseDouble($(INPUT_MONEY).getValue());
    }
}