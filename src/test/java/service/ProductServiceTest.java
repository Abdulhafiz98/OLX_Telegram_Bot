package service;

import dataBase.DataBase;
import exceptions.InvalidProductException;
import model.Category;
import model.Product;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

class ProductServiceTest {

    Product product ;
    private ProductService productService;

    @BeforeEach
    void setUp(){
        this.productService=new ProductService();
    }
    @BeforeEach
    void setUp1(){
        this.product = new Product("Iphone","AAAAAAAAAAA",1,new BigDecimal(15000),"adhf","+998971234567","blabla",false,"today");
    }

    @Test
    @DisplayName("skjdbcjksdbc")
    void getProductByStringIdInTest(){
        assertEquals(product,this.productService.getProductByStringId("1"));
    }


}