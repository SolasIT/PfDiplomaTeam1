package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Link;
import io.qameta.allure.Owner;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class SettleToHouseInputsTest extends BaseTest {

    @DataProvider(name = "Changing values in fields data")
    public Object[][] fieldsValues() {
        return new Object[][]{
                {"id_send", true, "1"},
                {"id_send", false, "-1"},
                {"house_send", true, "1"},
                {"house_send", false, "-1"}
        };
    }

    @DataProvider(name = "Positive inputting in fields data")
    public Object[][] fieldsInputsPositive() {
        return new Object[][]{
                {"id_send", "1234567890"},
                {"house_send", "1234567890"}
        };
    }

    @DataProvider(name = "Negative inputting in fields data")
    public Object[][] fieldsInputsNegative() {
        return new Object[][]{
                {"id_send", "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯабвгдеёжзийклмнопрстуфхцчшщъыьэюя"},
                {"id_send", "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz"},
                {"id_send", "!@#$%^&*()-=_+[]{}\\|;':\",./<>?"},
                {"id_send", " "},
                {"house_send", "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯабвгдеёжзийклмнопрстуфхцчшщъыьэюя"},
                {"house_send", "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz"},
                {"house_send", "!@#$%^&*()-=_+[]{}\\|;':\",./<>?"},
                {"house_send", " "}
        };
    }

    @Test(dataProvider = "Changing values in fields data",
            description = "Проверяет увеличение/уменьшение значений в полях ввода User ID и House ID при нажатии на стрелки",
            testName = "Изменение значений в полях ввода по нажатию на стрелки")
    @Owner("Zheltikov Vasiliy")
    @Link("http://82.142.167.37:4881/#/update/houseAndUser")
    @Feature("Settle to house")
    @Description("Проверка изменения значений в полях ввода по нажатию на стрелочки в этих полях")
    public void changeFieldsValue(String field_id, boolean isIncrease, String newValue) {
        mainPage.open()
                .isPageOpened()
                .auth();
        settleToHousePage.open()
                .isPageOpened()
                .changeFieldValueByKeys(field_id, isIncrease, newValue);
        assertEquals(settleToHousePage.getFieldValue(field_id),
                newValue,
                "Новое значение поля не соответствует ожидаемому");
    }

    @Test(dataProvider = "Positive inputting in fields data",
            description = "Ввод цифр в поля ввода",
            testName = "Проверка ввода цифр в поля ввода")
    @Owner("Zheltikov Vasiliy")
    @Link("http://82.142.167.37:4881/#/update/houseAndUser")
    @Feature("Settle to house")
    @Description("Позитивная проверка на ввод цифр в поля ввода")
    public void positiveInputInFields(String field_id, String text) {
        mainPage.open()
                .isPageOpened()
                .auth();
        settleToHousePage.open()
                .isPageOpened()
                .inputTextInField(field_id, text);
        assertEquals(settleToHousePage.getFieldValue(field_id),
                text,
                "Отображаемый текст не соответствует введённому");
    }

    @Test(dataProvider = "Negative inputting in fields data",
            description = "Ввод русских, английских букв, спецсимволов и пробела в поля ввода",
            testName = "Проверка ввода букв и символов в поля ввода")
    @Owner("Zheltikov Vasiliy")
    @Link("http://82.142.167.37:4881/#/update/houseAndUser")
    @Feature("Settle to house")
    @Description("Негативные проверки на ввод цифр в поля ввода")
    public void negativeInputInFields(String field_id, String text) {
        mainPage.open()
                .isPageOpened()
                .auth();
        settleToHousePage.open()
                .isPageOpened()
                .inputTextInField(field_id, text);
        assertTrue(settleToHousePage.getFieldValue(field_id).isEmpty(),
                String.format("В поле ввода отображается значение \"%s\"", text));
    }
}