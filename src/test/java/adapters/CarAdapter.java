package adapters;

import dto.api.cars.Car;
import io.qameta.allure.Step;
import static org.hamcrest.Matchers.equalTo;

public class CarAdapter extends BaseAPI {

    @Step("GET запрос на получение машины по id: {id}")
    public Car getCar(int id) {
        return getSpec()
                .removeHeader("Authorization") // для избежания дублирующегося header'а Authorization
                .header("Authorization", getToken())
                .log().all()
                .when()
                .get(BASE_URI + "/car/" + String.valueOf(id))
                .then()
                .log().all()
                .statusCode(200)
                .body("id", equalTo(id))
                .extract().as(Car.class);
    }

    @Step("POST запрос на создание машины: {car}")
    public Car createCar(Car car) {
        return getSpec()
                .removeHeader("Authorization") // для избежания дублирующегося header'а Authorization
                .header("Authorization",  getToken())
                .log().all()
                .body(gson.toJson(car))
                .when()
                .post(BASE_URI + "/car")
                .then()
                .log().all()
                .statusCode(201)
                .extract().as(Car.class);
    }

    public Car getCarByUserId(int id) {
        return getSpec()
                .removeHeader("Authorization") // для избежания дублирующегося header'а Authorization
                .header("Authorization",  getToken())
                .log().all()
                .when()
                .get(BASE_URI + "/user/" + String.valueOf(id) + "cars")
                .then()
                .log().all()
                .statusCode(200)
                .body("id", equalTo(id))
                .extract().as(Car.class);
    }

    @Step("PUT запрос на изменение иннформации машины с id:{id} данными: {car}")
    public Car changeCar(Car car, int id) {
        return getSpec()
                .removeHeader("Authorization") // для избежания дублирующегося header'а Authorization
                .header("Authorization",  getToken())
                .log().all()
                .body(gson.toJson(car))
                .when()
                .put(BASE_URI + "/car/" + String.valueOf(id))
                .then()
                .log().all()
                .statusCode(202)
                .extract().as(Car.class);
    }

    @Step("DELETE запрос на удалении иннформации машины с id: {id}")
    public void deleteCar(int id) {
        getSpec()
                .removeHeader("Authorization") // для избежания дублирующегося header'а Authorization
                .header("Authorization",  getToken())
                .log().all()
                .when()
                .delete(BASE_URI + "/car/" + String.valueOf(id))
                .then()
                .log().all()
                .statusCode(204);
    }
}