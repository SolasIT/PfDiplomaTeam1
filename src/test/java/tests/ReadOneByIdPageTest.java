package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Link;
import io.qameta.allure.Owner;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

public class ReadOneByIdPageTest extends BaseTest {

    @Test(description = "Проверка кнопки Read",
            testName = "Проверка кнопки Read")
    @Owner("Golubnichiy Vitaliy")
    @Link("http://82.142.167.37:4881/#/read/house")
    @Feature("Read one by ID")
    @Description("Проверка кнопки Read")
    public void checkReadButton(){
        mainPage.open()
                .isPageOpened()
                .auth(email, password);
        readOneByIdPage.openPage()
                .clickReadButton();
    }

    @Test(description = "Проверка поиска по ID = -1 через нажатие стрелки вниз",
            testName = "Проверка поиска по ID = -1 через нажатие стрелки вниз")
    @Owner("Golubnichiy Vitaliy")
    @Link("http://82.142.167.37:4881/#/read/house")
    @Feature("Read one by ID")
    @Description("Проверка поиска по ID = -1 через нажатие стрелки вниз")
    public void inputIdFromArrowDown(){
        mainPage.open()
                .isPageOpened()
                .auth(email, password);
        readOneByIdPage.openPage()
                .increaseInputNumber()
                .clickReadButton()
                .getStatus();
        assertEquals("Status:  Invalid input", readOneByIdPage.getStatus);
    }

    @Test(description = "Проверка поиска по ID = 0 через нажатие стрелок",
            testName = "Проверка поиска по ID = 0 через нажатие стрелок")
    @Owner("Golubnichiy Vitaliy")
    @Link("http://82.142.167.37:4881/#/read/house")
    @Feature("Read one by ID")
    @Description("Проверка поиска по ID = 0 через нажатие стрелок")
    public void inputNullIdByArrows(){
        mainPage.open()
                .isPageOpened()
                .auth(email, password);
        readOneByIdPage.openPage()
                .increaseInputNumber()
                .decreaseInputNumber()
                .clickReadButton()
                .getStatus();
        assertEquals("Status:  Invalid input", readOneByIdPage.getStatus);
    }

    @Test(description = "Проверка поиска по несуществующему ID через ввод числа",
            testName = "Проверка поиска по несуществующему ID через ввод числа")
    @Owner("Golubnichiy Vitaliy")
    @Link("http://82.142.167.37:4881/#/read/house")
    @Feature("Read one by ID")
    @Description("Проверка поиска по несуществующему ID через ввод числа")
    public void inputNonExistentId(){
        mainPage.open()
                .isPageOpened()
                .auth(email, password);
        readOneByIdPage.openPage()
                .sentInputNumber("999999999999999999")
                .clickReadButton()
                .getStatus();
        assertEquals("Status: 204 house not found", readOneByIdPage.getStatus);
    }


    @Test(description = "Проверка поиска по ID = 1 через нажатие стрелки вверх",
            testName = "Проверка поиска по ID = 1 через нажатие стрелки вверх")
    @Owner("Golubnichiy Vitaliy")
    @Link("http://82.142.167.37:4881/#/read/house")
    @Feature("Read one by ID")
    @Description("Проверка поиска по ID = 1 через нажатие стрелки вверх")
    public void inputIdFromArrowUp(){
        mainPage.open()
                .isPageOpened()
                .auth(email, password);
        readOneByIdPage.openPage()
                .decreaseInputNumber()
                .clickReadButton()
                .getStatus();
        assertEquals("Status: 200 ok", readOneByIdPage.getStatus);
    }
}
