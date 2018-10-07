package main.utils;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Util {

    /**
     * prints the result set in a readable format
     * @param rs
     * @param columnsNumber
     * @throws SQLException
     */
    public static void printResultSet(ResultSet rs, int columnsNumber) throws SQLException {
        while(rs.next()) {
            for (int i = 1; i <= columnsNumber; i++) {
                System.out.print(rs.getString(i) + " ");
            }
            System.out.println();
        }
    }
}
