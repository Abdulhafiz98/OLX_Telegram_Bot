package service;

import model.Category;
import model.Product;
import service.baseSerivice.BaseService;

import java.util.List;

public class ProductService implements BaseService<Product> {


    @Override
    public void add(Product product) {
        Category category = new Category();
        for (Product pro : category.getProductList()){
            if (product.getCategoryId() == category.getParentId()){
                category.setProductList((List<Product>) product);
            }
        }

    }

    public void


}
