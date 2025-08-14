package adapters;

import dto.api.users.rq.UserRequest;
import dto.api.users.rs.UserResponse;

import static org.hamcrest.Matchers.blankOrNullString;

public class UsersAdapter extends BaseAPI {

    AuthAPI authAPI = new AuthAPI();

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
}
