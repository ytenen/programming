package exeptions;

public class UserExistsException extends Exception{
    public UserExistsException(){
        super("This login already exists");
    }
}
