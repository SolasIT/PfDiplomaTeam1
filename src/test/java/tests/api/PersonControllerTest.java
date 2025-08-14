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
            description = "Проверка удаления несуществующего пользователя",
            testName = "API: DELETE /user/{userId}: userId не существует")
    @Owner("Zheltikov Vasiliy")
    @Link("http://82.142.167.37:4879/swagger-ui/index.html#/")
    @Feature("person-controller")
    @Description("Проверка API метода DELETE")
    public void deleteUserByNonexistentId() {
        usersAdapter.deleteUserById(createdUserId);
        usersAdapter.deleteUserByNonExistentId(createdUserId);
    }
}
