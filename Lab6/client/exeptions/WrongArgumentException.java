package exeptions;

public class WrongArgumentException extends Exception{
    public WrongArgumentException(String data){
        super("Something wrong with input argument: " + data);
    }
}

