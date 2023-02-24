package edu.matc.persistence;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * Provides access the database
 * Created on 8/31/16.
 *
 * @author cmalin
 */
public class Database {

    // create an object of the class Database
    private static Database instance = new Database();

    private Properties properties;

    private Connection connection;

    private final Logger log = Logger.getLogger("edu.matc.book");

    // private constructor prevents instantiating this class anywhere else
    private Database() {
        loadProperties();

    }

    private void loadProperties() {
        properties = new Properties();
        try {
            log.info("Starting to load properties");
            if("dev".equals(System.getenv("Stage"))) {
                properties.load(this.getClass().getResourceAsStream("/database-dev.properties"));
            }
            else if("staging".equals(System.getenv("Stage"))) {
                properties.load(this.getClass().getResourceAsStream("/database-staging.properties"));
            }
            else{
                properties.load(this.getClass().getResourceAsStream("/database.properties"));
            }
        } catch (IOException ioe) {
            log.log(Level.SEVERE, "Database.loadProperties()...Cannot load the properties file", ioe);
            ioe.printStackTrace();
        } catch (Exception e) {
            log.log(Level.SEVERE, "Database.loadProperties()...", e);
            e.printStackTrace();
        }

    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
// get the only Database object available
    public static Database getInstance() {
        return instance;
    }

    /**
     * Gets connection.
     *
     * @return the connection
     */
    public Connection getConnection() {
        return connection;
    }

    /**
     * Connect.
     *
     * @throws Exception the exception
     */
    public void connect() throws Exception {
        if (connection != null)
            return;

        try {
            Class.forName(properties.getProperty("driver"));
        } catch (ClassNotFoundException e) {
            log.log(Level.SEVERE, "Database.connect() error");
            throw new Exception("Database.connect()... Error: MySQL Driver not found");
        }

        String url = properties.getProperty("url");
        log.info("Connecting to DB:" + url);
        connection = DriverManager.getConnection(url, properties.getProperty("username"),  properties.getProperty("password"));
        log.info("Successfully connected to DB");
    }

    /**
     * Disconnect.
     */
    public void disconnect() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println("Cannot close connection" + e);
            }
        }

        connection = null;
    }

}