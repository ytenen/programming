package data;

import jakarta.xml.bind.annotation.XmlRootElement;

import java.io.Serial;
import java.io.Serializable;

@XmlRootElement
public class Coordinates implements Serializable {
    @Serial
    private static final long serialVersionUID = -5031736258320552712L;
    private double x;//Максимальное значение поля 264
    private Integer y;//Поле не может быть null
    public  Coordinates(double x, int y){
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }
}
