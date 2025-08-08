package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Link;
import io.qameta.allure.Owner;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;


public class BuyOrSellCarTest extends BaseTest {

    @DataProvider(name = "Fields values data")
    public Object[][] fieldsValues() {
        return new Object[][] {
                {"id_send", true, "1"},
                {"id_send", false, "-1"},
                {"car_send", true, "1"},
                {"car_send", false, "-1"}
        };
    }

    @Test(dataProvider = "Fields values data",
            description = "Проверяет увеличение/уменьшение значений в полях ввода User ID и Car Id при нажатии на стрелки",
            testName = "Изменение значений в полях ввода по нажатию на стрелки")
    @Owner("Zheltikov Vasiliy")
    @Link("http://82.142.167.37:4881/#/update/users/buyCar")
    @Feature("Buy or sell car")
    @Description("Проверка изменения значений в полях ввода по нажатию на стрелочки в этих полях")
    public void changeFieldsValue(String field_id, boolean isIncrease, String newValue) {
        mainPage.open()
                .isPageOpened()
                .auth();
        buyOrSellCarPage.open()
                .isPageOpened()
                .ChangeFieldValueByKeys(field_id, isIncrease, newValue);
        assertEquals(buyOrSellCarPage.getFieldValue(field_id),
                newValue,
                "Новое значение поля не соответствует ожидаемому");
    }
}
