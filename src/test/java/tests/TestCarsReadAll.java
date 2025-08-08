package tests;

import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.CarsReadAllPage;

public class TestCarsReadAll {
    private CarsReadAllPage carsPage;
    private SoftAssert softAssert;

    @BeforeMethod
    public void setUp() {
        softAssert = new SoftAssert();
        carsPage = new CarsReadAllPage()
                .open()
                .isPageOpened();
    }

    @Test(description = "Проверка видимости основных элементов и текста заголовков")
    @Owner("Martyanova Olga")
    @Feature("Car Read All")
    public void testPageElements() {
        carsPage.checkBasicElementsVisible();
        try {
            carsPage.checkHeadersText();
        } catch (AssertionError e) {
            softAssert.fail("Некорректные заголовки");
        }
        softAssert.assertAll();
    }

    @Test(description = "Проверка сортировки по ID (возрастание и убывание)")
    @Owner("Martyanova Olga")
    @Feature("Car Read All")
    public void testIdSorting() {
            SoftAssert softAssert = new SoftAssert();
            // Проверка сортировки по возрастанию
            carsPage.sortByIdAsc()
                    .verifyIdOrder(true, softAssert);
            // Проверка сортировки по убыванию
            carsPage.sortByIdDsc()
                    .verifyIdOrder(false, softAssert);
            softAssert.assertAll();
        }
}