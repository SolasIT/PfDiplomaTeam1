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
    String user_id;

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
        user_id = Integer.toString(userResponse.getId());
    }

    @Test (dependsOnMethods = "createUser")
    public void getUserById() {
        UserResponse userResponse = usersAdapter.getUserById(user_id);
    }
}
