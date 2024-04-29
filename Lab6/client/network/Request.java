package network;

import data.Organization;


import java.io.Serial;
import java.io.Serializable;

public class Request implements Serializable {
    @Serial
    private static final long serialVersionUID = 7460996574894336141L;
    public Organization organization;
    String[] args;

    //Передавать команды и аргументы стрингом или массивом стрингов

    public Request(Organization organization){
        this.organization = organization;
    }

    public Request(Organization organization,String[] args){
        this.organization = organization;
        this.args = args;
    }


    public Request(String[] args){
        this.args = args;
    }

    public String[] getArgs() {
        return args;
    }

    public Organization getOrganization() {
        return organization;
    }
}
