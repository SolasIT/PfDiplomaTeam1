package tests;

import dto.User;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Link;
import io.qameta.allure.Owner;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static dto.UserFactory.getUser;
import static org.testng.AssertJUnit.assertEquals;

public class CreateUserTest extends BaseTest {

    @DataProvider(name = "Users")
    public Object[][] users() {
        return new Object[][]{
                {"", "qwerty", 20, "MALE", 1, "Status: Invalid request data"},
                {"qwerty", "", 20, "MALE", 1, "Status: Invalid request data"},
                {"qwerty", "qwerty", 0, "MALE", 1, "Status: Invalid request data"},
                {"qwerty", "qwerty", 20, "", 1, "Status: Invalid request data"},
                {"qwerty", "qwerty", 20, "MALE", 0, "Status: Invalid request data"},
        };
    }

    @Test(dataProvider = "Users",
            description = "Проверка ошибок при неккоректном заполнение карточки клиента",
            testName = "Проверка ошибок при создании пользователя")
    @Owner("Laptev Denis")
    @Link("http://82.142.167.37:4881/#/create/user")
    @Feature("Create New")
    @Description("Проверка ошибок при неккоректном заполнении карточки клиента")
    public void createUserWithNegativeValue(String firstname, String lastname, int age, String sex, double money, String status) {
        User user = new User();
        user.setFirstname(firstname);
        user.setLastname(lastname);
        user.setAge(age);
        user.setSex(sex);
        user.setMoney(money);
        mainPage.open()
                .isPageOpened()
                .auth(email, password)
                .clickUserButton("Create new");
        createUserPage
                .isPageOpened()
                .createUser(user);
        assertEquals(status, user.getStatus());
    }

    @Test(description = "Проверка создания клиента",
            testName = "Проверка создания клиента")
    @Owner("Laptev Denis")
    @Link("http://82.142.167.37:4881/#/create/user")
    @Feature("Create New")
    @Description("Проверка создания клиента")
    public void createUser() {
        User user = getUser();
        mainPage.open()
                .isPageOpened()
                .auth(email, password)
                .clickUserButton("Create new");
        createUserPage
                .isPageOpened()
                .createUser(user);
        assertEquals("Status: Successfully pushed, code: 201", user.getStatus());
    }
}