package dataBase;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import exceptions.DataSavingFailedException;
import model.Product;
import model.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public interface DataBase<T> {
    List<User> LIST_OF_USERS = new ArrayList<>();
    List<Product> LIST_OF_PRODUCTS = new ArrayList<>();
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    default void addObjectToDataBase(T o) {
        if(o instanceof User){
            File file = new File("src\\main\\resources\\usersJson.json");
            fileWriter(file,o);
        }else if(o instanceof Product){
            File file = new File("src\\main\\resources\\productsJson.json");
            fileWriter(file,o);
        }else{
            try {
                throw new DataSavingFailedException();
            } catch (DataSavingFailedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void fileWriter(File file,T o){
        try(FileWriter fileWriter = new FileWriter(file)) {
            if(file.createNewFile()){
                String str = "["+gson.toJson(o)+"]";
                fileWriter.write(str);
            }else{
                List<T> users = gson.fromJson(new FileReader(file),new TypeToken<List<T>>(){}.getType());
                users.add(o);
                fileWriter.write(gson.toJson(users));
                fileWriter.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
