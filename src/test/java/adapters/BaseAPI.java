package adapters;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.FilterableRequestSpecification;
import utils.PropertyReader;

import static io.restassured.RestAssured.given;

public class BaseAPI {

    protected static final String BASE_URI = "http://82.142.167.37:4879";
    String email = System.getProperty("email", PropertyReader.getProperty("email"));
    String password = System.getProperty("password", PropertyReader.getProperty("password"));

    static Gson gson = new GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create();

    public FilterableRequestSpecification getSpec(){
     FilterableRequestSpecification spec = (FilterableRequestSpecification)
            given()
                    .filter(new AllureRestAssured()
                                    .setRequestAttachmentName("Запрос")
                                    .setResponseAttachmentName("Ответ")
                    )
                    .contentType(ContentType.JSON);
    return spec;
    }

    @Step("Получение токена")
    public String getToken(){
        FilterableRequestSpecification  spec = getSpec();
        return "Bearer " +
                spec
                        .removeHeader("Authorization") // для избежания дублирующегося header'а Authorization
                        .body("{\"username\": \"" + email + "\",\n" +
                                "  \"password\": \"" + password + "\"}")
                        .post(BASE_URI + "/login")
                        .then()
                        .log().all()
                        .statusCode(202)
                        .extract().path("access_token");
    }
}