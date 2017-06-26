package databaseIO;

import model.Column;
import model.Table;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
}
