package io;

import model.Column;
import model.Table;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * Input/Output to database in the form of SQL statements
 */
public class IO {
    private Statement statement;
    private MetaData metaData;

    public IO() {
        Connector connector = new Connector();
        statement = connector.getStatement();
        metaData = new MetaData(connector);
    }

    public List<Table> getTables() throws SQLException {
        return metaData.getTables();
    }

    public List<Column> getColumns(String tableName) throws SQLException {
        return metaData.getColumns(tableName);
    }

    public String getDatabaseSchema() {
        return metaData.toString();
    }

}
