package managers;

import data.OrganizationType;
import data.generators.IdGenerator;
import exeptions.ReplayIdException;
import exeptions.WrongArgumentException;

public class Validator {


    public static void idIsOk(String arg, String data) throws WrongArgumentException, ReplayIdException {
        long id;
        try {
            id = Long.parseLong(arg);
        } catch (Exception e) {
            throw new WrongArgumentException(data);
        }

        if (!IdGenerator.idIsUnique(id)) {
            throw new ReplayIdException(id);
        }
    }




    public static void inputIsNotEmpty(String arg, String data) throws WrongArgumentException {
        if (arg.isEmpty() || arg.trim().isEmpty()) {
            throw new WrongArgumentException(data);
        }
    }



    public static void coordinateXIsOk(String arg) throws WrongArgumentException {
        try {
            double n = Double.parseDouble(arg);
            if (n > 264){
                throw new WrongArgumentException("X");
            }
        } catch (Exception e) {
            throw new WrongArgumentException("X");
        }
    }

    public static void coordinateYIsOk(String arg) throws WrongArgumentException {
        try {
            int x = Integer.parseInt(arg);
        } catch (Exception e) {
            throw new WrongArgumentException("Y");
        }
    }

    public static void annualTurnoverIsOk(String arg) throws WrongArgumentException {
        try {
            int y = Integer.parseInt(arg);
            if (y <= 0){
                throw new WrongArgumentException("annualTurnover");
            }
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
