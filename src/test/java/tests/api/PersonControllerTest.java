package tests.api;

import adapters.CarAdapter;
import adapters.UsersAdapter;
import com.github.javafaker.Faker;
import db.DBRequests;
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

import java.sql.SQLException;

import static org.testng.Assert.assertEquals;

@Slf4j
public class PersonControllerTest extends DBRequests {

    // HTTP StatusCodes
    private final Integer OK_STATUS_CODE = 200,
            SUCCESS_CREATED_STATUS_CODE = 201,
            ACCEPTED_STATUS_CODE = 202,
            NO_CONTENT_STATUS_CODE = 204,
            BAD_REQUEST_STATUS_CODE = 400,
            NOT_FOUND_STATUS_CODE = 404,
            METHOD_NOT_ALLOWED_STATUS_CODE = 405,
            NOT_ACCEPTABLE_STATUS_CODE = 406;

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

    @DataProvider(name = "Missing values user buy or sell car data")
    public Object[][] MissingUserBuyOrSellCarData() {
        Integer userId = usersAdapter.createUser(userRequest, SUCCESS_CREATED_STATUS_CODE).getId();
        Integer carId = carAdapter.createCar(car).getId();
        return new Object[][]{
                {"", Integer.toString(carId), "buy"}, // не передан обязательный параметр userId
                {Integer.toString(userId), "", "sell"} // не передан обязательный параметр carId
        };
    }

    @DataProvider(name = "Non existent values user buy or sell car data")
    public Object[][] NonExistentUserBuyOrSellCarData() {
        Integer userId = usersAdapter.createUser(userRequest, SUCCESS_CREATED_STATUS_CODE).getId();
        Integer carId = carAdapter.createCar(car).getId();
        return new Object[][]{
                {userId + 1234, carId, "buy"}, // несуществующий параметр userId (response status 500)
                {userId, carId + 1234, "sell"} // несуществующий параметр carId (response status 404)
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
        // создание пользователя POST
        UserResponse userResponse = usersAdapter.createUser(userRequest, SUCCESS_CREATED_STATUS_CODE);
        // блок проверок на совпадение значений параметров в response и в request
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
        usersAdapter.createUserIncorrectData(userRequest, BAD_REQUEST_STATUS_CODE);
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
        usersAdapter.createUserWrongMethod(userRequest, METHOD_NOT_ALLOWED_STATUS_CODE);
    }

    // GET User/{userId}
    @Test(description = "Проверка получения информации по ранее созданному пользователю",
            testName = "API: GET /user/{userId}")
    @Owner("Zheltikov Vasiliy")
    @Link("http://82.142.167.37:4879/swagger-ui/index.html#/")
    @Feature("person-controller")
    @Description("Проверка API метода GET")
    public void getUserById() {
        createUser();
        UserResponse userResponse = usersAdapter.getUserById(createdUserId, OK_STATUS_CODE);
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
    @Test(dependsOnMethods = {"createUser", "deleteUserById"},
            description = "Попытка получения информации по несуществующему пользователю",
            testName = "API: GET /user/{userId}: userId не существует")
    @Owner("Zheltikov Vasiliy")
    @Link("http://82.142.167.37:4879/swagger-ui/index.html#/")
    @Feature("person-controller")
    @Description("Проверка API метода GET")
    public void getUserByNonExistentId() {
        usersAdapter.getUserByIdIncorrectData(createdUserId, NO_CONTENT_STATUS_CODE);
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
        usersAdapter.getUserByIdWrongMethod(createdUserId, METHOD_NOT_ALLOWED_STATUS_CODE);
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
        UserResponse userResponse = usersAdapter.changeUserData(userRequest, userRequest.getId(), ACCEPTED_STATUS_CODE);
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
        usersAdapter.changeUserDataIncorrect(userRequest, createdUserId, BAD_REQUEST_STATUS_CODE);
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
        usersAdapter.changeUserDataIncorrect(userRequest, createdUserId, NOT_FOUND_STATUS_CODE);
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
        usersAdapter.deleteUserById(createdUserId, NO_CONTENT_STATUS_CODE);
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
        createUser();
        usersAdapter.deleteUserById(createdUserId, NO_CONTENT_STATUS_CODE);
        usersAdapter.deleteUserById(createdUserId, NOT_FOUND_STATUS_CODE);
    }

    // POST /user/{userId}/buy/{carId}
    @Test(description = "Покупка автомобиля пользователем",
            testName = "API: POST /user/{userId}/buyCar/{carId}")
    @Owner("Zheltikov Vasiliy")
    @Link("http://82.142.167.37:4879/swagger-ui/index.html#/")
    @Feature("person-controller")
    @Description("Проверка покупки автомобиля (user.amount = car.price)")
    public void userBuyCarAmountEqualPrice() throws SQLException {
        createUser();
        car.setPrice(createdUserMoney); // car.price = user.amount
        Car carResponse = carAdapter.createCar(car); // создаём автомобиль (car.price = user.amount)
        Integer carId = carResponse.getId();
        UserResponse userResponse = usersAdapter.buyOrSellCarByUserIdCarId(
                createdUserId,
                carId,
                "buy",
                OK_STATUS_CODE);
        connect(); // подключение к БД
        // ищем запись в БД со связкой userId и carId
        Integer databaseEntry = checkUserOwnsPropertyByPropertyId(createdUserId, carId);
        softAssert.assertEquals(userResponse.getMoney(),
                0.0,
                "На счету пользователя сумма, отличная от 0");
        // проверяем значение carId из запроса к БД
        softAssert.assertEquals((int) databaseEntry, // найденное в БД кол-во записей
                1, // одна запись
                String.format("Для пользователя с id = %s отсутствует запись с carId = %s или их больше одной",
                        createdUserId, carId));
        close(); // закрытие подключения к БД
        softAssert.assertAll();
    }

    @Test(description = "Покупка того же автомобиля пользователем",
            testName = "API: POST /user/{userId}/buyCar/{carId}")
    @Owner("Zheltikov Vasiliy")
    @Link("http://82.142.167.37:4879/swagger-ui/index.html#/")
    @Feature("person-controller")
    @Description("Проверка покупки автомобиля (user.amount = car.price)")
    public void userBuyCarTwice() throws SQLException {
        UserResponse userResponse = usersAdapter.createUser(userRequest, SUCCESS_CREATED_STATUS_CODE);
        car.setPrice(createdUserMoney * 100 / 3 / 100);
        Integer carId = carAdapter.createCar(car).getId();
        for (int i = 0; i < 2; i++) {
            usersAdapter.buyOrSellCarByUserIdCarId(
                    createdUserId,
                    carId,
                    "buy",
                    OK_STATUS_CODE); // пользователь покупает автомобиль дважды
        }
        connect(); // подключение к БД
        // ищем запись в БД со связкой userId и carId
        Integer databaseEntry = checkUserOwnsPropertyByPropertyId(createdUserId, carId); // проверяем кол-во записей в БД
        // проверяем значение carId из запроса к БД
        softAssert.assertEquals((int) databaseEntry,
                1, // Одна запись = дублей нет
                String.format("Для пользователя с id = %s отсутствует запись с carId = %s или их больше одной",
                        createdUserId, carId));
        close(); // закрытие подключения к БД
        softAssert.assertAll();
    }

    // POST /user/{userId}/sell/{carId}
    @Test(description = "Продажа автомобиля пользователем",
            testName = "API: POST /user/{userId}/sellCar/{carId}")
    @Owner("Zheltikov Vasiliy")
    @Link("http://82.142.167.37:4879/swagger-ui/index.html#/")
    @Feature("person-controller")
    @Description("Проверка продажи автомобиля")
    public void userSellsCar() throws SQLException {
        createUser();
        car.setPrice(faker.number().randomDouble(2, 2, createdUserMoney.intValue()) - 1);
        Car carResponse = carAdapter.createCar(car); // создаём автомобиль
        Integer carId = carResponse.getId();
        usersAdapter.buyOrSellCarByUserIdCarId(createdUserId, carId, "buy", OK_STATUS_CODE);
        // чтобы продать что-то ненужное, надо сначала купить что-то ненужное!
        UserResponse userResponse = usersAdapter.buyOrSellCarByUserIdCarId(
                createdUserId,
                carId,
                "sell",
                OK_STATUS_CODE);
        connect(); // подключение к БД
        // ищем запись в БД со связкой userId и carId
        Integer databaseEntry = checkUserOwnsPropertyByPropertyId(createdUserId, carId);
        softAssert.assertEquals(userResponse.getMoney(),
                userRequest.getMoney(), // купил и продал - значение счёта после продажи = значению счёта до покупки
                "На счету пользователя неверная сумма после продажи авто.");
        // проверяем значение carId из запроса к БД
        softAssert.assertEquals((int) databaseEntry, // проверка кол-ва вернувшихся в запросе к БД строк
                0, // 0 возвращается, если записей нет
                String.format("Для пользователя с id = %s найдена минимум одна запись с carId = %s",
                        createdUserId, carId));
        // проверяем, что запись со связкой car.id + user.id не была найдена
        close(); // закрытие подключения к БД
        softAssert.assertAll();
    }

    // POST /user/{userId}/sell/{carId}
    @Test(dependsOnMethods = "createUser",
            description = "Попытка продажи автомобиля пользователем (авто не во владении пользователя)",
            testName = "API: POST /user/{userId}/sellCar/{carId}")
    @Owner("Zheltikov Vasiliy")
    @Link("http://82.142.167.37:4879/swagger-ui/index.html#/")
    @Feature("person-controller")
    @Description("Попытка продать автомобиль не в собственности пользователя")
    public void userSellsCarNotInOwn() {
        car.setPrice(faker.number().randomDouble(2, 2, createdUserMoney.intValue()) - 1);
        Car carResponse = carAdapter.createCar(car); // создаём автомобиль
        Integer carId = carResponse.getId();
        UserResponse userResponse = usersAdapter.buyOrSellCarByUserIdCarId(
                createdUserId,
                carId,
                "sell",
                OK_STATUS_CODE);
        assertEquals(userResponse.getMoney(),
                userRequest.getMoney(), // значение amount осталось неизменным
                "Сумма на счету у пользователя изменилась.");
    }

    // POST /user/{userId}/sell/{carId}
    @Test(description = "Попытка продажи автомобиля пользователем (авто во владении другого пользователя)",
            testName = "API: POST /user/{userId}/sellCar/{carId}")
    @Owner("Zheltikov Vasiliy")
    @Link("http://82.142.167.37:4879/swagger-ui/index.html#/")
    @Feature("person-controller")
    @Description("Попытка продать автомобиль другого пользователя")
    public void userSellsCarAnotherUserOwns() throws SQLException {
        createUser(); // создание пользователя
        Integer userOwnerCarId = createdUserId; // сохраняем userId будущего автовладельца
        car.setPrice(faker.number().randomDouble(2, 2, createdUserMoney.intValue()) - 1);
        Car carResponse = carAdapter.createCar(car); // создаём автомобиль
        Integer carId = carResponse.getId(); // сохраняем carId
        usersAdapter.buyOrSellCarByUserIdCarId( // записали carId на userOwnerCar
                userOwnerCarId,
                carId,
                "buy",
                OK_STATUS_CODE
        );
        createUser(); // создаём 2-го пользователя
        UserResponse userResponse = usersAdapter.buyOrSellCarByUserIdCarId( // 2-ой пользователь продаёт машину 1-го
                createdUserId,
                carId,
                "sell",
                OK_STATUS_CODE);
        connect(); // подключение к БД
        // ищем запись в БД со связкой userOwnerId и carId
        Integer databaseEntry = checkUserOwnsPropertyByPropertyId(userOwnerCarId, carId);
        softAssert.assertEquals(userResponse.getMoney(),
                userRequest.getMoney(), // значение amount осталось неизменным
                "Сумма на счету у пользователя изменилась.");
        // проверяем значение carId из запроса к БД
        softAssert.assertEquals((int) databaseEntry, // по userOwnerCar
                1, // должна найтись одна запись с carId (чужое авто не продано)
                String.format("Для пользователя с id = %s не найдена запись с carId = %s",
                        createdUserId, carId));
        close(); // закрытие подключения к БД
        softAssert.assertAll();
    }

    // POST /user/{userId}/buy/{carId}
    @Test(description = "Попытка покупки автомобиля пользователем при недостаточном кол-ве средств",
            testName = "API: POST /user/{userId}/buyCar/{carId}")
    @Owner("Zheltikov Vasiliy")
    @Link("http://82.142.167.37:4879/swagger-ui/index.html#/")
    @Feature("person-controller")
    @Description("Проверка покупки автомобиля (user.amount < car.price")
    public void userBuyCarAmountLessPrice() {
        createUser();
        car.setPrice((createdUserMoney * 100 + 1) / 100); // car.price больше user.amount на 0.01
        Car carResponse = carAdapter.createCar(car); // создаём автомобиль (car.price = user.amount)
        Integer carId = carResponse.getId();
        UserResponse userResponse = usersAdapter.buyOrSellCarByUserIdCarId(
                createdUserId,
                carId,
                "buy",
                NOT_ACCEPTABLE_STATUS_CODE);
        assertEquals(userResponse.getMoney(),
                createdUserMoney,
                "Сумма на счету у пользователя изменилась.");
    }

    // POST /user/{userId}/sell/{carId}
    // POST /user/{userId}/buy/{carId}
    @Test(dataProvider = "Missing values user buy or sell car data",
            dependsOnMethods = "createUser",
            description = "Нарушение контракта методов POST /user/{userId}/{buy/sell}Car/{carId}",
            testName = "API: POST /user/{userId}/{buy/sell}Car/{carId}: нарушение контракта")
    @Owner("Zheltikov Vasiliy")
    @Link("http://82.142.167.37:4879/swagger-ui/index.html#/")
    @Feature("person-controller")
    @Description("Проверка API метода POST: не переданы обязательные параметры userId, carId")
    public void userBuyOrSellCarMissRequiredValues(String userId, String carId, String option) {
        usersAdapter.buyOrSellCarByUserIdCarIdIncorrect(userId, carId, option, NOT_FOUND_STATUS_CODE);
    }

    // POST /user/{userId}/sell/{carId}
    // POST /user/{userId}/buy/{carId}
    @Test(dataProvider = "Non existent values user buy or sell car data",
            dependsOnMethods = "createUser",
            description = "Невалидные данные в запросе",
            testName = "Выполнение запроса POST с несуществующими значениями")
    @Owner("Zheltikov Vasiliy")
    @Link("http://82.142.167.37:4879/swagger-ui/index.html#/")
    @Feature("person-controller")
    @Description("Проверка API метода POST: переданы несуществующий параметры userId, carId")
    public void userBuyOrSellCarWithNonExistentValues(Integer userId, Integer carId, String option) {
        usersAdapter.buyOrSellCarByUserIdCarIdIncorrect(
                Integer.toString(userId),
                Integer.toString(carId),
                option,
                NOT_FOUND_STATUS_CODE);
    }

    // POST /user/{userId}/sell/{carId}
    // POST /user/{userId}/buy/{carId}
    @Test(description = "Нарушение контракта POST /user/{userId}",
            testName = "API: POST /user/{userId}/sellCar/{carId}: нарушение контракта")
    @Owner("Zheltikov Vasiliy")
    @Link("http://82.142.167.37:4879/swagger-ui/index.html#/")
    @Feature("person-controller")
    @Description("Проверка API метода POST: выполнение запроса с неверным методом")
    public void buyOrSellCarByUserIdCarIdWrongMethod() {
        createUser();
        car.setPrice(faker.number().randomDouble(2, 2, createdUserMoney.intValue()) - 1);
        Car carResponse = carAdapter.createCar(car); // создаём автомобиль
        Integer carId = carResponse.getId();
        usersAdapter.buyOrSellCarByUserIdCarIdWrongMethod(
                createdUserId,
                carId,
                "sell",
                METHOD_NOT_ALLOWED_STATUS_CODE);
        usersAdapter.buyOrSellCarByUserIdCarIdWrongMethod(
                createdUserId,
                carId,
                "buy",
                METHOD_NOT_ALLOWED_STATUS_CODE);
    }
    // Тест падает из-за того, что в ответе возвращается 404 ошибка, а не 405

    // GET /users
    @Test(description = "Запрос на получение всех пользователей",
            testName = "Получение всех пользователей")
    @Owner("Zheltikov Vasiliy")
    @Link("http://82.142.167.37:4879/swagger-ui/index.html#/")
    @Feature("person-controller")
    @Description("Проверка API метода GET")
    public void getAllUsers() {
        UserResponse[] users = usersAdapter.getUsers(OK_STATUS_CODE);
        for (UserResponse user : users) {
            softAssert.assertNotNull(user.getId(),
                    "У пользователя отсутствует параметр id");
            softAssert.assertNotNull(user.getFirstName(),
                    "У пользователя отсутствует параметр firstName");
            softAssert.assertNotNull(user.getSecondName(),
                    "У пользователя отсутствует параметр secondName");
            softAssert.assertNotNull(user.getAge(),
                    "У пользователя отсутствует параметр age");
            softAssert.assertNotNull(user.getSex(),
                    "У пользователя отсутствует параметр sex");
            softAssert.assertNotNull(user.getMoney(),
                    "У пользователя отсутствует параметр money");
        }
        softAssert.assertAll();
    }
    // GET /users
    @Test(description = "Нарушение контракта GET /users",
            testName = "API: GET /users: нарушение контракта")
    @Owner("Zheltikov Vasiliy")
    @Link("http://82.142.167.37:4879/swagger-ui/index.html#/")
    @Feature("person-controller")
    @Description("Проверка API метода GET: выполнение запроса с неверным методом")
    public void getAllUsersWrongMethod() {
        usersAdapter.getUsersWrongMethod(METHOD_NOT_ALLOWED_STATUS_CODE);
    }
}

