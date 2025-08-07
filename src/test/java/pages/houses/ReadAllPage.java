package pages.houses;

import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.selector.ByText;
import org.openqa.selenium.By;
import pages.BasePage;

import static com.codeborne.selenide.Selenide.$;

public class ReadAllPage extends BasePage {

    private final By BUTTON_RELOAD = Selectors.byText("Reload");

    public ReadAllPage clickReload(){
        $(BUTTON_RELOAD).click();
        return this;
    }
}
