package db;

import lombok.extern.log4j.Log4j2;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.PropertyReader;

import java.sql.*;

@Log4j2
public class DBConnection {

    private Connection connect = null;
    private Statement statement = null;
    private ResultSet result = null;

    String URL = System.getProperty("dburl", PropertyReader.getProperty("dburl"));
    String USER = System.getProperty("dbuser", PropertyReader.getProperty("dbuser"));
    String PASSWORD = System.getProperty("dbpassword", PropertyReader.getProperty("dbpassword"));

    public void connect(){
        try {
            connect = DriverManager.getConnection(URL,USER,PASSWORD);
            statement = connect.createStatement();
            log.info("Connect DB");
        } catch (SQLException e) {
           log.error(e.getMessage());
        }
    }

    public ResultSet select(String query){
        try {
            log.info("Request completed: {}",query);
            return statement.executeQuery(query);
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
        return null;
    }

    public void close(){
        try {
            if (connect != null) {
                connect.close();
                log.info("Close DB");
            }
            if (statement != null) {
                statement.close();
            }
            if (result != null) {
                result.close();
            }
        }catch (SQLException e){
            log.error(e.getMessage());
        }
    }
}