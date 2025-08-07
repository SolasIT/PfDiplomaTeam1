package pages.houses;

import com.codeborne.selenide.Selectors;
import org.openqa.selenium.By;
import pages.BasePage;

public class CreateNewPage extends BasePage {

    private final By FLOOR_INPUT = Selectors.byId("floor_send");
    private final By PRICE_INPUT = Selectors.byId("price_send");
    private final By WARM_AND_COVERED_PLACES_INPUT = Selectors.byId("parking_first_send");
    private final By WARM_AND_NOT_COVERED_PLACES_INPUT = Selectors.byId("parking_second_send");
    private final By NOT_WARM_AND_COVERED_PLACES_INPUT = Selectors.byId("parking_third_send");
    private final By NOT_WARM_AND_NOT_COVERED_PLACES_INPUT = Selectors.byId("parking_fourth_send");
    private final By BUTTON_PUSH = Selectors.byClassName("tableButton btn btn-primary");
    private final By STATUS_TEXT = Selectors.withText("Status");
    private final By NEW_HOUSE_ID = Selectors.withText("New house ID");
}
