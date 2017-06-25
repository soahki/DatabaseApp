import databaseIO.Connector;
import databaseIO.IO;

import java.io.IOException;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        IO io = new IO();
        io.getTables();
    }
}
