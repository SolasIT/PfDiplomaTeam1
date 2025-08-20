package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import lombok.extern.log4j.Log4j2;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;

@Log4j2
public class CreateNewPage extends BasePage {

    private final By FLOOR_INPUT = Selectors.byId("floor_send");
    private final By PRICE_INPUT = Selectors.byId("price_send");
    private final By WARM_AND_COVERED_PLACES_INPUT = Selectors.byId("parking_first_send");
    private final By WARM_AND_NOT_COVERED_PLACES_INPUT = Selectors.byId("parking_second_send");
    private final By NOT_WARM_AND_COVERED_PLACES_INPUT = Selectors.byId("parking_third_send");
    private final By NOT_WARM_AND_NOT_COVERED_PLACES_INPUT = Selectors.byId("parking_fourth_send");
    private final By BUTTON_PUSH = Selectors.byClassName("btn-primary");
    private final By STATUS_TEXT = Selectors.withText("Status");
    private final By NEW_HOUSE_ID = Selectors.withText("New house ID");

    @Step("Нажатие стрелки вверх в поле ввода Floor Number")
    public CreateNewPage increaseFloorNumber(){
        log.info("Sent Arrow Up to Floor input");
        $(FLOOR_INPUT).click();
        sleep(500);
        $(FLOOR_INPUT).sendKeys(Keys.ARROW_UP);
        return this;
    }

    @Step("Нажатие стрелки вниз в поле ввода Floor Number")
    public CreateNewPage decreaseFloorNumber(){
        log.info("Sent Arrow Down to Floor input");
        $(FLOOR_INPUT).click();
        sleep(500);
        $(FLOOR_INPUT).sendKeys(Keys.ARROW_DOWN);
        return this;
    }

    @Step("Отправка значения в поле ввода Floor Number")
    public CreateNewPage sentFloorNumber(String floorNumber){
        log.info("Sent " + floorNumber + " to Floor input");
        $(FLOOR_INPUT).click();
        sleep(500);
        $(FLOOR_INPUT).setValue(floorNumber);
        return this;
    }

    @Step("Нажатие стрелки вверх в поле ввода Price")
    public CreateNewPage increasePriceNumber(){
        log.info("Sent Arrow Up to Price input");
        $(PRICE_INPUT).click();
        sleep(500);
        $(PRICE_INPUT).sendKeys(Keys.ARROW_UP);
        return this;
    }

    @Step("Нажатие стрелки вниз в поле ввода Price")
    public CreateNewPage decreasePriceNumber(){
        log.info("Sent Arrow Down to Price input");
        $(PRICE_INPUT).click();
        sleep(500);
        $(PRICE_INPUT).sendKeys(Keys.ARROW_DOWN);
        return this;
    }

    @Step("Отправка значения в поле ввода Price")
    public CreateNewPage sentPriceNumber(String priceNumber){
        log.info("Sent " + priceNumber + " to Price input");
        $(PRICE_INPUT).click();
        sleep(500);
        $(PRICE_INPUT).setValue(priceNumber);
        return this;
    }

    @Step("Нажатие стрелки вверх в поле ввода Warm And Covered Places Number")
    public CreateNewPage increaseWarmAndCoveredPlacesNumber(){
        log.info("Sent Arrow Up to WarmAndCoveredPlacesNumber input");
        $(WARM_AND_COVERED_PLACES_INPUT).click();
        sleep(500);
        $(WARM_AND_COVERED_PLACES_INPUT).sendKeys(Keys.ARROW_UP);
        return this;
    }

    @Step("Нажатие стрелки вниз в поле ввода Warm And Covered Places Number")
    public CreateNewPage decreaseWarmAndCoveredPlacesNumber(){
        log.info("Sent Arrow Down to WarmAndCoveredPlacesNumber input");
        $(WARM_AND_COVERED_PLACES_INPUT).click();
        sleep(500);
        $(WARM_AND_COVERED_PLACES_INPUT).sendKeys(Keys.ARROW_DOWN);
        return this;
    }

    @Step("Отправка значения в поле ввода Warm And Covered Places Number")
    public CreateNewPage sentWarmAndCoveredPlacesNumber(String warmAndCoveredPlacesNumber){
        log.info("Sent " + warmAndCoveredPlacesNumber + " to warmAndCoveredPlacesNumber input");
        $(WARM_AND_COVERED_PLACES_INPUT).click();
        sleep(500);
        $(WARM_AND_COVERED_PLACES_INPUT).setValue(warmAndCoveredPlacesNumber);
        return this;
    }

    @Step("Нажатие стрелки вверх в поле ввода Warm And Not Covered Places Number")
    public CreateNewPage increaseWarmAndNotCoveredPlacesNumber(){
        log.info("Sent Arrow Up to WarmAndNotCoveredPlacesNumber input");
        $(WARM_AND_NOT_COVERED_PLACES_INPUT).click();
        sleep(500);
        $(WARM_AND_NOT_COVERED_PLACES_INPUT).sendKeys(Keys.ARROW_UP);
        return this;
    }

    @Step("Нажатие стрелки вниз в поле ввода Warm And Not Covered Places Number")
    public CreateNewPage decreaseWarmAndNotCoveredPlacesNumber(){
        log.info("Sent Arrow Down to WarmAndNotCoveredPlacesNumber input");
        $(WARM_AND_NOT_COVERED_PLACES_INPUT).click();
        sleep(500);
        $(WARM_AND_NOT_COVERED_PLACES_INPUT).sendKeys(Keys.ARROW_DOWN);
        return this;
    }

    @Step("Отправка значения в поле ввода Warm And Not Covered Places Number")
    public CreateNewPage sentWarmAndNotCoveredPlacesNumber(String warmAndNotCoveredPlacesNumber){
        log.info("Sent " + warmAndNotCoveredPlacesNumber + " to warmAndNotCoveredPlacesNumber input");
        $(WARM_AND_NOT_COVERED_PLACES_INPUT).click();
        sleep(500);
        $(WARM_AND_NOT_COVERED_PLACES_INPUT).setValue(warmAndNotCoveredPlacesNumber);
        return this;
    }

    @Step("Нажатие стрелки вверх в поле ввода Not Warm And Covered Places Number")
    public CreateNewPage increaseNotWarmAndCoveredPlacesNumber(){
        log.info("Sent Arrow Up to NotWarmAndCoveredPlacesNumber input");
        $(NOT_WARM_AND_COVERED_PLACES_INPUT).click();
        sleep(500);
        $(NOT_WARM_AND_COVERED_PLACES_INPUT).sendKeys(Keys.ARROW_UP);
        return this;
    }

    @Step("Нажатие стрелки вниз в поле ввода Not Warm And Covered Places Number")
    public CreateNewPage decreaseNotWarmAndCoveredPlacesNumber(){
        log.info("Sent Arrow Down to NotWarmAndCoveredPlacesNumber input");
        $(NOT_WARM_AND_COVERED_PLACES_INPUT).click();
        sleep(500);
        $(NOT_WARM_AND_COVERED_PLACES_INPUT).sendKeys(Keys.ARROW_DOWN);
        return this;
    }

    @Step("Отправка значения в поле ввода Not Warm And Covered Places Number")
    public CreateNewPage sentNotWarmAndCoveredPlacesNumber(String notWarmAndCoveredPlacesNumber){
        log.info("Sent " + notWarmAndCoveredPlacesNumber + " to NotWarmAndCoveredPlacesNumber input");
        $(NOT_WARM_AND_COVERED_PLACES_INPUT).click();
        sleep(500);
        $(NOT_WARM_AND_COVERED_PLACES_INPUT).setValue(notWarmAndCoveredPlacesNumber);
        return this;
    }

    @Step("Нажатие стрелки вверх в поле ввода Not Warm And Not Covered Places Number")
    public CreateNewPage increaseNotWarmAndNotCoveredPlacesNumber(){
        log.info("Sent Arrow Up to NotWarmAndNotCoveredPlacesNumber input");
        $(NOT_WARM_AND_NOT_COVERED_PLACES_INPUT).click();
        sleep(500);
        $(NOT_WARM_AND_NOT_COVERED_PLACES_INPUT).sendKeys(Keys.ARROW_UP);
        return this;
    }

    @Step("Нажатие стрелки вниз в поле ввода Not Warm And Not Covered Places Number")
    public CreateNewPage decreaseNotWarmAndNotCoveredPlacesNumber(){
        log.info("Sent Arrow Down to NotWarmAndNotCoveredPlacesNumber input");
        $(NOT_WARM_AND_NOT_COVERED_PLACES_INPUT).click();
        sleep(500);
        $(NOT_WARM_AND_NOT_COVERED_PLACES_INPUT).sendKeys(Keys.ARROW_DOWN);
        return this;
    }

    @Step("Отправка значения в поле ввода Not Warm And Not Covered Places Number")
    public CreateNewPage sentNotWarmAndNotCoveredPlacesNumber(String notWarmAndNotCoveredPlacesNumber){
        log.info("Sent " + notWarmAndNotCoveredPlacesNumber + " to NotWarmAndNotCoveredPlacesNumber input");
        $(NOT_WARM_AND_NOT_COVERED_PLACES_INPUT).click();
        sleep(500);
        $(NOT_WARM_AND_NOT_COVERED_PLACES_INPUT).setValue(notWarmAndNotCoveredPlacesNumber);
        return this;
    }

    @Step("Нажатие кнопки Push")
    public CreateNewPage clickPushButton(){
        log.info("Click on Push button");
        $(BUTTON_PUSH).click();
        sleep(1500);
        return this;
    }

    @Step("Получение статуса отправки")
    public String getStatus(){
        log.info("Getting operation status");
        return $(STATUS_TEXT).getText();
    }
    
    @Step("Получение ID Дома")
    public CreateNewPage getHouseId(){
        log.info("Getting house ID");
        $(NEW_HOUSE_ID).getText();
        return this;
    }

    @Override
    @Step("Открытие страницы Create new")
    public CreateNewPage open() {
        log.info("Opening Read All page");
        Selenide.open(BASE_URL + "/#/create/house");
        return this;
    }

    @Override
    @Step("Проверка открытия страницы Create new")
    public CreateNewPage isPageOpened() {
        log.info("Create new page is open");
        $(FLOOR_INPUT).shouldBe(Condition.visible);
        sleep(1500);
        return this;
    }
}