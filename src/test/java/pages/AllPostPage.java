package pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.conditions.Visible;
import com.github.javafaker.Faker;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class AllPostPage {

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
    private SelenideElement LOGIN_EMAIL = $x("//input[@placeholder='Enter your email...']");
    private SelenideElement LOGIN_PASSWORD = $x("//input[@placeholder='Enter your password...']");
    private SelenideElement BUTTON_GO = $x("//button[normalize-space()='GO']");



    Faker faker = new Faker();

    @Step("Заполнение полей формы 'Создание пользователя' валидными данными")
    public void fillFormCreateUserWithValidData(String sex) {
        setFirstName(faker.name().firstName());
        setLastName(faker.name().lastName());
        setAge(String.valueOf(faker.number().numberBetween(18, 60)));
        selectSex();
        setMoney(String.valueOf(faker.number().randomDouble(2, 1000, 100000)));
    }

    @Step("Заполнение полей формы 'Создание пользователя' невалидными данными")
    public void fillFormCreateUserWithInvalidData() {
        //setFirstName(String.valueOf(faker.number().numberBetween(10, 500)));
        setLastName(faker.name().lastName());
        setAge(String.valueOf(faker.number().numberBetween(1000, 1500)));
        selectSex();
        setMoney(String.valueOf(faker.number().randomDouble(2, 1000, 100000)));
    }

    @Step("Заполнение полей формы 'Создание автомобиля' невалидными данными")
    public void fillFormCreateCarWithInvalidData() {
        setEngineType(faker.options().option("111", "2222", "333", "444"));
        setModel(faker.options().option("Toyota", "Ford", "BMW", "Honda", "Nissan", "Mazda"));
        setMark(faker.options().option("Camry", "Corolla", "Focus", "Civic", "Altima", "CX-5"));
        setCarPrice(String.valueOf(faker.number().numberBetween(1000, 10000)));
    }

    @Step("Заполнение полей формы 'Создание автомобиля' валидными данными")
    public void fillFormCreateCarWithValidData() {
        setEngineType(faker.options().option("Diesel", "Petrol", "Electric", "Hybrid"));
        setModel(faker.options().option("Toyota", "Ford", "BMW", "Honda", "Nissan", "Mazda"));
        setMark(faker.options().option("Camry", "Corolla", "Focus", "Civic", "Altima", "CX-5"));
        setCarPrice(String.valueOf(faker.number().numberBetween(1000, 10000)));
    }

    @Step("Заполнение полей формы 'Продажа/покупка автомобиля' невалидными данными")
    public void fillFormSellOrBuyCarWithInvalidData(String BuyOrSell) {
        setUserID(faker.options().option("111000", "2222000", "33003"));
        setCarID(String.valueOf(faker.number().numberBetween(1000, 10000)));
        selectBuyOrSell();
    }

    @Step("Заполнение полей формы 'Продажа/покупка автомобиля' валидными данными")
    public void fillFormSellOrBuyCarWithValidData(String BuyOrSell) {
        String userId = saveNewUserId();
        INPUT_ID_SELL_CAR.shouldBe(visible).shouldBe(enabled).click();
        INPUT_ID_SELL_CAR.clear();
        INPUT_ID_SELL_CAR.setValue(userId);
        Selenide.sleep(1500);
        INPUT_ID_SELL_CAR.clear();
        INPUT_ID_SELL_CAR.setValue(userId);
        String carId = saveNewCarId();
        INPUT_CAR_ID_SELL_CAR.shouldBe(visible).shouldBe(enabled).click();
        INPUT_CAR_ID_SELL_CAR.clear();
        INPUT_CAR_ID_SELL_CAR.setValue(carId);
        Selenide.sleep(1500);
        INPUT_CAR_ID_SELL_CAR.clear();
        INPUT_CAR_ID_SELL_CAR.setValue(carId);
        selectBuyOrSell();
    }

    @Step("Вставить невалидное значение в поле First Name через клик")
    public void setFirstNameWithJs(String value) {
        INPUT_FIRST_NAME.shouldBe(visible);
        INPUT_FIRST_NAME.click();
        INPUT_FIRST_NAME.clear();
        INPUT_FIRST_NAME.sendKeys(value);
    }

    @Step("Добавить невалидное значение в поле ID через клик в форму Начисление денег пользователю")
    public void setIDWithJs(String value) {
        INPUT_ID_ADD_MONEY.shouldBe(visible);
        INPUT_ID_ADD_MONEY.click();
        INPUT_ID_ADD_MONEY.clear();
        INPUT_ID_ADD_MONEY.sendKeys(value);
    }

    @Step("Нажатие на кнопку отправки запроса 'Push to API' в форме создание пользователя")
    public void clickPushToApiButtonFormCreateUser() {
        PUSH_BUTTON_CREATE_USER.shouldBe(visible).click();
    }

    @Step("Нажатие на кнопку отправки запроса 'Push to API' в форме создание автомобиля")
    public void clickPushToApiButtonFormCreateCar() {
        PUSH_BUTTON_CREATE_CAR.shouldBe(visible);
        executeJavaScript("arguments[0].scrollIntoView(true);", PUSH_BUTTON_CREATE_CAR);
        PUSH_BUTTON_CREATE_CAR.shouldBe(enabled); // ждём, пока кнопка станет активной
        executeJavaScript("arguments[0].click();", PUSH_BUTTON_CREATE_CAR);
    }

    @Step("Нажатие на кнопку отправки запроса 'Push to API' в форме Начисление денег пользователю")
    public void clickPushToApiButtonFormAddMoney() {
        INPUT_ID_ADD_MONEY.click();
        PUSH_BUTTON_ADD_MONEY.shouldBe(visible).shouldBe(enabled).click();
        Selenide.sleep(1500);
        PUSH_BUTTON_ADD_MONEY.shouldBe(visible).shouldBe(enabled).click();
    }

    @Step("Нажатие на кнопку отправки запроса 'Push to API' в форме Покупка / продажа автомобиля")
    public void clickPushToApiButtonFormSellBuyCar() {
        INPUT_ID_SELL_CAR.click();
        PUSH_BUTTON_SELL_CAR.shouldBe(visible).shouldBe(enabled).click();
        Selenide.sleep(1500);
        PUSH_BUTTON_SELL_CAR.shouldBe(visible).shouldBe(enabled).click();
    }

    @Step("Проверка статуса ответа от сервера в форме создание пользователя")
    public void checkStatusForCreateUserForm(String text) {
        STATUS_BUTTON_CREATE_USER.shouldHave(text(text));
    }

    @Step("Проверка статуса ответа от сервера в форме Создание автомобиля")
    public void checkStatusForCreateCarForm(String text) {
        STATUS_BUTTON_CREATE_CAR.shouldHave(text(text));
    }

    @Step("Проверка статуса ответа от сервера в форме Начисление денег пользовател")
    public void checkStatusForAddMoneyForm(String text) {
        STATUS_BUTTON_ADD_MONEY.shouldHave(text(text));
    }

    @Step("Проверка статуса ответа от сервера в форме Покупка / продажа автомобиля")
    public void checkStatusForSellCarForm(String text) {
        STATUS_BUTTON_SELL_CAR.shouldHave(text(text));
    }


    // Элементы формы "Создание пользователя"

    public void openPageAllPost() {
        open("/#/create/all");
    }

    @Step("Заполение поля ввода First Name")
    public void setFirstName(String firstName) {
        INPUT_FIRST_NAME.setValue(firstName);
    }

    @Step("Заполение поля ввода Last Name")
    public void setLastName(String lastName) {
        INPUT_LAST_NAME.setValue(lastName);
    }

    @Step("Заполение поля ввода Age")
    public void setAge(String age) {
        INPUT_AGE.setValue(age);
    }

    @Step("Выбор чекбокса Sex")
    public void selectSex() {
        String sex = faker.options().option("MALE", "FEMALE");
        $x(String.format("//input[@name='sex_send' and @value='%s']", sex)).shouldBe(visible).click();
    }

    @Step("Заполение поля ввода Engine Type")
    public void setEngineType(String engine) {
        INPUT_ENGINE_TYPE.shouldBe(visible).shouldBe(enabled).click();
        INPUT_ENGINE_TYPE.clear();
        INPUT_ENGINE_TYPE.sendKeys(engine);
        Selenide.sleep(1500);
        INPUT_ENGINE_TYPE.clear();
        INPUT_ENGINE_TYPE.sendKeys(engine);
    }

    @Step("Заполение поля ввода Mark")
    public void setMark(String mark) {
        INPUT_MARK.setValue(mark);
    }

    @Step("Заполение поля ввода Model")
    public void setModel(String model) {
        INPUT_MODEL.setValue(model);
    }

    @Step("Заполение поля ввода CarPrice")
    public void setCarPrice(String carPrice) {
        INPUT_CAR_PRICE.setValue(carPrice);
    }

    @Step("Выбор чекбокса SettleOrEvict")
    public void selectSettleOrEvict() {
        String settleOrEvict = faker.options().option("SETTLE", "EVICT");
        $x(String.format("//input[@name='settleOrEvict' and @value='%s']", settleOrEvict)).shouldBe(visible).click();
    }

    @Step("Выбор чекбокса Buy or Sell")
    public void selectBuyOrSell() {
        String BuyOrSell = faker.options().option("buyCar", "sellCar");
        $x(String.format("//input[@name='settleOrEvict' and @value='%s']", BuyOrSell)).shouldBe(visible).click();
    }

    @Step("Заполение поля ввода Money в форме создание пользователя")
    public void setMoney(String money) {
        INPUT_MONEY_CREATE_USER.setValue(money);
    }

    @Step("Заполение поля ввода Money в форме начисление денег пользователю")
    public void setMoneyAddMoney(String money) {
        INPUT_MONEY_ADD_MONEY.setValue(money);
    }

    @Step("Заполение поля ввода ID в форме начисление денег пользователю")
    public void setID(String userid) {
        INPUT_ID_ADD_MONEY.setValue(userid);
    }

    @Step("Заполение поля ввода  User ID в форме Продажа / Покупка автомобиля")
    public void setUserID(String userid) {
        INPUT_ID_SELL_CAR.setValue(userid);
    }

    @Step("Заполение поля ввода Car ID в форме Продажа / Покупка автомобиля")
    public void setCarID(String userid) {
        INPUT_CAR_ID_SELL_CAR.setValue(userid);
    }

    // login
    @Step("Вход пользователя на сайт")
    public void login (String email, String password) {
        LOGIN_EMAIL.setValue(email);
        LOGIN_PASSWORD.setValue(password);
        BUTTON_GO.shouldBe(visible).click();
        Selenide.sleep(1500);
    }

    public  String saveNewUserId() {
        NEW_USER_ID.shouldBe(visible); // ждём появления элемента
        String userID = NEW_USER_ID.getText();
        String cleanUserId = userID.replaceAll("\\D+", "");
        System.out.println("Сохранённый userID: " + cleanUserId);
        return cleanUserId;
    }

    public  String saveNewCarId() {
        NEW_CAR_ID.shouldBe(visible); // ждём появления элемента
        String carID = NEW_CAR_ID.getText();
        String cleanCarId = carID.replaceAll("\\D+", "");
        System.out.println("Сохранённый carID: " + cleanCarId);
        return cleanCarId;
    }

    @Step("Заполнение полей формы 'Начисление денег пользователю' валидными данными")
    public void fillFormAddMoneyWithValidData() {
        String userIdMoney = saveNewUserId();
        INPUT_ID_ADD_MONEY.shouldBe(visible).shouldBe(enabled).click();
        INPUT_ID_ADD_MONEY.clear();
        INPUT_ID_ADD_MONEY.setValue(userIdMoney);
        Selenide.sleep(1500);
        INPUT_ID_ADD_MONEY.clear();
        INPUT_ID_ADD_MONEY.setValue(userIdMoney);
        Selenide.sleep(1500);
        INPUT_ID_ADD_MONEY.clear();
        INPUT_ID_ADD_MONEY.setValue(userIdMoney);
        setMoneyAddMoney(String.valueOf(faker.number().randomDouble(2, 100, 1000)));
    }
    @Step("Заполнение полей формы 'Начисление денег пользователю' невалидными данными")
    public void fillFormAddMoneyWithInvalidData() {
        //INPUT_ID_ADD_MONEY.shouldBe(visible).click();
        //setID(String.valueOf(faker.number().numberBetween(10000, 11000)));
        setMoneyAddMoney(String.valueOf(faker.number().randomDouble(2, 100, 1000)));
    }
}