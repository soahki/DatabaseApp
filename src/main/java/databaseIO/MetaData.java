package databaseIO;

import model.Column;
import model.Table;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class MetaData {
    private DatabaseMetaData metaData;
    private Map<String, String> primaryKeys = new HashMap<>();

    public MetaData(Connector connector) {
        try {
            setMetaData(connector);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void setMetaData(Connector connector) throws SQLException {
        metaData = connector.getConnection().getMetaData();
        setPrimaryKeys();
    }

    private void setPrimaryKeys() throws SQLException {
        String tableName;
        String columnName;
        List<Table> tables = getTables();
        for (Table table : tables) {
            ResultSet rs = metaData.getPrimaryKeys(null, null, table.getName());
            while (rs.next()) {
                tableName = rs.getString(3);
                columnName = rs.getString(4);
                primaryKeys.put(tableName, columnName);
            }
        }
    }

    public Map<Table, List<Column>> getMetaData() {
        Map<Table, List<Column>> metaDataMap = new HashMap<>();
        return metaDataMap;
    }

    public List<Table> getTables() throws SQLException {
        List<Table> tables = new ArrayList<>();

        ResultSet rs = metaData.getTables(null,null , "%", null);
        while (rs.next()) {
            String name = rs.getString(3);
            String type = rs.getString(4);
            String remarks = rs.getString(5);;
            String identifier = rs.getString(9);
            String identifierGeneration = rs.getString(10);;
            tables.add(new Table(name, type, remarks, identifier, identifierGeneration));
        }

        return tables;
    }

    public List<Column> getColumns(String tableName) throws SQLException {
        List<Column> columns = new ArrayList<>();

        ResultSet columnsRS = metaData.getColumns(null, null, tableName, "%");
        while (columnsRS.next()) {
            ResultSet foreignKeyRS = metaData.getImportedKeys(null, null, tableName);
            String name = columnsRS.getString(4);
            String type = columnsRS.getString(6);
            boolean isPrimaryKey = Objects.equals(primaryKeys.get(tableName), name);
            boolean isForeignKey = false;
            String foreignKeyReference = null;

            while (foreignKeyRS.next()){
                isForeignKey = Objects.equals(foreignKeyRS.getString(4), name);
                if(isForeignKey) {
                    foreignKeyReference = foreignKeyRS.getString(7) + " : " + foreignKeyRS.getString(8);
                    break;
                }
            }

            columns.add(new Column(name, type, isPrimaryKey, isForeignKey, foreignKeyReference));
        }

        return columns;
    }

    public Map<String, String> getPrimaryKeys() {
        return primaryKeys;
    }
}
