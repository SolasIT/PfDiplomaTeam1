package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.Selenide;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import lombok.extern.log4j.Log4j2;

import static com.codeborne.selenide.Selenide.$;

@Log4j2
public class ReadOneByIdPage extends BasePage {

    private final By BUTTON_READ = Selectors.byText("Read");
    private final By ID_INPUT = Selectors.byId("house_input");
    private final By STATUS_TEXT = Selectors.withText("Status");
    //public String getStatus;

    public ReadOneByIdPage openPage(){
        Selenide.open(BASE_URL + "/#/read/house");
        return this;
    }

    public ReadOneByIdPage clickReadButton(){
        $(BUTTON_READ).shouldBe(Condition.visible, Condition.enabled).click();
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

    public String getStatus(){
        return $(STATUS_TEXT).getText();
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
