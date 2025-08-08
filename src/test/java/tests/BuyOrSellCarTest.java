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

    @DataProvider(name = "Buy or sell car with empty data")
    public Object[][] fieldsInputsNegativeWithEmptyValues() {
        return new Object[][]{
                {"7176", "", "BUY"}, // нет значения Car Id, покупка
                {"", "3314", "BUY"}, // нет значения User Id, покупка
                {"7176", "", "SELL"}, // нет значения Car Id, продажа
                {"", "3314", "SELL"}, // нет значения User ID, продажа
                {"7176", "3314", ""}, // не выбран чекбокс
                {"", "", ""} // отправка пустой формы
        };
    }

    @DataProvider(name = "Buy or sell car with non-existent data")
    public Object[][] fieldsInputsNonExistentValues() {
        return new Object[][]{
                {"7176", "999999999", "SELL"}, // продажа несуществующего авто у существующего пользователя
                {"7176", "999999999", "BUY"}, // покупка несуществующего авто существующим пользователем
                {"999999999", "3314", "SELL"}, // продажа существующего авто несуществующим пользователем
                {"999999999", "3314", "BUY"} // покупка существующего авто несуществующим пользователем
        };
    }

    @Test(dataProvider = "Buy or sell car with empty data",
            description = "Попытка отправки формы с незаполненным(и) обязательным(и) значением(ями)",
            testName = "Проверка обязательности заполнения всех полей при покупке/продаже авто")
    @Owner("Zheltikov Vasiliy")
    @Link("http://82.142.167.37:4881/#/update/users/buyCar")
    @Feature("Buy or sell car")
    @Description("Попытка совершения продажи/покупки авто при неполном заполнении формы")
    public void buyOrSellCarWithEmptyFieldsValues(String user_id, String car_id, String checkboxLabel) {
        mainPage.open()
                .auth();
        buyOrSellCarPage.open()
                .isPageOpened()
                .buyOrSellCar(user_id, car_id, checkboxLabel);
        assertTrue($(withText("Status: Incorrect input data")).isDisplayed(),
                "Статус выполненного процесса не соответствует ожидаемому");
    }

    @Test(dataProvider = "Buy or sell car with non-existent data",
            description = "Попытка отправки формы с несуществующими в БД значениями",
            testName = "Проверка отправки формы с отсутствующими в БД значениями")
    @Owner("Zheltikov Vasiliy")
    @Link("http://82.142.167.37:4881/#/update/users/buyCar")
    @Feature("Buy or sell car")
    @Description("Попытка совершения продажи/покупки авто с отсутствующими в БД значениями")
    public void buyOrSellCarWithNonExistentValues(String user_id, String car_id, String checkboxLabel) {
        mainPage.open()
                .auth();
        buyOrSellCarPage.open()
                .isPageOpened()
                .buyOrSellCar(user_id, car_id, checkboxLabel);
        assertTrue($(withText("Status: AxiosError: Request failed with status code 404")).isDisplayed(),
                "Статус выполненного процесса не соответствует ожидаемому");
    }
}