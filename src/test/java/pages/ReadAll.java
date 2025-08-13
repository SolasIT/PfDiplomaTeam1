package pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SetValueOptions;
import groovy.util.logging.Log4j2;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.byCssSelector;
import static com.codeborne.selenide.SetValueOptions.withText;

    @Log4j2
    public class ReadAll extends BasePage{

    private final SetValueOptions RELOAD_BUTTON = withText("Reload"); //локатор кнопки Reload
    private final By ID_BUTTON = byCssSelector(".btn.btn-secondary"); //локатор кнопки ID
    private final By FIRST_NAME_BUTTON = byCssSelector("button.First"); //локатор кнопки First Name
    private final By LAST_NAME_BUTTON = byCssSelector("button.Last"); //локатор кнопки Last Name
    private final By AGE_BUTTON = byCssSelector("button.Age"); //локатор кнопки Age
    private final By SEX_BUTTON = byCssSelector("button.Sex"); //локатор кнопки Sex
    private final By MONEY_BUTTON = byCssSelector("button.Money");//локатор кнопки Money

    @Override
    @Step("Открытие страницы Read All")
    public ReadAll open(){
        Selenide.open(BASE_URL + "#/read/users");
        return this;
    }

        @Override
        public BasePage isPageOpened() {
            return null;
        }



    }
