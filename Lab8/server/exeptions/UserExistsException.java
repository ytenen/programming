package exeptions;

public class UserExistsException extends Exception{
    public UserExistsException(){
        super("User with this login already exists");
    }
}
