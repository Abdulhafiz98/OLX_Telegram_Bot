package model.base;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public abstract class Base {
    private static int idGenerator;
    private int id =0;
    private String callBackData;  // User = U, Category = C, Product = P;

    public Base(){
        idGenerator++;
        this.id = idGenerator;
    }
}
