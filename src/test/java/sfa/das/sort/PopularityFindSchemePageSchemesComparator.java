package sfa.das.sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static sfa.das.StepDefinitions.*;


public class PopularityFindSchemePageSchemesComparator implements Comparator<String> {

    static final List<String> order = new ArrayList<>( );
//    Popularity: (most popular first)
//    Apprenticeships
//    Skills Bootcamps
//    T Levels
//    Free Courses and additional training
//    Higher Technical Qualifications
//    SWAPs
//    Supported Internships
//    Multiply
//    Support prisoners and prison leavers
//    Care Leaver Covenant

    static {
        order.addAll(schemesHomePagePopularityMap.keySet());
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
        list.addAll(schemesHomePagePopularityMap.keySet());

        // Print the list before sorting
        System.out.println("Before sorting: " + list);

        // Sort the list using the custom comparator
        Collections.sort(list, new PopularityFindSchemePageSchemesComparator());

        // Print the list after sorting
        System.out.println("After sorting: " + list);
    }


}
