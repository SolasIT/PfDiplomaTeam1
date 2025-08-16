package adapters;

import dto.api.users.rq.UserRequest;
import dto.api.users.rs.UserResponse;

import static org.hamcrest.Matchers.*;

public class UsersAdapter extends BaseAPI {

    AuthAPI authAPI = new AuthAPI();

    // POST /user
    public UserResponse createUser(UserRequest user, Integer statusCode) {
        return spec
                .removeHeader("Authorization") // для избежания дублирующегося header'а Authorization
                .header("Authorization", authAPI.getToken())
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
    public void createUserIncorrectData(UserRequest user, Integer statusCode) {
        spec
                .removeHeader("Authorization") // для избежания дублирующегося header'а Authorization
                .header("Authorization", authAPI.getToken())
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
    public void createUserWrongMethod(UserRequest user, Integer statusCode) {
        spec
                .removeHeader("Authorization") // для избежания дублирующегося header'а Authorization
                .header("Authorization", authAPI.getToken())
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
    public UserResponse getUserById(Integer id, Integer statusCode) {
        spec.body(""); // в GET не передаётся тело запроса
        return spec
                .removeHeader("Authorization")
                .header("Authorization", authAPI.getToken())
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
    public void getUserByIdIncorrectData(Integer id, Integer statusCode) {
        spec.body(""); // в GET не передаётся тело запроса
        spec
                .removeHeader("Authorization")
                .header("Authorization", authAPI.getToken())
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
    public void getUserByIdWrongMethod(Integer id, Integer statusCode) {
        spec.body(""); // в GET не передаётся тело запроса
        spec
                .removeHeader("Authorization")
                .header("Authorization", authAPI.getToken())
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
    public UserResponse changeUserData(UserRequest user, Integer id, Integer statusCode) {
        return spec
                .removeHeader("Authorization") // для избежания дублирующегося header'а Authorization
                .header("Authorization", authAPI.getToken())
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
    public void changeUserDataIncorrect(UserRequest user, Integer id, Integer statusCode) {
        spec
                .removeHeader("Authorization") // для избежания дублирующегося header'а Authorization
                .header("Authorization", authAPI.getToken())
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
    public void deleteUserById(Integer id, Integer statusCode) {
        spec.body("");
        spec
                .removeHeader("Authorization")
                .header("Authorization", authAPI.getToken())
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
    public UserResponse buyOrSellCarByUserIdCarId(Integer userId, Integer carId, String option, Integer statusCode) {
        spec.body("");
        return spec
                .removeHeader("Authorization")
                .header("Authorization", authAPI.getToken())
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
    public void buyOrSellCarByUserIdCarIdIncorrect(String userId, String carId, String option, Integer statusCode) {
        if (!userId.isEmpty()) { // если userId не пустая строка
            userId += "/";
        }
        spec.body("");
        spec
                .removeHeader("Authorization")
                .header("Authorization", authAPI.getToken())
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
    public UserResponse[] getUsers(Integer statusCode) {
        spec.body("");
        return
                spec
                        .removeHeader("Authorization")
                        .header("Authorization", authAPI.getToken())
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
