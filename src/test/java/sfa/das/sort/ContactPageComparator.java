package sfa.das.sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static sfa.das.StepDefinitions.*;


public class ContactPageComparator implements Comparator<String> {

    static final List<String> order = new ArrayList<>( );

    static {

        order.add(ANCHOR_CONTACTS_CALL_CHARGES);
        order.add(ANCHOR_CONTACTS_APPRENTICESHIP_EMAIL);
        order.add(ANCHOR_CONTACTS_TLEVELS_EMAIL);
        order.add(ANCHOR_CONTACTS_MULTIPLY_EMAIL);
        order.add(ANCHOR_CONTACTS_MULTIPLY_FINDER);
        order.add(ANCHOR_CONTACTS_CARERLEAVERCOVENANT_EMAIL);
    }

    @Override
    public int compare(String s1, String s2) {
        // Get the index of s1 and s2 in the order list
        int index1 = order.indexOf(s1);
        int index2 = order.indexOf(s2);

        // Compare the index values
        return Integer.compare(index1, index2);
    }


    public static void main(String[] args) {
        List<String> list = new ArrayList<>();

        list.add(ANCHOR_CONTACTS_CALL_CHARGES);
        list.add(ANCHOR_CONTACTS_APPRENTICESHIP_EMAIL);
        list.add(ANCHOR_CONTACTS_TLEVELS_EMAIL);
        list.add(ANCHOR_CONTACTS_MULTIPLY_EMAIL);
        list.add(ANCHOR_CONTACTS_MULTIPLY_FINDER);
        list.add(ANCHOR_CONTACTS_CARERLEAVERCOVENANT_EMAIL);

        // Print the list before sorting
        System.out.println("Before sorting: " + list);

        // Sort the list using the custom comparator
        Collections.sort(list, new ContactPageComparator());

        // Print the list after sorting
        System.out.println("After sorting: " + list);
    }


}
