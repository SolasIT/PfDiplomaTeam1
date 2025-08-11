package adapters;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.api.user.rq.User;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class UsersAdapter {

    public Gson gson = new GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create();

    private final String BASE_URI = "http://82.142.167.37:4880/user/";

    public RequestSpecification spec = given()
            .contentType(ContentType.JSON);


    public User createUser(User user) {
        return spec
                .body(gson.toJson(user))
                .log().all()
                .when()
                .post(BASE_URI)
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .as(User.class);
    }
}
