import IO.Connector;

import java.io.IOException;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException {
        Connector connector = new Connector();
        System.out.println(connector.getConfigStatus());

    }
}
