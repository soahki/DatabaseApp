package databaseIO;

import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.List;

public class MetaData {
    private DatabaseMetaData metaData;

    public MetaData(Connector connector) {
        try {
            setMetaData(connector);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void setMetaData(Connector connector) throws SQLException {
        metaData = connector.getConnection().getMetaData();
    }

    public List<String> getMetaData() {

        return null;
    }
}
