package tests.ui;

import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.*;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;

public class TestCarsReadAll extends BaseTest {

    @BeforeMethod
    public void setUp() {
        carsReadAllPage.open()
                .isPageOpened();
    }

    @Test(description = "Проверка видимости основных элементов и текста заголовков")
    @Owner("Martyanova Olga")
    @Feature("Car Read All")
    public void testPageElements() {
        carsReadAllPage.getIdColumn().shouldBe(visible);
        carsReadAllPage.getReloadButton().shouldBe(visible);
        carsReadAllPage.getIdColumn().shouldHave(exactText("ID:"));
        carsReadAllPage.getMarkColumn().shouldHave(exactText("Mark:"));
        carsReadAllPage.getModelColumn().shouldHave(exactText("Model:"));
        carsReadAllPage.getPriceColumn().shouldHave(exactText("Price:"));
    }

    @Test(description = "Проверка сортировки по ID (возрастание и убывание)")
    @Owner("Martyanova Olga")
    @Feature("Car Read All")
    public void testIdSorting() {
        // Проверка сортировки по возрастанию
        carsReadAllPage.sortByIdAsc();
        List<Integer> ascendingIds = carsReadAllPage.getIds();
        Set<Integer> ascendingSortedIds = new TreeSet<>(ascendingIds);
        softAssert.assertEquals(ascendingIds, new ArrayList<>(ascendingSortedIds),
                "ID должны быть отсортированы по возрастанию");

        // Проверка сортировки по убыванию
        carsReadAllPage.sortByIdDsc();
        List<Integer> descendingIds = carsReadAllPage.getIds();
        Set<Integer> descendingSortedIds = new TreeSet<>(Comparator.reverseOrder());
        descendingSortedIds.addAll(descendingIds);
        softAssert.assertEquals(descendingIds, new ArrayList<>(descendingSortedIds),
                "ID должны быть отсортированы по убыванию");
        softAssert.assertAll();
    }
}