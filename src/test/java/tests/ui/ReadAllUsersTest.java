package tests.ui;


import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;


public class ReadAllUsersTest extends BaseTest {

    @BeforeMethod
    public void setUp() {
        readAllUsersPage.open()
                .isPageOpened();
    }

    @Test(description = "Проверка видимости элементов таблицы")
    @Owner("Malkov Artem")
    @Feature("User Read All")
    public void testElements() {
        readAllUsersPage.getReloadButton().shouldBe(visible);
        readAllUsersPage.getIdColumn().shouldBe(visible);
        readAllUsersPage.getAgeColumn().shouldBe(exactText("Age:"));
        readAllUsersPage.getSexColumn().shouldBe(exactText("Sex:"));
        readAllUsersPage.getMoneyColumn().shouldBe(exactText("Money:"));
    }

    @Test(description = "Сортировка по возростанию ID")
    @Owner("Malkov Artem")
    @Feature("User Read All")
    public void testSortId() {
    readAllUsersPage.sortByIdUp().getIds();
    List<Integer> increasedIds = carsReadAllPage.getIds();
        Set<Integer> increasedSortedIds = new TreeSet<>(increasedIds);
        softAssert.assertEquals(increasedIds, new ArrayList<>(increasedSortedIds),
                "ID должны быть отсортированы по возрастанию");

        readAllUsersPage.sortByIdDown().getIds();
        List<Integer> decreasingIds = carsReadAllPage.getIds();
        Set<Integer> decreasingSortedIds = new TreeSet<>(decreasingIds);
        softAssert.assertEquals(decreasingIds, new ArrayList<>(decreasingSortedIds),
                "ID должны быть отсортированы по возрастанию");
    }
}
