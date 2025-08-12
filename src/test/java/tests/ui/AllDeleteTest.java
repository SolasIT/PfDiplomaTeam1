package tests.ui;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Link;
import io.qameta.allure.Owner;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

public class AllDeleteTest extends BaseTest{

    @DataProvider(name = "Delete")
    public Object[][] users() {
        return new Object[][]{
                {3000, "user","Status: not pushed"},
                {999999999, "user","Status: not pushed"},
                {0,"user","Status: Bad request"},
                {-232,"user","Status: Bad request"},
                {11, "house","Status: not pushed"},
                {999999999, "house","Status: not pushed"},
                {0,"house","Status: Bad request"},
                {-232,"house","Status: Bad request"},
                {50, "car","Status: not pushed"},
                {999999999, "car","Status: not pushed"},
                {0,"car","Status: Bad request"},
                {-232,"car","Status: Bad request"},
        };
    }

    @Test(dataProvider = "Delete",
            description = "Проверка ошибок при удалении",
            testName = "Проверка ошибок при удалении")
    @Owner("Laptev Denis")
    @Link("http://82.142.167.37:4881/#/delete/all")
    @Feature("All Delete")
    @Description("Проверка ошибок  при удалении")
    public void notValidAddMoney(int id,String button, String status) {
        mainPage.open()
                .isPageOpened()
                .auth(email, password)
                .clickAllDelete()
                .isPageOpened()
                .inputID(button,id)
                .clickDelete(button);
        assertEquals(status, allDeletePage.getStatus(button));
    }
}