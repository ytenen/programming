package data.comparators;

import data.Organization;

import java.util.Comparator;

public class CoordXComparator implements Comparator<Organization> {
    @Override
    public int compare(Organization o1, Organization o2) {
        return (int)(o1.getCoordinates().getX()-o2.getCoordinates().getX());
    }
}
