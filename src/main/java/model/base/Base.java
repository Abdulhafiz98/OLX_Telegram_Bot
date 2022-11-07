package model.base;

public abstract class Base {
    private static int idGenerator;
    private int id =0;

    public Base(){
        idGenerator++;
        this.id = idGenerator;
    }
}
