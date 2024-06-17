package network;

import data.Organization;

import java.io.Serial;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

public class Response implements Serializable {
    @Serial
    private static final long serialVersionUID = 7903638490219874246L;

    private String result = "\nSuccess\n";
    private List<Organization> collection = null;

    public void setResult(String result){
        this.result = result;
    }

    public String getResult() {
        return result;
    }
    public List getCollection(){
        return collection;
    }

    public Response(String result){
        this.result = result;
    }
    public Response(String result,List collection){
        this.result = result;
        this.collection = collection;
    }
}
