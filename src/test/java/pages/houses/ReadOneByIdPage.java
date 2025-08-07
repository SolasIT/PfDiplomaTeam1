package pages.houses;

import com.codeborne.selenide.Selectors;
import org.openqa.selenium.By;
import pages.BasePage;

import static com.codeborne.selenide.Selenide.$;

public class ReadOneByIdPage extends BasePage {

    private final By BUTTON_READ = Selectors.byText("Read");
    private final By ID_INPUT = Selectors.byId("house_input");

    public ReadOneByIdPage clickRead(){
        $(BUTTON_READ).click();
        return this;
    }
}
