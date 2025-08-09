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


public class SettleToHouseTest extends BaseTest {

    @DataProvider(name = "Settle user to or evict from house with empty data")
    public Object[][] fieldsInputsNegativeWithEmptyValues() {
        return new Object[][]{
                {"7176", "", "SETTLE"}, // нет значения House Id, заселение
                {"", "2421", "SETTLE"}, // нет значения User Id, заселение
                {"7176", "", "EVICT"}, // нет значения House Id, выселение
                {"", "2421", "EVICT"}, // нет значения User ID, выселение
                {"7176", "2421", ""}, // не выбран чекбокс
                {"", "", ""} // отправка пустой формы
        };
    }

    @DataProvider(name = "Settle user to or evict from house with non-existent data")
    public Object[][] fieldsInputsNonExistentValues() {
        return new Object[][]{
                {"7176", "999999999", "EVICT"}, // выселение существующего пользователя из несуществующего дома
                {"7176", "999999999", "SETTLE"}, // заселение существующего пользователя в несуществующий дом
                {"999999999", "2421", "EVICT"}, // выселение несуществующего пользователя из существующего дома
                {"999999999", "2421", "SETTLE"} // заселение несуществующего пользователя в существующий дом
        };
    }

    @Test(dataProvider = "Settle user to or evict from house with empty data",
            description = "Попытка отправки формы с незаполненным(и) обязательным(и) значением(ями)",
            testName = "Проверка обязательности заполнения всех полей при покупке/продаже авто")
    @Owner("Zheltikov Vasiliy")
    @Link("http://82.142.167.37:4881/#/update/houseAndUser")
    @Feature("Settle to house")
    @Description("Попытка заселения / выселения пользователя при неполном заполнении формы")
    public void buyOrSellCarWithEmptyFieldsValues(String user_id, String house_id, String checkboxLabel) {
        mainPage.open()
                .auth();
        settleToHousePage.open()
                .isPageOpened()
                .settleToOrEvictFromHouse(user_id, house_id, checkboxLabel);
        assertTrue($(withText("Status: Incorrect input data")).isDisplayed(),
                "Статус выполненного процесса не соответствует ожидаемому");
    }

    @Test(dataProvider = "Settle user to or evict from house with non-existent data",
            description = "Попытка отправки формы с несуществующими в БД значениями",
            testName = "Проверка отправки формы с отсутствующими в БД значениями")
    @Owner("Zheltikov Vasiliy")
    @Link("http://82.142.167.37:4881/#/update/houseAndUser")
    @Feature("Settle to house")
    @Description("Попытка заселения / выселения пользователя с отсутствующими в БД значениями")
    public void buyOrSellCarWithNonExistentValues(String user_id, String house_id, String checkboxLabel) {
        mainPage.open()
                .auth();
        settleToHousePage.open()
                .isPageOpened()
                .settleToOrEvictFromHouse(user_id, house_id, checkboxLabel);
        assertTrue($(withText("Status: AxiosError: Request failed with status code 404")).isDisplayed(),
                "Статус выполненного процесса не соответствует ожидаемому");
    }
}