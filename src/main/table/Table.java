package main.table;

import main.mapper.DataTypesMap;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Table {

    private String tableName;
    private ArrayList<Column> columnNames;
    private ArrayList<String> primaryKey;


    public Table() {
        this.tableName = null;
        this.columnNames = new ArrayList<>();
        this.primaryKey = new ArrayList<>();
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public ArrayList<Column> getColumnNames() {
        return columnNames;
    }

    public void setColumnNames(ResultSet columnNames) throws SQLException {
        ArrayList<Column> columns = new ArrayList<>();
        while (columnNames.next()) {
            Column column = new Column();
            column.setColumnName(columnNames.getString("COLUMN_NAME"));
            column.setDataType(columnNames.getString("TYPE_NAME"));
            columns.add(column);
        }
        this.columnNames = columns;
    }

    public ArrayList<String> getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(ResultSet primaryKey) throws SQLException {
        ArrayList<String> primaryKeyNames = new ArrayList<>();
        while (primaryKey.next()) {
            primaryKeyNames.add(primaryKey.getString("COLUMN_NAME"));
        }
        this.primaryKey = primaryKeyNames;
    }

    public String buildCreateStatement() {
        String createStatement = "CREATE TABLE ";
        createStatement += tableName;
        createStatement += " ( ";
        int columNamesIndex = 0;
        for (Column column : columnNames) {
            createStatement += (column.getColumnName() + " ");
            createStatement += DataTypesMap.mapDataType(column.getDataType());
            if (columNamesIndex < columnNames.size() - 1) {
                createStatement += ",";
            }
            columNamesIndex++;
        }
        createStatement += ", primary key (";
        int primaryKeyIndex = 0;
        for (String primaryKeyComponent : primaryKey) {
            createStatement += primaryKeyComponent;
            if (primaryKeyIndex < primaryKey.size() - 1) {
                createStatement += ",";
            }
            primaryKeyIndex++;
        }
        createStatement += "))";
        return createStatement;
    }
}
