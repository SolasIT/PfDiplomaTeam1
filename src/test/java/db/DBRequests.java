package db;

import dto.api.cars.Car;
import io.qameta.allure.Step;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DBRequests extends DBConnection {

    @Step("Проверка подключения к БД")
    public void checkConnect() {
        connect();
        close();
    }

    @Step("Получение записи в БД по {carId}")
    public Car getCarByIdDB(int id) throws SQLException {
        connect();
        ResultSet result = select(
                "SELECT c.*,et.type_name FROM car c " +
                        "JOIN engine_type et ON c.engine_type_id = et.id " +
                        "WHERE c.id =" + id +
                        "LIMIT 1");
        result.next();
        Car car = new Car();
        car.setId(result.getInt("id"));
        car.setMark(result.getString("mark"));
        car.setPrice(result.getDouble("price"));
        car.setEngineType(result.getString("type_name"));
        car.setModel(result.getString("model"));
        close();
        return car;
    }

    @Step("Получение кол-ва записей в БД по связке {userId} <-> {carId}} ")
    public Integer checkUserOwnsPropertyByPropertyId(Integer userId, Integer carId) throws SQLException {
        // connect прописан в API тесте
        ResultSet result = select(
                String.format("SELECT count(*) FROM public.car " +
                                "WHERE car.id = %s " +
                                "AND person_id = %s",
                        carId, userId)
        );
        result.next();
        return result.getInt("count");
        // close прописан в API тесте
    }
}