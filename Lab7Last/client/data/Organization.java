package data;

import data.generators.IdGenerator;
import jakarta.xml.bind.annotation.XmlRootElement;
import managers.Validator;

import java.io.Serial;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.util.Date;

@XmlRootElement
public class Organization implements Comparable<Organization>, Serializable {
    @Serial
    private static final long serialVersionUID = 4863323752786388271L;
    private int id;//3начение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name;//Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private Date creationDate;//Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Integer annualTurnover;//Поле не может быть null, Значение поля должно быть больше 0
    private String fullName; //Длина строки не должна быть больше 1552, Поле может быть null
    private OrganizationType type;//Поле не может быть null
    private Address officialAddress; //Поле не может быть null

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public Organization(int id, String name, Coordinates coordinates, Integer annualTurnover, String fullName, OrganizationType type, Address officialAddress){
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.annualTurnover = annualTurnover;
        this.fullName = fullName;
        this.type = type;
        this.officialAddress = officialAddress;
    }

    public Organization(){
        this.id = IdGenerator.generateId();
        this.name = null;
        this.coordinates = null;
        this.creationDate = new Date();
        this.fullName = null;
        this.type = null;
        this.officialAddress = null;
    }

    public Organization(int id){
        this.id = id;
        this.name = null;
        this.coordinates = null;
        this.creationDate = new Date();
        this.annualTurnover = null;
        this.fullName = null;
        this.type = null;
        this.officialAddress = null;
    }



    public Organization(String[] data) throws Exception {
        // Проверяем корректность всех значений
        Validator.inputIsNotEmpty(data[2], "NAME");
        Validator.coordinateXIsOk(data[3]);
        Validator.coordinateYIsOk(data[4]);
        Validator.inputIsNotEmpty(data[5], "DATE");
        Validator.annualTurnoverIsOk(data[6]);
        Validator.inputIsNotEmpty(data[7], "FullName");
        Validator.typeIsOk(data[8]);
        Validator.inputIsNotEmpty(data[9], "zipCode");
        this.id = Integer.parseInt(data[1]);
        this.name = data[2];
        this.coordinates = new Coordinates(Integer.parseInt(data[3]), Integer.parseInt(data[4]));
        try{
            this.creationDate = sdf.parse(data[5]);
        } catch (ParseException e) {
            System.out.println("Wrong date value");
        }
        this.annualTurnover = Integer.parseInt(data[6]);
        this.fullName = data[7];
        this.type = OrganizationType.valueOf(data[8]);
        this.officialAddress = new Address(data[9]);
    }


    public String toXML() {
        return "id=\"" + id + "\"" +
                " name=\"" + name + "\"" +
                " x=\"" + coordinates.getX() + "\"" +
                " y=\"" + coordinates.getY() + "\"" +
                " creationDate=\"" + creationDate + "\"" +
                " annualTurnover=\"" + annualTurnover + "\"" +
                " fullName=\"" + fullName + "\"" +
                " type=\"" + type + "\"" +
                " zipCode=\"" + officialAddress.getZipCode() + "\"";
    }

    @Override
    public String toString() {
        return "Organization{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", coordinates=" + coordinates +
                ", creationDate=" + creationDate +
                ", annualTurnover=" + annualTurnover +
                ", fullName='" + fullName + '\'' +
                ", type=" + type +
                ", postalAddress=" + officialAddress +
                '}';
    }

    public Address getOfficialAddress() {
        return officialAddress;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public int getId() {
        return id;
    }

    public OrganizationType getType() {
        return type;
    }

    public Integer getAnnualTurnover() {
        return annualTurnover;
    }

    public String getFullName() {
        return fullName;
    }

    public String getName() {
        return name;
    }

    public void setAnnualTurnover(Integer annualTurnover) {
        this.annualTurnover = annualTurnover;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOfficialAddress(Address officialAddress) {
        this.officialAddress = officialAddress;
    }

    public void setType(OrganizationType type) {
        this.type = type;
    }

    public Date getCreationDate() {
        return creationDate;
    }


    @Override
    public int compareTo(Organization o) {
        return (int) (this.id - o.getId());
    }
}
