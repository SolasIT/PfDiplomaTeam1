package wrappers;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.$;

public class Input {

    String id; // id поля ввода

    public Input(String id) {
        this.id = id;
    }

    public void write(String text) { // заполнение текстового поля
        $(By.id(String.format("%s", id))).val(text);
    }

    public void increaseValue() { // заполнение текстового поля
        $(By.id(String.format("%s", id))).sendKeys(Keys.ARROW_UP);
    }

    public void decreaseValue() { // заполнение текстового поля
        $(By.id(String.format("%s", id))).sendKeys(Keys.ARROW_DOWN);
    }
}
