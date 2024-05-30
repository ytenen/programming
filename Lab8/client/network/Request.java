package network;


import data.Organization;


import java.io.Serial;
import java.io.Serializable;

public class Request implements Serializable {
    @Serial
    private static final long serialVersionUID = 7460996574894336141L;
    public Organization organization;
    public User user;
    String[] args;

    //Передавать команды и аргументы стрингом или массивом стрингов

    public Request(Organization organization, User user){
        this.organization = organization;
    }

    public Request(Organization organization,String[] args, User user){
        this.organization = organization;
        this.args = args;
        this.user = user;
    }
    public Request(String[] args, User user){
        this.args =args;
        this.user = user;
    }


    public String[] getArgs() {
        return args;
    }

    public Organization getOrganization() {
        return organization;
    }

    public User getUser() {
        return user;
    }
}
