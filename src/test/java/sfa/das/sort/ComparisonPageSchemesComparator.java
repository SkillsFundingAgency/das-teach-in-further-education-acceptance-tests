package sfa.das.sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static sfa.das.StepDefinitions.*;


public class ComparisonPageSchemesComparator implements Comparator<String> {

    static final List<String> order = new ArrayList<>( );
//    Apprenticeships
//    T Levels
//    Skills Bootcamps
//    Multiply
//    Higher Technical Qualifications
//    Free Courses and additional training
//    Care Leaver Covenant
//    Supported Internships
//    SWAPs
//    Support prisoners and prison leavers

    static {
        order.add(ANCHOR_HEADER_VIEW_COOKIES);
        order.add(ANCHOR_HEADER_BETA_BANNER_FEEDBACK);

        order.add(ANCHOR_COMPARISON_PAGE_CLEAR_FILTERS);


        order.addAll(schemesComparisonPageMap.keySet());
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
        list.addAll(schemesComparisonPageMap.keySet());

        // Print the list before sorting
        System.out.println("Before sorting: " + list);

        // Sort the list using the custom comparator
        Collections.sort(list, new ComparisonPageSchemesComparator());

        // Print the list after sorting
        System.out.println("After sorting: " + list);
    }


}
