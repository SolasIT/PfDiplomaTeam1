package tests.api;

import adapters.BaseAPI;
import adapters.CarAdapter;
import adapters.UsersAdapter;
import com.github.javafaker.Faker;
import dto.api.cars.Car;
import dto.api.users.rq.UserRequest;
import dto.api.users.rs.UserResponse;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Link;
import io.qameta.allure.Owner;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static org.testng.Assert.assertEquals;

@Slf4j
public class PersonControllerTest extends BaseAPI {

    SoftAssert softAssert = new SoftAssert();
    UsersAdapter usersAdapter = new UsersAdapter();
    CarAdapter carAdapter = new CarAdapter();
    // User params
    Integer createdUserId,
            createdUserAge;
    String createdUserFirstName,
            createdUserSecondName,
            createdUserSex;
    Double createdUserMoney;

    Faker faker = new Faker();
    UserRequest userRequest = UserRequest.builder()
            .firstName(faker.name().firstName())
            .secondName(faker.name().lastName())
            .age(faker.number().numberBetween(18, 65))
            .sex(faker.demographic().sex().toUpperCase())
            .money(faker.number().randomDouble(2, 0, 99999))
            .build();

    //Car params
    String carEngineType = "Gasoline",
            carMark = "Volvo",
            carModel = "S60";
    Double carPrice = createdUserMoney;

    Car car = Car.builder()
            .engineType(carEngineType)
            .mark(carMark)
            .model(carModel)
            .price(carPrice)
            .build();


    // Data Providers
    @DataProvider(name = "Negative user data")
    public Object[][] negativeUserData() {
        return new Object[][]{
                {null, "Surname", 21, "MALE", 1000.00}, // не передан обязательный параметр firstName
                {"Name", null, 22, "FEMALE", 2000.00}, // не передан обязательный параметр secondName
                {"Name", "Surname", null, "MALE", 3000.00}, // не передан обязательный параметр age
                {"Name", "Surname", 24, null, 4000.00}, // не передан обязательный параметр sex
                {"Name", "Surname", 25, "MALE", null}, // не передан обязательный параметр money
                {"Name", "Surname", 26, "TEAPOTE", 6000.00}, // неверное значение параметра sex
        };
    }

    // Tests
    // POST /user
    @Test(description = "Проверка создания пользователя",
            testName = "API: POST /user")
    @Owner("Zheltikov Vasiliy")
    @Link("http://82.142.167.37:4879/swagger-ui/index.html#/")
    @Feature("person-controller")
    @Description("Проверка API метода POST")
    public void createUser() {
        UserResponse userResponse = usersAdapter.createUser(userRequest); // создание пользователя POST
        softAssert.assertEquals(userResponse.getFirstName(), // блок проверок на совпадение значений параметров в response и в request
                userRequest.getFirstName(),
                "Значение параметра firstName не соответствует ожидаемому");
        softAssert.assertEquals(userResponse.getSecondName(),
                userRequest.getSecondName(),
                "Значение параметра secondName не соответствует ожидаемому");
        softAssert.assertEquals(userResponse.getAge(),
                userRequest.getAge(),
                "Значение параметра age не соответствует ожидаемому");
        softAssert.assertEquals(userResponse.getSex(),
                userRequest.getSex(),
                "Значение параметра sex не соответствует ожидаемому");
        softAssert.assertEquals(userResponse.getMoney(),
                userRequest.getMoney(),
                "Значение параметра money не соответствует ожидаемому");
        softAssert.assertAll();
        createdUserId = userResponse.getId(); // и запись всех значений в глобальные переменные для дальнейшей работы...
        createdUserFirstName = userResponse.getFirstName(); // ...в запросах GET, PUT, DELETE
        createdUserSecondName = userResponse.getSecondName();
        createdUserAge = userResponse.getAge();
        createdUserSex = userResponse.getSex();
        createdUserMoney = userResponse.getMoney();
    }

    // POST /user
    @Test(dataProvider = "Negative user data",
            description = "Нарушение контракта метода POST /user",
            testName = "API: POST /user: нарушение контракта")
    @Owner("Zheltikov Vasiliy")
    @Link("http://82.142.167.37:4879/swagger-ui/index.html#/")
    @Feature("person-controller")
    @Description("Проверка API метода POST: не переданы обязательные параметры, неверное значение параметра sex")
    public void createUserNegativeParams(
            String firstName,
            String secondName,
            Integer age,
            String sex,
            Double money) {
        UserRequest userRequest = UserRequest.builder()
                .firstName(firstName)
                .secondName(secondName)
                .age(age)
                .sex(sex)
                .money(money)
                .build();
        usersAdapter.createUserWithIncorrectData(userRequest);
        // баг: создаётся user, если не передать параметр sex (отправляется FEMALE), тест падает
    }

    // POST /user
    @Test(description = "Нарушение контракта метода POST /user",
            testName = "API: POST /user: нарушение контракта")
    @Owner("Zheltikov Vasiliy")
    @Link("http://82.142.167.37:4879/swagger-ui/index.html#/")
    @Feature("person-controller")
    @Description("Проверка API метода POST: выполнение запроса с неверным методом")
    public void createUserWrongMethod() {
        UserRequest userRequest = UserRequest.builder()
                .firstName("Method")
                .secondName("Wrong")
                .age(40)
                .sex("MALE")
                .money(405405405.00)
                .build();
        usersAdapter.createUserWithIncorrectMethod(userRequest);
    }

    // GET User/{userId}
    @Test(dependsOnMethods = "createUser",
            description = "Проверка получения информации по ранее созданному пользователю",
            testName = "API: GET /user/{userId}")
    @Owner("Zheltikov Vasiliy")
    @Link("http://82.142.167.37:4879/swagger-ui/index.html#/")
    @Feature("person-controller")
    @Description("Проверка API метода GET")
    public void getUserById() {
        UserResponse userResponse = usersAdapter.getUserById(createdUserId);
        softAssert.assertEquals(userResponse.getId(),
                createdUserId,
                "Значение параметра id не соответствует ожидаемому");
        softAssert.assertEquals(userResponse.getFirstName(),
                createdUserFirstName,
                "Значение параметра firstName не соответствует ожидаемому");
        softAssert.assertEquals(userResponse.getSecondName(),
                createdUserSecondName,
                "Значение параметра secondName не соответствует ожидаемому");
        softAssert.assertEquals(userResponse.getAge(),
                createdUserAge,
                "Значение параметра age не соответствует ожидаемому");
        softAssert.assertEquals(userResponse.getSex(),
                createdUserSex,
                "Значение параметра sex не соответствует ожидаемому");
        softAssert.assertEquals(userResponse.getMoney(),
                createdUserMoney,
                "Значение параметра money не соответствует ожидаемому");
        softAssert.assertAll();
    }

    // GET User/{userId}
    @Test(dependsOnMethods = {"createUser", "deleteUserByNonExistentId"},
            description = "Попытка получения информации по несуществующему пользователю",
            testName = "API: GET /user/{userId}: userId не существует")
    @Owner("Zheltikov Vasiliy")
    @Link("http://82.142.167.37:4879/swagger-ui/index.html#/")
    @Feature("person-controller")
    @Description("Проверка API метода GET")
    public void getUserByNonExistentId() {
        usersAdapter.getUserByNonExistentId(createdUserId);
    }

    // GET User/{userId}
    @Test(dependsOnMethods = "createUser",
            description = "Нарушение контракта GET /user/{userId}",
            testName = "API: GET /user/{userId}: нарушение контракта")
    @Owner("Zheltikov Vasiliy")
    @Link("http://82.142.167.37:4879/swagger-ui/index.html#/")
    @Feature("person-controller")
    @Description("Проверка API метода GET: выполнение запроса с неверным методом")
    public void getUserWithIncorrectMethod() {
        usersAdapter.getUserWithIncorrectMethod(createdUserId);
    }

    // PUT User/{userId}
    @Test(dependsOnMethods = "createUser",
            description = "Проверка изменения данных по ранее созданному пользователю",
            testName = "API: PUT /user/{userId}")
    @Owner("Zheltikov Vasiliy")
    @Link("http://82.142.167.37:4879/swagger-ui/index.html#/")
    @Feature("person-controller")
    @Description("Проверка API метода PUT")
    public void changeUserData() {
        Faker faker = new Faker();
        UserRequest userRequest = UserRequest.builder()
                .id(createdUserId)
                .firstName(faker.name().firstName())
                .secondName(faker.name().lastName())
                .age(faker.number().numberBetween(18, 65))
                .sex(faker.demographic().sex().toUpperCase())
                .money(faker.number().randomDouble(2, 0, 99999))
                .build();
        UserResponse userResponse = usersAdapter.changeUserData(userRequest, userRequest.getId());
        softAssert.assertEquals(userResponse.getId(),
                userRequest.getId(),
                "Значение параметра id не соответствует ожидаемому");
        softAssert.assertEquals(userResponse.getFirstName(),
                userRequest.getFirstName(),
                "Значение параметра firstName не соответствует ожидаемому");
        softAssert.assertEquals(userResponse.getSecondName(),
                userRequest.getSecondName(),
                "Значение параметра secondName не соответствует ожидаемому");
        softAssert.assertEquals(userResponse.getAge(),
                userRequest.getAge(),
                "Значение параметра age не соответствует ожидаемому");
        softAssert.assertEquals(userResponse.getSex(),
                userRequest.getSex(),
                "Значение параметра sex не соответствует ожидаемому");
        softAssert.assertEquals(userResponse.getMoney(),
                userRequest.getMoney(),
                "Значение параметра money не соответствует ожидаемому");
        softAssert.assertAll();

    }

    // PUT User/{userId}
    @Test(dataProvider = "Negative user data",
            dependsOnMethods = "createUser",
            description = "Нарушение контракта метода PUT /user",
            testName = "API: PUT /user: нарушение контракта")
    @Owner("Zheltikov Vasiliy")
    @Link("http://82.142.167.37:4879/swagger-ui/index.html#/")
    @Feature("person-controller")
    @Description("Проверка API метода PUT: не переданы обязательные параметры, неверное значение параметра sex")
    public void changeUserNegativeParams(
            String firstName,
            String secondName,
            Integer age,
            String sex,
            Double money) {
        UserRequest userRequest = UserRequest.builder()
                .id(createdUserId)
                .firstName(firstName)
                .secondName(secondName)
                .age(age)
                .sex(sex)
                .money(money)
                .build();
        usersAdapter.changeUserWithIncorrectData(userRequest, createdUserId);
        // баг: обновляются данные по user'у, если не передать параметр sex (обновляется на FEMALE), тест падает
    }

    // PUT User/{userId}
    @Test(dependsOnMethods = {"createUser", "deleteUserById"},
            description = "Попытка изменения информации по несуществующему пользователю",
            testName = "API: PUT /user: userId не существует")
    @Owner("Zheltikov Vasiliy")
    @Link("http://82.142.167.37:4879/swagger-ui/index.html#/")
    @Feature("person-controller")
    @Description("Проверка API метода PUT")
    public void changeUserWithNonExistentId() {
        Faker faker = new Faker();
        UserRequest userRequest = UserRequest.builder()
                .id(createdUserId)
                .firstName(faker.name().firstName())
                .secondName(faker.name().lastName())
                .age(faker.number().numberBetween(18, 65))
                .sex(faker.demographic().sex().toUpperCase())
                .money(faker.number().randomDouble(2, 0, 99999))
                .build();
        usersAdapter.changeUserWithNonExistentId(userRequest, createdUserId);
    }

    // DELETE User/{userId}
    @Test(dependsOnMethods = "createUser",
            description = "Проверка удаления ранее созданного пользователя",
            testName = "API: DELETE /user/{userId}")
    @Owner("Zheltikov Vasiliy")
    @Link("http://82.142.167.37:4879/swagger-ui/index.html#/")
    @Feature("person-controller")
    @Description("Проверка API метода DELETE")
    public void deleteUserById() {
        usersAdapter.deleteUserById(createdUserId);
    }

    // DELETE User/{userId}
    @Test(dependsOnMethods = "createUser",
            description = "Попытка удаления несуществующего пользователя",
            testName = "API: DELETE /user/{userId}: userId не существует")
    @Owner("Zheltikov Vasiliy")
    @Link("http://82.142.167.37:4879/swagger-ui/index.html#/")
    @Feature("person-controller")
    @Description("Проверка API метода DELETE")
    public void deleteUserByNonExistentId() {
        usersAdapter.deleteUserById(createdUserId);
        usersAdapter.deleteUserByNonExistentId(createdUserId);
    }

    // POST /user/{userId}/buy/{carId}
    @Test(dependsOnMethods = "createUser",
            description = "Покупка автомобиля пользователем",
            testName = "API: POST /user/{userId}/buyCar/{carId}")
    @Owner("Zheltikov Vasiliy")
    @Link("http://82.142.167.37:4879/swagger-ui/index.html#/")
    @Feature("person-controller")
    @Description("Проверка покупки автомобиля (user.amount = car.price")
    public void userBuyCarAmountEqualPrice() {
        car.setPrice(createdUserMoney); // car.price = user.amount
        Car carResponse = carAdapter.createCar(car); // создаём автомобиль (car.price = user.amount)
        Integer carId = carResponse.getId();
        UserResponse userResponse = usersAdapter.buyOrSellCarByUserIdCarId(createdUserId, carId, "buy");
        assertEquals(userResponse.getMoney(),
                0,
                "На счету пользователя сумма, отличная от 0");
    }

    // POST /user/{userId}/sell/{carId}
    @Test(dependsOnMethods = "createUser",
            description = "Продажа автомобиля пользователем",
            testName = "API: POST /user/{userId}/sellCar/{carId}")
    @Owner("Zheltikov Vasiliy")
    @Link("http://82.142.167.37:4879/swagger-ui/index.html#/")
    @Feature("person-controller")
    @Description("Проверка продажи автомобиля")
    public void userSellsCar() {
        car.setPrice(faker.number().randomDouble(2,2,createdUserMoney.intValue()) - 1);
        Car carResponse = carAdapter.createCar(car); // создаём автомобиль
        Integer carId = carResponse.getId();
        usersAdapter.buyOrSellCarByUserIdCarId(createdUserId, carId, "buy"); // чтобы продать что-то ненужное
        // надо сначала купить что-то ненужное!
        UserResponse userResponse = usersAdapter.buyOrSellCarByUserIdCarId(createdUserId, carId, "sell");
        assertEquals(userResponse.getMoney(),
                userRequest.getMoney(), // купил и продал - значение счёта после продажи = значению счёта до покупки
                "На счету пользователя неверная сумма после продажи авто.");
    }

    // POST /user/{userId}/buy/{carId}
    @Test(dependsOnMethods = "createUser",
            description = "Попытка покупки автомобиля пользователем при недостаточном кол-ве средств",
            testName = "API: POST /user/{userId}/buyCar/{carId}")
    @Owner("Zheltikov Vasiliy")
    @Link("http://82.142.167.37:4879/swagger-ui/index.html#/")
    @Feature("person-controller")
    @Description("Проверка покупки автомобиля (user.amount < car.price")
    public void userBuyCarAmountLessPrice() {
        car.setPrice((createdUserMoney * 100 + 1) / 100); // car.price больше user.amount на 0.01
        Car carResponse = carAdapter.createCar(car); // создаём автомобиль (car.price = user.amount)
        Integer carId = carResponse.getId();
        UserResponse userResponse = usersAdapter.buyCarNotEnoughMoney(createdUserId, carId, "buy");
        assertEquals(userResponse.getMoney(),
                createdUserMoney,
                "Значение user.amount изменилось");
    }
}

