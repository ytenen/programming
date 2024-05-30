package network;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public class User implements Serializable {
    @Serial
    private final static long serialVersionUID = 1071233047951533997L;
    private String login;

    private String password;



    public User(String login, String password) {
        this.login = login;
        this.password = password;

    }


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }



    public void setPassword(String password) {
        this.password = password;
    }

}
