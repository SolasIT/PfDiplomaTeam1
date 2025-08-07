package pages.houses;

import com.codeborne.selenide.Selectors;
import org.openqa.selenium.By;
import pages.BasePage;

import java.nio.channels.Selector;

public class SettleOrEvictUserPage extends BasePage {

    private final By USER_ID = Selectors.byId("id_send");
    private final By HOUSE_ID = Selectors.byId("house_send");
    private final By SETTLE_BUTTON = Selectors.byValue("settle");
    private final By EVICT_BUTTON = Selectors.byValue("evict");
    private final By BUTTON_PUSH = Selectors.byClassName("tableButton btn btn-primary");
    private final By STATUS_TEXT = Selectors.withText("Status");
}
