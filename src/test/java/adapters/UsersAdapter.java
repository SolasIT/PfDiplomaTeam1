package adapters;

import dto.api.users.rq.UserRequest;
import dto.api.users.rs.UserResponse;

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
                .extract()
                .as(UserResponse.class);
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
                .extract()
                .as(UserResponse.class);
    }
}
