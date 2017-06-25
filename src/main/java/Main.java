import IO.Connector;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Connector connector = new Connector();
        System.out.println("Database type: " + connector.getDatabaseType());
        System.out.println("Database origin: " + connector.getDatabaseOrigin());
        System.out.println("Database name: " + connector.getDatabaseName());

    }
}
