package com.prabuddha.poc.thread.Project_1.util;

import java.io.*;
import java.util.*;

public class AppUtil {

    public static List<String> addEachLineFromFileToList(String filePath){
        List<String> inputList=new ArrayList<String>();
        File sampleDataSet=new File(filePath);
        try (BufferedReader br = new BufferedReader(new FileReader(sampleDataSet))) {
            String line;
            while ((line = br.readLine()) != null) {
                if(!line.startsWith("First")) {
                    inputList.add(line);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return inputList;
    }

}
