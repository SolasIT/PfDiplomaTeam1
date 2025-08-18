package tests.ui;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class ReadUserWithCarsTest extends BaseTest {

    @DataProvider(name = "Значения для поля ввода")
    public Object[][] fieldsValues() {
        return new Object[][]{
                {"user_input", true, "1"},
                {"user_input", false, "-1"}
        };
    }

    @DataProvider(name = "Валидные данные")
    public Object[][] fieldsInputsPositive() {
        return new Object[][]{
                {"user_input", "1000"}
        };
    }

    @DataProvider(name = "Не валидные данные")
    public Object[][] fieldsInputsNegative() {
        return new Object[][]{
                {"user_input", "AAAAAAjkjkkЫВАОЫАвлыаывжа"},
                {"user_input", "ABCDEFGHIJKL"},
                {"user_input", "!@#$%^&*("},
                {"user_input", "ывъщавъппыъвЩЩЩЩъфылаъ"},
                {"user_input", " "}
        };
    }

    @Test(dataProvider = "Валидные данные",
            description = "Ввод цифр в поля ввода", testName = "Проверка поля с валидными данными")
    @Owner("Malkov Artem")
    @Description("Позитивная проверка на ввод цифр в поля ввода")
    public void positiveInputInFields(String fieldId, String text) {
        mainPage.open()
                .isPageOpened()
                .auth(email, password);
        readUserWithCarsPage.open()
                .isPageOpened();
        readUserWithCarsPage.inputTextInField(fieldId, text);
        assertEquals(readUserWithCarsPage.getFieldValue(fieldId),
                text,
                "Отображаемый текст не соответствует введённому");
    }

    @Test(dataProvider = "Не валидные данные",
            description = "Ввод цифр в поля ввода", testName = "Проверка поля с не валидными данными")
    @Owner("Malkov Artem")
    @Description("Негативная проверка на ввод цифр,букв, символов в поля ввода")
    public void negativeInputInFields(String fieldId, String text) {
        mainPage.open()
                .isPageOpened()
                .auth(email, password);
        readUserWithCarsPage.open()
                .isPageOpened();
        readUserWithCarsPage.inputTextInField(fieldId, text);
        assertTrue(readUserWithCarsPage.getFieldValue(fieldId).isEmpty(),
                String.format("В поле ввода отображается значение \"%s\"", text));
    }

    @Test(dataProvider = "Значения для поля ввода",
            description = "Проверка увеличение/уменьшение значений в поле ввода READ  при нажатии на стрелки",
            testName = "Проверка увеличение/уменьшение значений в поле ввода READ  при нажатии на стрелки"            )
    @Owner("Malkov Artem")
    @Description("Проверка изменения значения в поле ввода по нажатию на стрелочки")
    public void changeFieldsValue(String fieldId, boolean isIncrease, String newValue) {
        mainPage.open()
                .isPageOpened()
                .auth(email, password);
        readUserWithCarsPage.open()
                .isPageOpened();
        readUserWithCarsPage.changeValueByRead(fieldId, isIncrease, newValue);
        assertEquals(readUserWithCarsPage.getFieldValue(fieldId),
                newValue,
                "Новое значение поля не соответствует ожидаемому");
    }
}
