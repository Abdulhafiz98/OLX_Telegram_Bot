package model.base;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
public abstract class Base {
    private static int idGenerator = 0;
    private int id = 0;
    //private String callBackData;  // User = U, Category = C, Product = P;

    public Base(){
        this.id = idGenerator++;
    }
}
