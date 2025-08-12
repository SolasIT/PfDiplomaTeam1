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
                .sex(faker.demographic().sex().toUpperCase())
                .money(faker.number().randomDouble(2, 0, 99999))
                .build();
        User rs = usersAdapter.createUserPositive(user);
        softAssert.assertEquals(user.getFirstName(),
                rs.getFirstName(),
                "Значение параметра firstName не соответствует ожидаемому");
        softAssert.assertEquals(rs.getSecondName(),
                user.getSecondName(),
                "Значение параметра secondName не соответствует ожидаемому");
        softAssert.assertEquals(rs.getAge(),
                user.getAge(),
                "Значение параметра age не соответствует ожидаемому");
        softAssert.assertEquals(rs.getSex(),
                user.getSex(),
                "Значение параметра sex не соответствует ожидаемому");
        softAssert.assertEquals(rs.getMoney(),
                user.getMoney(),
                "Значение параметра money не соответствует ожидаемому");
    }
}
