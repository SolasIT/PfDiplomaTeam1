package tests.ui;

import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.testng.annotations.Test;
import pages.AllPostPage;
import pages.MainPage;
import utils.PropertyReader;

public class AllPostTest extends BaseTest {

    AllPostPage allPostPage = new AllPostPage();
    MainPage mainPage = new MainPage();

    String email = System.getProperty("email", PropertyReader.getProperty("email"));
    String password = System.getProperty("password", PropertyReader.getProperty("password"));

    // Create user Form

    @Test(description = "Отправка пустой формы 'Создание пользователя' — ошибка валидации")
    @Owner("Verbitskaia Daria")
    @Feature("All Post")
    public void testEmptyFormCreateUser() {
        mainPage.open()
                .isPageOpened()
                .auth(email, password);
        allPostPage.open()
                .isPageOpened()
                .clickPushToApiButtonFormCreateUser()
                .checkStatusForCreateUserForm("Invalid request data");
    }

    @Test(description = "Отправка некорректных данных при создании пользователя — ошибка 400")
    @Owner("Verbitskaia Daria")
    @Feature("All Post")
    public void testInvalidDataSubmissionOnCreateUserForm() {
        mainPage.open()
                .isPageOpened()
                .auth(email, password);
        allPostPage.open()
                .isPageOpened()
                .setFirstNameWithJs("123")
                .fillFormCreateUserWithInvalidData()
                .clickPushToApiButtonFormCreateUser()
                .checkStatusForCreateUserForm("Request failed with status code 400");
    }

    @Test(description = "Успешное создание нового пользователя")
    @Owner("Verbitskaia Daria")
    @Feature("All Post")
    public void testValidUserSubmissionOnCreateUserForm() {
        mainPage.open()
                .isPageOpened()
                .auth(email, password);
        allPostPage.open()
                .isPageOpened()
                .fillFormCreateUserWithValidData("MALE")
                .clickPushToApiButtonFormCreateUser()
                .checkStatusForCreateUserForm("Successfully pushed");
    }

    // Add money Form

    @Test(description = "Отправка пустой формы 'Начисление денег пользователю'  — ошибка валидации")
    @Owner("Verbitskaia Daria")
    @Feature("All Post")
    public void testEmptyFormAddMoney() {
        mainPage.open()
                .isPageOpened()
                .auth(email, password);
        allPostPage.open()
                .isPageOpened()
                .clickPushToApiButtonFormAddMoney()
                .checkStatusForAddMoneyForm("Incorrect input data");
    }

    @Test(description = "Успешное начисление денег пользователю")
    @Owner("Verbitskaia Daria")
    @Feature("All Post")
    public void testValidUserSubmissionOnAddMoneyForm() {
        mainPage.open()
                .isPageOpened()
                .auth(email, password);
        allPostPage.open()
                .isPageOpened()
                .fillFormCreateUserWithValidData("MALE")
                .clickPushToApiButtonFormCreateUser()
                .saveNewUserId();
        allPostPage.fillFormAddMoneyWithValidData()
                .clickPushToApiButtonFormAddMoney()
                .checkStatusForAddMoneyForm("Successfully pushed");
    }

    @Test(description = "Отправка некорректных данных при начислениии денег пользователю — ошибка 404")
    @Owner("Verbitskaia Daria")
    @Feature("All Post")
    public void testInvalidDataSubmissionInAddMoneyForm() {
        mainPage.open()
                .isPageOpened()
                .auth(email, password);
        allPostPage.open()
                .isPageOpened()
                .setIDWithJs("11000")
                .fillFormAddMoneyWithInvalidData()
                .clickPushToApiButtonFormAddMoney()
                .checkStatusForAddMoneyForm("Request failed with status code 404");
    }

    // Create Car Form

    @Test(description = "Отправка пустой формы 'Создание автомобиля' — ошибка валидации")
    @Owner("Verbitskaia Daria")
    @Feature("All Post")
    public void testEmptyFormCreateCar() {
        mainPage.open()
                .isPageOpened()
                .auth(email, password);
        allPostPage.open()
                .isPageOpened()
                .clickPushToApiButtonFormCreateCar()
                .checkStatusForCreateCarForm("Invalid request data");
    }

    @Test(description = "Отправка некорректных данных при создании автомобиля — ошибка 400")
    @Owner("Verbitskaia Daria")
    @Feature("All Post")
    public void testInvalidDataSubmissionOnCreateCarForm() {
        mainPage.open()
                .isPageOpened()
                .auth(email, password);
        allPostPage.open()
                .isPageOpened()
                .fillFormCreateCarWithInvalidData()
                .clickPushToApiButtonFormCreateCar()
                .checkStatusForCreateCarForm("Request failed with status code 400");
    }

    @Test(description = "Успешное создание нового автомобиля")
    @Owner("Verbitskaia Daria")
    @Feature("All Post")
    public void testValidUserSubmissionOnCreateCarForm() {
        mainPage.open()
                .isPageOpened()
                .auth(email, password);
        allPostPage.open()
                .isPageOpened()
                .fillFormCreateCarWithValidData()
                .clickPushToApiButtonFormCreateCar()
                .checkStatusForCreateCarForm("Successfully pushed");
    }

    // Buy or sell car Form

    @Test(description = "Отправка пустой формы 'Продажа/покупка автомобиля'  — ошибка валидации")
    @Owner("Verbitskaia Daria")
    @Feature("All Post")
    public void testEmptyFormSellOrBuyCar() {
        mainPage.open()
                .isPageOpened()
                .auth(email, password);
        allPostPage.open()
                .isPageOpened()
                .clickPushToApiButtonFormSellBuyCar()
                .checkStatusForSellCarForm("Incorrect input data");
    }

    @Test(description = "Отправка некорректных данных при Продаже /покупке автомобиля  — ошибка 404")
    @Owner("Verbitskaia Daria")
    @Feature("All Post")
    public void testInvalidDataSubmissionSellOrBuyCar() {
        mainPage.open()
                .isPageOpened()
                .auth(email, password);
        allPostPage.open()
                .isPageOpened()
                .fillFormSellOrBuyCarWithInvalidData("buyCar")
                .clickPushToApiButtonFormSellBuyCar()
                .checkStatusForSellCarForm("Request failed with status code 404");
    }

    @Test(description = "Успешная продажа / покупка автомобиля")
    @Owner("Verbitskaia Daria")
    @Feature("All Post")
    public void testValidDataSubmissionSellOrBuyCar() {
        mainPage.open()
                .isPageOpened()
                .auth(email, password);
        allPostPage.open()
                .isPageOpened()
                .fillFormCreateUserWithValidData("MALE")
                .clickPushToApiButtonFormCreateUser()
                .saveNewUserId();
        allPostPage.fillFormCreateCarWithValidData()
                .clickPushToApiButtonFormCreateCar()
                .saveNewCarId();
        allPostPage.fillFormSellOrBuyCarWithValidData("buyCar")
                .clickPushToApiButtonFormSellBuyCar()
                .checkStatusForSellCarForm("Successfully pushed");
    }
}