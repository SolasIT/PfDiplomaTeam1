package tests.api;

import adapters.CarAdapter;
import dto.api.cars.Car;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Link;
import io.qameta.allure.Owner;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static dto.api.cars.CarFactory.getCar;


public class CarAPITest {
    SoftAssert softAssert = new SoftAssert();

    @Test(
            description = "Проверка создания авто API",
            testName = "Проверка создания авто API")
    @Owner("Laptev Denis")
    @Link("http://82.142.167.37:4881/#/create/user")
    @Feature("Create New")
    @Description("Проверка создания авто API")
    public void createCarAPI() {
        CarAdapter carAdapter = new CarAdapter();
        Car car = getCar("us");
        Car req = carAdapter.createCar(car);
        softAssert.assertEquals(car.getMark(), req.getMark(), "Марка не соответствует");
        softAssert.assertEquals(car.getEngineType(), req.getEngineType(), "Тип двигателя не соответствует");
        softAssert.assertEquals(car.getModel(), req.getModel(), "Модель не соответствует");
        softAssert.assertEquals(car.getPrice(), req.getPrice(), "Цена не соответствует");
        softAssert.assertAll();
    }

    @Test(
            description = "Проверка получения авто по ID API",
            testName = "Проверка получения авто по ID API")
    @Owner("Laptev Denis")
    @Link("http://82.142.167.37:4881/#/create/user")
    @Feature("Create New")
    @Description("Проверка получения авто по ID API")
    public void getCarByIDAPI() {
        CarAdapter carAdapter = new CarAdapter();
        Car car = getCar("us"); // Создаем авто
        Car req = carAdapter.createCar(car);//прокидываем его в api
        Car getcar = carAdapter.getCar(req.getId());
        softAssert.assertEquals(req.getId(), getcar.getId(), "ID не соответствует");
        softAssert.assertEquals(req.getMark(), getcar.getMark(), "Марка не соответствует");
        softAssert.assertEquals(req.getEngineType(), getcar.getEngineType(), "Тип двигателя не соответствует");
        softAssert.assertEquals(req.getModel(), getcar.getModel(), "Модель не соответствует");
        softAssert.assertEquals(req.getPrice(), getcar.getPrice(), "Цена не соответствует");
        softAssert.assertAll();
    }

    @Test(
            description = "Проверка изменения данных авто по ID API",
            testName = "Проверка изменения данных авто по ID API")
    @Owner("Laptev Denis")
    @Link("http://82.142.167.37:4881/#/create/user")
    @Feature("Create New")
    @Description("Проверка изменения данных авто по ID API")
    public void changeCarByIDAPI() {
        CarAdapter carAdapter = new CarAdapter();
        Car car = getCar("us"); // Создаем авто
        Car req = carAdapter.createCar(car);//прокидываем его в api
        Car newcar = getCar("us"); // Создаем новые значения
        newcar.setId(req.getId());//Присвоим полученный ID
        Car changescar = carAdapter.changeCar(newcar, req.getId());//Меняем значения в api
        Car getcar = carAdapter.getCar(req.getId());
        softAssert.assertEquals(changescar.getId(), getcar.getId(), "ID не соответствует");
        softAssert.assertEquals(changescar.getMark(), getcar.getMark(), "Марка не соответствует");
        softAssert.assertEquals(changescar.getEngineType(), getcar.getEngineType(), "Тип двигателя не соответствует");
        softAssert.assertEquals(changescar.getModel(), getcar.getModel(), "Модель не соответствует");
        softAssert.assertEquals(changescar.getPrice(), getcar.getPrice(), "Цена не соответствует");
        softAssert.assertAll();
    }

    @Test(
            description = "Проверка Удаления авто по ID API",
            testName = "Проверка Удаления авто по ID API")
    @Owner("Laptev Denis")
    @Link("http://82.142.167.37:4881/#/create/user")
    @Feature("Create New")
    @Description("Проверка Удаления авто по ID API")
    public void deleteCarByIDAPI() {
        CarAdapter carAdapter = new CarAdapter();
        Car car = getCar("us"); // Создаем авто
        Car req = carAdapter.createCar(car);//прокидываем его в api
        carAdapter.deleteCar(req.getId());
    }
}