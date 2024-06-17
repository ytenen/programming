package data.comparators;

import data.Organization;

import java.util.Comparator;

public class CoordYComparator implements Comparator<Organization> {
    @Override
    public int compare(Organization o1, Organization o2) {
        return o1.getCoordinates().getY()-o2.getCoordinates().getY();
    }
}
