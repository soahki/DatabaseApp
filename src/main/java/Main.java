import io.IO;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        IO io = new IO();
        System.out.println(io.getDatabaseSchema());
    }
}
