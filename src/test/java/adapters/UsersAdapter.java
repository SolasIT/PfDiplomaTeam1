package adapters;

import dto.api.users.rq.UserRequest;
import dto.api.users.rs.UserResponse;

import static org.hamcrest.Matchers.blankOrNullString;

public class UsersAdapter extends BaseAPI {

    AuthAPI authAPI = new AuthAPI();

    // POST /user
    public UserResponse createUser(UserRequest user) {
        return spec
                .removeHeader("Authorization") // для избежания дублирующегося header'а Authorization
                .header("Authorization", authAPI.getToken())
                .body(gson.toJson(user))
                .log().all()
                .when()
                .post(BASE_URI + "/user")
                .then()
                .log().all()
                .statusCode(201)
                .extract()
                .as(UserResponse.class);
    }

    // POST /user
    public void createUserWithIncorrectData(UserRequest user) {
        spec
                .removeHeader("Authorization") // для избежания дублирующегося header'а Authorization
                .header("Authorization", authAPI.getToken())
                .body(gson.toJson(user))
                .log().all()
                .when()
                .post(BASE_URI + "/user")
                .then()
                .log().all()
                .statusCode(400)
                .body(blankOrNullString());
    }

    // POST /user
    public void createUserWithIncorrectMethod(UserRequest user) {
        spec
                .removeHeader("Authorization") // для избежания дублирующегося header'а Authorization
                .header("Authorization", authAPI.getToken())
                .log().all()
                .body(gson.toJson(user))
                .when()
                .patch(BASE_URI + "/user")
                .then()
                .log().all()
                .statusCode(405)
                .body(blankOrNullString());
    }

    // GET /user/{userId}
    public UserResponse getUserById(Integer id) {
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
                .statusCode(200)
                .extract()
                .as(UserResponse.class);
    }

    // GET /user/{userId}
    public void getUserByNonExistentId(Integer id) {
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
                .statusCode(204)
                .body(blankOrNullString());
    }

    // GET /user/{userId}
    public void getUserWithIncorrectMethod(Integer id) {
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
                .statusCode(405)
                .body(blankOrNullString());
    }

    // PUT /user/{userId}
    public UserResponse changeUserData(UserRequest user, Integer id) {
        return spec
                .removeHeader("Authorization") // для избежания дублирующегося header'а Authorization
                .header("Authorization", authAPI.getToken())
                .body(gson.toJson(user))
                .log().all()
                .when()
                .put(BASE_URI + "/user/" + id)
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .as(UserResponse.class);
    }

    // PUT /user/{userId}
    public void changeUserWithIncorrectData(UserRequest user, Integer id) {
        spec
                .removeHeader("Authorization") // для избежания дублирующегося header'а Authorization
                .header("Authorization", authAPI.getToken())
                .body(gson.toJson(user))
                .log().all()
                .when()
                .put(BASE_URI + "/user/" + id)
                .then()
                .log().all()
                .statusCode(400)
                .body(blankOrNullString());
    }

    // PUT /user/{userId}
    public void changeUserWithNonExistentId(UserRequest user, Integer id) {
        spec
                .removeHeader("Authorization") // для избежания дублирующегося header'а Authorization
                .header("Authorization", authAPI.getToken())
                .body(gson.toJson(user))
                .log().all()
                .when()
                .put(BASE_URI + "/user/" + id)
                .then()
                .log().all()
                .statusCode(404)
                .body(blankOrNullString());
    }

    // DELETE /user/{userId}
    public void deleteUserById(Integer id) {
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
                .statusCode(204);
    }

    // PUT /user/{userId}
    public void deleteUserByNonExistentId(Integer id) {
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
                .statusCode(404)
                .body(blankOrNullString());
    }

    // POST /user/{userId}/sellCar/{carId}
    // POST /user/{userId}/buyCar/{carId}
    /*

    1. Создать пользователя с user.amount = x
    2. Создать автомобиль с car.price = x
    3. Передать userId, carId в теле запроса
    4. Передать опцию (buy или sell) в URL запроса (сначала купить!)
    5. Проверить статус-код:
    6. Проверить, что user.amount при покупке/продаже стал меньше/больше на car.price
    406 - не хватает денег на покупку
    404 - не найден пользователь или машина или машина
    200 - куплена / продана
     */

    public UserResponse buyOrSellCarByUserIdCarId(Integer userId, Integer carId, String option) {
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
                .statusCode(200)
                .extract()
                .as(UserResponse.class);
    }

    public UserResponse buyCarNotEnoughMoney(Integer userId, Integer carId, String option) {
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
                .statusCode(406)
                .extract()
                .as(UserResponse.class);
    }
}
