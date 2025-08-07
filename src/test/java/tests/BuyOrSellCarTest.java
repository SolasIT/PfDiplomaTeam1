package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Link;
import io.qameta.allure.Owner;
import org.testng.annotations.Test;



public class BuyOrSellCarTest extends BaseTest {

    @Test(description = "Увеличение значения User ID в поле ввода нажатием на стрелочку",
            testName = "Увеличение значения в поле ввода User ID")
    @Owner("Zheltikov Vasiliy")
    @Link("http://82.142.167.37:4881/#/update/users/buyCar")
    @Feature("Buy or sell car")
    @Description("Проверка увеличения значения UserId по нажатию на стрелочку увеличения в поле ввода")
    public void increaseValueOnUserIdField() {
        mainPage.open()
                .isPageOpened()
                .auth()
                .moveToUsersBuyOrSellCar()
                .isPageOpened()
                .increaseValue("id_send");
    }
}
