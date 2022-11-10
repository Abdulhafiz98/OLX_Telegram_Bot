package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import model.base.Base;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class Product extends Base {
    private String name;
    private int categoryId;
    private double price;
    private String userId;
    private String description;
    private boolean saleState;
    private String phoneNumber;
    private String date;
    @Override
    public String toString() {
        String truOrFalse;
        if (saleState){
            truOrFalse = "✅";
        }
        else
            truOrFalse = "❌";

        return "\t\t Product ID :   " + getId() + "\n"+
                "  Nomi\uD83D\uDCC4  :  " + name + '\n' +
                "  Tavsif :  " + description +"\n"+
                "  Narxi\uD83D\uDCB5 :   " + price +" so`m"+"\n"+
                "  Telefon raqami☎ :  "+ phoneNumber +"\n"+
                "  User Id :  " + userId +"\n"+
                "  Qo`yilgan sanasi : " + date +"\n"+
                "  Categoriya Id  :  " + categoryId + '\n' +
                "  Sotuvda mavjud :  " + truOrFalse ;
    }
}
