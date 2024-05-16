package managers;

import exeptions.WrongArgumentException;

public class Validator {

    public static void inputIsNotEmpty(String arg, String data) throws WrongArgumentException {
        if (arg.isEmpty() || arg.trim().isEmpty()) {
            throw new WrongArgumentException(data);
        }
    }

    public static void coordinateXIsOk(String arg) throws WrongArgumentException {
        try {
            Double.parseDouble(arg);
        } catch (Exception e) {
            throw new WrongArgumentException("X");
        }
    }

    public static void coordinateYIsOk(String arg) throws WrongArgumentException {
        try {
            Integer.parseInt(arg);
        } catch (Exception e) {
            throw new WrongArgumentException("Y");
        }
    }

    public static void annualTurnoverIsOk(String arg) throws WrongArgumentException {
        try {
            Integer.parseInt(arg);
        } catch (Exception e) {
            throw new WrongArgumentException("annualTurnover");
        }
    }

    public static void typeIsOk(String arg) throws WrongArgumentException {
        try {
            int n = Integer.parseInt(arg);
            if (n<1 || n>4){
                throw new WrongArgumentException("OrganizationType");
            }
        } catch (Exception e) {
            throw new WrongArgumentException("OrganizationType");
        }
    }

}
