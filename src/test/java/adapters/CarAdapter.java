package adapters;


import dto.api.Car;

import static org.hamcrest.Matchers.equalTo;

public class CarAdapter extends BaseAPI {

    AuthAPI authAPI = new AuthAPI();

    public Car getCar(int id) {
        return spec
                .removeHeader("Authorization")
                .header("Authorization", authAPI.getToken())
                .log().all()
                .when()
                .get(BASE_URI + "/car/" + String.valueOf(id))
                .then()
                .log().all()
                .statusCode(200)
                .body("id", equalTo(id))
                .extract().as(Car.class);
    }

    public Car createCar(Car car) {
        return spec
                .removeHeader("Authorization")
                .header("Authorization", authAPI.getToken())
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
        return spec
                .removeHeader("Authorization")
                .header("Authorization", authAPI.getToken())
                .log().all()
                .when()
                .get(BASE_URI + "/user/" + String.valueOf(id) + "cars")
                .then()
                .log().all()
                .statusCode(200)
                .body("id", equalTo(id))
                .extract().as(Car.class);
    }

    public Car changeCar(Car car, int id) {
        return spec
                .removeHeader("Authorization")
                .header("Authorization", authAPI.getToken())
                .log().all()
                .body(gson.toJson(car))
                .when()
                .put(BASE_URI + "/car/" + String.valueOf(id))
                .then()
                .log().all()
                .statusCode(202)
                .extract().as(Car.class);
    }

    public void deleteCar(int id) {
        spec
                .removeHeader("Authorization")
                .header("Authorization", authAPI.getToken())
                .log().all()
                .when()
                .delete(BASE_URI + "/car/" + String.valueOf(id))
                .then()
                .log().all()
                .statusCode(204);
    }
}