package model;

import lombok.*;
import model.base.Base;



@AllArgsConstructor
@NoArgsConstructor
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
    private String dateAndTime;


}
