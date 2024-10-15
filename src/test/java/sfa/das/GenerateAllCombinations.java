package sfa.das;

import java.util.*;
import java.util.stream.Collectors;

public class GenerateAllCombinations {

    public static final String ANCHOR_APPRENTICESHIPS = "Apprenticeships";
    public static final String ANCHOR_T_LEVELS = "T Levels: industry placements";
    public static final String ANCHOR_SWAP = "Sector-based Work Academy Programme (SWAP)";
    public static final String ANCHOR_BOOTCAMPS = "Skills Bootcamps";
    public static final String ANCHOR_MULTIPLY = "Multiply";
    private static final String ANCHOR_HTQ = "Higher Technical Qualifications (HTQs)";
    public static final String ANCHOR_INTERNSHIPS = "Supported Internships for learners with an education, health and care plan";
    public static final String ANCHOR_CARE_LEAVER_COVENANT = "Care-Leaver covenant";
    public static final String ANCHOR_PRISONERS = "Employing prisoners and prison leavers";
    public static final String ANCHOR_FREE_COURSES = "Free courses and additional training for your employees";



    public static void main(String[] args) {

        // Create an array to store all the possible combinations
        //Boolean[] combinations = new Boolean[8];

        // Generate all the possible combinations
        //generateAllCombinations(combinations, 0);


        generateSchemes();
    }

    public static void generateAllCombinations(Boolean[] combinations, int index) {
        if (index == combinations.length) {
            // Convert the array of Boolean values to a string
            String combinationString = Arrays.toString(combinations);

            // Print the combination string
            System.out.println(combinationString);
            return;
        }

        combinations[index] = true;
        generateAllCombinations(combinations, index + 1);
        combinations[index] = false;
        generateAllCombinations(combinations, index + 1);
    }

    public static void generateSchemes()  {

        ArrayList all_schemes =
                new ArrayList<>(List.of(ANCHOR_APPRENTICESHIPS, ANCHOR_BOOTCAMPS, ANCHOR_CARE_LEAVER_COVENANT, ANCHOR_PRISONERS, ANCHOR_T_LEVELS, ANCHOR_SWAP, ANCHOR_INTERNSHIPS, ANCHOR_FREE_COURSES, ANCHOR_MULTIPLY, ANCHOR_HTQ));

        ArrayList motivations_recruit_new_staff =
                new ArrayList<>(List.of(ANCHOR_APPRENTICESHIPS, ANCHOR_BOOTCAMPS, ANCHOR_CARE_LEAVER_COVENANT, ANCHOR_PRISONERS, ANCHOR_T_LEVELS, ANCHOR_SWAP, ANCHOR_INTERNSHIPS, ANCHOR_HTQ));
        ArrayList motivations_retrain_or_upskill_existing_staff =
                new ArrayList<>(List.of(ANCHOR_APPRENTICESHIPS, ANCHOR_BOOTCAMPS, ANCHOR_FREE_COURSES, ANCHOR_MULTIPLY, ANCHOR_HTQ));
        ArrayList motivations_offer_short_terms_work_experience_placements =
                new ArrayList<>(List.of(ANCHOR_BOOTCAMPS, ANCHOR_T_LEVELS,  ANCHOR_SWAP, ANCHOR_INTERNSHIPS ));
        ArrayList scheme_length_less_than_6_months =
                new ArrayList<>(List.of(ANCHOR_CARE_LEAVER_COVENANT, ANCHOR_PRISONERS, ANCHOR_FREE_COURSES, ANCHOR_MULTIPLY, ANCHOR_SWAP, ANCHOR_T_LEVELS, ANCHOR_BOOTCAMPS));
        ArrayList scheme_length_between_6_months_and_1_year =
            new ArrayList<>(List.of(ANCHOR_CARE_LEAVER_COVENANT, ANCHOR_PRISONERS, ANCHOR_FREE_COURSES, ANCHOR_INTERNSHIPS, ANCHOR_MULTIPLY));
        ArrayList scheme_length_longer_than_1_year =
                new ArrayList<>(List.of(ANCHOR_HTQ, ANCHOR_APPRENTICESHIPS, ANCHOR_FREE_COURSES, ANCHOR_CARE_LEAVER_COVENANT, ANCHOR_PRISONERS,   ANCHOR_MULTIPLY));
        ArrayList  pay_free =
                new ArrayList<>(List.of(ANCHOR_CARE_LEAVER_COVENANT, ANCHOR_PRISONERS, ANCHOR_FREE_COURSES, ANCHOR_MULTIPLY, ANCHOR_T_LEVELS, ANCHOR_SWAP, ANCHOR_INTERNSHIPS ));
        ArrayList pay_may_require_employer_contribution =
                new ArrayList<>(List.of(ANCHOR_APPRENTICESHIPS, ANCHOR_BOOTCAMPS, ANCHOR_HTQ));

        Map<String, List<String>> list_of_each_checkbox = new LinkedHashMap<>();

        list_of_each_checkbox.put("motivations_recruit_new_staff", motivations_recruit_new_staff);
        list_of_each_checkbox.put("motivations_retrain_or_upskill_existing_staff",motivations_retrain_or_upskill_existing_staff);
        list_of_each_checkbox.put("motivations_offer_short_terms_work_experience_placements",motivations_offer_short_terms_work_experience_placements);
        list_of_each_checkbox.put("scheme_length_less_than_6_months",scheme_length_less_than_6_months);
        list_of_each_checkbox.put("scheme_length_between_6_months_and_1_year",scheme_length_between_6_months_and_1_year);
        list_of_each_checkbox.put("scheme_length_longer_than_1_year",scheme_length_longer_than_1_year);
        list_of_each_checkbox.put("pay_free",pay_free);
        list_of_each_checkbox.put("pay_may_require_employer_contribution",pay_may_require_employer_contribution);

        Map<List<Boolean>, List<String>> combinationsMap = new LinkedHashMap<>();
        StepDefinitions.populateCombinationsMap(combinationsMap);


        for (List<Boolean> combination : combinationsMap.keySet()) {
            // Check the checkboxes
            ArrayList i_want_to_list = new ArrayList();
            ArrayList duration_of_scheme_list = new ArrayList();
            ArrayList cost_of_training_list = new ArrayList();

            boolean i_want_to_list_selected = false;
            boolean duration_of_scheme_list_selected = false;
            boolean cost_of_training_list_selected = false;

            ArrayList combined_list = new ArrayList();
            if (combination.get(0)) {
                i_want_to_list.addAll(list_of_each_checkbox.get("motivations_recruit_new_staff"));
                i_want_to_list_selected = true;

            }
            if (combination.get(1)) {
                i_want_to_list.addAll((Collection) list_of_each_checkbox.get("motivations_retrain_or_upskill_existing_staff"));
                i_want_to_list_selected = true;
            }
            if (combination.get(2)) {
                i_want_to_list.addAll((Collection) list_of_each_checkbox.get("motivations_offer_short_terms_work_experience_placements"));
                i_want_to_list_selected = true;
            }
            if (combination.get(3)) {
                duration_of_scheme_list.addAll((Collection) list_of_each_checkbox.get("scheme_length_less_than_6_months"));
                duration_of_scheme_list_selected = true;
            }
            if (combination.get(4)) {
                duration_of_scheme_list.addAll((Collection) list_of_each_checkbox.get("scheme_length_between_6_months_and_1_year"));
                duration_of_scheme_list_selected = true;
            }
            if (combination.get(5)) {
                duration_of_scheme_list.addAll((Collection) list_of_each_checkbox.get("scheme_length_longer_than_1_year"));
                duration_of_scheme_list_selected = true;
            }
            if (combination.get(6)) {
                cost_of_training_list.addAll((Collection) list_of_each_checkbox.get("pay_free"));
                cost_of_training_list_selected = true;
            }
            if (combination.get(7)) {
                cost_of_training_list.addAll((Collection) list_of_each_checkbox.get("pay_may_require_employer_contribution"));
                cost_of_training_list_selected = true;
            }

            //nothing selected
            if (!i_want_to_list_selected && !duration_of_scheme_list_selected && !cost_of_training_list_selected) {
                LinkedHashSet<String> uniqueElements = new LinkedHashSet<>(all_schemes);
                printComboMapItem(combination, uniqueElements);
                continue;
            }

            //something was selected
            LinkedHashSet<String> uniqueElements_i_want_to_list = new LinkedHashSet<>(i_want_to_list);
            LinkedHashSet<String> uniqueElements_duration_of_scheme_list = new LinkedHashSet<>(duration_of_scheme_list);
            LinkedHashSet<String> uniqueElements_cost_of_training_list = new LinkedHashSet<>(cost_of_training_list);

            combined_list.addAll(uniqueElements_i_want_to_list.stream().toList());
            combined_list.addAll(uniqueElements_duration_of_scheme_list.stream().toList());
            combined_list.addAll(uniqueElements_cost_of_training_list.stream().toList());

            int true_count = 0;

            if (i_want_to_list_selected)
            true_count += 1;

            if (duration_of_scheme_list_selected)
            true_count += 1;

            if (cost_of_training_list_selected)
            true_count += 1;

            int finalTrue_count = true_count-1;
            List<String> duplicates = (List<String>) combined_list.stream()
                    .filter(scheme -> Collections.frequency(combined_list, scheme) > (finalTrue_count))
                    .distinct()
                    .collect(Collectors.toList());

            LinkedHashSet<String> uniqueElements = new LinkedHashSet<>(duplicates);
            printComboMapItem(combination, uniqueElements);
        }
    }
    private static void printComboMapItem(List<Boolean> combination, LinkedHashSet<String> uniqueElements) {
        System.out.println("combinationsMap.put(new ArrayList<>(List.of(" +
                combination.toString().replace("[", "").replace("]","") +
                ")), Arrays.asList(" +
                uniqueElements.toString()
                        .replace(ANCHOR_APPRENTICESHIPS, "ANCHOR_APPRENTICESHIPS")
                        .replace(ANCHOR_T_LEVELS, "ANCHOR_T_LEVELS")
                        .replace(ANCHOR_SWAP, "ANCHOR_SWAP")
                        .replace(ANCHOR_BOOTCAMPS, "ANCHOR_BOOTCAMPS")
                        .replace(ANCHOR_MULTIPLY, "ANCHOR_MULTIPLY")
                        .replace(ANCHOR_HTQ, "ANCHOR_HTQ")
                        .replace(ANCHOR_INTERNSHIPS, "ANCHOR_INTERNSHIPS")
                        .replace(ANCHOR_CARE_LEAVER_COVENANT, "ANCHOR_CARE_LEAVER_COVENANT")
                        .replace(ANCHOR_PRISONERS, "ANCHOR_PRISONERS")
                        .replace(ANCHOR_FREE_COURSES, "ANCHOR_FREE_COURSES").replace("[", "").replace("]","") + "));");
    }
}
