package databaseIO;

import java.io.IOException;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Input/Output to database in the form of SQL statements
 */
public class IO {
    private Statement statement;
    private Connector connector;

    public IO() {
            connector = new Connector();
            statement = connector.getStatement();
    }

    public List<String> getTables() throws SQLException {
        List<String> tables = new ArrayList<>();
        DatabaseMetaData metaData = connector.getConnection().getMetaData();
        ResultSet rs = metaData.getTables(null,null , "%", null);

        while (rs.next()) {
            tables.add(rs.getString(3));
        }
        return tables;
    }
}
