package data.comparators;

import data.Organization;

import java.util.Comparator;

public class DateComparator implements Comparator<Organization> {
    @Override
    public int compare(Organization o1, Organization o2) {
        return o1.getCreationDate().compareTo(o2.getCreationDate());
    }
}
