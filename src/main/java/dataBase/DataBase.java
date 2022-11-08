package dataBase;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import model.Product;
import model.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public abstract class DataBase {
    public static List<User> LIST_OF_USERS = new ArrayList<>();
    public static List<Product> LIST_OF_PRODUCTS = new ArrayList<>();
    private static Gson gson =new GsonBuilder().setPrettyPrinting().create();

    public static void saveUserToDataBase(User user) {
        File file = new File("F:\\Java lessons\\OLX_Telegram_Bot\\src\\main\\resources\\users\\usersJson.json");
        try {
            if(file.createNewFile()){
                String str = "["+gson.toJson(user)+"]";
                fileWriterMethod(file,str);

            }else{
                List<User> users = gson.fromJson(new FileReader(file),new TypeToken<List<User>>(){}.getType());
                users.add(user);
                fileWriterMethod(file,gson.toJson(users));
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void saveProductToDataBase(Product product) {
        File file = new File("F:\\Java lessons\\OLX_Telegram_Bot\\src\\main\\resources\\products\\productsJson.json");
        try {
            if(file.createNewFile()){
                String str = "["+gson.toJson(product)+"]";
                fileWriterMethod(file,str);

            }else{
                List<Product> products = gson.fromJson(new FileReader(file),new TypeToken<List<Product>>(){}.getType());
                products.add(product);
                fileWriterMethod(file,gson.toJson(products));
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void fileWriterMethod(File file, String text) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            bufferedWriter.write(text);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}