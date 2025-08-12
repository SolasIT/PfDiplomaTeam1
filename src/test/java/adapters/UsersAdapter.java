package adapters;

import dto.api.user.rq.User;

public class UsersAdapter extends BaseAPI {

    AuthAPI authAPI = new AuthAPI();

    public User createUserPositive(User user) {
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
}
