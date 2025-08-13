package adapters;

import dto.api.users.rq.UserRequest;
import dto.api.users.rs.UserResponse;

public class UsersAdapter extends BaseAPI {

    AuthAPI authAPI = new AuthAPI();

    public UserResponse createUser(UserRequest user) {
        return spec
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

    public UserResponse getUserById(String id) {
        return spec
                .header("Authorization", authAPI.getToken())
                .log().all()
                .when()
                .get(BASE_URI + "/user/" + id)
                .then()
                .log().all()
                .extract()
                .as(UserResponse.class);
    }
}
