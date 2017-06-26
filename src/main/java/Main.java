import databaseIO.Connector;
import databaseIO.IO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws SQLException {
        IO io = new IO();
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



    }
}
