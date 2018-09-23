package main.mapper;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DataTypesMap {

    private static Map<String, String> dataTypesMap = new HashMap<>();

    public static boolean readMapFile(String file) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] lineWords = line.split(" ");
                if (lineWords.length > 2) {
                    System.err.print("One of the Map file lines contains more than  two datatypes!");
                    return  false;
                } else if (lineWords.length != 2) {
                    System.err.print("Incorrect format of line!");
                    return  false;
                }
                System.out.println("Before adding to Map + " + lineWords[0] + "  " + lineWords[1]);
                dataTypesMap.put(lineWords[0], lineWords[1]);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    public static String mapDataType (String dataType) {
        if (!dataTypesMap.containsKey(dataType)) {
            return dataType;
        }
        return dataTypesMap.get(dataType);
    }
}
