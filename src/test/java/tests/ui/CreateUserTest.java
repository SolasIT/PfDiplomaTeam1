package tests.ui;

import dto.ui.User;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Link;
import io.qameta.allure.Owner;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import tests.ui.BaseTest;

import static dto.ui.UserFactory.getUser;
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
                {"qwerty", "qwerty", 20, "MALE", -100, "Status: Invalid request data"},
                {"123", "qwerty", 20, "MALE", 100, "Status: AxiosError: Request failed with status code 400"},
                {"qwerty", "123", 20, "MALE", 100, "Status: AxiosError: Request failed with status code 400"},
                {"", "Иванов", 20, "MALE", 1, "Status: Invalid request data"},
                {"Иван", "", 20, "MALE", 1, "Status: Invalid request data"},
                {"Иван", "Иванов", 0, "MALE", 1, "Status: Invalid request data"},
                {"Иван", "Иванов", 20, "", 1, "Status: Invalid request data"},
                {"Иван", "Иванов", 20, "MALE", 0, "Status: Invalid request data"},
                {"Иван", "Иванов", 20, "MALE", -100, "Status: Invalid request data"},
                {"122", "Иванов", 20, "MALE", 100, "Status: AxiosError: Request failed with status code 400"},
                {"Иван", "123", 20, "MALE", 100, "Status: AxiosError: Request failed with status code 400"},
        };
    }

    @Test(dataProvider = "Users",
            description = "Проверка ошибок при неккоректном заполнение карточки клиента",
            testName = "Проверка ошибок при создании пользователя")
    @Owner("Laptev Denis")
    @Link("http://82.142.167.37:4881/#/create/user")
    @Feature("Create New User")
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

    @DataProvider(name = "lang")
    public Object[][] lang() {
        return new Object[][]{
                {"ru"},
                {"us"}
        };
    }

    @Test(dataProvider = "lang",
            description = "Проверка создания клиента",
            testName = "Проверка создания клиента")
    @Owner("Laptev Denis")
    @Link("http://82.142.167.37:4881/#/create/user")
    @Feature("Create New User")
    @Description("Проверка создания клиента")
    public void createUser(String lang) {
        User user = getUser(lang);
        mainPage.open()
                .isPageOpened()
                .auth(email, password)
                .clickUserButton("Create new");
        createUserPage
                .isPageOpened()
                .createUser(user);
        assertEquals("Status: Successfully pushed, code: 201", user.getStatus());
    }

    @DataProvider(name = "arrowClickAge")
    public Object[][] Age() {
        return new Object[][]{
                {"Age", 10},
                {"Age", -10},
                {"Age", 8},
                {"Age", -4}
        };
    }

    @Test(dataProvider = "arrowClickAge",
            description = "Проверка изменения значения возраста нажатием стрелочек",
            testName = "Проверка изменения значения возраста нажатием стрелочек")
    @Owner("Laptev Denis")
    @Link("http://82.142.167.37:4881/#/create/user")
    @Feature("Create New User")
    @Description("Проверка изменения значения возраста нажатием стрелочек")
    public void arrowClickAge(String fild, double value) {
        User user = getUser("us");
        mainPage.open()
                .isPageOpened()
                .auth(email, password)
                .clickUserButton("Create new");
        createUserPage
                .isPageOpened()
                .createUser(user)
                .arrowClick(fild, value);
        assertEquals((user.getAge() + value), createUserPage.getAge());
    }

    @DataProvider(name = "arrowClickMoney")
    public Object[][] Money() {
        return new Object[][]{
                {"Money", 100},
                {"Money", -100},
                {"Money", -5},
                {"Money", 12}
        };
    }

    @Test(dataProvider = "arrowClickMoney",
            description = "Проверка изменения значения денег нажатием стрелочек",
            testName = "Проверка изменения значения денег нажатием стрелочек")
    @Owner("Laptev Denis")
    @Link("http://82.142.167.37:4881/#/create/user")
    @Feature("Create New User")
    @Description("Проверка изменения значения денег нажатием стрелочек")
    public void arrowClickMoney(String fild, double value) {
        User user = getUser("us");
        mainPage.open()
                .isPageOpened()
                .auth(email, password)
                .clickUserButton("Create new");
        createUserPage
                .isPageOpened()
                .createUser(user)
                .arrowClick(fild, value);
        value = value * 0.01;
        double sum = user.getMoney() + value;
        double res = Math.round(sum*100.0)/100.0;
        assertEquals(res, createUserPage.getMoney());
    }
}