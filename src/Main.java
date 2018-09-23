import javax.xml.crypto.Data;
import java.sql.*;
class Main{
    public static void main(String args[]){
        try{
//            Class.forName("com.mysql.jdbc.Driver");
//            Connection con=DriverManager.getConnection(
//                    "jdbc:mysql://salma:3306/shopping","nifi-cluster","root");
//            Statement stmt=con.createStatement();
//            ResultSet rs=stmt.executeQuery("describe categories");
//            ResultSetMetaData rsmd = rs.getMetaData();
            DbMetaData metaData = DbMetaData.getInstance();
//            metaData.initPrimaryKeys();
//            con.close();
            for (String createStatement : metaData.buildCreateStatements()) {
                System.out.println(createStatement);
            }
        }catch(Exception e){ System.out.println(e);}
    }
}