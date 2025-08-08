package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Link;
import io.qameta.allure.Owner;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static org.testng.Assert.assertTrue;


public class BuyOrSellCarTest extends BaseTest {

    @DataProvider(name = "Buy or sell car negative data with empty values")
    public Object[][] fieldsInputsNegativeWithEmptyValues() {
        return new Object[][] {
                {"7176","","BUY"}, // нет значения Car Id, покупка
                {"","3314","BUY"}, // нет значения User Id, покупка
                {"7176","","SELL"}, // нет значения Car Id, продажа
                {"","3314","SELL"}, // нет значения User ID, продажа
                {"7176","3314",""}, // не выбран чекбокс
                {"","",""} // отправка пустой формы
        };
    }

    @Test(dataProvider = "Buy or sell car negative data with empty values",
            description = "Попытка отправки формы с незаполненным(и) обязательным(и) значением(ями)",
            testName = "Проверка обязательности заполнения всех полей при покупке/продаже авто")
    @Owner("Zheltikov Vasiliy")
    @Link("http://82.142.167.37:4881/#/update/users/buyCar")
    @Feature("Buy or sell car")
    @Description("Попытка совершения продажи/покупки авто при неполном заполнении формы")
    public void buyCarWithEmptyFieldsValues(String user_id, String car_id, String checkboxLabel) {
        mainPage.open()
                .auth();
        buyOrSellCarPage.open()
                .isPageOpened()
                .buyOrSellCar(user_id, car_id, checkboxLabel);
        assertTrue($(withText("Status: Incorrect input data")).isDisplayed(),
                "Статус выполненного процесса не соответствует ожидаемому");

    }
}
