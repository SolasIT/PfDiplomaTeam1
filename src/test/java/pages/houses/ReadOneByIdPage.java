package pages.houses;

import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.Selenide;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import pages.BasePage;

import static com.codeborne.selenide.Selenide.$;

public class ReadOneByIdPage extends BasePage {

    private final By BUTTON_READ = Selectors.byText("Read");
    private final By ID_INPUT = Selectors.byId("house_input");
    private final By STATUS_TEXT = Selectors.withText("Status");

    public ReadOneByIdPage openPage(){
        Selenide.open(BASE_URL + "/#/read/house");
        return this;
    }

    public ReadOneByIdPage clickReadButton(){
        $(BUTTON_READ).click();
        return this;
    }

    public ReadOneByIdPage increaseInputNumber(){
        $(ID_INPUT).click();
        $(ID_INPUT).sendKeys(Keys.ARROW_UP);
        return this;
    }

    public ReadOneByIdPage decreaseInputNumber(){
        $(ID_INPUT).click();
        $(ID_INPUT).sendKeys(Keys.ARROW_DOWN);
        return this;
    }

    public ReadOneByIdPage sentInputNumber(String idNumber){
        $(ID_INPUT).click();
        $(ID_INPUT).setValue(idNumber);
        return this;
    }

    public ReadOneByIdPage getStatus(String statusText){
        $(STATUS_TEXT).getText();
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
