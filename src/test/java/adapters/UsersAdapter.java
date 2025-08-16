package adapters;

import dto.api.users.rq.UserRequest;
import dto.api.users.rs.UserResponse;
import io.qameta.allure.Step;

import io.restassured.specification.FilterableRequestSpecification;
import static org.hamcrest.Matchers.*;

public class UsersAdapter extends BaseAPI {

    // POST /user
    @Step("Создание пользователя")
    public UserResponse createUser(UserRequest user, Integer statusCode) {
        return getSpec()
                .removeHeader("Authorization") // для избежания дублирующегося header'а Authorization
                .header("Authorization", getToken())
                .body(gson.toJson(user))
                .log().all()
                .when()
                .post(BASE_URI + "/user")
                .then()
                .log().all()
                .statusCode(statusCode)
                .extract()
                .as(UserResponse.class);
    }

    // POST /user
    @Step("Создание пользователя с некорректными данными")
    public void createUserIncorrectData(UserRequest user, Integer statusCode) {
        getSpec()
                .removeHeader("Authorization") // для избежания дублирующегося header'а Authorization
                .header("Authorization", getToken())
                .body(gson.toJson(user))
                .log().all()
                .when()
                .post(BASE_URI + "/user")
                .then()
                .log().all()
                .statusCode(statusCode)
                .body(blankOrNullString()); // проверка, что в теле ответа нет данных
    }

    // POST /user
    @Step("Создание пользователя с неверным методом запроса")
    public void createUserWrongMethod(UserRequest user, Integer statusCode) {
        getSpec()
                .removeHeader("Authorization") // для избежания дублирующегося header'а Authorization
                .header("Authorization", getToken())
                .body(gson.toJson(user))
                .log().all()
                .when()
                .patch(BASE_URI + "/user") // используется неподдерживаемый метод
                .then()
                .log().all()
                .statusCode(statusCode)
                .body(blankOrNullString()); // проверка, что в теле ответа нет данных
    }

    // GET /user/{userId}
    @Step("Получение пользователя по {userId}")
    public UserResponse getUserById(Integer id, Integer statusCode) {
        FilterableRequestSpecification  spec = getSpec();
        spec.body(""); // в GET не передаётся тело запроса
        return spec
                .removeHeader("Authorization")
                .header("Authorization", getToken())
                .request()
                .log().all()
                .when()
                .get(BASE_URI + "/user/" + id)
                .then()
                .log().all()
                .statusCode(statusCode)
                .extract()
                .as(UserResponse.class);
    }

    // GET /user/{userId}
    @Step("Получение пользователя по {userId} с некорректными данными")
    public void getUserByIdIncorrectData(Integer id, Integer statusCode) {
        FilterableRequestSpecification  spec = getSpec();
        spec.body(""); // в GET не передаётся тело запроса
        spec
                .removeHeader("Authorization")
                .header("Authorization", getToken())
                .request()
                .log().all()
                .when()
                .get(BASE_URI + "/user/" + id)
                .then()
                .log().all()
                .statusCode(statusCode)
                .body(blankOrNullString()); // проверка, что в теле ответа нет данных
    }

    // GET /user/{userId}
    @Step("Получение пользователя по {userId} с неверным методом запроса")
    public void getUserByIdWrongMethod(Integer id, Integer statusCode) {
        FilterableRequestSpecification  spec = getSpec();
        spec.body(""); // в GET не передаётся тело запроса
        spec
                .removeHeader("Authorization")
                .header("Authorization", getToken())
                .request()
                .log().all()
                .when()
                .patch(BASE_URI + "/user/" + id)
                .then()
                .log().all()
                .statusCode(statusCode)
                .body(blankOrNullString()); // проверка, что в теле ответа нет данных
    }

    // PUT /user/{userId}
    @Step("Изменение данных пользователя по {userId}")
    public UserResponse changeUserData(UserRequest user, Integer id, Integer statusCode) {

        return getSpec()
                .removeHeader("Authorization") // для избежания дублирующегося header'а Authorization
                .header("Authorization", getToken())
                .body(gson.toJson(user))
                .log().all()
                .when()
                .put(BASE_URI + "/user/" + id)
                .then()
                .log().all()
                .statusCode(statusCode)
                .extract()
                .as(UserResponse.class);
    }

    // PUT /user/{userId}
    @Step("Изменение данных пользователя по {userId} на некорректные")
    public void changeUserDataIncorrect(UserRequest user, Integer id, Integer statusCode) {
        getSpec()
                .removeHeader("Authorization") // для избежания дублирующегося header'а Authorization
                .header("Authorization", getToken())
                .body(gson.toJson(user))
                .log().all()
                .when()
                .put(BASE_URI + "/user/" + id)
                .then()
                .log().all()
                .statusCode(statusCode)
                .body(blankOrNullString()); // проверка, что в теле ответа нет данных
    }

    // DELETE /user/{userId}
    @Step("Удаление пользователя по {userId}")
    public void deleteUserById(Integer id, Integer statusCode) {
        FilterableRequestSpecification  spec = getSpec();
        spec.body("");
        spec
                .removeHeader("Authorization")
                .header("Authorization", getToken())
                .request()
                .log().all()
                .when()
                .delete(BASE_URI + "/user/" + id)
                .then()
                .log().all()
                .statusCode(statusCode)
                .body(blankOrNullString()); // проверка, что в теле ответа нет данных
    }

    // POST /user/{userId}/sellCar/{carId}
    // POST /user/{userId}/buyCar/{carId}
    @Step("Покупка/продажа авто {carId} пользователем {userId}")
    public UserResponse buyOrSellCarByUserIdCarId(Integer userId, Integer carId, String option, Integer statusCode) {
        FilterableRequestSpecification  spec = getSpec();
        spec.body("");
        return spec
                .removeHeader("Authorization")
                .header("Authorization", getToken())
                .request()
                .log().all()
                .when()
                .post(String.format("%s/user/%s/%sCar/%s", BASE_URI, userId, option.toLowerCase(), carId))
                .then()
                .log().all()
                .statusCode(statusCode)
                .extract()
                .as(UserResponse.class);
    }

    // POST /user/{userId}/buyCar/{carId}
    // POST /user/{userId}/sellCar/{carId}
    @Step("Покупка/продажа авто пользователем с некорректными данными {userId}, {carId}")
    public void buyOrSellCarByUserIdCarIdIncorrect(String userId, String carId, String option, Integer statusCode) {
        FilterableRequestSpecification  spec = getSpec();
        if (!userId.isEmpty()) { // если userId не пустая строка
            userId += "/";
        }
        spec.body("");
        spec
                .removeHeader("Authorization")
                .header("Authorization", getToken())
                .request()
                .log().all()
                .when()
                .post(BASE_URI + "/" + userId + option.toLowerCase() + "Car/" + carId)
                .then()
                .log().all()
                .statusCode(statusCode)
                .body(blankOrNullString()); // проверка, что в теле ответа нет данных
    }

    // POST /user/{userId}/buyCar/{carId}
    // POST /user/{userId}/sellCar/{carId}
    @Step("Покупка/продажа авто {carId} пользователем {userId} с неверным методом запроса")
    public void buyOrSellCarByUserIdCarIdWrongMethod(Integer userId, Integer carId, String option, Integer statusCode) {
        spec.body("");
        spec
                .removeHeader("Authorization")
                .header("Authorization", authAPI.getToken())
                .request()
                .log().all()
                .when()
                .put(BASE_URI + "/" + userId + "/" + option.toLowerCase() + "Car/" + carId)
                .then()
                .log().all()
                .statusCode(statusCode)
                .body(blankOrNullString()); // проверка, что в теле ответа нет данных
    }

    // GET /users
    @Step("Получение данных по всем пользователям")
    public UserResponse[] getUsers(Integer statusCode) {
        FilterableRequestSpecification  spec = getSpec();
        spec.body("");
        return
                spec
                .removeHeader("Authorization")
                .header("Authorization", getToken())
                .request()
                .log().all()
                .when()
                .get(BASE_URI + "/users")
                .then()
                .log().all()
                .statusCode(statusCode)
                .extract()
                .body().as(UserResponse[].class);
    }

    // GET /users
    @Step("Получение данных по всем пользователям с неверным методом запроса")
    public void getUsersWrongMethod(Integer statusCode) {
        spec.body("");
        spec
                .removeHeader("Authorization")
                .header("Authorization", authAPI.getToken())
                .request()
                .log().all()
                .when()
                .put(BASE_URI + "/users")
                .then()
                .log().all()
                .statusCode(statusCode)
                .body(blankOrNullString()); // проверка, что в теле ответа нет данных
    }
}
