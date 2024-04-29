package data;

import jakarta.xml.bind.annotation.XmlRootElement;

import java.io.Serial;
import java.io.Serializable;

@XmlRootElement
public class Address implements Serializable {
    @Serial
    private static final long serialVersionUID = -2198189148456433335L;
    private String zipCode;//Длина строки не должна быть больше 22, Поле может быть null

    public  Address(String zipCode){
        this.zipCode = zipCode;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
}
