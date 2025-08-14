package tests.api;

import adapters.BaseAPI;
import adapters.UsersAdapter;
import com.github.javafaker.Faker;
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

@Slf4j
public class PersonControllerTest extends BaseAPI {

    SoftAssert softAssert = new SoftAssert();
    UsersAdapter usersAdapter = new UsersAdapter();
    Integer createdUserId,
            createdUserAge;
    String createdUserFirstName,
            createdUserSecondName,
            createdUserSex;
    Double createdUserMoney;

    @DataProvider(name = "Negative POST user data")
    public Object[][] negativeUserData() {
        return new Object[][]{
                {null,"Surname", 21, "MALE", 1000.00}, // не передан обязательный параметр firstName
                {"Name", null, 22, "FEMALE", 2000.00}, // не передан обязательный параметр secondName
                {"Name", "Surname", null, "MALE", 3000.00}, // не передан обязательный параметр age
                {"Name", "Surname", 24, null, 4000.00}, // не передан обязательный параметр sex
                {"Name", "Surname", 25, "MALE", null}, // не передан обязательный параметр money
                {"Name", "Surname", 26, "TEAPOTE", 6000.00}, // неверное значение параметра sex
        };
    }

    @Test(description = "Проверка создания пользователя",
            testName = "API: POST /user")
    @Owner("Zheltikov Vasiliy")
    @Link("http://82.142.167.37:4879/swagger-ui/index.html#/")
    @Feature("person-controller")
    @Description("Проверка API метода POST")
    public void createUser() {
        Faker faker = new Faker();
        UserRequest userRequest = UserRequest.builder()
                .firstName(faker.name().firstName())
                .secondName(faker.name().lastName())
                .age(faker.number().numberBetween(18, 65))
                .sex(faker.demographic().sex().toUpperCase())
                .money(faker.number().randomDouble(2, 0, 99999))
                .build();
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

    @Test(dataProvider = "Negative POST user data",
            description = "Нарушение контракта метода POST /user",
            testName = "API: POST /user: нарушение контракта")
    @Owner("Zheltikov Vasiliy")
    @Link("http://82.142.167.37:4879/swagger-ui/index.html#/")
    @Feature("person-controller")
    @Description("Проверка API метода POST: не переданы обязательные параметры, добавлен лишний параметр")
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

    @Test(dependsOnMethods = {"createUser","deleteUserByNonExistentId"},
            description = "Попытка получения информации по несуществующему пользователю",
            testName = "API: GET /user/{userId}: userId не существует")
    @Owner("Zheltikov Vasiliy")
    @Link("http://82.142.167.37:4879/swagger-ui/index.html#/")
    @Feature("person-controller")
    @Description("Проверка API метода GET")
    public void getUserByNonExistentId(){
        usersAdapter.getUserByNonExistentId(createdUserId);
    }

    @Test(dependsOnMethods = "createUser",
            description = "Нарушение контракта GET /user/{userId}",
            testName = "API: GET /user/{userId}: нарушение контракта")
    @Owner("Zheltikov Vasiliy")
    @Link("http://82.142.167.37:4879/swagger-ui/index.html#/")
    @Feature("person-controller")
    @Description("Проверка API метода GET: выполнение запроса с неверным методом")
    public void getUserWithoutId(){
        usersAdapter.getUserWithIncorrectMethod(createdUserId);
    }

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
}
