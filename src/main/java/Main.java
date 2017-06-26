import databaseIO.Connector;
import databaseIO.IO;
import databaseIO.MetaData;
import model.Column;
import model.Table;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws SQLException {
        IO io = new IO();
        MetaData metaData = new MetaData(new Connector());

        System.out.println(metaData);

        /*
        List<Table> tables = metaData.getTables();
        for (Table table : tables) {
            System.out.println("\n" + table.getName().toUpperCase());
            for (Column column : metaData.getColumns(table.getName())) {
                System.out.println("  " + column);
            }
        }

        System.out.println("\nPRIMARY KEYS:");
        Map<String, String> primaryKeys = metaData.getPrimaryKeys();
        for (String table : primaryKeys.keySet()) {
            System.out.println(table + " : " + primaryKeys.get(table));
        }
        */

        /*
        List<String> tables = io.getTables();
        System.out.println("TABLES: ");
        for (String table : tables) {
            System.out.println("  " + table);
        }

        Map<String, String> columns = io.getColumns(tables.get(0));
        System.out.println("\nCOLUMNS IN " + tables.get(0));
        for (String column : columns.keySet()) {
            System.out.println("  " + column + " <" + columns.get(column) + ">");
        }
        */



    }
}
