package adapters;

import dto.api.user.rs.User;

public class UsersAdapter extends BaseAPI {

    AuthAPI authAPI = new AuthAPI();

    public User createUser(User user) {
        return spec
                .header("Authorization", authAPI.getToken())
                .body(gson.toJson(user))
                .log().all()
                .when()
                .post(BASE_URI + "/user")
                .then()
                .log().all()
                .extract()
                .as(User.class);
    }

    public User getUserById(String id) {
        return spec
                .header("Authorization", authAPI.getToken())
                .log().all()
                .when()
                .get(BASE_URI + "/user/" + id)
                .then()
                .log().all()
                .extract()
                .as(User.class);
    }
}
