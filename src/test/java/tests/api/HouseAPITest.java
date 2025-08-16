package tests.api;

import adapters.HouseAdapter;
import dto.api.houses.House;
import dto.api.houses.HouseFactory;
import io.qameta.allure.*;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class HouseAPITest {

    SoftAssert softAssert = new SoftAssert();
    HouseAdapter houseAdapter = new HouseAdapter();

    @Test(description = "Создание дома (POST /house)", testName = "Create House API")
    @Owner("Martyanova Olga")
    @Link("http://82.142.167.37:4879/swagger-ui/index.html#/")
    @Story("Create")
    public void createHouseTest() {
        House house = HouseFactory.getHouse("ru");
        House createdHouse = houseAdapter.createHouse(house, 200);
        softAssert.assertNotNull(createdHouse.getId(), "ID должен вернуться после создания");
        softAssert.assertEquals(createdHouse.getFloorCount(), house.getFloorCount(), "Этажи не совпадают");
        softAssert.assertEquals(createdHouse.getPrice(), house.getPrice(), "Цена не совпадает");
        softAssert.assertAll();
    }

    @Test(description = "Получение дома по id (GET /house/{id})", testName = "Get House API")
    @Owner("Martyanova Olga")
    @Link("http://82.142.167.37:4879/swagger-ui/index.html#/")
    @Story("Get")
    public void getHouseByIdTest() {
        House houseRq = HouseFactory.getHouse("ru");
        House createdHouse = houseAdapter.createHouse(houseRq, 200);
        House receivedHouse = houseAdapter.getHouseById(createdHouse.getId(), 200);
        softAssert.assertEquals(receivedHouse.getId(), createdHouse.getId(), "ID не совпадает");
        softAssert.assertEquals(receivedHouse.getFloorCount(), createdHouse.getFloorCount(), "Этажи не совпадают");
        softAssert.assertEquals(receivedHouse.getPrice(), createdHouse.getPrice(), "Цена не совпадает");
        softAssert.assertAll();
    }

    @Test(description = "Обновление дома (PUT /house/{id})", testName = "Update House API")
    @Owner("Martyanova Olga")
    @Link("http://82.142.167.37:4879/swagger-ui/index.html#/")
    @Story("Update")
    public void updateHouseTest() {
        House houseRq = HouseFactory.getHouse("ru");
        House createdHouse = houseAdapter.createHouse(houseRq, 200);
        createdHouse.setPrice(171315.99);
        createdHouse.setFloorCount(17);
        House updatedHouse = houseAdapter.changeHouse(createdHouse, createdHouse.getId(), 200);
        softAssert.assertEquals(updatedHouse.getId(), createdHouse.getId(), "ID не совпадает");
        softAssert.assertEquals(updatedHouse.getPrice(), 171315.99, "Цена не обновилась");
        softAssert.assertEquals(updatedHouse.getFloorCount().intValue(), 17, "Этажи не обновились");
        softAssert.assertAll();
    }

    @Test(description = "Удаление дома (DELETE /house/{id})", testName = "Delete House API")
    @Owner("Martyanova Olga")
    @Link("http://82.142.167.37:4879/swagger-ui/index.html#/")
    @Story("Delete")
    public void deleteHouseTest() {
        House houseRq = HouseFactory.getHouse("ru");
        House createdHouse = houseAdapter.createHouse(houseRq, 200);
        houseAdapter.deleteHouse(createdHouse.getId(), 200);
    }

    @Test(description = "Получение списка домов (GET /houses)", testName = "Get All Houses API")
    @Owner("Martyanova Olga")
    @Link("http://82.142.167.37:4879/swagger-ui/index.html#/")
    @Story("Get")
    public void getAllHousesTest() {
        House[] houses = houseAdapter.getHouses(200);
        softAssert.assertNotNull(houses, "Список домов не получен");
        softAssert.assertAll();
    }
}