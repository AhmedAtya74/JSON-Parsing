/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Parsing;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Catalogue {

    private final String path = "E:\\FCAI\\4th year\\SOA\\Solutions\\JSON-Parsing\\Catalogue.json";
    final JSONArray CDList;

    public Catalogue() throws IOException {
        this.CDList = new JSONArray();
    }

    public void addCD() throws IOException {
        FileWriter file = new FileWriter(path);
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Number of CD: ");
        int iters = sc.nextInt();
        sc.nextLine();
        for (int i = 0; i < iters; i++) {
            System.out.println("CDs #" + (i + 1) + " Information U wants to stored it:- \n");

            JSONObject CD = new JSONObject();
            System.out.println("Enter CD Title");
            String title = sc.nextLine();

            System.out.println("Enter CD Artist");
            String artist = sc.nextLine();

            System.out.println("Enter CD Country");
            String country = sc.nextLine();

            System.out.println("Enter CD Company");
            String company = sc.nextLine();

            System.out.println("Enter CD Price");
            float price = sc.nextFloat();

            System.out.println("Enter CD Year");
            int year = sc.nextInt();

            CD.put("Title", title);
            CD.put("Artist", artist);
            CD.put("Country", country);
            CD.put("Company", company);
            CD.put("Price", price);
            CD.put("Year", year);

            JSONObject CDObject = new JSONObject();
            CDObject.put("CD", CD);

            CDList.add(CDObject);

            sc.nextLine();
        }
        file.write(CDList.toString());
        file.flush();

    }

    private boolean found(JSONObject CD, String key) {

        JSONObject CDObject = (JSONObject) CD.get("CD");

        String title = (String) CDObject.get("Title");

        String artist = (String) CDObject.get("Artist");

        return key.equals(title) || key.equals(artist);

    }

    public JSONObject searchByKey(String key) throws FileNotFoundException, IOException, ParseException {
        JSONObject targetCD = null;
        JSONParser jsonParser = new JSONParser();
        FileReader reader = new FileReader(path);
        Object obj = jsonParser.parse(reader);
        JSONArray CDs = (JSONArray) obj;
        for (Object CD : CDs) {
            if (found((JSONObject) CD, key) == true) {
                targetCD = (JSONObject) CD;
            }
        }
        return targetCD;
    }

    private boolean isEmpty() throws FileNotFoundException, IOException {
        FileReader reader = new FileReader(path);
        return reader.read() == -1;
    }

    public void print() throws FileNotFoundException, IOException, ParseException {

        JSONParser jsonParser = new JSONParser();

        if (!isEmpty()) {
            FileReader reader = new FileReader(path);
            Object parsedFile = jsonParser.parse(reader);
            JSONArray CDs = (JSONArray) parsedFile;
            for (Object CD : CDs) {
                System.out.println(CD.toString());
            }
        } else {
            System.out.println("JSON File is Empty");

        }

    }
}
