package model.base;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public abstract class Base {
    static int idGenerator=0;
    int id;
    private String callBackData;  // User = U, Category = C, Product = P;

    public Base(){
        idGenerator++;
        this.id = idGenerator;
    }

    public void setCallBackData(String callBackData){
        this.callBackData = callBackData;
    }


}
