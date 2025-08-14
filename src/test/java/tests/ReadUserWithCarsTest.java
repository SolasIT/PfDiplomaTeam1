package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Link;
import io.qameta.allure.Owner;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class ReadUserWithCarsTest extends BaseTest {

    @DataProvider(name = "Значения для поля ввода")
    public Object[][] fieldsValues() {
        return new Object[][]{
                {"user_input", true, "1"},
                {"user_input", false, "-1"},

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
                {"user_input", "888888888888888888888888888888888888888"},
                {"user_input", "ABCDEFGHIJKL"},
                {"user_input", "!@#$%^&*("},
                {"user_input", "ывъщавъппыъв ЩЩЩЩъфылаъ"},
                {"user_input", " "}
        };
    }

    @Test(dataProvider = "Валидные данные",
            description = "Ввод цифр в поля ввода")
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
}
