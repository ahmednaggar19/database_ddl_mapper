//import connection.Connector;
//import model.tables.FKey;
//import model.tables.PKey;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public  class DbMetaData {
//    private HashMap<String, PKey> primaryKeys;
//    private HashMap<String, HashMap<String,FKey>> foreignKeys;
    java.sql.DatabaseMetaData metaData;
    static DbMetaData dbMetaData;

    public synchronized static DbMetaData getInstance()
    {
        if(dbMetaData==null) {
            Connection connection= Connector.getInstance().getSQLConnection();
            dbMetaData = new DbMetaData(connection);
        }
        return dbMetaData;
    }
    private DbMetaData(Connection connection) {
        try {
            metaData = connection.getMetaData();
//            primaryKeys=new HashMap<>();
//            foreignKeys=new HashMap<>();
//            initPrimaryKeys();
            initForeignKeys();
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
//            {
//                for(int i=0;primaryKey.next();i++){
//                    String pkey = (primaryKey.getString("COLUMN_NAME"));
//                }
//            }
            Table table = new Table();
            table.setTableName(tableName);
            table.setPrimaryKey(primaryKey);
            ResultSet columns = metaData.getColumns(null, null, tableName, "%");
            table.setColumnNames(columns);
            createStatements.add(table.buildCreateStatement());
//            Util.printResultSet(columns, tables.getMetaData().getColumnCount());
        }
        return createStatements;
    }

    private void initForeignKeys()  throws SQLException {
        ResultSet tables = metaData.getTables(null, null, "%", new String[]{"TABLE"});
        while (tables.next()) {
            String catalog = tables.getString("TABLE_CAT");
            String schema = tables.getString("TABLE_SCHEM");
            String tableName = tables.getString("TABLE_NAME");
//            HashMap<String,FKey> foreignKeys=new HashMap<>();
//            try (ResultSet foreignKey = metaData.getImportedKeys(catalog, schema, tableName)) {
//                FKey foreignkey;
//                //  System.out.println(foreignKey.getMetaData());
//                while(foreignKey.next()) {
//                    System.out.println(tableName);
//                    foreignkey=new FKey(foreignKey.getString("PKCOLUMN_NAME"),
//                            foreignKey.getString("PKTABLE_NAME"),
//                            foreignKey.getString("FKCOLUMN_NAME"));
//                    foreignKeys.put(foreignKey.getString("PKTABLE_NAME"),foreignkey);
//                }
//                this.foreignKeys.put(tableName,foreignKeys);
//            }
        }

    }


}
