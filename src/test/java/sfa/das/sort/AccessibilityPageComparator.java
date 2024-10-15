package sfa.das.sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static sfa.das.StepDefinitions.*;


public class AccessibilityPageComparator implements Comparator<String> {

    static final List<String> order = new ArrayList<>( );

    static {

        order.add(ANCHOR_ACCESSIBILITY_ABILITY_NET);
        order.add(ANCHOR_ACCESSIBILITY_EMAIL);
        order.add(ANCHOR_ACCESSIBILITY_EMAIL2);
        order.add(ANCHOR_ACCESSIBILITY_EASS);
        order.add(ANCHOR_ACCESSIBILITY_WCAG);
        order.add(ANCHOR_ACCESSIBILITY_WCAG2);
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

        list.add(ANCHOR_ACCESSIBILITY_ABILITY_NET);
        list.add(ANCHOR_ACCESSIBILITY_EMAIL);
        list.add(ANCHOR_ACCESSIBILITY_EMAIL2);
        list.add(ANCHOR_ACCESSIBILITY_EASS);
        list.add(ANCHOR_ACCESSIBILITY_WCAG);
        list.add(ANCHOR_ACCESSIBILITY_WCAG2);

        // Print the list before sorting
        System.out.println("Before sorting: " + list);

        // Sort the list using the custom comparator
        Collections.sort(list, new AccessibilityPageComparator());

        // Print the list after sorting
        System.out.println("After sorting: " + list);
    }


}
