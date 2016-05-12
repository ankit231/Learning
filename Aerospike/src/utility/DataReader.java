package utility;

import java.io.FileReader;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

import com.google.gson.Gson;

public class DataReader {
    public ArrayList<Person> parseJsonFile() {
        JSONParser parser = new JSONParser();
        ArrayList<Person> personList = new ArrayList<>(20);
        try {
            JSONArray jsonArr = (JSONArray) parser.parse(new FileReader("/home/ankit/Desktop/JsonFile"));
            for (Object o : jsonArr) {
                Person p = new Gson().fromJson(o.toString(), Person.class);
                personList.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return personList;
    }
}