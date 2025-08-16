package db;

import dto.api.cars.Car;
import org.testng.annotations.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DBRequests extends DBConnection{

    @Test
    public Car getCarByIdDB (int id) throws SQLException {
        connect();
        ResultSet result = select(
                    "SELECT c.*,et.type_name FROM car c " +
                          "JOIN engine_type et ON c.engine_type_id = et.id " +
                          "WHERE c.id ="+id+
                          " LIMIT 1");
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
}