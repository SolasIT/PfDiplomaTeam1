package db;

import dto.api.cars.Car;
import org.testng.annotations.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DBRequests extends DBConnection{

    @Test
    public void checkConnect() {
        connect();
        close();
    }

    @Test
    public Car getCarByIdDB (int id) throws SQLException {
        connect();
        ResultSet result = select(
                    "SELECT c.*,et.type_name FROM car c " +
                          "JOIN engine_type et ON c.engine_type_id = et.id " +
                          "WHERE c.id ="+ id +
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

    @Test
    public Integer checkUserOwnsPropertyByPropertyId(Integer userId, Integer propertyId) throws SQLException {
        // connect прописан в API тесте
        ResultSet result = select(
                String.format("SELECT car.id FROM public.car " +
                        "WHERE car.id = %s " +
                        "AND person_id = %s",
                        propertyId, userId)
        );
        if (!result.next()) {
            return 0;
        } else {
            return result.getInt(1);
        }
        // close прописан в API тесте
    }
}