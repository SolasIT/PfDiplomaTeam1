package tests.api;

import adapters.UsersAdapter;
import com.github.javafaker.Faker;
import dto.api.user.rq.User;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Slf4j
public class PersonControllerTest {

    SoftAssert softAssert = new SoftAssert();

    @Test
    public void createUser() {
        Faker faker = new Faker();
        UsersAdapter usersAdapter = new UsersAdapter();
        User user = User.builder()
                .firstName(faker.name().firstName())
                .secondName(faker.name().lastName())
                .age(faker.number().numberBetween(18, 65))
                .sex(faker.demographic().sex())
                .money(faker.number().numberBetween(0, 999999999))
                .build();
        User rs = usersAdapter.createUser(user);
        softAssert.assertEquals(user.getFirstName(),
                rs.getFirstName(),
                "Значение параметра firstName не соответствует ожидаемому");
        softAssert.assertEquals(user.getSecondName(),
                rs.getSecondName(),
                "Значение параметра secondName не соответствует ожидаемому");
        softAssert.assertEquals(user.getAge(),
                rs.getAge(),
                "Значение параметра age не соответствует ожидаемому");
        softAssert.assertEquals(user.getSex(),
                rs.getSex(),
                "Значение параметра sex не соответствует ожидаемому");
        softAssert.assertEquals(user.getMoney(),
                rs.getMoney(),
                "Значение параметра money не соответствует ожидаемому");
    }
}
