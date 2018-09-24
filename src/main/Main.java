package main;

import main.database.DatabaseMetaData;
import main.mapper.DataTypesMap;
import main.utils.Properties;

import java.util.ArrayList;

class Main{
    public static void main(String args[]){
        try{
            if (args.length == 0) {
                throw new IllegalArgumentException();
            }
            Properties.loadProperties();
            DataTypesMap.readMapFile(args[0]);
            DatabaseMetaData metaData = DatabaseMetaData.getInstance();
            ArrayList<String> ddlStatements = metaData.buildCreateStatements();
            for (String createStatement : ddlStatements) {
                System.out.println(createStatement);
            }
            metaData.executeCreateStatements(ddlStatements);
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
}