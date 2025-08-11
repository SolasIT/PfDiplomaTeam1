package tests;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.testng.annotations.Test;
import pages.AllPostPage;

public class AllPostTest extends BaseTest {

    AllPostPage allPostPage = new AllPostPage();

    // Create user Form

    @Test(description = "Отправка пустой формы Создание пользователя — ошибка валидации")
    @Owner("Verbitskaia Daria")
    @Feature("All Post")
    public void testEmptyFormCreateUser() {
        mainPage.open();
        mainPage.isPageOpened();
        allPostPage.login("user@pflb.ru", "user");
        allPostPage.openPageAllPost();
        allPostPage.clickPushToApiButtonFormCreateUser();
        allPostPage.checkStatusForCreateUserForm("Invalid request data");
    }

    @Test(description = "Отправка некорректных данных при создании пользователя — ошибка 400")
    public void testInvalidDataSubmissionOnCreateUserForm() {
        mainPage.open();
        mainPage.isPageOpened();
        allPostPage.login("user@pflb.ru", "user");
        allPostPage.openPageAllPost();
        allPostPage.setFirstNameWithJs("123");
        allPostPage.setFirstNameWithJs("123");
        allPostPage.fillFormCreateUserWithInvalidData();
        allPostPage.clickPushToApiButtonFormCreateUser();
        allPostPage.checkStatusForCreateUserForm("Request failed with status code 400");
    }

    @Test(description = "Успешное создание нового пользователя")
    public void testValidUserSubmissionOnCreateUserForm() {
        mainPage.open();
        mainPage.isPageOpened();
        allPostPage.login("user@pflb.ru", "user");
        allPostPage.openPageAllPost();
        allPostPage.fillFormCreateUserWithValidData("MALE");
        allPostPage.clickPushToApiButtonFormCreateUser();
        allPostPage.checkStatusForCreateUserForm("Successfully pushed");
    }

    // Add money Form

    @Test(description = "Отправка пустой формы Начисление денег пользователю  — ошибка валидации")
    @Owner("Verbitskaia Daria")
    @Feature("All Post")
    public void testEmptyFormAddMoney() {
        mainPage.open();
        mainPage.isPageOpened();
        allPostPage.login("user@pflb.ru", "user");
        allPostPage.openPageAllPost();
        allPostPage.clickPushToApiButtonFormAddMoney();
        allPostPage.checkStatusForAddMoneyForm("Incorrect input data");
    }

    @Test(description = "Успешное начисление денег пользователю") // не вставляет полученный id в поле в форме
    public void testValidUserSubmissionOnAddMoneyForm() {
        mainPage.open();
        mainPage.isPageOpened();
        allPostPage.login("user@pflb.ru", "user");
        allPostPage.openPageAllPost();
        allPostPage.fillFormCreateUserWithValidData("MALE");
        allPostPage.clickPushToApiButtonFormCreateUser();
        allPostPage.saveNewUserId();
        allPostPage.fillFormAddMoneyWithValidData();
        allPostPage.clickPushToApiButtonFormAddMoney();
        allPostPage.checkStatusForAddMoneyForm("Successfully pushed");
    }

    @Test(description = "Отправка некорректных данных при начислениии денег пользователю — ошибка 404")
    public void testInvalidDataSubmissionInAddMoneyForm() {
        mainPage.open();
        mainPage.isPageOpened();
        allPostPage.login("user@pflb.ru", "user");
        allPostPage.openPageAllPost();
        allPostPage.setIDWithJs("11000");
        allPostPage.setIDWithJs("11000");
        allPostPage.fillFormAddMoneyWithInvalidData();
        allPostPage.clickPushToApiButtonFormAddMoney();
        allPostPage.checkStatusForAddMoneyForm("Request failed with status code 404");
    }

    // Settle in house / Evict from house Form

    // Create house Form

    // Create Car Form

    @Test(description = "Отправка пустой формы Создание автомобиля — ошибка валидации")
    @Owner("Verbitskaia Daria")
    @Feature("All Post")
    public void testEmptyFormCreateCar() {
        mainPage.open();
        mainPage.isPageOpened();
        allPostPage.login("user@pflb.ru", "user");
        allPostPage.openPageAllPost();
        allPostPage.clickPushToApiButtonFormCreateCar();
        allPostPage.checkStatusForCreateCarForm("Invalid request data");
    }

    @Test(description = "Отправка некорректных данных при создании автомобиля — ошибка 403")
    public void testInvalidDataSubmissionOnCreateCarForm() {
        mainPage.open();
        mainPage.isPageOpened();
        allPostPage.login("user@pflb.ru", "user");
        allPostPage.openPageAllPost();
        allPostPage.fillFormCreateCarWithInvalidData();
        allPostPage.clickPushToApiButtonFormCreateCar();
        allPostPage.checkStatusForCreateCarForm("Request failed with status code 400");
    }

    @Test(description = "Успешное создание нового автомобиля")
    public void testValidUserSubmissionOnCreateCarForm() {
        mainPage.open();
        mainPage.isPageOpened();
        allPostPage.login("user@pflb.ru", "user");
        allPostPage.openPageAllPost();
        allPostPage.fillFormCreateCarWithValidData();
        allPostPage.clickPushToApiButtonFormCreateCar();
        allPostPage.checkStatusForCreateUserForm("Successfully pushed");
    }

    // Buy or sell car Form

    @Test(description = "Отправка пустой формы Продажа/покупка автомобиля  — ошибка валидации")
    @Owner("Verbitskaia Daria")
    @Feature("All Post")
    public void testEmptyFormSellOrBuyCar() {
        mainPage.open();
        mainPage.isPageOpened();
        allPostPage.login("user@pflb.ru", "user");
        allPostPage.openPageAllPost();
        allPostPage.clickPushToApiButtonFormSellBuyCar();
        allPostPage.checkStatusForSellCarForm("Incorrect input data");
    }
    // еще пара тестов на покупку и продажу и тесты про дома
}
