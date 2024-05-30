package managers;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import data.Organization;
import data.generators.IdGenerator;
import exeptions.ReplayIdException;
import exeptions.WrongArgumentException;
import network.Response;
import system.XMLSerializer;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.util.ArrayDeque;
@XStreamAlias("collectionManager")
public class CollectionManager {
    @XStreamImplicit(itemFieldName = "organization")
    static ArrayDeque<Organization> collection;
    static LocalDate date;


    public  CollectionManager(){
        collection = new ArrayDeque<>();
        date = LocalDate.now();
    }

    public static LocalDate getDate() {
        return date;
    }

    public static Response add(Organization organization){
        try{
            Validator.annualTurnoverIsOk(Integer.toString(organization.getAnnualTurnover()));
            Validator.coordinateXIsOk(Double.toString(organization.getCoordinates().getX()));
            Validator.coordinateYIsOk(Integer.toString(organization.getCoordinates().getY()));
            Validator.idIsOk(Integer.toString((int)organization.getId()),"ID");
        } catch (WrongArgumentException e) {
            return new Response(e.getMessage());
        } catch (ReplayIdException e) {
            return new Response(e.getMessage());
        }

        if (collection == null){
            collection = new ArrayDeque<>();
        }
        collection.add(organization);
        IdGenerator.add(organization.getId());
        return new Response("Organization added successfully");
    }

    public static void setCollection(ArrayDeque<Organization> col){
        collection = col;
    }

    public static ArrayDeque<Organization> getCollection() {
        return collection;
    }

    public static void removeById(int id){
        for (Organization organization : collection){
            if (organization.getId() == id){
                collection.remove(organization);
                break;
            }
        }
    }


    public static Response save(String[] args) {
        // Get the path to the XML file
        String path = args[1];
        // Create an XStream instance with a DOM driver
        XMLSerializer stream = new XMLSerializer();

        // Convert the collection to XML format
        String xml = stream.serialize(collection);

        try {
            // Write the XML data to the specified file
            BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream(path));
            byte[] bytes = xml.getBytes();
            output.write(bytes);
            output.close();
            return new Response("Collection saved");
        } catch (Exception e) {
            return new Response("Error occured while saving");
        }
    }

}
