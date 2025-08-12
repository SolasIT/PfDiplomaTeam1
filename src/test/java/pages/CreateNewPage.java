package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.Selenide;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.$;

public class CreateNewPage extends BasePage {

    private final By FLOOR_INPUT = Selectors.byId("floor_send");
    private final By PRICE_INPUT = Selectors.byId("price_send");
    private final By WARM_AND_COVERED_PLACES_INPUT = Selectors.byId("parking_first_send");
    private final By WARM_AND_NOT_COVERED_PLACES_INPUT = Selectors.byId("parking_second_send");
    private final By NOT_WARM_AND_COVERED_PLACES_INPUT = Selectors.byId("parking_third_send");
    private final By NOT_WARM_AND_NOT_COVERED_PLACES_INPUT = Selectors.byId("parking_fourth_send");
    private final By BUTTON_PUSH = Selectors.byXpath("//button[contains(normalize-space(text()), 'PUSH TO API')]");
    private final By STATUS_TEXT = Selectors.withText("Status");
    private final By NEW_HOUSE_ID = Selectors.withText("New house ID");

    public CreateNewPage openPage(){
        Selenide.open(BASE_URL + "/#/create/house");
        return this;
    }

    public CreateNewPage increaseFloorNumber(){
        $(FLOOR_INPUT).click();
        $(FLOOR_INPUT).sendKeys(Keys.ARROW_UP);
        return this;
    }

    public CreateNewPage decreaseFloorNumber(){
        $(FLOOR_INPUT).click();
        $(FLOOR_INPUT).sendKeys(Keys.ARROW_DOWN);
        return this;
    }

    public CreateNewPage sentFloorNumber(String floorNumber){
        $(FLOOR_INPUT).click();
        $(FLOOR_INPUT).setValue(floorNumber);
        return this;
    }

    public CreateNewPage increasePriceNumber(){
        $(PRICE_INPUT).click();
        $(PRICE_INPUT).sendKeys(Keys.ARROW_UP);
        return this;
    }

    public CreateNewPage decreasePriceNumber(){
        $(PRICE_INPUT).click();
        $(PRICE_INPUT).sendKeys(Keys.ARROW_DOWN);
        return this;
    }

    public CreateNewPage sentPriceNumber(String priceNumber){
        $(PRICE_INPUT).click();
        $(PRICE_INPUT).setValue(priceNumber);
        return this;
    }


    public CreateNewPage increaseWarmAndCoveredPlacesNumber(){
        $(WARM_AND_COVERED_PLACES_INPUT).click();
        $(WARM_AND_COVERED_PLACES_INPUT).sendKeys(Keys.ARROW_UP);
        return this;
    }

    public CreateNewPage decreaseWarmAndCoveredPlacesNumber(){
        $(WARM_AND_COVERED_PLACES_INPUT).click();
        $(WARM_AND_COVERED_PLACES_INPUT).sendKeys(Keys.ARROW_DOWN);
        return this;
    }

    public CreateNewPage sentWarmAndCoveredPlacesNumber(String warmAndCoveredPlacesNumber){
        $(WARM_AND_COVERED_PLACES_INPUT).click();
        $(WARM_AND_COVERED_PLACES_INPUT).setValue(warmAndCoveredPlacesNumber);
        return this;
    }

    public CreateNewPage increaseWarmAndNotCoveredPlacesNumber(){
        $(WARM_AND_NOT_COVERED_PLACES_INPUT).click();
        $(WARM_AND_NOT_COVERED_PLACES_INPUT).sendKeys(Keys.ARROW_UP);
        return this;
    }

    public CreateNewPage decreaseWarmAndNotCoveredPlacesNumber(){
        $(WARM_AND_NOT_COVERED_PLACES_INPUT).click();
        $(WARM_AND_NOT_COVERED_PLACES_INPUT).sendKeys(Keys.ARROW_DOWN);
        return this;
    }

    public CreateNewPage sentWarmAndNotCoveredPlacesNumber(String warmAndNotCoveredPlacesNumber){
        $(WARM_AND_NOT_COVERED_PLACES_INPUT).click();
        $(WARM_AND_NOT_COVERED_PLACES_INPUT).setValue(warmAndNotCoveredPlacesNumber);
        return this;
    }

    public CreateNewPage increaseNotWarmAndCoveredPlacesNumber(){
        $(NOT_WARM_AND_COVERED_PLACES_INPUT).click();
        $(NOT_WARM_AND_COVERED_PLACES_INPUT).sendKeys(Keys.ARROW_UP);
        return this;
    }

    public CreateNewPage decreaseNotWarmAndCoveredPlacesNumber(){
        $(NOT_WARM_AND_COVERED_PLACES_INPUT).click();
        $(NOT_WARM_AND_COVERED_PLACES_INPUT).sendKeys(Keys.ARROW_DOWN);
        return this;
    }

    public CreateNewPage sentNotWarmAndCoveredPlacesNumber(String notWarmAndCoveredPlacesNumber){
        $(NOT_WARM_AND_COVERED_PLACES_INPUT).click();
        $(NOT_WARM_AND_COVERED_PLACES_INPUT).setValue(notWarmAndCoveredPlacesNumber);
        return this;
    }

    public CreateNewPage increaseNotWarmAndNotCoveredPlacesNumber(){
        $(NOT_WARM_AND_NOT_COVERED_PLACES_INPUT).click();
        $(NOT_WARM_AND_NOT_COVERED_PLACES_INPUT).sendKeys(Keys.ARROW_UP);
        return this;
    }

    public CreateNewPage decreaseNotWarmAndNotCoveredPlacesNumber(){
        $(NOT_WARM_AND_NOT_COVERED_PLACES_INPUT).click();
        $(NOT_WARM_AND_NOT_COVERED_PLACES_INPUT).sendKeys(Keys.ARROW_DOWN);
        return this;
    }

    public CreateNewPage sentNotWarmAndNotCoveredPlacesNumber(String notWarmAndNotCoveredPlacesNumber){
        $(NOT_WARM_AND_NOT_COVERED_PLACES_INPUT).click();
        $(NOT_WARM_AND_NOT_COVERED_PLACES_INPUT).setValue(notWarmAndNotCoveredPlacesNumber);
        return this;
    }

    public CreateNewPage clickPushButton(){
        $(BUTTON_PUSH).shouldBe(Condition.visible, Condition.enabled).click();
        return this;
    }

    public String getStatus(){
        return $(STATUS_TEXT).getText();
    }

    public CreateNewPage getHouseId(){
        $(NEW_HOUSE_ID).getText();
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
