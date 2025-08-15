package pages;

import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.Selenide;
import org.openqa.selenium.By;
import lombok.extern.log4j.Log4j2;

import static com.codeborne.selenide.Selenide.$;

@Log4j2
public class ReadAllPage extends BasePage {

    private final By BUTTON_RELOAD = Selectors.byText("Reload");

    public ReadAllPage openPage(){
        Selenide.open(BASE_URL + "/#/read/houses");
        return this;
    }

    public ReadAllPage clickReload(){
        $(BUTTON_RELOAD).click();
        return this;
    }

    @Override
    public BasePage open() {
        return null;
    }

    @Override
    public BasePage isPageOpened() {
        return null;
    }
}
