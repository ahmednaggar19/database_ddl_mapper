package main.database;//import connection.main.Connector;
//import model.tables.FKey;
//import model.tables.PKey;

import main.Connector;
import main.table.Table;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public  class DatabaseMetaData {
//    private HashMap<String, PKey> primaryKeys;
//    private HashMap<String, HashMap<String,FKey>> foreignKeys;
    java.sql.DatabaseMetaData metaData;
    static DatabaseMetaData databaseMetaData;

    public synchronized static DatabaseMetaData getInstance()
    {
        if(databaseMetaData ==null) {
            Connection connection= Connector.getInstance().getSQLConnection();
            databaseMetaData = new DatabaseMetaData(connection);
        }
        return databaseMetaData;
    }
    private DatabaseMetaData(Connection connection) {
        try {
            metaData = connection.getMetaData();
//            primaryKeys=new HashMap<>();
//            foreignKeys=new HashMap<>();
//            initPrimaryKeys();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
    public void initDatawarehouse(String dataWareHouseName) {

    }

    public ArrayList<String> buildCreateStatements() throws SQLException {
        ResultSet tables = metaData.getTables(null, null, "%", new String[]{"TABLE"});
        ArrayList<String> createStatements = new ArrayList<>();
        while (tables.next()) {
            String catalog = tables.getString("TABLE_CAT");
            String schema = tables.getString("TABLE_SCHEM");
            String tableName = tables.getString("TABLE_NAME");

            ResultSet primaryKey = metaData.getPrimaryKeys(catalog, schema, tableName);
            Table table = new Table();
            table.setTableName(tableName);
            table.setPrimaryKey(primaryKey);
            ResultSet columns = metaData.getColumns(null, null, tableName, "%");
            table.setColumnNames(columns);
            createStatements.add(table.buildCreateStatement());
//            main.Util.printResultSet(columns, tables.getMetaData().getColumnCount());
        }
        return createStatements;
    }


}
