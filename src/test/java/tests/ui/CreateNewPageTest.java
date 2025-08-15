package tests.ui;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Link;
import io.qameta.allure.Owner;
import org.testng.annotations.Test;
import tests.ui.BaseTest;

import static org.testng.AssertJUnit.assertEquals;

public class CreateNewPageTest extends BaseTest {

    @Test(description = "Проверка нажатия Push To API без ввода данных",
            testName = "Проверка нажатия Push To API без ввода данных")
    @Owner("Golubnichiy Vitaliy")
    @Link("http://82.142.167.37:4881/#/create/house")
    @Feature("Create New")
    @Description("Проверка нажатия Push To API без ввода данных")
    public void pushCreateWithoutData(){
        mainPage.open()
                .isPageOpened()
                .auth(email, password);
        createNewPage.openPage()
                .clickPushButton()
                .getStatus();
        assertEquals("Status: Invalid input data", createNewPage.getStatus());
    }

    @Test(description = "Проверка нажатия Push To API с отрицательными значениями инпутов",
            testName = "Проверка нажатия Push To API с отрицательными значениями инпутов")
    @Owner("Golubnichiy Vitaliy")
    @Feature("Create New")
    @Description("Проверка нажатия Push To API с отрицательными значениями инпутов")
    public void pushCreateWithNegativeNumbers(){
        mainPage.open()
                .isPageOpened()
                .auth(email, password);
        createNewPage.openPage()
                .decreaseFloorNumber()
                .decreasePriceNumber()
                .decreaseNotWarmAndNotCoveredPlacesNumber()
                .decreaseWarmAndNotCoveredPlacesNumber()
                .decreaseNotWarmAndCoveredPlacesNumber()
                .decreaseWarmAndCoveredPlacesNumber()
                .clickPushButton()
                .getStatus();
        assertEquals("Status: Invalid input data", createNewPage.getStatus());
    }

    @Test(description = "Проверка нажатия Push To API с нулевыми значениями инпутов",
            testName = "Проверка нажатия Push To API с нулевыми значениями инпутов")
    @Owner("Golubnichiy Vitaliy")
    @Feature("Create New")
    @Description("Проверка нажатия Push To API с нулевыми значениями инпутов")
    public void pushCreateWithNull(){
        mainPage.open()
                .isPageOpened()
                .auth(email, password);
        createNewPage.openPage()
                .decreaseFloorNumber()
                .decreasePriceNumber()
                .decreaseNotWarmAndNotCoveredPlacesNumber()
                .decreaseWarmAndNotCoveredPlacesNumber()
                .decreaseNotWarmAndCoveredPlacesNumber()
                .decreaseWarmAndCoveredPlacesNumber()
                .increaseFloorNumber()
                .increasePriceNumber()
                .increaseNotWarmAndNotCoveredPlacesNumber()
                .increaseWarmAndNotCoveredPlacesNumber()
                .increaseNotWarmAndCoveredPlacesNumber()
                .increaseWarmAndCoveredPlacesNumber()
                .clickPushButton()
                .getStatus();
        assertEquals("Status: Invalid input data", createNewPage.getStatus());
    }

    @Test(description = "Проверка нажатия Push To API с положительными значениями инпутов",
            testName = "Проверка нажатия Push To API с положительными значениями инпутов")
    @Owner("Golubnichiy Vitaliy")
    @Feature("Create New")
    @Description("Проверка нажатия Push To API с положительными значениями инпутов")
    public void pushCreateWithPositiveNumbers(){
        mainPage.open()
                .isPageOpened()
                .auth(email, password);
        createNewPage.openPage()
                .increaseFloorNumber()
                .increasePriceNumber()
                .increaseNotWarmAndNotCoveredPlacesNumber()
                .increaseWarmAndNotCoveredPlacesNumber()
                .increaseNotWarmAndCoveredPlacesNumber()
                .increaseWarmAndCoveredPlacesNumber()
                .clickPushButton()
                .getStatus();
        assertEquals("Status: Successfully pushed, code: 201", createNewPage.getStatus());
    }
}
