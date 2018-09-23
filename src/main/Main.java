package main;

import main.database.DatabaseMetaData;
import main.mapper.DataTypesMap;

class Main{
    public static void main(String args[]){
        try{
//            Class.forName("com.mysql.jdbc.Driver");
//            Connection con=DriverManager.getConnection(
//                    "jdbc:mysql://salma:3306/shopping","nifi-cluster","root");
//            Statement stmt=con.createStatement();
//            ResultSet rs=stmt.executeQuery("describe categories");
//            ResultSetMetaData rsmd = rs.getMetaData();
            DataTypesMap.readMapFile("datatypesMap");
            DatabaseMetaData metaData = DatabaseMetaData.getInstance();
//            metaData.initPrimaryKeys();
//            con.close();
            for (String createStatement : metaData.buildCreateStatements()) {
                System.out.println(createStatement);
            }
        }catch(Exception e){ System.out.println(e);}
    }
}