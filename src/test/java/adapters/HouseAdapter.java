package adapters;

import dto.api.houses.House;
import io.qameta.allure.Step;
import static org.hamcrest.Matchers.blankOrNullString;

public class HouseAdapter extends BaseAPI {

    AuthAPI authAPI = new AuthAPI();

    //POST /house
    @Step("Создание дома")
    public House createHouse(House house, Integer statusCode) {
        return spec
                .removeHeader("Authorization")
                .header("Authorization", authAPI.getToken())
                .body(gson.toJson(house))
                .log().all()
                .when()
                .post(BASE_URI + "/house")
                .then()
                .log().all()
                .statusCode(201)
                .extract()
                .as(House.class);
    }

    //GET /house/{houseId}
    @Step("Получение дома по {houseId}")
    public House getHouseById(Integer id, Integer statusCode) {
        spec.body("");
        return spec
                .removeHeader("Authorization")
                .header("Authorization", authAPI.getToken())
                .request()
                .log().all()
                .when()
                .get(BASE_URI + "/house/" + id)
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .as(House.class);
    }

    //PUT /house/{houseId}
    @Step("Изменение дома по {houseId}")
    public House changeHouse(House house, Integer id, Integer statusCode) {
        return spec
                .removeHeader("Authorization")
                .header("Authorization", authAPI.getToken())
                .body(gson.toJson(house))
                .log().all()
                .when()
                .put(BASE_URI + "/house/" + id)
                .then()
                .log().all()
                .statusCode(202)
                .extract()
                .as(House.class);
    }

    //DELETE /house/{houseId}
    @Step("Удаление дома по {houseId}")
    public void deleteHouse(Integer id, Integer statusCode) {
        spec.body("");
        spec
                .removeHeader("Authorization")
                .header("Authorization", authAPI.getToken())
                .request()
                .log().all()
                .when()
                .delete(BASE_URI + "/house/" + id)
                .then()
                .log().all()
                .statusCode(204)
                .body(blankOrNullString());
    }

    //GET /houses
    @Step("Получение всех домов")
    public House[] getHouses(Integer statusCode) {
        spec.body("");
        return spec
                .removeHeader("Authorization")
                .header("Authorization", authAPI.getToken())
                .request()
                .log().all()
                .when()
                .get(BASE_URI + "/houses")
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .as(House[].class);
    }
}