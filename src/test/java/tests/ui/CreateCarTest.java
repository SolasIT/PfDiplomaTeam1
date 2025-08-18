package tests.ui;

import dto.ui.Car;
import dto.ui.CarFactory;
import io.qameta.allure.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import tests.ui.BaseTest;

import static org.testng.Assert.assertEquals;

public class CreateCarTest extends BaseTest {

    @BeforeMethod
    public void setUp() {
        mainPage.open()
                .isPageOpened()
                .auth(email, password)
                .openCarsCreateNew()
                .isPageOpened();
    }

    @DataProvider(name = "invalidCars")
    public Object[][] invalidCars() {
        return new Object[][]{
                {"", "Toyota", "Camry", 25000, "Status: Invalid request data"},
                {"Gasoline", "", "Camry", 25000, "Status: Invalid request data"},
                {"Diesel", "Toyota", "", 25000, "Status: Invalid request data"},
                {"Electric", "Toyota", "Camry", 0, "Status: Invalid request data"},
        };
    }

    @Test(dataProvider = "invalidCars")
    @Owner("Martyanova Olga")
    @Feature("Car Create New")
    @Description("Проверка ошибок при некорректном заполнении карточки авто")
    public void createCarWithInvalidData(String engineType, String mark, String model, double price, String expectedStatus) {
        Car car = Car.builder()
                .engineType(engineType)
                .mark(mark)
                .model(model)
                .price(price)
                .build();

        carsCreateNewPage.createCar(car);
        assertEquals(car.getStatus(), expectedStatus);
    }

    @Test
    @Owner("Martyanova Olga")
    @Feature("Car Create New")
    @Description("Проверка при заполнении карточки авто валидными данными")
    public void createCarWithValidData() {
        Car car = CarFactory.getCar();
        carsCreateNewPage.createCar(car);
        assertEquals(car.getStatus(), "Status: Successfully pushed, code: 201",
                "Ошибка при создании новой карточки: " + car);
    }

    @Test
    @Owner("Martyanova Olga")
    @Feature("Car Create New")
    @Description("Проверка создания карточки машины с локализированными валидными данными")
    public void createLocalizedCar() {
        Car car = CarFactory.getCar("ru");
        carsCreateNewPage.createCar(car);
        assertEquals(car.getStatus(), "Status: Successfully pushed, code: 201",
                "Failed to create localized car with data: " + car);
    }
}