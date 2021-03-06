package io;

import model.Column;
import model.Table;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * Meta data of database, including tables, its columns and keys.
 */
public class MetaData {
    private DatabaseMetaData metaData;
    private String dbName;
    private Map<String, String> primaryKeys = new HashMap<>();

    MetaData(Connector connector) {
        try {
            initializeMetaData(connector);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void initializeMetaData(Connector connector) throws SQLException {
        metaData = connector.getConnection().getMetaData();
        dbName = connector.getDatabaseName();
        initializePrimaryKeys();
    }

    private void initializePrimaryKeys() throws SQLException {
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

    Map<Table, List<Column>> getMetaDataMap() throws SQLException {
        Map<Table, List<Column>> metaDataMap = new HashMap<>();
        for (Table table : getTables()) {
            metaDataMap.put(table, new ArrayList<>());
            for (Column column : getColumns(table.getName())) {
                metaDataMap.get(table).add(column);
            }
        }
        return metaDataMap;
    }

    List<Table> getTables() throws SQLException {
        List<Table> tables = new ArrayList<>();

        ResultSet rs = metaData.getTables(null,null , "%", null);
        while (rs.next()) {
            String name = rs.getString(3);
            String type = rs.getString(4);
            String remarks = rs.getString(5);
            String identifier = rs.getString(9);
            String identifierGeneration = rs.getString(10);
            tables.add(new Table(name, type, remarks, identifier, identifierGeneration));
        }

        return tables;
    }

    List<Column> getColumns(String tableName) throws SQLException {
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
                    foreignKeyReference = foreignKeyRS.getString(4) + "(" + foreignKeyRS.getString(3) + ")";
                    break;
                }
            }

            columns.add(new Column(name, type, isPrimaryKey, isForeignKey, foreignKeyReference));
        }

        return columns;
    }

    Map<String, String> getPrimaryKeys() {
        return primaryKeys;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        String data = "No meta data available.";
        try {
            Map<Table, List<Column>> map = getMetaDataMap();
            sb.append("Database: ").append(dbName);
            for (Table table : map.keySet()) {
                sb.append("\nTable: ").append(table.getName()).append("\n");
                sb.append("Columns: ");
                List<Column> columns = map.get(table);
                for (int i = 0; i < columns.size(); i++) {
                    sb.append(columns.get(i).getName()).append(" ").append(columns.get(i).getType());
                    if (columns.get(i).isPrimaryKey()) {
                        sb.append(" <PRIMARY KEY>");
                    }
                    if (columns.get(i).isForeignKey()) {
                        sb.append(" <FOREIGN KEY: ").append(columns.get(i).getForeignKeyReference()).append(">");
                    }
                    if (i < (columns.size() - 1)) {
                        sb.append(" | ");
                    } else {
                        sb.append("\n");
                    }
                }
                data = sb.toString();
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }
}
