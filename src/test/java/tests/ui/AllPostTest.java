package tests.ui;

import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static org.testng.Assert.assertTrue;
import static org.testng.AssertJUnit.assertEquals;

public class AllPostTest extends BaseTest {

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
                .clickPushToApiButtonFormCreateUser();
        assertEquals("Status: Invalid request data", allPostPage.checkStatusForCreateUserForm());
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
                .clickPushToApiButtonFormCreateUser();
        assertEquals("Status: AxiosError: Request failed with status code 400", allPostPage.checkStatusForCreateUserForm());
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
                .clickPushToApiButtonFormCreateUser();
        assertEquals("Status: Successfully pushed, code: 201", allPostPage.checkStatusForCreateUserForm());
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
                .clickPushToApiButtonFormAddMoney();
        assertEquals("Status: Incorrect input data", allPostPage.checkStatusForAddMoneyForm());
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
                .clickPushToApiButtonFormAddMoney();
        assertEquals("Status: Successfully pushed, code: 200", allPostPage.checkStatusForAddMoneyForm());
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
                .setIDWithJs("110000")
                .fillFormAddMoneyWithInvalidData()
                .clickPushToApiButtonFormAddMoney();
        assertEquals("Status: AxiosError: Request failed with status code 404", allPostPage.checkStatusForAddMoneyForm());
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
                .clickPushToApiButtonFormCreateCar();
        assertEquals("Status: Invalid request data", allPostPage.checkStatusForCreateCarForm());
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
                .clickPushToApiButtonFormCreateCar();
        assertEquals("Status: AxiosError: Request failed with status code 400", allPostPage.checkStatusForCreateCarForm());
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
                .clickPushToApiButtonFormCreateCar();
        assertEquals("Status: Successfully pushed, code: 201", allPostPage.checkStatusForCreateCarForm());
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
                .clickPushToApiButtonFormSellBuyCar();
        assertEquals("Status: Incorrect input data", allPostPage.checkStatusForSellCarForm());
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
                .clickPushToApiButtonFormSellBuyCar();
        assertEquals("Status: AxiosError: Request failed with status code 404", allPostPage.checkStatusForSellCarForm());
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
                .clickPushToApiButtonFormSellBuyCar();
        assertEquals("Status: Successfully pushed, code: 200", allPostPage.checkStatusForSellCarForm());
    }
}