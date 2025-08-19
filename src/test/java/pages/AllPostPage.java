package pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.github.javafaker.Faker;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.testng.Assert;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

@Log4j2
public class AllPostPage extends BasePage {

    private SelenideElement PUSH_BUTTON_CREATE_USER = $x("//table[.//input[@id='first_name_send']]/following::button[contains(@class, 'tableButton')][1]");
    private SelenideElement PUSH_BUTTON_ADD_MONEY = $x("//table[.//input[@id='first_name_send']]/following::button[contains(@class, 'tableButton')][2]");
    private SelenideElement PUSH_BUTTON_CREATE_CAR = $x("//table[.//input[@id='first_name_send']]/following::button[contains(@class, 'tableButton')][5]");
    private SelenideElement PUSH_BUTTON_SELL_CAR = $x("//table[.//input[@id='first_name_send']]/following::button[contains(@class, 'tableButton')][4]");
    private SelenideElement STATUS_BUTTON_CREATE_USER = $x("//table[.//input[@id='first_name_send']]/following::button[contains(@class, 'btn')][2]");
    private SelenideElement STATUS_BUTTON_ADD_MONEY = $x("//table[.//input[@id='first_name_send']]/following::button[contains(@class, 'btn')][5]");
    private SelenideElement STATUS_BUTTON_CREATE_CAR = $x("//table[.//input[@id='first_name_send']]/following::button[contains(@class, 'btn')][14]");
    private SelenideElement STATUS_BUTTON_SELL_CAR = $x("//table[.//input[@id='first_name_send']]/following::button[contains(@class, 'btn')][11]");
    private SelenideElement NEW_USER_ID = $x("//table[.//input[@id='first_name_send']]/following::button[contains(@class, 'btn')][3]");
    private SelenideElement NEW_CAR_ID = $x("//table[.//input[@id='first_name_send']]/following::button[contains(@class, 'btn')][15]");
    private SelenideElement INPUT_FIRST_NAME = $x("//*[@id='first_name_send']");
    private SelenideElement INPUT_LAST_NAME = $x("//*[@id='last_name_send']");
    private SelenideElement INPUT_AGE = $x("//*[@id='age_send']");
    private SelenideElement INPUT_ENGINE_TYPE = $x("//*[@id='car_engine_type_send']");
    private SelenideElement INPUT_MARK = $x("//*[@id='car_mark_send']");
    private SelenideElement INPUT_MODEL = $x("//*[@id='car_model_send']");
    private SelenideElement INPUT_CAR_PRICE = $x("//*[@id='car_price_send']");
    private SelenideElement INPUT_MONEY_CREATE_USER = $x("(//*[@id='money_send'])[1]");
    private SelenideElement INPUT_MONEY_ADD_MONEY = $x("(//*[@id='money_send'])[2]");
    private SelenideElement INPUT_ID_ADD_MONEY = $x("(//table[1]//*[@id='id_send'])[1]");
    private SelenideElement INPUT_ID_SELL_CAR = $x("(//table[1]//*[@id='id_send'])[3]");
    private SelenideElement INPUT_CAR_ID_SELL_CAR = $x("//table[1]//*[@id='car_send']");

    Faker faker = new Faker();

    @Step("Заполнение полей формы 'Создание пользователя' валидными данными")
    public AllPostPage fillFormCreateUserWithValidData(String sex) {
        setFirstName(faker.name().firstName());
        setLastName(faker.name().lastName());
        setAge(String.valueOf(faker.number().numberBetween(18, 60)));
        selectSex();
        setMoney(String.valueOf(faker.number().randomDouble(2, 1000, 100000)));
        log.info("All form's fields 'Create user' are filled with correct data");
        return this;
    }

    @Step("Заполнение полей формы 'Создание пользователя' невалидными данными")
    public AllPostPage fillFormCreateUserWithInvalidData() {
        setLastName(faker.name().lastName());
        setAge(String.valueOf(faker.number().numberBetween(1000, 1500)));
        selectSex();
        setMoney(String.valueOf(faker.number().randomDouble(2, 1000, 100000)));
        log.info("All form's fields 'Create user' are filled with incorrect data");
        return this;
    }

    @Step("Заполнение полей формы 'Создание автомобиля' невалидными данными")
    public AllPostPage fillFormCreateCarWithInvalidData() {
        setEngineType(faker.options().option("111", "2222", "333", "444"));
        setModel(faker.options().option("Toyota", "Ford", "BMW", "Honda", "Nissan", "Mazda"));
        setMark(faker.options().option("Camry", "Corolla", "Focus", "Civic", "Altima", "CX-5"));
        setCarPrice(String.valueOf(faker.number().numberBetween(1000, 10000)));
        log.info("All form's fields 'Create car' are filled with incorrect data");
        return this;
    }

    @Step("Заполнение полей формы 'Создание автомобиля' валидными данными")
    public AllPostPage fillFormCreateCarWithValidData() {
        executeJavaScript("arguments[0].scrollIntoView(true);", PUSH_BUTTON_CREATE_CAR);
        setEngineType(faker.options().option("Gasoline", "Diesel", "Electric", "CNG", "Hydrogenic ", "PHEV"));
        setModel(faker.options().option("Toyota", "Ford", "BMW", "Honda", "Nissan", "Mazda"));
        setMark(faker.options().option("Camry", "Corolla", "Focus", "Civic", "Altima", "CX-5"));
        setCarPrice(String.valueOf(faker.number().numberBetween(1000, 10000)));
        Selenide.sleep(1500);
        log.info("All form's fields 'Create car' are filled with correct data");
        return this;
    }

    @Step("Заполнение полей формы 'Продажа/покупка автомобиля' невалидными данными")
    public AllPostPage fillFormSellOrBuyCarWithInvalidData(String BuyOrSell) {
        setUserID(faker.options().option("111000", "2222000", "33003"));
        setCarID(String.valueOf(faker.number().numberBetween(1000, 10000)));
        selectBuyOrSell();
        log.info("All form's fields 'Buy/Sell car' are filled with incorrect data");
        return this;
    }

    @Step("Заполнение полей формы 'Продажа/покупка автомобиля' валидными данными")
    public AllPostPage fillFormSellOrBuyCarWithValidData(String BuyOrSell) {
        String userId = saveNewUserId();
        INPUT_ID_SELL_CAR.shouldBe(visible).shouldBe(enabled).click();
        INPUT_ID_SELL_CAR.clear();
        INPUT_ID_SELL_CAR.setValue(userId);
        INPUT_ID_SELL_CAR.shouldHave(value(userId));
        log.info("Value set in 'User ID': {}", userId);
        String carId = saveNewCarId();
        INPUT_CAR_ID_SELL_CAR.shouldBe(visible).shouldBe(enabled).click();
        INPUT_CAR_ID_SELL_CAR.clear();
        INPUT_CAR_ID_SELL_CAR.setValue(carId);
        INPUT_CAR_ID_SELL_CAR.shouldHave(value(carId));
        log.info("Value set in 'User ID': {}", carId);
        selectBuyOrSell();
        log.info("All form's fields 'Buy/Sell car' are filled with correct data");
        return this;
    }

    @Step("Вставить невалидное значение в поле First Name через клик")
    public AllPostPage setFirstNameWithJs(String value) {
        INPUT_FIRST_NAME.shouldBe(visible);
        INPUT_FIRST_NAME.click();
        INPUT_FIRST_NAME.clear();
        INPUT_FIRST_NAME.sendKeys(value);
        log.info("Incorrect value set in 'First name field'");
        return this;
    }

    @Step("Добавить невалидное значение в поле ID через клик в форму 'Начисление денег пользователю'")
    public AllPostPage setIDWithJs(String value) {
        INPUT_ID_ADD_MONEY.shouldBe(visible);
        INPUT_ID_ADD_MONEY.click();
        INPUT_ID_ADD_MONEY.clear();
        INPUT_ID_ADD_MONEY.sendKeys(value);
        log.info("Incorrect value set in 'User ID field'");
        return this;
    }

    @Step("Нажатие на кнопку отправки запроса 'Push to API' в форме создание пользователя")
    public AllPostPage clickPushToApiButtonFormCreateUser() {
        PUSH_BUTTON_CREATE_USER.shouldBe(visible).click();
        log.info("Click to PUSH_BUTTON_CREATE_USER");
        return this;
    }

    @Step("Нажатие на кнопку отправки запроса 'Push to API' в форме создание автомобиля")
    public AllPostPage clickPushToApiButtonFormCreateCar() {
        PUSH_BUTTON_CREATE_CAR.shouldBe(visible);
        executeJavaScript("arguments[0].scrollIntoView(true);", PUSH_BUTTON_CREATE_CAR);
        PUSH_BUTTON_CREATE_CAR.shouldBe(enabled);
        Selenide.sleep(1500);
        executeJavaScript("arguments[0].click();", PUSH_BUTTON_CREATE_CAR);
        log.info("Click to PUSH_BUTTON_CREATE_CAR");
        return this;
    }

    @Step("Нажатие на кнопку отправки запроса 'Push to API' в форме 'Начисление денег пользователю'")
    public AllPostPage clickPushToApiButtonFormAddMoney() {
        INPUT_ID_ADD_MONEY.click();
        PUSH_BUTTON_ADD_MONEY.shouldBe(visible).shouldBe(enabled).click();
        Selenide.sleep(1500);
        PUSH_BUTTON_ADD_MONEY.shouldBe(visible).shouldBe(enabled).click();
        log.info("Click to PUSH_BUTTON_ADD_MONEY");
        return this;
    }

    @Step("Нажатие на кнопку отправки запроса 'Push to API' в форме Покупка / продажа автомобиля")
    public AllPostPage clickPushToApiButtonFormSellBuyCar() {
        INPUT_ID_SELL_CAR.click();
        PUSH_BUTTON_SELL_CAR.shouldBe(visible).shouldBe(enabled).click();
        Selenide.sleep(1500);
        PUSH_BUTTON_SELL_CAR.shouldBe(visible).shouldBe(enabled).click();
        log.info("Click to PUSH_BUTTON_SELL_CAR");
        return this;
    }

    @Step("Проверка статуса ответа от сервера в форме 'Cоздание пользователя'")
    public AllPostPage checkStatusForCreateUserForm(String text) {
        STATUS_BUTTON_CREATE_USER.shouldHave(text(text));
        log.info("Got status message");
        return this;
    }

    @Step("Проверка статуса ответа от сервера в форме 'Создание автомобиля'")
    public AllPostPage checkStatusForCreateCarForm(String text) {
        STATUS_BUTTON_CREATE_CAR.shouldHave(text(text));
        log.info("Got status message");
        return this;
    }

    @Step("Проверка статуса ответа от сервера в форме Начисление денег пользовател")
    public AllPostPage checkStatusForAddMoneyForm(String text) {
        STATUS_BUTTON_ADD_MONEY.shouldHave(text(text));
        log.info("Got status message");
        return this;
    }

    @Step("Проверка статуса ответа от сервера в форме Покупка / продажа автомобиля")
    public AllPostPage checkStatusForSellCarForm(String text) {
        STATUS_BUTTON_SELL_CAR.shouldHave(text(text));
        log.info("Got status message");
        return this;
    }

    @Override
    public AllPostPage open() {
        Selenide.open(BASE_URL + "#/create/all");
        return this;
    }

    @Override
    public AllPostPage isPageOpened() {
        try {
            $(PUSH_BUTTON_CREATE_USER).shouldBe(visible);
            log.info("AllPostPage is opened");
        } catch (Exception e) {
            log.error("Page isn't opened: {}", e.getMessage());
            Assert.fail("Page isn't opened: " + e.getMessage());
        }
        return this;
    }
    // Элементы формы "Создание пользователя"
    @Step("Заполение поля ввода First Name")
    public void setFirstName(String firstName) {
        INPUT_FIRST_NAME.setValue(firstName);
        log.info("Value set in 'First name' field");
    }

    @Step("Заполение поля ввода Last Name")
    public void setLastName(String lastName) {
        INPUT_LAST_NAME.setValue(lastName);
        log.info("Value set in 'Last name' field");
    }

    @Step("Заполение поля ввода Age")
    public void setAge(String age) {
        INPUT_AGE.setValue(age);
        log.info("Value set in 'Age' field");
    }

    @Step("Выбор чекбокса Sex")
    public void selectSex() {
        String sex = faker.options().option("MALE", "FEMALE");
        $x(String.format("//input[@name='sex_send' and @value='%s']", sex)).shouldBe(visible).click();
        log.info("Checkbox selected");
    }

    @Step("Заполение поля ввода Engine Type")
    public void setEngineType(String engine) {
        INPUT_ENGINE_TYPE.shouldBe(visible).shouldBe(enabled).click();
        INPUT_ENGINE_TYPE.clear();
        INPUT_ENGINE_TYPE.sendKeys(engine);
        INPUT_ENGINE_TYPE.shouldHave(value(engine));
        log.info("Value set in 'Engine type': {}", engine);
    }

    @Step("Заполение поля ввода Mark")
    public void setMark(String mark) {
        INPUT_MARK.setValue(mark);
        log.info("Value set in 'Mark' field");
    }

    @Step("Заполение поля ввода Model")
    public void setModel(String model) {
        INPUT_MODEL.setValue(model);
        log.info("Value set in 'Model' field");
    }

    @Step("Заполение поля ввода CarPrice")
    public void setCarPrice(String carPrice) {
        INPUT_CAR_PRICE.setValue(carPrice);
        log.info("Value set in 'Car Price' field");
    }

    @Step("Выбор чекбокса Buy or Sell")
    public void selectBuyOrSell() {
        String BuyOrSell = faker.options().option("buyCar", "sellCar");
        $x(String.format("//input[@name='settleOrEvict' and @value='%s']", BuyOrSell)).shouldBe(visible).click();
        log.info("Checkbox selected in 'Buy or Sell' Form");
    }

    @Step("Заполение поля ввода Money в форме 'Создание пользователя'")
    public void setMoney(String money) {
        INPUT_MONEY_CREATE_USER.setValue(money);
        log.info("Value set in 'Money' field");
    }

    @Step("Заполение поля ввода Money в форме 'Начисление денег пользователю'")
    public void setMoneyAddMoney(String money) {
        INPUT_MONEY_ADD_MONEY.setValue(money);
        log.info("Value set in 'Money' field in Add Money Form");
    }

    @Step("Заполение поля ввода ID в форме 'Начисление денег пользователю'")
    public void setID(String userid) {
        INPUT_ID_ADD_MONEY.setValue(userid);
        log.info("Value set in 'User ID' field in Add Money Form");
    }

    @Step("Заполение поля ввода  User ID в форме 'Продажа / Покупка автомобиля'")
    public void setUserID(String userid) {
        INPUT_ID_SELL_CAR.setValue(userid);
        log.info("Value set in 'User ID' field in 'Buy/Sell car' Form");
    }

    @Step("Заполение поля ввода Car ID в форме 'Продажа / Покупка автомобиля'")
    public void setCarID(String userid) {
        INPUT_CAR_ID_SELL_CAR.setValue(userid);
        log.info("Value set in 'Car ID' field in 'Buy/Sell car' Form");
    }

    public  String saveNewUserId() {
        NEW_USER_ID.shouldBe(visible)
                .shouldHave(matchText("\\d+"));
        String userID = NEW_USER_ID.getText();
        log.info("Raw NEW_USER_ID value: '{}'", userID);
        String cleanUserId = userID.replaceAll("\\D+", "");
        log.info("New User ID: {}", cleanUserId);
        return cleanUserId;
    }

    public  String saveNewCarId() {
        NEW_CAR_ID.shouldBe(visible)
                .shouldHave(matchText("\\d+"));
        String carID = NEW_CAR_ID.getText();
        log.info("Raw NEW_USER_ID value: '{}'", carID);
        String cleanCarId = carID.replaceAll("\\D+", "");
        log.info("New Car ID: {}", cleanCarId);
        return cleanCarId;
    }

    @Step("Заполнение полей формы 'Начисление денег пользователю' валидными данными")
    public AllPostPage fillFormAddMoneyWithValidData() {
        String userIdMoney = saveNewUserId();
        INPUT_ID_ADD_MONEY.shouldBe(visible).shouldBe(enabled).click();
        INPUT_ID_ADD_MONEY.clear();
        INPUT_ID_ADD_MONEY.setValue(userIdMoney);
        INPUT_ID_ADD_MONEY.shouldHave(value(userIdMoney));
        log.info("Value set in 'User ID': {}", userIdMoney);
        setMoneyAddMoney(String.valueOf(faker.number().randomDouble(2, 100, 1000)));
        log.info("All form's fields 'Add money' are filled with correct data");
        return this;
    }

    @Step("Заполнение полей формы 'Начисление денег пользователю' невалидными данными")
    public AllPostPage fillFormAddMoneyWithInvalidData() {
        setMoneyAddMoney(String.valueOf(faker.number().randomDouble(2, 100, 1000)));
        log.info("All form's fields 'Add money' are filled with incorrect data");
        return this;
    }
}