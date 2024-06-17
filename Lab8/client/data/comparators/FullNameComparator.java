package data.comparators;

import data.Organization;

import java.util.Comparator;

public class FullNameComparator implements Comparator<Organization> {
    @Override
    public int compare(Organization o1, Organization o2) {
        return o1.getFullName().compareTo(o2.getFullName());
    }
}
