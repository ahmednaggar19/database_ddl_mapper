package main;

import main.database.DatabaseMetaData;
import main.mapper.DataTypesMap;
import main.utils.Properties;

import java.util.ArrayList;

/**
 * Main Class
 */
class Main{
    /**
     * Main method that runs the project
     * @param args args containing the name of the map file
     */
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