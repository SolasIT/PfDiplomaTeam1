package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Link;
import io.qameta.allure.Owner;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;


public class BuyOrSellCarTest extends BaseTest {

    @DataProvider(name = "Changing values in fields data")
    public Object[][] fieldsValues() {
        return new Object[][] {
                {"id_send", true, "1"},
                {"id_send", false, "-1"},
                {"car_send", true, "1"},
                {"car_send", false, "-1"}
        };
    }

    @DataProvider(name = "Positive inputting in fields data")
    public Object[][] fieldsInputsPositive() {
        return new Object[][] {
                {"id_send","1234567890"},
                {"car_send", "1234567890"}
        };
    }

    @DataProvider(name = "Negative inputting in fields data")
    public Object[][] fieldsInputsNegative() {
        return new Object[][] {
                {"id_send","АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯабвгдеёжзийклмнопрстуфхцчшщъыьэюя"},
                {"id_send","ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz"},
                {"id_send","!@#$%^&*()-=_+[]{}\\|;':\",./<>?"},
                {"id_send"," "},
                {"car_send","АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯабвгдеёжзийклмнопрстуфхцчшщъыьэюя"},
                {"car_send","ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz"},
                {"car_send","!@#$%^&*()-=_+[]{}\\|;':\",./<>?"},
                {"car_send"," "}
        };
    }

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

    @Test(dataProvider = "Changing values in fields data",
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
                .changeFieldValueByKeys(field_id, isIncrease, newValue);
        assertEquals(buyOrSellCarPage.getFieldValue(field_id),
                newValue,
                "Новое значение поля не соответствует ожидаемому");
    }

    @Test(dataProvider = "Positive inputting in fields data",
            description = "Ввод цифр в поля ввода",
            testName = "Проверка ввода цифр в поля ввода")
    @Owner("Zheltikov Vasiliy")
    @Link("http://82.142.167.37:4881/#/update/users/buyCar")
    @Feature("Buy or sell car")
    @Description("Позитивная проверка на ввод цифр в поля ввода")
    public void positiveInputInFields(String field_id, String text) {
        mainPage.open()
                .isPageOpened()
                .auth();
        buyOrSellCarPage.open()
                .isPageOpened()
                .inputTextInField(field_id, text);
        assertEquals(buyOrSellCarPage.getFieldValue(field_id),
                text,
                "Отображаемый текст не соответствует введённому");
    }

    @Test(dataProvider = "Negative inputting in fields data",
            description = "Ввод русских, английских букв, спецсимволов и пробела в поля ввода",
            testName = "Проверка ввода букв и символов в поля ввода")
    @Owner("Zheltikov Vasiliy")
    @Link("http://82.142.167.37:4881/#/update/users/buyCar")
    @Feature("Buy or sell car")
    @Description("Негативные проверки на ввод цифр в поля ввода")
    public void negativeInputInFields(String field_id, String text) {
        mainPage.open()
                .isPageOpened()
                .auth();
        buyOrSellCarPage.open()
                .isPageOpened()
                .inputTextInField(field_id, text);
        assertTrue(buyOrSellCarPage.getFieldValue(field_id).isEmpty(),
                String.format("В поле ввода отображается значение \"%s\"", text));
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
