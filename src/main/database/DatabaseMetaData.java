package main.database;//import connection.main.Connector;
//import model.tables.FKey;
//import model.tables.PKey;

import main.Connector;
import main.table.Table;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Class for dealing with database meta data.
 */
public  class DatabaseMetaData {
    /**
     * Meta Data object
     */
    java.sql.DatabaseMetaData metaData;
    /**
     * Singleton Object
     */
    static DatabaseMetaData databaseMetaData;

    public synchronized static DatabaseMetaData getInstance()
    {
        if(databaseMetaData ==null) {
            Connection connection= Connector.getInstance().getSourceConnection();
            databaseMetaData = new DatabaseMetaData(connection);
        }
        return databaseMetaData;
    }
    private DatabaseMetaData(Connection connection) {
        try {
            metaData = connection.getMetaData();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    /**
     * Builds create table statements for the destination database.
     * @return ArrayList of string create statements
     * @throws SQLException
     */
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
//            main.utils.Util.printResultSet(columns, tables.getMetaData().getColumnCount());
        }
        return createStatements;
    }

    /**
     * Executes the create statements on the destination database.
     * @param createStatements the create statements to be executed
     * @throws SQLException
     */
    public void executeCreateStatements(ArrayList<String> createStatements) throws SQLException {
        Connection connection = Connector.getInstance().getDestinationConnection();
        Statement statement = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
        for (String createStatement : createStatements) {
            statement.execute(createStatement);
        }
        return;
    }


}
