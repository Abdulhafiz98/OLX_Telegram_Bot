package exceptions;

public class DataSavingFailedException extends Exception{
    public DataSavingFailedException() {
        super("Data saving failed");
    }
}
