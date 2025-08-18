package tests.ui;

import dto.ui.User;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Link;
import io.qameta.allure.Owner;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static dto.ui.UserFactory.getUser;
import static org.testng.AssertJUnit.assertEquals;

public class AddMoneyTest extends BaseTest{

    @Test(description = "Проверка добавления денег",
            testName = "Проверка добавления денег")
    @Owner("Laptev Denis")
    @Link("http://82.142.167.37:4881/#/update/users/plusMoney")
    @Feature("Add Money")
    @Description("Проверка добавления денег")
    public void validAddMoney() {
        User user = getUser("us");
        double oldMoney = user.getMoney();
        mainPage.open()
                .isPageOpened()
                .auth(email, password)
                .clickUserButton("Create new");
        createUserPage
                .isPageOpened()
                .createUser(user);
        mainPage.clickUserButton("Add money");
        addMoneyPage.isPageOpened()
                .addMoney(user, 200);
        assertEquals(oldMoney + 200, user.getMoney());
    }

    @DataProvider(name = "Money")
    public Object[][] users() {
        return new Object[][]{
                {0, 200, "Status: Incorrect input data"},
                {7000, 0, "Status: Incorrect input data"},
                {999999, 2000, "Status: AxiosError: Request failed with status code 404"},
                {0, 0, "Status: Incorrect input data"},
                {7000, -1000, "Status: Incorrect input data"},
        };
    }

    @Test(dataProvider = "Money",
            description = "Проверка ошибок при неккоректном заполнении полей добавление денег",
            testName = "Проверка ошибок при неккоректном заполнении полей добавление денег")
    @Owner("Laptev Denis")
    @Link("http://82.142.167.37:4881/#/update/users/plusMoney")
    @Feature("Add Money")
    @Description("Проверка ошибок при неккоректном заполнении полей добавление денег")
    public void notValidAddMoney(int id, double money, String status) {
        User user = new User();
        user.setId(id);
        mainPage.open()
                .isPageOpened()
                .auth(email, password)
                .clickUserButton("Add money");
        addMoneyPage.isPageOpened()
                .addMoney(user, money);
        assertEquals(status, user.getStatus());
    }
}