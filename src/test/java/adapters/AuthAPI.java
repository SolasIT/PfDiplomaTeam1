package adapters;

import io.restassured.http.ContentType;
import utils.PropertyReader;

import static io.restassured.RestAssured.given;

public class AuthAPI {

    private static final String BASE_URI = "http://82.142.167.37:4879";
    String email = System.getProperty("email", PropertyReader.getProperty("email"));
    String password = System.getProperty("password", PropertyReader.getProperty("password"));

    public String getToken() {
        return "Bearer " + given()
                .contentType(ContentType.JSON)
                .body("{\"username\": \"" + email + "\",\n" +
                        "  \"password\": \"" + password + "\"}")
                .post(BASE_URI + "/login")
                .then()
                .log().all()
                .statusCode(202)
                .extract().path("access_token");
    }
}