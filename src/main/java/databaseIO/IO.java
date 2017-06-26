package databaseIO;

import java.io.IOException;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Input/Output to database in the form of SQL statements
 */
public class IO {
    private Statement statement;
    private Connector connector;
    private DatabaseMetaData metaData;

    public IO() {
        connector = new Connector();
        statement = connector.getStatement();
        setMetaData();

    }

    private void setMetaData() {
        try {
            metaData = connector.getConnection().getMetaData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<String> getTables() throws SQLException {
        List<String> tables = new ArrayList<>();
        ResultSet rs = metaData.getTables(null,null , "%", null);

        while (rs.next()) {
            tables.add(rs.getString(3));
        }
        return tables;
    }

    public Map<String, String> getColumns(String tableName) throws SQLException {
        Map<String, String> columnsMap = new HashMap<>();
        ResultSet columnsRS = metaData.getColumns(null, null, tableName, "%");

        while (columnsRS.next()) {
            columnsMap.put(columnsRS.getString(4), columnsRS.getString(6));
        }
        return columnsMap;
    }

}
