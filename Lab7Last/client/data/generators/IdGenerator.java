package data.generators;

import data.Organization;

import java.util.ArrayDeque;
import java.util.ArrayList;

public class IdGenerator {
    private static ArrayList<Integer> idList = new ArrayList<>();

    public IdGenerator(){
        idList = new ArrayList<>();
    }

    public static int generateId(){
        int id = (int)Math.floor(Math.random()*12312312) + 1231212;
        while (idList.contains(id)){
            id = (int)Math.floor(Math.random()*(123123123)) + 234234;
        }
        idList.add(id);
        return id;
    }

    public static boolean idIsUnique(long id){
        if (idList.contains(id)){
            return false;
        }
        return true;
    }

    public static void  correlateId(ArrayDeque<Organization> dec){
        for(Organization organization : dec){
            IdGenerator.add(organization.getId());
        }
    }
    public static void remove(int id){
        idList.remove(id);
    }
    public static void add(int id){
        idList.add(id);
    }
}
