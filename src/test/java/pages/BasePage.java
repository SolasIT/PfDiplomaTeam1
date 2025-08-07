package pages;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;

import static com.codeborne.selenide.Selenide.open;

public abstract class BasePage {

    protected final String BASE_URL = "http://82.142.167.37:4881/";

    public BasePage() {
    }

    public abstract BasePage open();

    public abstract BasePage isPageOpened();
}