package exeptions;

public class UnknowCommandException extends Exception{
    public UnknowCommandException(String arg){
        super("Unknow command: "+ arg);
    }
}
