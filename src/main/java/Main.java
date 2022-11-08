import com.fasterxml.jackson.core.PrettyPrinter;
import dataBase.DataBase;
import model.Product;

public class Main extends DataBase {

    public static void main(String[] args) {
        Product product = new Product();
        product.setSaleState(false);
        product.setName(" Samsung s20");
        product.setPrice(9_100_500);
        product.setDescription("Holati yangi");
        product.setPhoneNumber("+998997474347");
        saveProductToDataBase(product);
    }

}
