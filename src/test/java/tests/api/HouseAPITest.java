package tests.api;

import adapters.HouseAdapter;
import dto.api.houses.House;
import io.qameta.allure.*;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static dto.api.houses.HouseFactory.getHouse;

public class HouseAPITest {

    SoftAssert softAssert = new SoftAssert();

    @Test(description = "Создание дома (POST /house)", testName = "Create House API")
    @Owner("Martyanova Olga")
    @Link("http://82.142.167.37:4879/swagger-ui/index.html#/")
    @Feature("Create")
    @Description("Проверка создания дома API")
    public void createHouseTest() {
        HouseAdapter houseAdapter = new HouseAdapter();
        House house = getHouse("ru");
        House createdHouse = houseAdapter.createHouse(house);
        softAssert.assertNotNull(createdHouse.getId(), "ID должен вернуться после создания");
        softAssert.assertEquals(createdHouse.getFloorCount(), house.getFloorCount(), "Этажи не совпадают");
        softAssert.assertEquals(createdHouse.getPrice(), house.getPrice(), "Цена не совпадает");
        softAssert.assertAll();
    }

    @Test(description = "Получение дома по id (GET /house/{id})", testName = "Get House API")
    @Owner("Martyanova Olga")
    @Link("http://82.142.167.37:4879/swagger-ui/index.html#/")
    @Feature("Create")
    @Description("Проверка получения дома API")
    public void getHouseByIdTest() {
        HouseAdapter houseAdapter = new HouseAdapter();
        House house = getHouse("ru");
        House createdHouse = houseAdapter.createHouse(house);
        House[] getHouse = houseAdapter.getHouses(createdHouse.getId());
        softAssert.assertEquals(createdHouse.getId(), createdHouse.getId(), "ID не совпадает");
        softAssert.assertEquals(createdHouse.getFloorCount(), createdHouse.getFloorCount(), "Этажи не совпадают");
        softAssert.assertEquals(createdHouse.getPrice(), createdHouse.getPrice(), "Цена не совпадает");
        softAssert.assertAll();
    }

    @Test(description = "Обновление дома (PUT /house/{id})", testName = "Update House API")
    @Owner("Martyanova Olga")
    @Link("http://82.142.167.37:4879/swagger-ui/index.html#/")
    @Feature("Update")
    @Description("Проверка изменения дома API")
    public void updateHouseTest() {
        HouseAdapter houseAdapter = new HouseAdapter();
        House house = getHouse("ru");
        House createdHouse = houseAdapter.createHouse(house);
        House newHouse = getHouse("ru");
        newHouse.setId(createdHouse.getId());
        House chageHouse = houseAdapter.changeHouse(newHouse, createdHouse.getId());
        House updatedHouse = houseAdapter.getHouseById(createdHouse.getId());
        softAssert.assertEquals(chageHouse.getId(), createdHouse.getId(), "ID не совпадает");
        softAssert.assertEquals(chageHouse.getPrice(), updatedHouse.getPrice(), "Цена не обновилась");
        softAssert.assertEquals(chageHouse.getFloorCount(), updatedHouse.getFloorCount(), "Этажи не обновились");
    }

    @Test(description = "Удаление дома (DELETE /house/{id})", testName = "Delete House API")
    @Owner("Martyanova Olga")
    @Link("http://82.142.167.37:4879/swagger-ui/index.html#/")
    @Feature("Delete")
    @Description("Проверка удаления дома API")
    public void deleteHouseTest() {
        HouseAdapter houseAdapter = new HouseAdapter();
        House house = getHouse("ru");
        House createdHouse = houseAdapter.createHouse(house);
        houseAdapter.deleteHouse(createdHouse.getId());
    }

    @Test(description = "Получение списка домов (GET /houses)", testName = "Get All Houses API")
    @Owner("Martyanova Olga")
    @Link("http://82.142.167.37:4879/swagger-ui/index.html#/")
    @Feature("Get")
    @Description("Проверка получения всех домов API")
    public void getAllHousesTest() {
        HouseAdapter houseAdapter = new HouseAdapter();
        House[] houses = houseAdapter.getHouses(200);
        softAssert.assertNotNull(houses, "Список домов не получен");
        softAssert.assertAll();
    }
}