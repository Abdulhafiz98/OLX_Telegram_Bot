package model;

import lombok.*;
import model.base.Base;

import javax.swing.text.DateFormatter;
import java.sql.Time;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Date;
import java.util.Formatter;

@AllArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
public class Product extends Base {
    private String name;
    private int categoryId;
    private double price;
    private int userId;
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

        return "\t\t Product ID :   " + getUserId() + "\n"+
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
