package service;

import model.Product;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

public class PProductService {
    static ConcurrentHashMap<String, ArrayList<Product>> conHashMap=new ConcurrentHashMap<>();

}
