package sfa.das.sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static sfa.das.StepDefinitions.*;


public class HomePageComparator implements Comparator<String> {

    static final List<String> order = new ArrayList<>( );

    static {
        order.add(ANCHOR_HEADER_VIEW_COOKIES);
        order.add(ANCHOR_HEADER_BETA_BANNER_FEEDBACK);


        order.addAll(homeMap.keySet());

        order.add(ANCHOR_SHARE_EMAIL);
        order.add(ANCHOR_SHARE_FACEBOOK);
        order.add(ANCHOR_SHARE_X);
        order.add(ANCHOR_SHARE_LINKEDIN);
        order.add(ANCHOR_FOOTER_ACCESSIBILITY_STATEMENT);
        order.add(ANCHOR_FOOTER_PRIVACY);
        order.add(ANCHOR_FOOTER_COOKIES);
        order.add(ANCHOR_FOOTER_CONTACT);
        order.add(ANCHOR_FOOTER_SCOTLAND);
        order.add(ANCHOR_FOOTER_WALES);
        order.add(ANCHOR_FOOTER_NI);
        order.add(ANCHOR_FOOTER_OPEN_GOVERNMENT_LICENCE_V_3_0);
        order.add(ANCHOR_FOOTER_CROWN_COPYRIGHT);

    }

    @Override
    public int compare(String s1, String s2) {
        // Get the index of s1 and s2 in the order list
        int index1 = order.indexOf(s1);
        int index2 = order.indexOf(s2);

        // Compare the index values
        return Integer.compare(index1, index2);
    }

//        Apprenticeships
//        T Levels
//        Skills Bootcamps
//        Multiply
//        HTQs
//        Free Courses
//        Care Leaver Covenant
//        Supported Internships
//        SWAPs
//        Support prisoners and prison leavers

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
     //   list.addAll((backMap.keySet()));
      //  list.addAll(freeCourses.keySet());
      //  list.addAll(schemesHomePageDefaultMap.keySet());

        list.add(ANCHOR_CONTACTS_CALL_CHARGES);
        list.add(ANCHOR_CONTACTS_APPRENTICESHIP_EMAIL);
        list.add(ANCHOR_CONTACTS_TLEVELS_EMAIL);
        list.add(ANCHOR_CONTACTS_MULTIPLY_EMAIL);
        list.add(ANCHOR_CONTACTS_MULTIPLY_FINDER);
        list.add(ANCHOR_CONTACTS_CARERLEAVERCOVENANT_EMAIL);

        // Print the list before sorting
        System.out.println("Before sorting: " + list);

        // Sort the list using the custom comparator
        Collections.sort(list, new HomePageComparator());

        // Print the list after sorting
        System.out.println("After sorting: " + list);
    }


}
