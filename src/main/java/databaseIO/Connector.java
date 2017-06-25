package databaseIO;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * This is the entry point to the database. Determine here which database to connect to and
 * its type.
 */
public class Connector {
    private String databaseType;
    private String databaseOrigin;
    private String databaseName;

    private Connection connection;
    private Statement statement;

    Connector() {
        try {
            new ConfigReader().read();
        } catch (IOException e) {
            e.printStackTrace();
        }

        connect();

    }

    private void connect() {

        try {
            Class.forName(databaseType);
            connection = DriverManager.getConnection(databaseOrigin + databaseName);
            statement = connection.createStatement();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public String getConfigStatus() {
        String status = "Database type: %s\n" +
                "Database origin: %s\n" +
                "Database name: %s\n";
        return String.format(status, databaseType, databaseOrigin, databaseName);
    }

    Connection getConnection() {
        return connection;
    }

    Statement getStatement() {
        return statement;
    }

    public String getDatabaseOrigin() {
        return databaseOrigin;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public String getDatabaseType() {
        return databaseType;
    }

    /**
     * This class provides the Connector class with the initial values to connect to a SQL database,
     * based upon a cfg-file.
     */
    private class ConfigReader {
        private String cfgPath = "./src/main/resources/ConnectorConfig.cfg";
        void read() throws IOException {
            FileInputStream fis = new FileInputStream(cfgPath);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));

            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("DATABASE_TYPE:")) {
                    String[] dbType = line.split(" ");
                    databaseType = dbType[1];
                } else if (line.contains("DATABASE_ORIGIN:")) {
                    String[] dbType = line.split(" ");
                    databaseOrigin = dbType[1];
                } else if (line.contains("DATABASE_NAME:")) {
                    String[] dbType = line.split(" ");
                    databaseName = dbType[1];
                }

            }
        }
    }
}
