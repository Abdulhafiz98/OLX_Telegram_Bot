package model.base;

import dataBase.BaseMethod;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import model.Id;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.json.JSONPropertyIgnore;

import java.io.*;

@Getter
@Setter
@ToString(callSuper = true)
public abstract class Base extends BaseMethod {
    private int id = 0;
    //private String callBackData;  // User = U, Category = C, Product = P
    static int idGenerator = getIdFromFile();
    public Base(){
        this.id = idGenerator;

    }

    private static int getIdFromFile(){
        try {
           return idGenerator = getIdLastId();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        finally {
            try {
                saveId(idGenerator);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }


}
