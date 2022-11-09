package dataBase;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import model.Id;
import model.Product;

import java.io.*;
import java.util.List;

public class BaseMethod extends DataBase{
  static Gson gson = new GsonBuilder().setPrettyPrinting().create();
   static File file = new File("src\\main\\java\\resources\\generationId.json");
    public static void saveId(int id) throws IOException {
        Id id1 = gson.fromJson(new FileReader(file),new TypeToken<Id>(){}.getType());
        id1.setId(id);
        String json = gson.toJson(id1);
        fileWriterMethod(file,json);

    }
    public static int getIdLastId() throws FileNotFoundException {
        Id id1 = gson.fromJson(new FileReader(file),new TypeToken<Id>(){}.getType());
        int id = id1.getId();
        return ++id;

    }
    private static void fileWriterMethod(File file, String text) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            bufferedWriter.write(text);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
