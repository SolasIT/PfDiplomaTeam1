package pages.houses;

import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.Selenide;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import pages.BasePage;

import java.nio.channels.Selector;

import static com.codeborne.selenide.Selenide.$;

public class SettleOrEvictUserPage extends BasePage {

    private final By USER_ID = Selectors.byId("id_send");
    private final By HOUSE_ID = Selectors.byId("house_send");
    private final By SETTLE_BUTTON = Selectors.byValue("settle");
    private final By EVICT_BUTTON = Selectors.byValue("evict");
    private final By BUTTON_PUSH = Selectors.byClassName("tableButton btn btn-primary");
    private final By STATUS_TEXT = Selectors.withText("Status");

    public SettleOrEvictUserPage openPage(){
        Selenide.open(BASE_URL + "/#/update/houseAndUser");
        return this;
    }

    public SettleOrEvictUserPage clickPushButton(){
        $(BUTTON_PUSH).click();
        return this;
    }

    public SettleOrEvictUserPage increaseUserIdNumber(){
        $(USER_ID).click();
        $(USER_ID).sendKeys(Keys.ARROW_UP);
        return this;
    }

    public SettleOrEvictUserPage decreaseUserIdNumber(){
        $(USER_ID).click();
        $(USER_ID).sendKeys(Keys.ARROW_DOWN);
        return this;
    }

    public SettleOrEvictUserPage sentUserIdNumber(String userIdNumber){
        $(USER_ID).click();
        $(USER_ID).setValue(userIdNumber);
        return this;
    }

    public SettleOrEvictUserPage increaseHouseIdNumber(){
        $(HOUSE_ID).click();
        $(HOUSE_ID).sendKeys(Keys.ARROW_UP);
        return this;
    }

    public SettleOrEvictUserPage decreaseHouseIdNumber(){
        $(HOUSE_ID).click();
        $(HOUSE_ID).sendKeys(Keys.ARROW_DOWN);
        return this;
    }

    public SettleOrEvictUserPage sentHouseIdNumber(String houseIdNumber){
        $(HOUSE_ID).click();
        $(HOUSE_ID).setValue(houseIdNumber);
        return this;
    }

    public SettleOrEvictUserPage setSettleOption(){
        $(SETTLE_BUTTON).click();
        return this;
    }

    public SettleOrEvictUserPage setEvictOption(){
        $(EVICT_BUTTON).click();
        return this;
    }

    public SettleOrEvictUserPage getStatusText(){
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
