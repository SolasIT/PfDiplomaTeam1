package adapters;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class BaseAPI {

    protected static final String BASE_URI = "http://82.142.167.37:4879";
    static Gson gson = new GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create();

    static FilterableRequestSpecification spec = (FilterableRequestSpecification)
            given()
                    .contentType(ContentType.JSON);
}