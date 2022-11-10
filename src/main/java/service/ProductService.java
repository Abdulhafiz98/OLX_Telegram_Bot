package service;

import dataBase.DataBase;
import model.Product;

public class ProductService extends DataBase {

    public void saveProductToDatabase(Product product){
        saveProductToDataBase(product);
    }
}
