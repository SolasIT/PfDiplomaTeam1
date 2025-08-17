package adapters;

import dto.api.houses.House;
import io.qameta.allure.Step;
import static org.hamcrest.Matchers.blankOrNullString;
import static org.hamcrest.Matchers.equalTo;

public class HouseAdapter extends BaseAPI {

    //POST /house
    @Step("Создание дома")
    public House createHouse(House house) {
        return getSpec()
                .removeHeader("Authorization")
                .header("Authorization", getToken())
                .log().all()
                .body(gson.toJson(house))
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
    public House getHouseById(int id) {
        return getSpec()
                .removeHeader("Authorization")
                .header("Authorization", getToken())
                .log().all()
                .when()
                .get(BASE_URI + "/house/" + id)
                .then()
                .log().all()
                .statusCode(200)
                .body("id", equalTo(id))
                .extract()
                .as(House.class);
    }

    //PUT /house/{houseId}
    @Step("Изменение дома по {houseId}")
    public House changeHouse(House house, int id) {
        return getSpec()
                .removeHeader("Authorization")
                .header("Authorization", getToken())
                .log().all()
                .body(gson.toJson(house))
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
    public void deleteHouse(int id) {
        getSpec()
                .removeHeader("Authorization")
                .header("Authorization",  getToken())
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
        return getSpec()
                .removeHeader("Authorization")
                .header("Authorization", getToken())
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