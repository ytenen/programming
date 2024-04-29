package exeptions;

public class ReplayIdException extends Exception{
    public ReplayIdException(long id){
        super("Id: "+ id + " is already used");
    }
}
