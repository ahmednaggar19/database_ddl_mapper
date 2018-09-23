package main;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Util {


    public static void printResultSet(ResultSet rs, int columnsNumber) throws SQLException {
        while(rs.next()) {
            for (int i = 1; i <= columnsNumber; i++) {
                System.out.print(rs.getString(i) + " ");
            }
            System.out.println();
        }
    }
}
