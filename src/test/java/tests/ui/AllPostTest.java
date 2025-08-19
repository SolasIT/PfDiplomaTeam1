package tests.ui;

import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static org.testng.Assert.assertTrue;

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
                .clickPushToApiButtonFormCreateUser()
                .checkStatusForCreateUserForm();
        assertTrue(
                $(withText("Status: Invalid request data")).isDisplayed(),
                "Ожидался статус 'Status: Invalid request data'"
        );
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
                .checkStatusForCreateUserForm();
        assertTrue(
                $(withText("Status: AxiosError: Request failed with status code 400")).isDisplayed(),
                "Ожидался статус 'Status: AxiosError: Request failed с status code 400'"
        );
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
                .checkStatusForCreateUserForm();
        assertTrue(
                $(withText("Status: Successfully pushed")).isDisplayed(),
                "Ожидался статус 'Status: Successfully pushed'"
        );
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
                .checkStatusForAddMoneyForm();
        assertTrue(
                $(withText("Status: Incorrect input data")).isDisplayed(),
                "Ожидался статус 'Status: Incorrect input data'"
        );
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
                .checkStatusForAddMoneyForm();
        assertTrue(
                $(withText("Status: Successfully pushed")).isDisplayed(),
                "Ожидался статус 'Status: Successfully pushed'"
        );
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
                .checkStatusForAddMoneyForm();
        assertTrue(
                $(withText("Status: AxiosError: Request failed with status code 404")).isDisplayed(),
                "Ожидался статус 'Status: AxiosError: Request failed с status code 404'"
        );
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
                .checkStatusForCreateCarForm();
        assertTrue(
                $(withText("Status: Invalid request data")).isDisplayed(),
                "Ожидался статус 'Invalid request data'"
        );
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
                .checkStatusForCreateCarForm();
        assertTrue(
                $(withText("Status: AxiosError: Request failed with status code 400")).isDisplayed(),
                "Ожидался статус 'Status: AxiosError: Request failed with status code 400'"
        );
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
                .checkStatusForCreateCarForm();
        assertTrue(
                $(withText("Status: Successfully pushed")).isDisplayed(),
                "Ожидался статус 'Successfully pushed'"
        );
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
                .checkStatusForSellCarForm();
        assertTrue(
                $(withText("Status: Incorrect input data")).isDisplayed(),
                "Ожидался статус 'Status: Incorrect input data'"
        );
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
                .checkStatusForSellCarForm();
        assertTrue(
                $(withText("Status: AxiosError: Request failed with status code 404")).isDisplayed(),
                "Ожидался статус 'Status: AxiosError: Request failed с status code 404'"
        );
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
                .checkStatusForSellCarForm();
        assertTrue(
                $(withText("Status: Successfully pushed")).isDisplayed(),
                "Ожидался статус 'Successfully pushed'"
        );
    }
}