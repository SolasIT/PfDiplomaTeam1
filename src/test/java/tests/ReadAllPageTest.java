package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Link;
import io.qameta.allure.Owner;
import org.testng.annotations.Test;

public class ReadAllPageTest extends BaseTest {

    @Test(description = "Проверка кнопки Reload",
            testName = "Проверка кнопки Reload")
    @Owner("Golubnichiy Vitaliy")
    @Link("http://82.142.167.37:4881/#/read/houses")
    @Feature("Read All")
    @Description("Проверка кнопки Reload")
    public void clickReloadButton(){
        mainPage.open()
                .isPageOpened()
                .auth(email, password);
        readAllPage.openPage()
                .clickReload();
    }
}
