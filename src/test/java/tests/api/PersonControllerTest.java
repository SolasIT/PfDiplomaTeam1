package tests.api;

import adapters.UsersAdapter;
import com.github.javafaker.Faker;
import dto.api.users.rq.UserRequest;
import dto.api.users.rs.UserResponse;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Slf4j
public class PersonControllerTest {

    SoftAssert softAssert = new SoftAssert();
    UsersAdapter usersAdapter = new UsersAdapter();
    Integer createdUserId,
            createdUserAge;
    String createdUserFirstName,
            createdUserSecondName,
            createdUserSex;
    Double createdUserMoney;

    @Test
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
        softAssert.assertEquals(userRequest.getFirstName(),
                userResponse.getFirstName(),
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
        createdUserId = userResponse.getId();
        createdUserFirstName = userResponse.getFirstName();
        createdUserSecondName = userResponse.getSecondName();
        createdUserAge = userResponse.getAge();
        createdUserSex = userResponse.getSex();
        createdUserMoney = userResponse.getMoney();
    }

    @Test (dependsOnMethods = "createUser")
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
}
