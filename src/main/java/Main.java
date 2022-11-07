import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.Category;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Category category = new Category();
        category.setName("wqdqdfgdf");
        category.setParentId(2);
        objectMapper.writerWithDefaultPrettyPrinter().
                writeValue(new File("src\\main\\resources\\categories.json"),category);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String s = "["+gson.toJson(category)+"]";

        objectMapper.writerWithDefaultPrettyPrinter().
                writeValue(new File("src\\main\\resources\\categories.json"),s);


    }
}
