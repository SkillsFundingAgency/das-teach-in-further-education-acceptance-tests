package sfa.das;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;

import org.jetbrains.annotations.NotNull;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


import sfa.das.base.Hooks;
import sfa.das.driver.DriverFactory;
import sfa.das.sort.*;
import sfa.das.sort.interim_pages.*;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.fail;
import static sfa.das.Helper.log;

@Slf4j
public class StepDefinitions  {

    private WebDriver driver;

    public StepDefinitions(Hooks hooks){
        this.driver=hooks.getDriver();
    }



    public static final int SCHEME_ANCHOR_NAME_EXPLORE_SECTION = 0;
    public static final int SCHEME_ANCHOR_NAME_HOMEPAGE = 1;
    public static final int SCHEME_ANCHOR_NAME_COMPARISON_PAGE = 2;
    public static final List<String> ANCHOR_HOMEPAGE = Arrays.asList("Learn how to prepare a Micro teach for your next interview.", "Find out more about your funding and training options", "0800 389 2502", "teach.fe@education.gov.uk");
    public static final List<String> ANCHOR_APPRENTICESHIPS = Arrays.asList("Apprenticeships", "Find out more about apprenticeships", "Apprenticeships");
    public static final List<String> ANCHOR_T_LEVELS = Arrays.asList("T Levels: industry placements", "Find out more about T Levels and industry placements", "T Levels");
    public static final List<String> ANCHOR_SWAP = Arrays.asList("Sector-based Work Academy Programme (SWAP)", "Find out more about SWAPs", "SWAPs");
    public static final List<String> ANCHOR_BOOTCAMPS = Arrays.asList("Skills Bootcamps", "Find out more about Skills Bootcamps", "Skills Bootcamps");
    public static final List<String> ANCHOR_MULTIPLY = Arrays.asList("Multiply", "Find out more about Multiply", "Multiply");
    public static final List<String> ANCHOR_HTQ = Arrays.asList("Higher Technical Qualifications (HTQs)", "Find out more about HTQs", "HTQs");
    public static final List<String> ANCHOR_INTERNSHIPS = Arrays.asList("Supported internships for learners with an education, health and care plan", "Find out more about supported internships and work placements", "Supported internships");
    public static final List<String> ANCHOR_CARE_LEAVER_COVENANT = Arrays.asList("Care Leaver Covenant", "Find out more about the Care Leaver Covenant", "Care Leaver Covenant");
    public static final List<String> ANCHOR_PRISONERS = Arrays.asList("Employing prisoners and prison leavers", "Find out more about employing prisoners and prison leavers", "Employing prisoners and prison leavers");
    public static final List<String> ANCHOR_FREE_COURSES = Arrays.asList("Free courses and additional free training", "Find out more about free courses and additional free training", "Free courses for jobs");

    //todo  public static final String ANCHOR_HEADER_SERVICE_NAME = "Find schemes for your business";
    //public static final String ANCHOR_HEADER_SKIP_TO_MAIN_CONTENT = "Skip to main content";
    //public static final String ANCHOR_HEADER_VIEW_COOKIES = "View cookies";
    //public static final String ANCHOR_HEADER_BETA_BANNER_FEEDBACK = "feedback";
    public static final String ANCHOR_HOME = "Home";
    //public static final String ANCHOR_FIND_SCHEMES = "Find training and employment schemes";
    //public static final String ANCHOR_COMPARED_PAGE_RETURN_TO_LIST = "Return to list of all schemes";

    //todo- now a button    public static final String ANCHOR_HOME_PAGE_COMPARE_SCHEMES_IN_A_TABLE = "Compare these schemes in a table";
    public static final String ANCHOR_SHARE_EMAIL = "Email";
    public static final String ANCHOR_SHARE_FACEBOOK = "Facebook";
    public static final String ANCHOR_SHARE_X = "X (formerly Twitter)";
    public static final String ANCHOR_SHARE_LINKEDIN = "LinkedIn";
    public static final String ANCHOR_HOME_PAGE_CLEAR_FILTERS = "Clear filters";
    public static final String ANCHOR_HOME_PAGE_NO_SCHEMES_PRESENT_CLEAR_FILTERS = "clear all filters";
    public static final String ANCHOR_HOME_SKILLS_FOR_CAREERS = "Get career ideas and browse your training options";

    public static final String ANCHOR_FOOTER_ACCESSIBILITY_STATEMENT = "Accessibility";
    public static final String ANCHOR_FOOTER_PRIVACY = "Privacy";
    public static final String ANCHOR_FOOTER_COOKIES = "Cookies";
    public static final String ANCHOR_FOOTER_CONTACT = "Contact";
    public static final String ANCHOR_FOOTER_ACCESSIBILITY = "Accessibility";
    public static final String ANCHOR_FOOTER_SCOTLAND = "Scotland";
    public static final String ANCHOR_FOOTER_WALES = "Wales";
    public static final String ANCHOR_FOOTER_NI = "Northern Ireland";
    public static final String ANCHOR_FOOTER_OPEN_GOVERNMENT_LICENCE_V_3_0 = "Open Government Licence v3.0";
    public static final String ANCHOR_FOOTER_CROWN_COPYRIGHT = "© Crown copyright";

    public static final String ANCHOR_CONTENTS_BENEFITS = "Benefits for your business";
    public static final String ANCHOR_CONTENTS_STORIES = "Employer stories";
    public static final String ANCHOR_CONTENTS_COST = "Cost";
    public static final String ANCHOR_CONTENTS_RESPONSIBILITIES = "Your responsibilities";
    public static final String ANCHOR_CONTENTS_CASE_STUDIES = "Case studies";
    public static final String ANCHOR_CONTENTS_FIND_OUT_MORE = "Find out more";


    public static final String ANCHOR_APPRENTICESHIP_THE_MINIMUM_WAGE = "the apprentice minimum wage";
    public static final String ANCHOR_APPRENTICESHIP_CALCULATE_YOUR_ESTIMATED_TRAINING_COST_FUNDING = "Calculate your estimated training cost funding";
    public static final String ANCHOR_APPRENTICESHIP_STEP_BY_STEP = "Read the step-by-step guide on employing an apprentice for your business";
    public static final String ANCHOR_APPRENTICESHIP_EMAIL = "employer.enquiries@education.gov.uk";


    public static final String ANCHOR_TLEVEL_SEE_MORE_ABOUT_HOW_INDUSTRY_PLACEMENTS_WORK = "See more about how industry placements work";
    public static final String ANCHOR_TLEVEL_EMPLOYER_SUPPORT = "Find out more about the employer support fund and costs it covers";
    public static final String ANCHOR_TLEVEL_INDUSTRY_PLACEMENT = "Read more about your industry placement responsibilities";

    public static final String ANCHOR_TLEVEL_POTENTIAL_BENEFITS = "use the potential benefits document";
    public static final String ANCHOR_TLEVEL_ACCELERATE_THEIR_APPRENTICESHIPS = "accelerated apprenticeships";
    public static final String ANCHOR_TLEVEL_GET_MORE_INFORMATION_ABOUT_T_LEVELS_AND_INDUSTRY_PLACEMENTS = "Find out more about industry placements";
    public static final String ANCHOR_TLEVEL_REGISTER_YOUR_INTEREST_IN_HOSTING = "Register your interest in hosting industry placements";
    public static final String ANCHOR_SWAP_HOW_EMPLOYERS_ARE_USING_SECTOR_BASED_WORK_ACADEMY_PROGRAMMES = "how employers are using sector-based work academy programmes";

    public static final String ANCHOR_SWAP_READ_THE_EMPLOYER_GUIDE_ON_GOV_UK = "read the employer guide on GOV.UK";
    public static final String ANCHOR_SWAP_CONTACT_THE_EMPLOYER_SERVICES_LINE = "contact the Employer Services Line";
    public static final String ANCHOR_BOOTCAMP_ACCELERATE_THEIR_APPRENTICESHIPS = "accelerated apprenticeships";
    public static final String ANCHOR_BOOTCAMP_FINDING_TRAINING_PROVIDERS_WHO_ARE_CURRENTLY_BEING_FUNDED_TO_DELIVER_SKILLS_BOOTCAMPS = "Find a local provider";
    public static final String ANCHOR_BOOTCAMP_READ_MORE_ON_APPLYING_TO_DELIVER_SKILLS_BOOTCAMPS = "Read more on applying to deliver Skills Bootcamps";

    public static final String ANCHOR_MULTIPLY_QUIZ = "Your employees can take a short quiz to assess their numeracy skills";
    public static final String ANCHOR_MULTIPLY_EMAIL = "multiply.localallocations@education.gov.uk";
    public static final String ANCHOR_MULTIPLY_READ_MORE_ABOUT_MULTIPLY_SKILLS_FOR_LIFE = "You can also search for Multiply courses on the National Careers Service website";
    public static final String ANCHOR_HTQ_2024_2025 = "In September 2024 and September 2025 students will be able to take HTQs in more sectors";
    public static final String ANCHOR_HTQ_LEARN_MORE_AND_GET_STARTED_WITH_HTQS = "View a list of HTQ providers and the courses they offer";
    public static final String ANCHOR_INTERNSHIPS_EDUCATION_HEALTH_AND_CARE_EHC_PLAN = "an education health and care (EHC) plan";
    public static final String ANCHOR_INTERNSHIPS_GUIDANCE_ON_SUPPORTED_INTERNSHIPS = "read guidance on GOV.UK";
    public static final String ANCHOR_INTERNSHIPS_WORK_LEAD = "the Internships Work lead for your region";
    public static final String ANCHOR_CARERLEAVERCOVENANT_THE_INCLUSIVE_EMPLOYER_TOOLKIT = "the Inclusive Employer Toolkit";
    public static final String ANCHOR_CARERLEAVERCOVENANT_FIND_OUT_HOW_TO_JOIN_THE_CARE_LEAVERS_COVENANT = "Visit the Care Leaver Covenant website for more information";
    public static final String ANCHOR_CARERLEAVERCOVENANT_EMAIL = "info@mycovenant.org.uk";

    public static final String ANCHOR_PRISONERS_REGISTER_YOUR_INTEREST_ON_THE_NEW_FUTURES_NETWORK_WEBSITE = "New Futures Network website";
    public static final String ANCHOR_FREECOURSES_FOR_JOBS = "Level 3 free courses for jobs";
    public static final String ANCHOR_FREECOURSES_THE_NATIONAL_CAREERS_SERVICE_AND_HOW_TO_CONTACT_THEM = "Find a course on the National Careers Service website";

    public static final String ANCHOR_CONTACTS_CALL_CHARGES = "Call charges and phone numbers - GOV.UK (www.gov.uk)";
    public static final String ANCHOR_CONTACTS_APPRENTICESHIP_EMAIL = ANCHOR_APPRENTICESHIP_EMAIL;
    public static final String ANCHOR_CONTACTS_APPRENTICESHIP_WEBSITE = "APPRENTICESHIP Scheme website";
    public static final String ANCHOR_CONTACTS_TLEVELS_EMAIL = "Tlevel.placement@education.gov.uk";
    public static final String ANCHOR_CONTACTS_TLEVELS_FORM = "TLEVELS Online contact form";
    public static final String ANCHOR_CONTACTS_TLEVELS_WEBSITE = "TLEVELS Scheme website";
    public static final String ANCHOR_CONTACTS_SWAP_WEBSITE = "SWAP Scheme website";
    public static final String ANCHOR_CONTACTS_MULTIPLY_EMAIL = "multiply.localallocations@education.gov.uk";
    public static final String ANCHOR_CONTACTS_MULTIPLY_FINDER = "Scheme course finder";
    public static final String ANCHOR_CONTACTS_CARERLEAVERCOVENANT_EMAIL = "info@mycovenant.org.uk";
    public static final String ANCHOR_CONTACTS_CARERLEAVERCOVENANT_FORM = "CARERLEAVERCOVENANT Online contact form";
    public static final String ANCHOR_CONTACTS_CARERLEAVERCOVENANT_WEBSITE = "Scheme website";
    public static final String ANCHOR_CONTACTS_BOOTCAMPS_WEBSITE = "BOOTCAMPS Scheme website";
    public static final String ANCHOR_CONTACTS_HTQ_WEBSITE = "HTQ Scheme website";
    public static final String ANCHOR_CONTACTS_INTERNSHIPS_WEBSITE = "INTERNSHIPS Scheme website";
    public static final String ANCHOR_CONTACTS_FREECOURSES_WEBSITE = "FREECOURSES Scheme website";
    public static final String ANCHOR_CONTACTS_PRISONERS_FORM = "PRISONERS Online contact form";
    public static final String ANCHOR_CONTACTS_PRISONERS_WEBSITE = "PRISONERS Scheme website";

    public static final String ANCHOR_ACCESSIBILITY_ABILITY_NET = "AbilityNet";
    public static final String ANCHOR_ACCESSIBILITY_EMAIL = "Customer.EXPERIENCE@service.education.gov.uk";
    public static final String ANCHOR_ACCESSIBILITY_EMAIL2 = ANCHOR_ACCESSIBILITY_EMAIL;
    public static final String ANCHOR_ACCESSIBILITY_EASS = "contact the Equality Advisory and Support Service (EASS)";

    public static final String ANCHOR_ACCESSIBILITY_WCAG = "Web Content Accessibility Guidelines version 2.2 AA standard";
    public static final String ANCHOR_ACCESSIBILITY_WCAG2 = ANCHOR_ACCESSIBILITY_WCAG;

    public static final String CURRENT_PAGE_URL = "CURRENT_PAGE_URL";
    public static final String SCHEME_SORT = "scheme-sort";

    public static final String ANCHOR_COMPARISON_PAGE_CLEAR_FILTERS = "Clear filters and show all schemes";
    public static final String BLUE = "#EBF0F5";
    public static final String PINK = "#F6ECF1";
    public static final String PAGE_TITLE_SCHEME_HOME = "Scheme Home";
    public static final String PAGE_TITLE_LANDING = "Teach in further education";


    //private WebDriver driver;
    private Environments.Environment environment;

    Map<String, String> headersMap = new HashMap<>();
    Map<String, String> findSchemesMap = new HashMap<>();


    Map<String, String> footerMap = new HashMap<>();


    public static Map<String, String> schemesExploreSectionMap = new LinkedHashMap<>();

    static {
        //explore section ordered list
        schemesExploreSectionMap.put(ANCHOR_APPRENTICESHIPS.get(SCHEME_ANCHOR_NAME_EXPLORE_SECTION), ANCHOR_APPRENTICESHIPS.get(SCHEME_ANCHOR_NAME_EXPLORE_SECTION));
        schemesExploreSectionMap.put(ANCHOR_T_LEVELS.get(SCHEME_ANCHOR_NAME_EXPLORE_SECTION), ANCHOR_T_LEVELS.get(SCHEME_ANCHOR_NAME_EXPLORE_SECTION));
        schemesExploreSectionMap.put(ANCHOR_BOOTCAMPS.get(SCHEME_ANCHOR_NAME_EXPLORE_SECTION), ANCHOR_BOOTCAMPS.get(SCHEME_ANCHOR_NAME_EXPLORE_SECTION));
        schemesExploreSectionMap.put(ANCHOR_MULTIPLY.get(SCHEME_ANCHOR_NAME_EXPLORE_SECTION), ANCHOR_MULTIPLY.get(SCHEME_ANCHOR_NAME_EXPLORE_SECTION));
        schemesExploreSectionMap.put(ANCHOR_HTQ.get(SCHEME_ANCHOR_NAME_EXPLORE_SECTION), ANCHOR_HTQ.get(SCHEME_ANCHOR_NAME_EXPLORE_SECTION));
        schemesExploreSectionMap.put(ANCHOR_FREE_COURSES.get(SCHEME_ANCHOR_NAME_EXPLORE_SECTION), ANCHOR_FREE_COURSES.get(SCHEME_ANCHOR_NAME_EXPLORE_SECTION));
        schemesExploreSectionMap.put(ANCHOR_CARE_LEAVER_COVENANT.get(SCHEME_ANCHOR_NAME_EXPLORE_SECTION), ANCHOR_CARE_LEAVER_COVENANT.get(SCHEME_ANCHOR_NAME_EXPLORE_SECTION));
        schemesExploreSectionMap.put(ANCHOR_INTERNSHIPS.get(SCHEME_ANCHOR_NAME_EXPLORE_SECTION), "Supported internships for learners with an education, health and"); //todo title not complete
        schemesExploreSectionMap.put(ANCHOR_SWAP.get(SCHEME_ANCHOR_NAME_EXPLORE_SECTION), ANCHOR_SWAP.get(SCHEME_ANCHOR_NAME_EXPLORE_SECTION));
        schemesExploreSectionMap.put(ANCHOR_PRISONERS.get(SCHEME_ANCHOR_NAME_EXPLORE_SECTION), ANCHOR_PRISONERS.get(SCHEME_ANCHOR_NAME_EXPLORE_SECTION));
    }

    public static Map<String, String> schemesHomePageDefaultMap = new LinkedHashMap<>();

    static {
        //default ordered list
        schemesHomePageDefaultMap.put(ANCHOR_APPRENTICESHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_APPRENTICESHIPS.get(SCHEME_ANCHOR_NAME_EXPLORE_SECTION));
        schemesHomePageDefaultMap.put(ANCHOR_T_LEVELS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_T_LEVELS.get(SCHEME_ANCHOR_NAME_EXPLORE_SECTION));
        schemesHomePageDefaultMap.put(ANCHOR_BOOTCAMPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_BOOTCAMPS.get(SCHEME_ANCHOR_NAME_EXPLORE_SECTION));
        schemesHomePageDefaultMap.put(ANCHOR_MULTIPLY.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_MULTIPLY.get(SCHEME_ANCHOR_NAME_EXPLORE_SECTION));
        schemesHomePageDefaultMap.put(ANCHOR_HTQ.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_HTQ.get(SCHEME_ANCHOR_NAME_EXPLORE_SECTION));
        schemesHomePageDefaultMap.put(ANCHOR_FREE_COURSES.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_FREE_COURSES.get(SCHEME_ANCHOR_NAME_EXPLORE_SECTION));
        schemesHomePageDefaultMap.put(ANCHOR_CARE_LEAVER_COVENANT.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_CARE_LEAVER_COVENANT.get(SCHEME_ANCHOR_NAME_EXPLORE_SECTION));
        schemesHomePageDefaultMap.put(ANCHOR_INTERNSHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), "Supported internships for learners with an education, health and"); //todo title not complete
        schemesHomePageDefaultMap.put(ANCHOR_SWAP.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_SWAP.get(SCHEME_ANCHOR_NAME_EXPLORE_SECTION));
        schemesHomePageDefaultMap.put(ANCHOR_PRISONERS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_PRISONERS.get(SCHEME_ANCHOR_NAME_EXPLORE_SECTION));
    }

    public static Map<String, String> schemesHomePagePopularityMap = new LinkedHashMap<>();

    static {
        //Popularity ordered list
        schemesHomePagePopularityMap.put(ANCHOR_APPRENTICESHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_APPRENTICESHIPS.get(SCHEME_ANCHOR_NAME_EXPLORE_SECTION));
        schemesHomePagePopularityMap.put(ANCHOR_BOOTCAMPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_BOOTCAMPS.get(SCHEME_ANCHOR_NAME_EXPLORE_SECTION));
        schemesHomePagePopularityMap.put(ANCHOR_T_LEVELS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_T_LEVELS.get(SCHEME_ANCHOR_NAME_EXPLORE_SECTION));
        schemesHomePagePopularityMap.put(ANCHOR_FREE_COURSES.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_FREE_COURSES.get(SCHEME_ANCHOR_NAME_EXPLORE_SECTION));
        schemesHomePagePopularityMap.put(ANCHOR_HTQ.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_HTQ.get(SCHEME_ANCHOR_NAME_EXPLORE_SECTION));
        schemesHomePagePopularityMap.put(ANCHOR_SWAP.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_SWAP.get(SCHEME_ANCHOR_NAME_EXPLORE_SECTION));
        schemesHomePagePopularityMap.put(ANCHOR_INTERNSHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), "Supported internships for learners with an education, health and"); //todo title not complete
        schemesHomePagePopularityMap.put(ANCHOR_MULTIPLY.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_MULTIPLY.get(SCHEME_ANCHOR_NAME_EXPLORE_SECTION));
        schemesHomePagePopularityMap.put(ANCHOR_PRISONERS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_PRISONERS.get(SCHEME_ANCHOR_NAME_EXPLORE_SECTION));
        schemesHomePagePopularityMap.put(ANCHOR_CARE_LEAVER_COVENANT.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_CARE_LEAVER_COVENANT.get(SCHEME_ANCHOR_NAME_EXPLORE_SECTION));
    }

    public static Map<String, String> schemesHomePageDurationMap = new LinkedHashMap<>();

    static {
        //Duration ordered list
        schemesHomePageDurationMap.put(ANCHOR_MULTIPLY.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_MULTIPLY.get(SCHEME_ANCHOR_NAME_EXPLORE_SECTION));
        schemesHomePageDurationMap.put(ANCHOR_SWAP.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_SWAP.get(SCHEME_ANCHOR_NAME_EXPLORE_SECTION));
        schemesHomePageDurationMap.put(ANCHOR_T_LEVELS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_T_LEVELS.get(SCHEME_ANCHOR_NAME_EXPLORE_SECTION));
        schemesHomePageDurationMap.put(ANCHOR_BOOTCAMPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_BOOTCAMPS.get(SCHEME_ANCHOR_NAME_EXPLORE_SECTION));
        schemesHomePageDurationMap.put(ANCHOR_INTERNSHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), "Supported internships for learners with an education, health and"); //todo title not complete
        schemesHomePageDurationMap.put(ANCHOR_HTQ.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_HTQ.get(SCHEME_ANCHOR_NAME_EXPLORE_SECTION));
        schemesHomePageDurationMap.put(ANCHOR_APPRENTICESHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_APPRENTICESHIPS.get(SCHEME_ANCHOR_NAME_EXPLORE_SECTION));
        schemesHomePageDurationMap.put(ANCHOR_FREE_COURSES.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_FREE_COURSES.get(SCHEME_ANCHOR_NAME_EXPLORE_SECTION));
        schemesHomePageDurationMap.put(ANCHOR_CARE_LEAVER_COVENANT.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_CARE_LEAVER_COVENANT.get(SCHEME_ANCHOR_NAME_EXPLORE_SECTION));
        schemesHomePageDurationMap.put(ANCHOR_PRISONERS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_PRISONERS.get(SCHEME_ANCHOR_NAME_EXPLORE_SECTION));
    }

    public static Map<String, String> schemesHomePageCostMap = new LinkedHashMap<>();

    static {
        //Cost ordered list
        schemesHomePageCostMap.put(ANCHOR_FREE_COURSES.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_FREE_COURSES.get(SCHEME_ANCHOR_NAME_EXPLORE_SECTION));
        schemesHomePageCostMap.put(ANCHOR_MULTIPLY.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_MULTIPLY.get(SCHEME_ANCHOR_NAME_EXPLORE_SECTION));
        schemesHomePageCostMap.put(ANCHOR_T_LEVELS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_T_LEVELS.get(SCHEME_ANCHOR_NAME_EXPLORE_SECTION));
        schemesHomePageCostMap.put(ANCHOR_SWAP.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_SWAP.get(SCHEME_ANCHOR_NAME_EXPLORE_SECTION));
        schemesHomePageCostMap.put(ANCHOR_CARE_LEAVER_COVENANT.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_CARE_LEAVER_COVENANT.get(SCHEME_ANCHOR_NAME_EXPLORE_SECTION));
        schemesHomePageCostMap.put(ANCHOR_PRISONERS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_PRISONERS.get(SCHEME_ANCHOR_NAME_EXPLORE_SECTION));
        schemesHomePageCostMap.put(ANCHOR_INTERNSHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), "Supported internships for learners with an education, health and"); //todo title not complete
        schemesHomePageCostMap.put(ANCHOR_BOOTCAMPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_BOOTCAMPS.get(SCHEME_ANCHOR_NAME_EXPLORE_SECTION));
        schemesHomePageCostMap.put(ANCHOR_APPRENTICESHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_APPRENTICESHIPS.get(SCHEME_ANCHOR_NAME_EXPLORE_SECTION));
        schemesHomePageCostMap.put(ANCHOR_HTQ.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_HTQ.get(SCHEME_ANCHOR_NAME_EXPLORE_SECTION));
    }

    public static Map<String, String> schemesComparisonPageMap = new LinkedHashMap<>();

    static {
        //Comparison ordered list
        schemesComparisonPageMap.put(ANCHOR_APPRENTICESHIPS.get(SCHEME_ANCHOR_NAME_COMPARISON_PAGE), ANCHOR_APPRENTICESHIPS.get(SCHEME_ANCHOR_NAME_EXPLORE_SECTION));
        schemesComparisonPageMap.put(ANCHOR_T_LEVELS.get(SCHEME_ANCHOR_NAME_COMPARISON_PAGE), ANCHOR_T_LEVELS.get(SCHEME_ANCHOR_NAME_EXPLORE_SECTION));
        schemesComparisonPageMap.put(ANCHOR_BOOTCAMPS.get(SCHEME_ANCHOR_NAME_COMPARISON_PAGE), ANCHOR_BOOTCAMPS.get(SCHEME_ANCHOR_NAME_EXPLORE_SECTION));
        schemesComparisonPageMap.put(ANCHOR_MULTIPLY.get(SCHEME_ANCHOR_NAME_COMPARISON_PAGE), ANCHOR_MULTIPLY.get(SCHEME_ANCHOR_NAME_EXPLORE_SECTION));
        schemesComparisonPageMap.put(ANCHOR_HTQ.get(SCHEME_ANCHOR_NAME_COMPARISON_PAGE), ANCHOR_HTQ.get(SCHEME_ANCHOR_NAME_EXPLORE_SECTION));
        schemesComparisonPageMap.put(ANCHOR_FREE_COURSES.get(SCHEME_ANCHOR_NAME_COMPARISON_PAGE), ANCHOR_FREE_COURSES.get(SCHEME_ANCHOR_NAME_EXPLORE_SECTION));
        schemesComparisonPageMap.put(ANCHOR_CARE_LEAVER_COVENANT.get(SCHEME_ANCHOR_NAME_COMPARISON_PAGE), ANCHOR_CARE_LEAVER_COVENANT.get(SCHEME_ANCHOR_NAME_EXPLORE_SECTION));
        schemesComparisonPageMap.put(ANCHOR_INTERNSHIPS.get(SCHEME_ANCHOR_NAME_COMPARISON_PAGE), "Supported internships for learners with an education, health and"); //todo title not complete
        schemesComparisonPageMap.put(ANCHOR_SWAP.get(SCHEME_ANCHOR_NAME_COMPARISON_PAGE), ANCHOR_SWAP.get(SCHEME_ANCHOR_NAME_EXPLORE_SECTION));
        schemesComparisonPageMap.put(ANCHOR_PRISONERS.get(SCHEME_ANCHOR_NAME_COMPARISON_PAGE), ANCHOR_PRISONERS.get(SCHEME_ANCHOR_NAME_EXPLORE_SECTION));
    }


    public static Map<String, String> homePageToComparePageSchemeMap = new HashMap<>();

    static {
        homePageToComparePageSchemeMap.put(ANCHOR_APPRENTICESHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_APPRENTICESHIPS.get(SCHEME_ANCHOR_NAME_COMPARISON_PAGE));
        homePageToComparePageSchemeMap.put(ANCHOR_T_LEVELS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_T_LEVELS.get(SCHEME_ANCHOR_NAME_COMPARISON_PAGE));
        homePageToComparePageSchemeMap.put(ANCHOR_BOOTCAMPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_BOOTCAMPS.get(SCHEME_ANCHOR_NAME_COMPARISON_PAGE));
        homePageToComparePageSchemeMap.put(ANCHOR_MULTIPLY.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_MULTIPLY.get(SCHEME_ANCHOR_NAME_COMPARISON_PAGE));
        homePageToComparePageSchemeMap.put(ANCHOR_HTQ.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_HTQ.get(SCHEME_ANCHOR_NAME_COMPARISON_PAGE));
        homePageToComparePageSchemeMap.put(ANCHOR_FREE_COURSES.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_FREE_COURSES.get(SCHEME_ANCHOR_NAME_COMPARISON_PAGE));
        homePageToComparePageSchemeMap.put(ANCHOR_CARE_LEAVER_COVENANT.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_CARE_LEAVER_COVENANT.get(SCHEME_ANCHOR_NAME_COMPARISON_PAGE));
        homePageToComparePageSchemeMap.put(ANCHOR_INTERNSHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_INTERNSHIPS.get(SCHEME_ANCHOR_NAME_COMPARISON_PAGE));
        homePageToComparePageSchemeMap.put(ANCHOR_SWAP.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_SWAP.get(SCHEME_ANCHOR_NAME_COMPARISON_PAGE));
        homePageToComparePageSchemeMap.put(ANCHOR_PRISONERS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_PRISONERS.get(SCHEME_ANCHOR_NAME_COMPARISON_PAGE));
    }

    public static Map<String, String> comparisonPageMap = new HashMap<>();

    static {
        comparisonPageMap.put(ANCHOR_COMPARISON_PAGE_CLEAR_FILTERS, PAGE_TITLE_SCHEME_HOME);
    }

 //todo remove   public static Map<String, String> backMap = new LinkedHashMap<>();

    //todo remove    static {
//todo remove         backMap.put(ANCHOR_BACK, PAGE_TITLE_SCHEME_HOME);
    //todo remove    }

    public static Map<String, String> homePageMap = new LinkedHashMap<>();

    static {
        //homePageMap.put(ANCHOR_HOME_SKILLS_FOR_CAREERS, "https://www.skillsforcareers.education.gov.uk/");
        //homePageMap.put(ANCHOR_SKIP_TO_MAIN_CONTENT, "https://at-teach-in-further-education.apprenticeships.education.gov.uk/#main-content");
        //homePageMap.put(ANCHOR_HOME_MAINPAGE, "/");
        //homePageMap.put(ANCHOR_HOME_WHAT_IS_FE_TEACHING, "https://at-teach-in-further-education.apprenticeships.education.gov.uk/cookies");
        //homePageMap.put(ANCHOR_HOME_HEAR_FROM_CURRENT_FE_TEACHERS, "https://www.google.com/");
       // homePageMap.put(ANCHOR_HOME_FIND_A_JOB_IN_FE, "https://www.google.com/");
        // homePageMap.put(ANCHOR_HOME_FIND_YOUR_LOCAL_COLLEGES, "https://www.google.com/");
        // homePageMap.put(ANCHOR_HOME_FUNDING_AND_TRAINING_OPTIONS, "https://www.google.com/");


        homePageMap.put(ANCHOR_CONTENTS_BENEFITS, "CURRENT_PAGE_URL#apprenticeships-benefits-for-your-business");
        homePageMap.put(ANCHOR_CONTENTS_STORIES, "CURRENT_PAGE_URL#apprenticeships-employer-stories");
        homePageMap.put(ANCHOR_CONTENTS_CASE_STUDIES, "CURRENT_PAGE_URL#apprenticeships-case-studies");
        homePageMap.put(ANCHOR_CONTENTS_COST, "CURRENT_PAGE_URL#apprenticeships-cost-to-my-business");
        homePageMap.put(ANCHOR_CONTENTS_RESPONSIBILITIES, "CURRENT_PAGE_URL#apprenticeships-responsibilities-as-an-employer");
        homePageMap.put(ANCHOR_CONTENTS_FIND_OUT_MORE, "CURRENT_PAGE_URL#apprenticeships-employing-an-apprentice");
        homePageMap.put(ANCHOR_APPRENTICESHIP_THE_MINIMUM_WAGE, "https://www.gov.uk/national-minimum-wage-rates#apprentices");
        homePageMap.put(ANCHOR_APPRENTICESHIP_CALCULATE_YOUR_ESTIMATED_TRAINING_COST_FUNDING, "https://www.apprenticeships.gov.uk/employers/understanding-apprenticeship-benefits-and-funding#available-funding");
        homePageMap.put(ANCHOR_APPRENTICESHIP_STEP_BY_STEP, "https://www.apprenticeships.gov.uk/employers/the-road-to-a-quality-apprenticeship");
        homePageMap.put(ANCHOR_APPRENTICESHIP_EMAIL, "mailto:" + ANCHOR_APPRENTICESHIP_EMAIL);

        homePageMap.put("Learn more about the Apprenticeship Ambassador Network", "https://www.apprenticeships.gov.uk/influencers/yaan-regional-networks");
        homePageMap.put("Get guidance on developing apprenticeships", "https://www.instituteforapprenticeships.org/developing-new-apprenticeships/");
        homePageMap.put("Sign up for the employer skills newsletter", "https://www.apprenticeships.gov.uk/employers/sign-up");
        homePageMap.put("Get financial support to offer apprenticeships", "https://find-employer-schemes.education.gov.uk/interim/get-financial-support-to-offer-apprenticeships");

    }

    public static Map<String, String> tLevels = new LinkedHashMap<>();

    static {
        tLevels.put(ANCHOR_TLEVEL_SEE_MORE_ABOUT_HOW_INDUSTRY_PLACEMENTS_WORK, "https://employers.tlevels.gov.uk/hc/en-gb/articles/4403442955154-Which-industry-placement-models-could-work-for-you-");
        tLevels.put(ANCHOR_HOME_SKILLS_FOR_CAREERS, "https://www.skillsforcareers.education.gov.uk/");

        tLevels.put(ANCHOR_CONTENTS_BENEFITS, "CURRENT_PAGE_URL#t-levels-benefits-for-your-business");
        tLevels.put(ANCHOR_CONTENTS_STORIES, "CURRENT_PAGE_URL#t-levels-employer-stories");
        tLevels.put(ANCHOR_CONTENTS_COST, "CURRENT_PAGE_URL#t-levels-cost-to-my-business");
        tLevels.put(ANCHOR_CONTENTS_RESPONSIBILITIES, "CURRENT_PAGE_URL#t-levels-responsibilities-as-an-employer");
        tLevels.put(ANCHOR_CONTENTS_CASE_STUDIES, "CURRENT_PAGE_URL#t-levels-case-studies");
        tLevels.put(ANCHOR_CONTENTS_FIND_OUT_MORE, "CURRENT_PAGE_URL#t-levels-employing-an-apprentice");

        tLevels.put(ANCHOR_TLEVEL_POTENTIAL_BENEFITS, "https://employers.tlevels.gov.uk/hc/en-gb/articles/4403442965266#h_01F6S75ZDY82ZPCV5TCVJR6WFB");
        tLevels.put(ANCHOR_TLEVEL_ACCELERATE_THEIR_APPRENTICESHIPS, "https://www.gov.uk/guidance/how-can-apprenticeships-be-delivered-for-your-business#accelerated-apprenticeships");
        tLevels.put(ANCHOR_TLEVEL_EMPLOYER_SUPPORT, "https://www.gov.uk/government/publications/employer-support-fund-for-t-level-industry-placements");
        tLevels.put(ANCHOR_TLEVEL_INDUSTRY_PLACEMENT, "https://employers.tlevels.gov.uk/hc/en-gb/articles/4403450083346-Your-industry-placement-responsibilities");

        tLevels.put(ANCHOR_TLEVEL_GET_MORE_INFORMATION_ABOUT_T_LEVELS_AND_INDUSTRY_PLACEMENTS, "https://employers.tlevels.gov.uk/hc/en-gb");
        tLevels.put(ANCHOR_TLEVEL_REGISTER_YOUR_INTEREST_IN_HOSTING, "https://employers.tlevels.gov.uk/hc/en-gb/articles/8481078453138");

        tLevels.put("T Level online events", "https://employers.tlevels.gov.uk/hc/en-gb/sections/4403450054674-Webinars");
        tLevels.put("Follow T Levels on Linkedin", "https://www.linkedin.com.mcas.ms/company/t-levels");
        tLevels.put("Sign up for the employer skills newsletter", "https://www.apprenticeships.gov.uk/employers/sign-up");

    }

    public static Map<String, String> bootcamps = new LinkedHashMap<>();

    static {
        bootcamps.put(ANCHOR_HOME_SKILLS_FOR_CAREERS, "https://www.skillsforcareers.education.gov.uk/");

        bootcamps.put(ANCHOR_CONTENTS_BENEFITS, "CURRENT_PAGE_URL#skills-bootcamps-benefits-for-your-business");
        bootcamps.put(ANCHOR_CONTENTS_STORIES, "CURRENT_PAGE_URL#skills-bootcamps-employer-stories");

        bootcamps.put(ANCHOR_CONTENTS_CASE_STUDIES, "CURRENT_PAGE_URL#skills-bootcamps-case-studies");

        bootcamps.put(ANCHOR_CONTENTS_COST, "CURRENT_PAGE_URL#skills-bootcamps-cost-to-my-business");
        bootcamps.put(ANCHOR_CONTENTS_RESPONSIBILITIES, "CURRENT_PAGE_URL#skills-bootcamps-responsibilities-as-an-employer");
        bootcamps.put(ANCHOR_CONTENTS_FIND_OUT_MORE, "CURRENT_PAGE_URL#skills-bootcamps-employing-an-apprentice");

        bootcamps.put(ANCHOR_BOOTCAMP_ACCELERATE_THEIR_APPRENTICESHIPS, "How can apprenticeships be delivered for your business? - GOV.UK");
        bootcamps.put(ANCHOR_BOOTCAMP_FINDING_TRAINING_PROVIDERS_WHO_ARE_CURRENTLY_BEING_FUNDED_TO_DELIVER_SKILLS_BOOTCAMPS, "Skills Bootcamps training providers - GOV.UK");
        bootcamps.put(ANCHOR_BOOTCAMP_READ_MORE_ON_APPLYING_TO_DELIVER_SKILLS_BOOTCAMPS, "https://www.gov.uk/guidance/bid-to-supply-skills-bootcamps");
    }
    public static Map<String, String> employer_standards = new LinkedHashMap<>();

    static {
        employer_standards.put("the Employer Standards is a new, free framework and digital tool", "https://www.careersandenterprise.co.uk/employers/employer-standards/");
        employer_standards.put("Take the free self-assessment", "https://bit.ly/skillsforlifees");
    }

    public static Map<String, String> multiply = new LinkedHashMap<>();

    static {
        multiply.put(ANCHOR_HOME_SKILLS_FOR_CAREERS, "https://www.skillsforcareers.education.gov.uk/");

        multiply.put(ANCHOR_CONTENTS_BENEFITS, "CURRENT_PAGE_URL#multiply-benefits-for-your-business");
        multiply.put(ANCHOR_CONTENTS_COST, "CURRENT_PAGE_URL#multiply-cost-to-my-business");
        multiply.put(ANCHOR_CONTENTS_RESPONSIBILITIES, "CURRENT_PAGE_URL#multiply-responsibilities-as-an-employer");
        multiply.put(ANCHOR_CONTENTS_FIND_OUT_MORE, "CURRENT_PAGE_URL#multiply-employing-an-apprentice");

        multiply.put(ANCHOR_MULTIPLY_QUIZ, "https://www.riddle.com/view/373677");
        multiply.put(ANCHOR_MULTIPLY_EMAIL, "mailto:" + ANCHOR_MULTIPLY_EMAIL);
        multiply.put(ANCHOR_MULTIPLY_READ_MORE_ABOUT_MULTIPLY_SKILLS_FOR_LIFE, "https://nationalcareers.service.gov.uk/find-a-course/page?searchTerm=Multiply&distance=10%20miles&town=&orderByValue=Relevance&startDate=Anytime&courseType=&courseHours=&courseStudyTime=&filterA=true&page=1&D=0&coordinates=&campaignCode=&qualificationLevels=");
    }

    public static Map<String, String> htq = new LinkedHashMap<>();

    static {
        htq.put(ANCHOR_HOME_SKILLS_FOR_CAREERS, "https://www.skillsforcareers.education.gov.uk/");

        htq.put(ANCHOR_CONTENTS_BENEFITS, "CURRENT_PAGE_URL#htqs-benefits-for-your-business");
        htq.put("Higher Technical Qualifications video", "CURRENT_PAGE_URL#htqs-employer-stories");
        htq.put(ANCHOR_CONTENTS_COST, "CURRENT_PAGE_URL#htqs-cost-to-my-business");
        htq.put(ANCHOR_CONTENTS_RESPONSIBILITIES, "CURRENT_PAGE_URL#htqs-responsibilities-as-an-employer");
        htq.put(ANCHOR_CONTENTS_CASE_STUDIES, "CURRENT_PAGE_URL#htqs-case-studies");
        htq.put(ANCHOR_CONTENTS_FIND_OUT_MORE, "CURRENT_PAGE_URL#htqs-employing-an-apprentice");


        htq.put("HTQ guidance on GOV.UK", "https://www.gov.uk/government/publications/higher-technical-qualification-overview");
        htq.put("UCAS website", "https://www.ucas.com/");
        htq.put("National Careers Service", "https://nationalcareers.service.gov.uk/");

        htq.put(ANCHOR_HTQ_LEARN_MORE_AND_GET_STARTED_WITH_HTQS, "Providers delivering Higher Technical Qualifications - GOV.UK");
        htq.put("See the full list of the available and upcoming subjects for HTQs", "https://www.gov.uk/government/publications/higher-technical-qualification-overview/higher-technical-qualification-an-introduction#available-subjects");


    }

    public static Map<String, String> freeCourses = new LinkedHashMap<>();

    static {
        freeCourses.put(ANCHOR_FREECOURSES_FOR_JOBS, "Free courses for jobs - GOV.UK");
        freeCourses.put(ANCHOR_HOME_SKILLS_FOR_CAREERS, "https://www.skillsforcareers.education.gov.uk/");

        freeCourses.put(ANCHOR_CONTENTS_BENEFITS, "CURRENT_PAGE_URL#free-courses-benefits-for-your-business");
        freeCourses.put(ANCHOR_CONTENTS_COST, "CURRENT_PAGE_URL#free-courses-cost-to-my-business");


        freeCourses.put(ANCHOR_CONTENTS_RESPONSIBILITIES, "CURRENT_PAGE_URL#free-courses-responsibilities-as-an-employer");
        freeCourses.put(ANCHOR_CONTENTS_FIND_OUT_MORE, "CURRENT_PAGE_URL#free-courses-employing-an-apprentice");

        freeCourses.put("Find out who is eligible for free Level 3 courses", "https://www.gov.uk/guidance/free-courses-for-jobs");
        freeCourses.put(ANCHOR_FREECOURSES_THE_NATIONAL_CAREERS_SERVICE_AND_HOW_TO_CONTACT_THEM, "https://nationalcareers.service.gov.uk/find-a-course/page?searchTerm=&town=&courseType=[%22%22]&courseHours=[%22%22]&courseStudyTime=[%22%22]&startDate=Anytime&distance=10%20miles&filterA=True&orderByValue=Relevance&coordinates=&campaignCode=LEVEL3_FREE&qualificationLevels=[]&page=11&D=0");
    }

    public static Map<String, String> careLeaverCovenant = new LinkedHashMap<>();

    static {
        careLeaverCovenant.put(ANCHOR_CARERLEAVERCOVENANT_THE_INCLUSIVE_EMPLOYER_TOOLKIT, "https://mycovenant.org.uk/support-the-covenant/inclusive-employment-toolkit/");
        careLeaverCovenant.put(ANCHOR_HOME_SKILLS_FOR_CAREERS, "https://www.skillsforcareers.education.gov.uk/");

        careLeaverCovenant.put(ANCHOR_CONTENTS_BENEFITS, "CURRENT_PAGE_URL#care-leavers-benefits-for-your-business");
        careLeaverCovenant.put(ANCHOR_CONTENTS_COST, "CURRENT_PAGE_URL#care-leavers-cost-to-my-business");
        careLeaverCovenant.put(ANCHOR_CONTENTS_RESPONSIBILITIES, "CURRENT_PAGE_URL#care-leavers-responsibilities-as-an-employer");
        careLeaverCovenant.put(ANCHOR_CONTENTS_FIND_OUT_MORE, "CURRENT_PAGE_URL#care-leavers-employing-an-apprentice");

        careLeaverCovenant.put(ANCHOR_CARERLEAVERCOVENANT_FIND_OUT_HOW_TO_JOIN_THE_CARE_LEAVERS_COVENANT, "https://mycovenant.org.uk/about/");
        careLeaverCovenant.put(ANCHOR_CARERLEAVERCOVENANT_EMAIL, "mailto:" + ANCHOR_CARERLEAVERCOVENANT_EMAIL);
        careLeaverCovenant.put("Follow the Care Leaver Covenant on Linkedin", "https://www.linkedin.com.mcas.ms/company/care-leaver-cov/");

    }

    public static Map<String, String> internships = new LinkedHashMap<>();

    static {
        internships.put(ANCHOR_INTERNSHIPS_EDUCATION_HEALTH_AND_CARE_EHC_PLAN, "Children with special educational needs and disabilities (SEND): Extra help - GOV.UK");
        internships.put(ANCHOR_HOME_SKILLS_FOR_CAREERS, "https://www.skillsforcareers.education.gov.uk/");
        internships.put(ANCHOR_CONTENTS_BENEFITS, "CURRENT_PAGE_URL#supported-internships-benefits-for-your-business");
        internships.put(ANCHOR_CONTENTS_COST, "CURRENT_PAGE_URL#supported-internships-cost-to-my-business");
        internships.put(ANCHOR_CONTENTS_RESPONSIBILITIES, "CURRENT_PAGE_URL#supported-internships-responsibilities-as-an-employer");
        internships.put(ANCHOR_CONTENTS_FIND_OUT_MORE, "CURRENT_PAGE_URL#supported-internships-employing-an-apprentice");

        internships.put(ANCHOR_INTERNSHIPS_GUIDANCE_ON_SUPPORTED_INTERNSHIPS, "Providing supported internships for young people with an EHC plan - GOV.UK");
        internships.put(ANCHOR_INTERNSHIPS_WORK_LEAD, "https://www.ndti.org.uk/about-us/ndti-people/ndti-regional-leads");
        internships.put("Employing disabled people and people with health conditions", "https://www.gov.uk/government/publications/employing-disabled-people-and-people-with-health-conditions");
        internships.put("Support with employee health and disability service", "https://www.support-with-employee-health-and-disability.dwp.gov.uk/support-with-employee-health-and-disability");


    }

    public static Map<String, String> swap = new LinkedHashMap<>();

    static {
        swap.put(ANCHOR_HOME_SKILLS_FOR_CAREERS, "https://www.skillsforcareers.education.gov.uk/");

        swap.put(ANCHOR_CONTENTS_BENEFITS, "CURRENT_PAGE_URL#swaps-benefits-for-your-business");
        swap.put(ANCHOR_CONTENTS_COST, "CURRENT_PAGE_URL#swaps-cost-to-my-business");
        swap.put(ANCHOR_CONTENTS_RESPONSIBILITIES, "CURRENT_PAGE_URL#swaps-responsibilities-as-an-employer");
        swap.put(ANCHOR_CONTENTS_FIND_OUT_MORE, "CURRENT_PAGE_URL#swaps-employing-an-apprentice");

        swap.put(ANCHOR_SWAP_READ_THE_EMPLOYER_GUIDE_ON_GOV_UK, "https://www.gov.uk/government/publications/sector-based-work-academies-employer-guide");
        swap.put(ANCHOR_SWAP_CONTACT_THE_EMPLOYER_SERVICES_LINE, "https://www.gov.uk/jobcentre-plus-help-for-recruiters/recruitment-advice-and-support#contact-the-employer-services-line");
    }

    public static Map<String, String> prisoners = new LinkedHashMap<>();

    static {
        prisoners.put(ANCHOR_HOME_SKILLS_FOR_CAREERS, "https://www.skillsforcareers.education.gov.uk/");

        prisoners.put(ANCHOR_CONTENTS_BENEFITS, "CURRENT_PAGE_URL#prisoners-benefits-for-your-business");
        prisoners.put(ANCHOR_CONTENTS_CASE_STUDIES, "CURRENT_PAGE_URL#prisoners-case-studies");
        prisoners.put(ANCHOR_CONTENTS_COST, "CURRENT_PAGE_URL#prisoners-cost-to-my-business");
        prisoners.put(ANCHOR_CONTENTS_RESPONSIBILITIES, "CURRENT_PAGE_URL#prisoners-responsibilities-as-an-employer");
        prisoners.put(ANCHOR_CONTENTS_FIND_OUT_MORE, "CURRENT_PAGE_URL#prisoners-employing-an-apprentice");

        prisoners.put(ANCHOR_PRISONERS_REGISTER_YOUR_INTEREST_ON_THE_NEW_FUTURES_NETWORK_WEBSITE, "https://newfuturesnetwork.gov.uk/");
        prisoners.put("Read guidance on employing prisoners and ex-offenders", "https://www.gov.uk/government/publications/unlock-opportunity-employer-information-pack-and-case-studies");
        prisoners.put("Follow New Futures Network on Linkedin", "https://www.linkedin.com.mcas.ms/company/new-futures-network/");


    }

    public static Map<String, String> homeMap = new LinkedHashMap<>();

    static {

        //homeMap.put(ANCHOR_HOME_SKILLS_FOR_CAREERS, "https://www.skillsforcareers.education.gov.uk/");
        homeMap.put("What is FE teaching?", "Scheme Home");
        homeMap.put("Hear from current FE teachers", "https://www.gov.uk/business/finance-support");
        homeMap.put("Find a job in FE", "https://www.gov.uk/browse/employing-people");
        homeMap.put("Find your local colleges", "https://www.gov.uk/employ-someone");
        //homeMap.put("Employer standards for careers education", "Find training and employment schemes for your business - Employer Standards for careers education");
    }

    Map<String, String> contacts = new HashMap<>();
    Map<String, String> accessibility = new HashMap<>();

    /*@Before(order = 1)
    public void setup(Scenario scenario) {
        if (driver == null) {
            var webdriver = System.getProperty("browser", "chrome").toLowerCase();
            try {
                driver = DriverFactory.getBaseDriver(DriverFactory.DriverType.value(webdriver));
                driver.manage().window().maximize();
                //need more time for videos to load
                driver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        final var env = System.getProperty("env", "at");
        environment = Environments.get(env);
        driver.get(environment.getUrl());

        //headersMap.put(ANCHOR_HEADER_VIEW_COOKIES, "Cookies");
        //headersMap.put(ANCHOR_HEADER_SKIP_TO_MAIN_CONTENT, PAGE_TITLE_SCHEME_HOME);
        //headersMap.put(ANCHOR_HEADER_BETA_BANNER_FEEDBACK, "https://dferesearch.fra1.qualtrics.com/jfe/form/SV_3geG7aDVWFuYTvo");


        //findSchemesMap.put(ANCHOR_FIND_SCHEMES, PAGE_TITLE_SCHEME_HOME);
        findSchemesMap.put(ANCHOR_HOME, PAGE_TITLE_LANDING);

        //findSchemesMap.put(ANCHOR_HOME_PAGE_CLEAR_FILTERS, PAGE_TITLE_SCHEME_HOME);
        //findSchemesMap.put(ANCHOR_HOME_SKILLS_FOR_CAREERS, "https://www.skillsforcareers.education.gov.uk/");


        footerMap.put(ANCHOR_SHARE_EMAIL, "mailto:?body=" + CURRENT_PAGE_URL);
        footerMap.put(ANCHOR_SHARE_FACEBOOK, "https://www.facebook.com/sharer.php?u=" + CURRENT_PAGE_URL);
        footerMap.put(ANCHOR_SHARE_X, "https://twitter.com/intent/tweet?url=" + CURRENT_PAGE_URL);
        footerMap.put(ANCHOR_SHARE_LINKEDIN, "https://www.linkedin.com/sharing/share-offsite/?url=" + CURRENT_PAGE_URL);
        footerMap.put(ANCHOR_FOOTER_PRIVACY, "https://www.gov.uk/government/organisations/department-for-education/about/personal-information-charter");
        footerMap.put(ANCHOR_FOOTER_COOKIES, "Cookies");
        footerMap.put(ANCHOR_FOOTER_ACCESSIBILITY_STATEMENT, "Accessibility statement");
        footerMap.put(ANCHOR_FOOTER_CONTACT, "Contact");
        footerMap.put(ANCHOR_FOOTER_OPEN_GOVERNMENT_LICENCE_V_3_0, "Open Government Licence");
        footerMap.put(ANCHOR_FOOTER_CROWN_COPYRIGHT, "Crown copyright - Re-using PSI");

        footerMap.put(ANCHOR_FOOTER_SCOTLAND, "https://findbusinesssupport.gov.scot/service/training/get-funded-training-for-new-or-existing-staff-through-apprenticeships");
        footerMap.put(ANCHOR_FOOTER_WALES, "https://careerswales.gov.wales/employers");
        footerMap.put(ANCHOR_FOOTER_NI, "https://www.nibusinessinfo.co.uk/");

        contacts.put(ANCHOR_CONTACTS_CALL_CHARGES, "https://www.gov.uk/call-charges");
        contacts.put(ANCHOR_CONTACTS_APPRENTICESHIP_EMAIL, "mailto:" + ANCHOR_CONTACTS_APPRENTICESHIP_EMAIL);
        contacts.put(ANCHOR_CONTACTS_APPRENTICESHIP_WEBSITE, "https://www.apprenticeships.gov.uk/");

        contacts.put(ANCHOR_CONTACTS_TLEVELS_EMAIL, "mailto:" + ANCHOR_CONTACTS_TLEVELS_EMAIL);
        contacts.put(ANCHOR_CONTACTS_TLEVELS_FORM, "https://employers.tlevels.gov.uk/hc/en-gb/requests/new");
        contacts.put(ANCHOR_CONTACTS_TLEVELS_WEBSITE, "https://employers.tlevels.gov.uk/hc/en-gb");

        contacts.put(ANCHOR_CONTACTS_SWAP_WEBSITE, "https://find-employer-schemes.education.gov.uk/schemes/sector-based-work-academy-programme-swap");

        contacts.put(ANCHOR_CONTACTS_MULTIPLY_EMAIL, "mailto:" + ANCHOR_CONTACTS_MULTIPLY_EMAIL);
        contacts.put(ANCHOR_CONTACTS_MULTIPLY_FINDER, "https://nationalcareers.service.gov.uk/find-a-course/page?searchTerm=maths%20mathematics%20numeracy%20multiply&distance=10%20miles&town=&orderByValue=Relevance&startDate=Anytime&courseType=&courseHours=&courseStudyTime=&filterA=true&page=1&D=0");

        contacts.put(ANCHOR_CONTACTS_CARERLEAVERCOVENANT_EMAIL, "mailto:" + ANCHOR_CONTACTS_CARERLEAVERCOVENANT_EMAIL);
        contacts.put(ANCHOR_CONTACTS_CARERLEAVERCOVENANT_FORM, "https://mycovenant.org.uk/contact/");
        contacts.put(ANCHOR_CONTACTS_CARERLEAVERCOVENANT_WEBSITE, "https://mycovenant.org.uk/");

        contacts.put(ANCHOR_CONTACTS_BOOTCAMPS_WEBSITE, "https://www.gov.uk/guidance/find-a-skills-bootcamp");
        contacts.put(ANCHOR_CONTACTS_HTQ_WEBSITE, "https://www.gov.uk/government/publications/higher-technical-qualification-overview");
        contacts.put(ANCHOR_CONTACTS_INTERNSHIPS_WEBSITE, "https://www.gov.uk/government/publications/supported-internships-for-young-people-with-learning-difficulties?_ga=2.140947824.39900855.1698662915-1634194323.1693994602");
        contacts.put(ANCHOR_CONTACTS_FREECOURSES_WEBSITE, "https://nationalcareers.service.gov.uk/find-a-course");

        contacts.put(ANCHOR_CONTACTS_PRISONERS_FORM, "https://newfuturesnetwork.gov.uk/register/");
        contacts.put(ANCHOR_CONTACTS_PRISONERS_WEBSITE, "https://newfuturesnetwork.gov.uk/");

        accessibility.put(ANCHOR_ACCESSIBILITY_ABILITY_NET, "https://mcmw.abilitynet.org.uk/");
        accessibility.put(ANCHOR_ACCESSIBILITY_EMAIL, "mailto:" + ANCHOR_CONTACTS_APPRENTICESHIP_EMAIL);
        accessibility.put(ANCHOR_ACCESSIBILITY_EMAIL2, "mailto:" + ANCHOR_ACCESSIBILITY_EMAIL2);
        accessibility.put(ANCHOR_ACCESSIBILITY_EASS, "https://www.equalityadvisoryservice.com/");
        accessibility.put(ANCHOR_ACCESSIBILITY_WCAG, "https://w3c.github.io/wcag/guidelines/22/");
        accessibility.put(ANCHOR_ACCESSIBILITY_WCAG2, "https://w3c.github.io/wcag/guidelines/22/");


    }
*/
    public WebElement getAnchor(String text) {
        return driver.findElement(By.xpath(String.format("//a[normalize-space()='%s'", text)));
    }

    @Then("all APPRENTICESHIPS anchors link to the correct pages")
    public void allHomeAnchorsLinkToTheCorrectPages() throws InterruptedException {
        genericSchemePageChecks();
        checkBackgroundColors(homePageMap);

        Map<String, String> expectedAnchorsMap = new HashMap<>();
        expectedAnchorsMap.putAll(homePageMap);

        List<String> anchorsToRemove = new ArrayList<>();
        //anchorsToRemove.add(ANCHOR_FIND_SCHEMES);
        anchorsToRemove.add(ANCHOR_HOME);

        anchorsToRemove.addAll(schemesExploreSectionMap.keySet());

        confirmPageAnchorsLite(expectedAnchorsMap, anchorsToRemove, new ApprenticeshipsComparator());
    }

    private void checkBackgroundColor(String dataId, String color) {

        String cssSelector = "div[data-id='" + dataId.substring(dataId.indexOf("#")) + "']";

        WebElement element = driver.findElement(By.cssSelector(cssSelector));

        String pattern = "rgba\\(\\s*(\\d+),\\s*(\\d+),\\s*(\\d+),\\s*(\\d+)\\)";
        Matcher matcher = Pattern.compile(pattern).matcher(element.getCssValue("background-color"));
        String hex = null;
        if (matcher.find()) {
            int red = Integer.parseInt(matcher.group(1));
            int green = Integer.parseInt(matcher.group(2));
            int blue = Integer.parseInt(matcher.group(3));
            hex = String.format("#%02X%02X%02X", red, green, blue);
        } else {
            // Handle cases where the
            log.error("format is not rgba");
        }

        Assert.assertEquals("Section background is wrong - https://dfedigital.atlassian.net/browse/CE-224 dataId = " + dataId, color, hex);
    }

    private void genericSchemePageChecks() {
        List<WebElement> videoTranscriptElements = driver.findElements(By.xpath("//span[contains(text(), 'Video Transcript')]"));
        List<WebElement> videos = driver.findElements(By.xpath("//iframe"));

        Assert.assertEquals("There should be a video transcript per video", videoTranscriptElements.size(), videos.size());
        List<WebElement> h2Elements = driver.findElements(By.tagName("h2"));
        for (WebElement h2Element : h2Elements) {
            Assert.assertTrue("H2 should contain govuk-heading-l - https://dfedigital.atlassian.net/browse/CE-307 for " + h2Element.getText(), h2Element.getAttribute("class").contains("govuk-heading-l"));
        }

        List<WebElement> h3Elements = driver.findElements(By.tagName("h3"));
        for (WebElement h3Element : h3Elements) {
            Assert.assertTrue("H3 should contain govuk-heading-m - https://dfedigital.atlassian.net/browse/CE-307 for " + h3Element.getText(), h3Elements.get(0).getAttribute("class").contains("govuk-heading-m"));
        }
    }

    @Then("all SWAP anchors link to the correct pages")
    public void allSWAPAnchorsLinkToTheCorrectPages() throws InterruptedException {
        genericSchemePageChecks();
        checkBackgroundColors(swap);

        Map<String, String> expectedAnchorsMap = new HashMap<>();
        expectedAnchorsMap.putAll(swap);

        List<String> anchorsToRemove = new ArrayList<>();//stays on same page
        //anchorsToRemove.add(ANCHOR_FIND_SCHEMES);
        anchorsToRemove.add(ANCHOR_HOME);

        anchorsToRemove.addAll(schemesExploreSectionMap.keySet());
        confirmPageAnchorsLite(expectedAnchorsMap, anchorsToRemove, new SwapComparator());
    }

    @Then("all BOOTCAMPS anchors link to the correct pages")
    public void allBOOTCAMPSAnchorsLinkToTheCorrectPages() throws InterruptedException {
        genericSchemePageChecks();
        checkBackgroundColors(bootcamps);


        Map<String, String> expectedAnchorsMap = new HashMap<>();
        expectedAnchorsMap.putAll(bootcamps);

        List<String> anchorsToRemove = new ArrayList<>();//stays on same page
        //anchorsToRemove.add(ANCHOR_FIND_SCHEMES);
        anchorsToRemove.add(ANCHOR_HOME);

        anchorsToRemove.addAll(schemesExploreSectionMap.keySet());
        confirmPageAnchorsLite(expectedAnchorsMap, anchorsToRemove, new BootCampsComparator());
    }

    @Then("all MULTIPLY anchors link to the correct pages")
    public void allMULTIPLYAnchorsLinkToTheCorrectPages() throws InterruptedException {
        genericSchemePageChecks();
        checkBackgroundColors(multiply);

        Map<String, String> expectedAnchorsMap = new HashMap<>();
        expectedAnchorsMap.putAll(multiply);

        List<String> anchorsToRemove = new ArrayList<>();//stays on same page
        //anchorsToRemove.add(ANCHOR_FIND_SCHEMES);
        anchorsToRemove.add(ANCHOR_HOME);

        anchorsToRemove.addAll(schemesExploreSectionMap.keySet());
        confirmPageAnchorsLite(expectedAnchorsMap, anchorsToRemove, new MultiplyComparator());
    }

    @Then("all EMPLOYER_STANDARDS anchors link to the correct pages")
    public void allEMPLOYER_STANDARDSAnchorsLinkToTheCorrectPages() throws InterruptedException {
        genericSchemePageChecks();

        Map<String, String> expectedAnchorsMap = new HashMap<>();
        expectedAnchorsMap.putAll(employer_standards);

        List<String> anchorsToRemove = new ArrayList<>();//stays on same page
        //anchorsToRemove.add(ANCHOR_FIND_SCHEMES);
        anchorsToRemove.add(ANCHOR_HOME);

        confirmPageAnchorsLite(expectedAnchorsMap, anchorsToRemove, new MultiplyComparator());
    }

    @Then("all HTQ anchors link to the correct pages")
    public void allHTQAnchorsLinkToTheCorrectPages() throws InterruptedException {
        genericSchemePageChecks();
        checkBackgroundColors(htq);

        Map<String, String> expectedAnchorsMap = new HashMap<>();
        expectedAnchorsMap.putAll(htq);
        List<String> anchorsToRemove = new ArrayList<>();//stays on same page
        //anchorsToRemove.add(ANCHOR_FIND_SCHEMES);
        anchorsToRemove.add(ANCHOR_HOME);

        if (driver instanceof ChromeDriver) {
            //need to open Video Transcript so Chrome can see the anchors within it
            WebElement transcriptLink = driver.findElement(By.xpath("/html/body/main/div[2]/div/div[1]/div/div[3]/div/div[2]/details/summary/span"));
            transcriptLink.click();
        }

        anchorsToRemove.addAll(schemesExploreSectionMap.keySet());
        confirmPageAnchorsLite(expectedAnchorsMap, anchorsToRemove, new HtqComparator());
    }

    @Then("all INTERNSHIPS anchors link to the correct pages")
    public void allINTERNSHIPSAnchorsLinkToTheCorrectPages() throws InterruptedException {
        genericSchemePageChecks();
        checkBackgroundColors(internships);

        Map<String, String> expectedAnchorsMap = new HashMap<>();
        expectedAnchorsMap.putAll(internships);

        List<String> anchorsToRemove = new ArrayList<>();//stays on same page
        //anchorsToRemove.add(ANCHOR_FIND_SCHEMES);
        anchorsToRemove.add(ANCHOR_HOME);

        anchorsToRemove.addAll(schemesExploreSectionMap.keySet());
        confirmPageAnchorsLite(expectedAnchorsMap, anchorsToRemove, new InternshipsComparator());
    }

    @Then("all CARE_LEAVER_COVENANT anchors link to the correct pages")
    public void allCARE_LEAVER_COVENANTAnchorsLinkToTheCorrectPages() throws InterruptedException {
        genericSchemePageChecks();
        checkBackgroundColors(careLeaverCovenant);

        Map<String, String> expectedAnchorsMap = new HashMap<>();
        expectedAnchorsMap.putAll(careLeaverCovenant);

        List<String> anchorsToRemove = new ArrayList<>();//stays on same page
        //anchorsToRemove.add(ANCHOR_FIND_SCHEMES);
        anchorsToRemove.add(ANCHOR_HOME);

        anchorsToRemove.addAll(schemesExploreSectionMap.keySet());
        confirmPageAnchorsLite(expectedAnchorsMap, anchorsToRemove, new CareLeaverCovenantComparator());
    }

    @Then("all PRISONERS anchors link to the correct pages")
    public void allPRISONERSAnchorsLinkToTheCorrectPages() throws InterruptedException {
        genericSchemePageChecks();
        checkBackgroundColors(prisoners);

        Map<String, String> expectedAnchorsMap = new HashMap<>();
        expectedAnchorsMap.putAll(prisoners);

        List<String> anchorsToRemove = new ArrayList<>();//stays on same page
        //anchorsToRemove.add(ANCHOR_FIND_SCHEMES);
        anchorsToRemove.add(ANCHOR_HOME);

        anchorsToRemove.addAll(schemesExploreSectionMap.keySet());
        confirmPageAnchorsLite(expectedAnchorsMap, anchorsToRemove, new PrisonersComparator());
    }


    @Then("all HOME anchors link to the correct pages")
    public void allHOMEAnchorsLinkToTheCorrectPages() throws InterruptedException {
        Map<String, String> expectedAnchorsMap = new HashMap<>();
        expectedAnchorsMap.putAll(homeMap);

        List<String> anchorsToRemove = new ArrayList<>();//stays on same page
        //anchorsToRemove.add(ANCHOR_FIND_SCHEMES);
        anchorsToRemove.add(ANCHOR_HOME);

        anchorsToRemove.addAll(schemesExploreSectionMap.keySet());
        confirmPageAnchorsLite(expectedAnchorsMap, anchorsToRemove, new HomePageComparator());
    }
























  /*  @Then("User should be redirected to Home Page")
    public void userShouldBeRedirectedToHomePage(){
        String URL=driver.getCurrentUrl();
        Assert.assertEquals(URL,"https://at-teach-in-further-education.apprenticeships.education.gov.uk/");

    }*/


    @Then("all FREE_COURSES anchors link to the correct pages and all explore section")
    public void allFREE_COURSESAnchorsLinkToTheCorrectPageAndAllExploreSection() throws InterruptedException {
        genericSchemePageChecks();
        checkBackgroundColors(freeCourses);

        Map<String, String> expectedAnchorsMap = new HashMap<>();
        expectedAnchorsMap.putAll(freeCourses);
        expectedAnchorsMap.putAll(schemesExploreSectionMap);

        List<String> anchorsToRemove = new ArrayList<>();
        //anchorsToRemove.add(ANCHOR_FIND_SCHEMES); //stays on same page
        anchorsToRemove.add(ANCHOR_HOME);

        anchorsToRemove.add(ANCHOR_FREE_COURSES.get(SCHEME_ANCHOR_NAME_EXPLORE_SECTION)); //this is the selected scheme
        confirmPageAnchorsLiteIncludeExploreSection(expectedAnchorsMap, anchorsToRemove, new FreeCoursesComparator());
    }

    @Then("all CONTACT anchors link to the correct pages")
    public void allCONTACTAnchorsLinkToTheCorrectPages() throws InterruptedException {
        Map<String, String> expectedAnchorsMap = new HashMap<>();
        expectedAnchorsMap.put(ANCHOR_CONTACTS_CALL_CHARGES, contacts.get(ANCHOR_CONTACTS_CALL_CHARGES));
        expectedAnchorsMap.put(ANCHOR_CONTACTS_APPRENTICESHIP_EMAIL, contacts.get(ANCHOR_CONTACTS_APPRENTICESHIP_EMAIL));
        expectedAnchorsMap.put(ANCHOR_CONTACTS_TLEVELS_EMAIL, contacts.get(ANCHOR_CONTACTS_TLEVELS_EMAIL));
        expectedAnchorsMap.put(ANCHOR_CONTACTS_MULTIPLY_EMAIL, contacts.get(ANCHOR_CONTACTS_MULTIPLY_EMAIL));
        expectedAnchorsMap.put(ANCHOR_CONTACTS_MULTIPLY_FINDER, contacts.get(ANCHOR_CONTACTS_MULTIPLY_FINDER));
        expectedAnchorsMap.put(ANCHOR_CONTACTS_CARERLEAVERCOVENANT_EMAIL, contacts.get(ANCHOR_CONTACTS_CARERLEAVERCOVENANT_EMAIL));

        List<String> anchorsToRemove = populateAnchorsToRemove();
        confirmPageAnchorsLite(expectedAnchorsMap, anchorsToRemove, new ContactPageComparator());

        //assure scheme websites
        ArrayList<WebElement> websites = (ArrayList<WebElement>) driver.findElements(By.xpath(String.format("//a[normalize-space()='%s']", "Scheme website")));
        Assert.assertEquals("Href not as expected " + ANCHOR_CONTACTS_APPRENTICESHIP_WEBSITE, contacts.get(ANCHOR_CONTACTS_APPRENTICESHIP_WEBSITE), websites.get(0).getAttribute("href").trim());
        Assert.assertEquals("Href not as expected " + ANCHOR_CONTACTS_TLEVELS_WEBSITE, contacts.get(ANCHOR_CONTACTS_TLEVELS_WEBSITE), websites.get(1).getAttribute("href").trim());
        Assert.assertEquals("Href not as expected " + ANCHOR_CONTACTS_SWAP_WEBSITE, contacts.get(ANCHOR_CONTACTS_SWAP_WEBSITE), websites.get(2).getAttribute("href").trim());
        Assert.assertEquals("Href not as expected " + ANCHOR_CONTACTS_CARERLEAVERCOVENANT_WEBSITE, contacts.get(ANCHOR_CONTACTS_CARERLEAVERCOVENANT_WEBSITE), websites.get(3).getAttribute("href").trim());
        Assert.assertEquals("Href not as expected " + ANCHOR_CONTACTS_BOOTCAMPS_WEBSITE, contacts.get(ANCHOR_CONTACTS_BOOTCAMPS_WEBSITE), websites.get(4).getAttribute("href").trim());
        Assert.assertEquals("Href not as expected " + ANCHOR_CONTACTS_HTQ_WEBSITE, contacts.get(ANCHOR_CONTACTS_HTQ_WEBSITE), websites.get(5).getAttribute("href").trim());
        Assert.assertEquals("Href not as expected " + ANCHOR_CONTACTS_INTERNSHIPS_WEBSITE, contacts.get(ANCHOR_CONTACTS_INTERNSHIPS_WEBSITE), websites.get(6).getAttribute("href").trim());
        Assert.assertEquals("Href not as expected " + ANCHOR_CONTACTS_FREECOURSES_WEBSITE, contacts.get(ANCHOR_CONTACTS_FREECOURSES_WEBSITE), websites.get(7).getAttribute("href").trim());
        Assert.assertEquals("Href not as expected " + ANCHOR_CONTACTS_PRISONERS_WEBSITE, contacts.get(ANCHOR_CONTACTS_PRISONERS_WEBSITE), websites.get(8).getAttribute("href").trim());

        //assure forms
        ArrayList<WebElement> forms = (ArrayList<WebElement>) driver.findElements(By.xpath(String.format("//a[normalize-space()='%s']", "Online contact form")));
        Assert.assertEquals("Href not as expected " + ANCHOR_CONTACTS_TLEVELS_FORM, contacts.get(ANCHOR_CONTACTS_TLEVELS_FORM), forms.get(0).getAttribute("href").trim());
        Assert.assertEquals("Href not as expected " + ANCHOR_CONTACTS_CARERLEAVERCOVENANT_FORM, contacts.get(ANCHOR_CONTACTS_CARERLEAVERCOVENANT_FORM), forms.get(1).getAttribute("href").trim());
        Assert.assertEquals("Href not as expected " + ANCHOR_CONTACTS_PRISONERS_FORM, contacts.get(ANCHOR_CONTACTS_PRISONERS_FORM), forms.get(2).getAttribute("href").trim());
    }

    @Then("all ACCESSIBILITY anchors link to the correct pages")
    public void allACCESSIBILITYAnchorsLinkToTheCorrectPages() throws InterruptedException {
        Map<String, String> expectedAnchorsMap = new HashMap<>();
        expectedAnchorsMap.put(ANCHOR_ACCESSIBILITY_ABILITY_NET, accessibility.get(ANCHOR_ACCESSIBILITY_ABILITY_NET));
        expectedAnchorsMap.put(ANCHOR_ACCESSIBILITY_EMAIL, accessibility.get(ANCHOR_ACCESSIBILITY_EMAIL));
        expectedAnchorsMap.put(ANCHOR_ACCESSIBILITY_EMAIL2, accessibility.get(ANCHOR_ACCESSIBILITY_EMAIL2));
        expectedAnchorsMap.put(ANCHOR_ACCESSIBILITY_EASS, accessibility.get(ANCHOR_ACCESSIBILITY_EASS));
        expectedAnchorsMap.put(ANCHOR_ACCESSIBILITY_WCAG, accessibility.get(ANCHOR_ACCESSIBILITY_WCAG));
        expectedAnchorsMap.put(ANCHOR_ACCESSIBILITY_WCAG2, accessibility.get(ANCHOR_ACCESSIBILITY_WCAG2));

        List<String> anchorsToRemove = populateAnchorsToRemove();
        confirmPageAnchorsLite(expectedAnchorsMap, anchorsToRemove, new AccessibilityPageComparator());

    }

    @Then("all PAGE_NOT_FOUND anchors link to the correct pages")
    public void allPAGE_NOT_FOUNDAnchorsLinkToTheCorrectPages() throws InterruptedException {
        Map<String, String> expectedAnchorsMap = new HashMap<>();
        expectedAnchorsMap.put("Home page", "Landing Page");

        List<String> anchorsToRemove = populateAnchorsToRemove();
        confirmPageAnchorsLite(expectedAnchorsMap, anchorsToRemove, new DefaultFindSchemePageSchemesComparator());

    }

    @NotNull
    private static List<String> populateAnchorsToRemove() {
        List<String> anchorsToRemove = new ArrayList<>();

        anchorsToRemove.add(ANCHOR_FREE_COURSES.get(SCHEME_ANCHOR_NAME_HOMEPAGE));
        //anchorsToRemove.add(ANCHOR_FIND_SCHEMES);
        anchorsToRemove.add(ANCHOR_HOME);


        anchorsToRemove.add(ANCHOR_CONTACTS_APPRENTICESHIP_WEBSITE.replace("APPRENTICESHIP ", ""));
        anchorsToRemove.add(ANCHOR_CONTACTS_TLEVELS_WEBSITE.replace("TLEVELS ", ""));
        anchorsToRemove.add(ANCHOR_CONTACTS_SWAP_WEBSITE.replace("SWAP ", ""));
        anchorsToRemove.add(ANCHOR_CONTACTS_CARERLEAVERCOVENANT_WEBSITE.replace("CARERLEAVERCOVENANT ", ""));
        anchorsToRemove.add(ANCHOR_CONTACTS_BOOTCAMPS_WEBSITE.replace("BOOTCAMPS ", ""));
        anchorsToRemove.add(ANCHOR_CONTACTS_HTQ_WEBSITE.replace("HTQ ", ""));
        anchorsToRemove.add(ANCHOR_CONTACTS_INTERNSHIPS_WEBSITE.replace("INTERNSHIPS ", ""));
        anchorsToRemove.add(ANCHOR_CONTACTS_FREECOURSES_WEBSITE.replace("FREECOURSES ", ""));
        anchorsToRemove.add(ANCHOR_CONTACTS_PRISONERS_WEBSITE.replace("PRISONERS ", ""));

        anchorsToRemove.add(ANCHOR_CONTACTS_TLEVELS_FORM.replace("TLEVELS ", ""));
        anchorsToRemove.add(ANCHOR_CONTACTS_CARERLEAVERCOVENANT_FORM.replace("CARERLEAVERCOVENANT ", ""));
        anchorsToRemove.add(ANCHOR_CONTACTS_PRISONERS_FORM.replace("PRISONERS ", ""));
        return anchorsToRemove;
    }

    @Then("all T_LEVELS anchors link to the correct pages")
    public void allT_LEVELSAnchorsLinkToTheCorrectPages() throws InterruptedException {
        genericSchemePageChecks();

        checkBackgroundColors(tLevels );

        Map<String, String> expectedAnchorsMap = new HashMap<>();
        expectedAnchorsMap.putAll(tLevels);

        List<String> anchorsToRemove = new ArrayList<>();//stays on same page
        //anchorsToRemove.add(ANCHOR_FIND_SCHEMES);
        anchorsToRemove.add(ANCHOR_HOME);

        anchorsToRemove.addAll(schemesExploreSectionMap.keySet());
        confirmPageAnchorsLite(expectedAnchorsMap, anchorsToRemove, new TLevelsComparator());
    }

    private void checkBackgroundColors(Map map) {
        checkBackgroundColor((String) map.get(ANCHOR_CONTENTS_BENEFITS), BLUE);
        checkBackgroundColor((String) map.get(ANCHOR_CONTENTS_COST), BLUE);
        checkBackgroundColor((String) map.get(ANCHOR_CONTENTS_RESPONSIBILITIES), BLUE);
        checkBackgroundColor((String) map.get(ANCHOR_CONTENTS_FIND_OUT_MORE), PINK);
    }

    private void confirmPageHeaderAndFooterAnchors(Map<String, String> expectedAnchorsMap, List<String> anchorsToRemove) throws InterruptedException {
        expectedAnchorsMap.putAll(headersMap);
        expectedAnchorsMap.putAll(footerMap);

        //anchorsToRemove.add(ANCHOR_HEADER_SKIP_TO_MAIN_CONTENT); //stays on same page
        compareAndClickAnchors(expectedAnchorsMap, anchorsToRemove, new DefaultFindSchemePageSchemesComparator());
    }

    private void confirmPageAnchorsLiteIncludeExploreSection(Map<String, String> expectedAnchorsMap, List<String> anchorsToRemove) throws InterruptedException {
        confirmPageAnchorsLiteIncludeExploreSection(expectedAnchorsMap, anchorsToRemove, new DefaultExploreSchemesComparator());
    }

    private void confirmPageAnchorsLiteIncludeExploreSection(Map<String, String> expectedAnchorsMap, List<String> anchorsToRemove, Comparator comparator) throws InterruptedException {
        anchorsToRemove.addAll(headersMap.keySet());
        anchorsToRemove.addAll(footerMap.keySet());
        //anchorsToRemove.add(ANCHOR_HEADER_SKIP_TO_MAIN_CONTENT); //stays on same page
        compareAndClickAnchors(expectedAnchorsMap, anchorsToRemove, comparator);
    }


    private void confirmPageAnchorsLite(Map<String, String> expectedAnchorsMap, List<String> anchorsToRemove, Comparator comparator) throws InterruptedException {
        anchorsToRemove.addAll(headersMap.keySet());
        anchorsToRemove.addAll(footerMap.keySet());
        //anchorsToRemove.add(ANCHOR_HEADER_SKIP_TO_MAIN_CONTENT); //stays on same page
        compareAndClickAnchors(expectedAnchorsMap, anchorsToRemove, comparator);
    }

    public String ensureTrailingSlashOrEncodedSlash(String url) {
        String returnUrl = null;
        if (url.contains("%")){
            returnUrl =  url.endsWith("%2f")  ? url : url + "%2f";
        } else {
            returnUrl =  url.endsWith("/")  ? url : url + "/";
        }

        return returnUrl;
    }
    private void compareAndClickAnchors(Map<String, String> expectedAnchorsMap, List<String> anchorsToRemove, Comparator comparator) throws InterruptedException {
        List<String> mutableNonEmptyActualAnchors = confirmActualAnchors(new ArrayList<>(expectedAnchorsMap.keySet()), anchorsToRemove, comparator);

        for (String anchorText : mutableNonEmptyActualAnchors) {
            log.info("link " + anchorText);

            WebElement anchorElement = getAnchor(anchorText);
            int retryCount = 3;
            boolean previous_page_using_forward = false;
            try {
                if (expectedAnchorsMap.get(anchorText).contains("#") || expectedAnchorsMap.get(anchorText).startsWith("http") || (expectedAnchorsMap.get(anchorText).startsWith("mailto")) || expectedAnchorsMap.get(anchorText).startsWith("tel:")) {
                    String actualHref = ensureTrailingSlashOrEncodedSlash(anchorElement.getAttribute("href").trim());

                    if (actualHref.contains("%")) {
                        try {
                            Assert.assertEquals("Href not as expected " + anchorText, ensureTrailingSlashOrEncodedSlash(expectedAnchorsMap.get(anchorText).replace(CURRENT_PAGE_URL, URLEncoder.encode(driver.getCurrentUrl(), "UTF-8").toLowerCase())), actualHref);
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }

                    } else {
                        Assert.assertEquals("Href not as expected " + anchorText, ensureTrailingSlashOrEncodedSlash(expectedAnchorsMap.get(anchorText).replace(CURRENT_PAGE_URL, driver.getCurrentUrl())), actualHref);
                    }

                } else {

                    if (anchorElement.getAttribute("href").equals("javascript:void(0);")) {
                        previous_page_using_forward = true;
                    } else {
                        previous_page_using_forward = false;
                    }
                    anchorElement.click();
                    if (driver.getTitle().equals("Find training and employment schemes for your business - Contacts")) {
                        //Title changes to Contacts instead of Contact
                    }
                    else if (driver.getTitle().contains("Find training and employment schemes for your business - ") && !(expectedAnchorsMap.get(anchorText).contains("Find training and employment schemes for your business - "))) {
                        Assert.assertEquals("Wrong title for anchor " + anchorText, "Find training and employment schemes for your business - " + expectedAnchorsMap.get(anchorText), driver.getTitle());
                    } else {
                        Assert.assertEquals("Wrong title for anchor " + anchorText, expectedAnchorsMap.get(anchorText), driver.getTitle());
                    }


                    if (previous_page_using_forward) {
                        driver.navigate().forward();
                    } else {
                        if (previous_page_using_forward) {
                            driver.navigate().forward();
                        } else {
                            driver.navigate().back();
                        }
                    }

                }
            } catch (AssertionError e) {
                log.info("AssertionError");
                for (int i = 0; i < retryCount; i++) {
                    //sometimes there's a delay in getting the title
                    if (expectedAnchorsMap.get(anchorText).contains("#") || expectedAnchorsMap.get(anchorText).startsWith("http") || expectedAnchorsMap.get(anchorText).startsWith("mailto") || expectedAnchorsMap.get(anchorText).startsWith("tel:")) {
                        String actualHref = ensureTrailingSlashOrEncodedSlash(anchorElement.getAttribute("href").trim())  ;

                        if (actualHref.contains("%")) {
                            try {
                                Assert.assertEquals("Href not as expected " + anchorText, ensureTrailingSlashOrEncodedSlash(expectedAnchorsMap.get(anchorText).replace(CURRENT_PAGE_URL, URLEncoder.encode(driver.getCurrentUrl(), "UTF-8").toLowerCase())), actualHref);
                            } catch (UnsupportedEncodingException uee) {
                                uee.printStackTrace();
                            }

                        } else {
                            Assert.assertEquals("Href not as expected " + anchorText, ensureTrailingSlashOrEncodedSlash(expectedAnchorsMap.get(anchorText).replace(CURRENT_PAGE_URL, driver.getCurrentUrl())), actualHref);
                        }
                    } else {

                        if (driver.getTitle().equals("Find training and employment schemes for your business - Contacts")) {
                            //Title changes to Contacts instead of Contact
                        }
                        else if (driver.getTitle().contains("Find training and employment schemes for your business - ") && !(expectedAnchorsMap.get(anchorText).contains("Find training and employment schemes for your business - "))) {
                            Assert.assertEquals("Wrong title for anchor " + anchorText, "Find training and employment schemes for your business - " + expectedAnchorsMap.get(anchorText), driver.getTitle());
                        } else {
                            Assert.assertEquals("Wrong title for anchor " + anchorText, expectedAnchorsMap.get(anchorText), driver.getTitle());
                        }
                        if (previous_page_using_forward) {
                            driver.navigate().forward();
                        } else {
                            driver.navigate().back();
                        }
                    }
                }
            }
        }
    }

    @NotNull
    private List<String> confirmActualAnchors(List<String> expectedAnchors, List<String> anchorsToRemove, Comparator comparator) throws InterruptedException {
        List<String> actualAnchors = new ArrayList<>();

        Thread.sleep(4000);
        //remove any breadcrumbs/ tab anchors
        List<WebElement> anchors = driver.findElements(By.cssSelector("a:not([class='govuk-breadcrumbs__link']):not([class='govuk-header__link'])"));

        for (WebElement anchor : anchors) {
            String anchorText = anchor.getText();
            actualAnchors.add(anchorText);
        }

        for (String anchor : anchorsToRemove) {
            removeAnchorToCompare(actualAnchors, expectedAnchors, anchor);
        }

        List<String> nonEmptyActualAnchors = actualAnchors.stream().filter(anchor -> !anchor.isEmpty()).toList();
        Collections.sort(expectedAnchors, comparator);
        List<String> mutableNonEmptyActualAnchors = new ArrayList<>(nonEmptyActualAnchors);
        //  Collections.sort(mutableNonEmptyActualAnchors);

//        log.info("mutableNonEmptyActualAnchors.toString()");
//        log.info(mutableNonEmptyActualAnchors.toString()
//                .replace(ANCHOR_APPRENTICESHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), "APPRENTICESHIPS")
//                .replace(ANCHOR_T_LEVELS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), "T_LEVELS")
//                .replace(ANCHOR_SWAP.get(SCHEME_ANCHOR_NAME_HOMEPAGE), "SWAP")
//                .replace(ANCHOR_BOOTCAMPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), "BOOTCAMPS")
//                .replace(ANCHOR_MULTIPLY.get(SCHEME_ANCHOR_NAME_HOMEPAGE), "MULTIPLY")
//                .replace(ANCHOR_HTQ.get(SCHEME_ANCHOR_NAME_HOMEPAGE), "HTQ")
//                .replace(ANCHOR_INTERNSHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), "INTERNSHIPS")
//                .replace(ANCHOR_CARE_LEAVER_COVENANT.get(SCHEME_ANCHOR_NAME_HOMEPAGE), "CARE_LEAVER_COVENANT")
//                .replace(ANCHOR_PRISONERS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), "PRISONERS")
//                .replace(ANCHOR_FREE_COURSES.get(SCHEME_ANCHOR_NAME_HOMEPAGE), "FREE_COURSES")
        //       );

        //removed duplicates
        LinkedHashSet<String> uniqueSet = new LinkedHashSet<>(mutableNonEmptyActualAnchors);
        ArrayList<String> uniqueList = new ArrayList<>(uniqueSet);

        Assert.assertEquals("anchors list should be the same", expectedAnchors, uniqueList);
        return mutableNonEmptyActualAnchors;
    }

    @Then("all FIND_SCHEMES anchors link to the correct pages")
    public void allFIND_SCHEMESAnchorsLinkToTheCorrectPages() throws InterruptedException {
        genericSchemePageChecks();

        Map<String, String> expectedAnchorsMap = new HashMap<>();
        expectedAnchorsMap.putAll(findSchemesMap);

        List<String> anchorsToRemove = new ArrayList<>();
        //anchorsToRemove.add(ANCHOR_FIND_SCHEMES);
        anchorsToRemove.add(ANCHOR_HOME);

        anchorsToRemove.add(ANCHOR_HOME_PAGE_CLEAR_FILTERS);
        anchorsToRemove.addAll(schemesHomePageDefaultMap.keySet());

        confirmPageAnchorsLite(expectedAnchorsMap, anchorsToRemove, new DefaultFindSchemePageSchemesComparator());
    }

    private static void removeAnchorToCompare(List<String> actualAnchors, List<String> expectedAnchors, String link) {
        actualAnchors.remove(link); //stays on same page
        expectedAnchors.remove(link); //stays on same page
    }


    //@When("the user navigates to the {string} page")
    /*public void theUserNavigatesToThePage(String destination) {


        if (destination.equals("HOME")) {

            Assert.assertEquals("Home page", driver.getTitle());

            //WebElement activeItem = driver.findElement(By.cssSelector("li.govuk-header__navigation-item--active")).findElement(By.tagName("a"));
            //Assert.assertEquals("Home", activeItem.getText());

           *//* String activeItem1 = driver.findElement(By.className("das-header__logo-container")).getAttribute("src");
            Assert.assertEquals("Homepage links are not clickable", "/svg/teach-in-further-education.svg", activeItem1);

            String activeItem2 = driver.findElement(By.className("das-header__logo-container")).getText();
            Assert.assertEquals("Homepage links are not clickable", "Talk to a specialist", activeItem2);

            WebElement activeItem3 = driver.findElement(By.id("imageCardBannerDiv")).findElement(By.tagName("a"));
            Assert.assertEquals("Homepage links are not clickable", "Learn how to prepare a Micro teach for your next interview.", activeItem3.getText());
*//*
//            getAnchor(ANCHOR_FIND_SCHEMES).click();
//            getAnchor(ANCHOR_HOME).click();
//            Assert.assertEquals("Find training and employment schemes for your business - Landing Page", driver.getTitle());
//            return;
        }

        if (destination.equals("Become a further education (FE) teacher")) {
           driver.findElement(By.xpath("//div[@class='dfe-grid-container']//div[1]//div[1]//h3[1]//a[1]")).click();
            Assert.assertEquals("Google", driver.getTitle());

            *//*Map<String, String> breadCrumbs = new LinkedHashMap<>();
            breadCrumbs.put("Home", environment.getUrl());
            assertBreadCrumbsAnchors(breadCrumbs);
            assertBreadCrumbsLi("Employer standards for careers education");
            return;*//*
        }

        if (destination.equalsIgnoreCase("Is teaching right for me?")) {
            driver.findElement(By.xpath("//a[normalize-space()='Is teaching right for me?' and @id='link-']")).click();
            Assert.assertEquals("Google", driver.getTitle());
        }

        if (destination.equalsIgnoreCase("Check if you can teach in FE")) {
            driver.findElement(By.xpath("//div[2]//div[1]//h3[1]//a[1]")).click();

            Assert.assertEquals("Google", driver.getTitle());
        }

        if (destination.equals("Find funding and training")) {
          //  getAnchor("Find funding and training").click();
            driver.findElement(By.xpath("//div[4]//div[1]//h3[1]//a[1]")).click();
            Assert.assertEquals("Home page", driver.getTitle());
        }



            //getAnchor(ANCHOR_FIND_SCHEMES).click();

        *//*switch (destination) {

            case "FIND_SCHEMES" -> {
               // Assert.assertEquals("Find training and employment schemes for your business - " + findSchemesMap.get(ANCHOR_FIND_SCHEMES), driver.getTitle());

                Map<String, String> breadCrumbs = new LinkedHashMap<>();
                breadCrumbs.put("Home", environment.getUrl());
                assertBreadCrumbsAnchors(breadCrumbs);
                //assertBreadCrumbsLi(ANCHOR_FIND_SCHEMES);
            }
            case "APPRENTICESHIPS" -> {
                getAnchor(ANCHOR_APPRENTICESHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE)).click();
                Assert.assertEquals("Find training and employment schemes for your business - " + schemesHomePageDefaultMap.get(ANCHOR_APPRENTICESHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE)), driver.getTitle());
                assertBreadCrumbs(ANCHOR_APPRENTICESHIPS.get(SCHEME_ANCHOR_NAME_EXPLORE_SECTION));
            }
            case "T_LEVELS" -> {
                getAnchor(ANCHOR_T_LEVELS.get(SCHEME_ANCHOR_NAME_HOMEPAGE)).click();
                Assert.assertEquals("Find training and employment schemes for your business - " + schemesHomePageDefaultMap.get(ANCHOR_T_LEVELS.get(SCHEME_ANCHOR_NAME_HOMEPAGE)), driver.getTitle());
                assertBreadCrumbs(ANCHOR_T_LEVELS.get(SCHEME_ANCHOR_NAME_EXPLORE_SECTION));
            }
            case "SWAP" -> {
                getAnchor(ANCHOR_SWAP.get(SCHEME_ANCHOR_NAME_HOMEPAGE)).click();
                Assert.assertEquals("Find training and employment schemes for your business - " + schemesHomePageDefaultMap.get(ANCHOR_SWAP.get(SCHEME_ANCHOR_NAME_HOMEPAGE)), driver.getTitle());
                assertBreadCrumbs(ANCHOR_SWAP.get(SCHEME_ANCHOR_NAME_EXPLORE_SECTION));
            }
            case "BOOTCAMPS" -> {
                getAnchor(ANCHOR_BOOTCAMPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE)).click();
                Assert.assertEquals("Find training and employment schemes for your business - " + schemesHomePageDefaultMap.get(ANCHOR_BOOTCAMPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE)), driver.getTitle());
                assertBreadCrumbs(ANCHOR_BOOTCAMPS.get(SCHEME_ANCHOR_NAME_EXPLORE_SECTION));
            }
            case "MULTIPLY" -> {
                getAnchor(ANCHOR_MULTIPLY.get(SCHEME_ANCHOR_NAME_HOMEPAGE)).click();
                Assert.assertEquals("Find training and employment schemes for your business - " + schemesHomePageDefaultMap.get(ANCHOR_MULTIPLY.get(SCHEME_ANCHOR_NAME_HOMEPAGE)), driver.getTitle());
                assertBreadCrumbs(ANCHOR_MULTIPLY.get(SCHEME_ANCHOR_NAME_EXPLORE_SECTION));
            }
            case "HTQ" -> {
                getAnchor(ANCHOR_HTQ.get(SCHEME_ANCHOR_NAME_HOMEPAGE)).click();
                Assert.assertEquals("Find training and employment schemes for your business - " + schemesHomePageDefaultMap.get(ANCHOR_HTQ.get(SCHEME_ANCHOR_NAME_HOMEPAGE)), driver.getTitle());
                assertBreadCrumbs(ANCHOR_HTQ.get(SCHEME_ANCHOR_NAME_EXPLORE_SECTION));
            }
            case "INTERNSHIPS" -> {
                getAnchor(ANCHOR_INTERNSHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE)).click();
                Assert.assertEquals("Find training and employment schemes for your business - " + schemesHomePageDefaultMap.get(ANCHOR_INTERNSHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE)), driver.getTitle());
                assertBreadCrumbs(ANCHOR_INTERNSHIPS.get(SCHEME_ANCHOR_NAME_EXPLORE_SECTION));
            }
            case "CARE_LEAVER_COVENANT" -> {
                getAnchor(ANCHOR_CARE_LEAVER_COVENANT.get(SCHEME_ANCHOR_NAME_HOMEPAGE)).click();
                Assert.assertEquals("Find training and employment schemes for your business - " + schemesHomePageDefaultMap.get(ANCHOR_CARE_LEAVER_COVENANT.get(SCHEME_ANCHOR_NAME_HOMEPAGE)), driver.getTitle());
                assertBreadCrumbs(ANCHOR_CARE_LEAVER_COVENANT.get(SCHEME_ANCHOR_NAME_EXPLORE_SECTION));
            }
            case "PRISONERS" -> {
                getAnchor(ANCHOR_PRISONERS.get(SCHEME_ANCHOR_NAME_HOMEPAGE)).click();
                Assert.assertEquals("Find training and employment schemes for your business - " + schemesHomePageDefaultMap.get(ANCHOR_PRISONERS.get(SCHEME_ANCHOR_NAME_HOMEPAGE)), driver.getTitle());
            }
            case "FREE_COURSES" -> {
                getAnchor(ANCHOR_FREE_COURSES.get(SCHEME_ANCHOR_NAME_HOMEPAGE)).click();
                Assert.assertEquals("Find training and employment schemes for your business - " + schemesHomePageDefaultMap.get(ANCHOR_FREE_COURSES.get(SCHEME_ANCHOR_NAME_HOMEPAGE)), driver.getTitle());
                assertBreadCrumbs(ANCHOR_FREE_COURSES.get(SCHEME_ANCHOR_NAME_EXPLORE_SECTION));
            }
            case "CONTACT" -> {
                getAnchor(ANCHOR_FOOTER_CONTACT).click();
                Assert.assertEquals("Find training and employment schemes for your business - " + footerMap.get(ANCHOR_FOOTER_CONTACT) + "s", driver.getTitle());

                Map<String, String> breadCrumbs = new LinkedHashMap<>();
                breadCrumbs.put("Home", environment.getUrl());
                assertBreadCrumbsAnchors(breadCrumbs);
                assertBreadCrumbsLi("Contact");
            }
            case "ACCESSIBILITY" -> {
                getAnchor(ANCHOR_FOOTER_ACCESSIBILITY).click();
                Assert.assertEquals("Find training and employment schemes for your business - " + footerMap.get(ANCHOR_FOOTER_ACCESSIBILITY), driver.getTitle());

                Map<String, String> breadCrumbs = new LinkedHashMap<>();
                breadCrumbs.put("Home", environment.getUrl());
                assertBreadCrumbsAnchors(breadCrumbs);
                assertBreadCrumbsLi("Accessibility");

            }
            case "PAGE_NOT_FOUND" -> {
                driver.get(environment.getUrl() + "sss");
                Assert.assertEquals("Page not found", driver.getTitle());
            }


            default -> Assert.fail("unknown page");
        }*//*
    }*/

    private void assertBreadCrumbs(String breadCrumbLi) {
        Map<String, String> breadCrumbs = new LinkedHashMap<>();
        breadCrumbs.put("Home", environment.getUrl());
        //breadCrumbs.put(ANCHOR_FIND_SCHEMES, environment.getUrl() + "schemes/");
        assertBreadCrumbsAnchors(breadCrumbs);
        assertBreadCrumbsLi(breadCrumbLi);
    }

    private void assertBreadCrumbsAnchors(Map<String, String> breadCrumbs) {
        List<WebElement> anchors =  driver.findElement(By.cssSelector("ol.govuk-breadcrumbs__list")).findElements(By.tagName("a"));
        Assert.assertEquals("Breadcrumb count wrong", breadCrumbs.size(), anchors.size());
        int i = 0;
        for (Map.Entry<String, String> entry : breadCrumbs.entrySet()) {
            Assert.assertEquals( "element " + i + "  href should match", entry.getValue(), anchors.get(i).getAttribute("href"));
            Assert.assertEquals("element " + i + " text should match", entry.getKey(), anchors.get(i).getText());
            i++;
        }
    }
    private void assertBreadCrumbsLi(String breadCrumb) {
        List<WebElement> li_s =  driver
                .findElement(By.cssSelector("ol.govuk-breadcrumbs__list")).findElements(By.tagName("li"));
        boolean found = false;
        for (WebElement li : li_s) {
            if (breadCrumb.equals(li.getText()))
            {
                found = true;
                break;
            }
        }
        Assert.assertTrue("Li not found for "  + breadCrumb, found);
    }

    /*@After()
    public void after(Scenario scenario) throws Exception {
        if (scenario.isFailed()) {
            final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", String.valueOf(scenario) + UUID.randomUUID());

            LogEntries logEntries = driver.manage().logs().get(LogType.BROWSER);
            List<LogEntry> logs = logEntries.getAll();
            for (LogEntry entry : logs) {
                Log.Info(new Date(entry.getTimestamp()) + " " + entry.getLevel() + " " + entry.getMessage());
            }
        }
        driver.quit();
    }*/

    @Then("filter reports the correct data")
    public void filterReportsTheCorrectData() throws InterruptedException {
        filterSchemesClicked();

        WebElement numberOfSchemes = driver.findElement(By.id("schemes"));
        log.info("numberOfSchemes : " + numberOfSchemes.getText());

        Assert.assertTrue("When nothing is filtered, scheme count is wrong ", numberOfSchemes.getText().contains("Number of schemes: 10"));
        confirmHomePageSchemes(new ArrayList<>(schemesHomePageDefaultMap.keySet()), new DefaultFindSchemePageSchemesComparator());
        checkboxCombo();
    }

    private void confirmHomePageSchemes(List list, Comparator comparator) throws InterruptedException {
        List<String> anchorsToRemove = new ArrayList<>();
        removeAllHomePageAnchorsExpectSchemes(anchorsToRemove);
        confirmActualAnchors(list, anchorsToRemove, comparator);
    }

    private void filterSchemesClicked() {
        WebElement button = driver.findElement(By.xpath("//button[normalize-space(text())='Filter schemes']"));
        button.click();
    }

    public void checkboxCombo() throws InterruptedException {
        By checkBox1Locator = By.id("motivation--recruit-new-staff");
        By checkBox2Locator = By.id("motivation--retrain-or-upskill-existing-staff");
        By checkBox3Locator = By.id("motivation--offer-short-terms-work-experience-placements");

        By checkBox4Locator = By.id("duration--less-than-6-months");
        By checkBox5Locator = By.id("duration--between-6-months-and-1-year");
        By checkBox6Locator = By.id("duration--longer-than-1-year");

        By checkBox7Locator = By.id("cost--free");
        By checkBox8Locator = By.id("cost--may-require-employer-contribution");

        // Create a Map to store the checkbox combinations
        Map<List<Boolean>, List<String>> combinationsMap = new LinkedHashMap<>();

        populateCombinationsMap(combinationsMap);

        List<String> anchorsToRemove = new ArrayList<>();

        removeAllHomePageAnchorsExpectSchemes(anchorsToRemove);

        // Iterate over the Set of unique checkbox combinations and check the corresponding checkboxes
        for (List<Boolean> combination : combinationsMap.keySet()) {
            getAnchor(ANCHOR_HOME_PAGE_CLEAR_FILTERS).click();
            // Check the checkboxes
            if (combination.get(0)) {
                driver.findElement(checkBox1Locator).click();
            }
            if (combination.get(1)) {
                driver.findElement(checkBox2Locator).click();
            }
            if (combination.get(2)) {
                driver.findElement(checkBox3Locator).click();
            }
            if (combination.get(3)) {
                driver.findElement(checkBox4Locator).click();
            }
            if (combination.get(4)) {
                driver.findElement(checkBox5Locator).click();
            }
            if (combination.get(5)) {
                driver.findElement(checkBox6Locator).click();
            }
            if (combination.get(6)) {
                driver.findElement(checkBox7Locator).click();
            }
            if (combination.get(7)) {
                driver.findElement(checkBox8Locator).click();
            }

            filterSchemesClicked();
            log.info(combination.toString());
            log.info("expected " + combinationsMap.get(combination).toString());
            confirmActualAnchors(combinationsMap.get(combination), anchorsToRemove, new DefaultFindSchemePageSchemesComparator());

            if (combinationsMap.get(combination).equals(Arrays.asList(ANCHOR_HOME_PAGE_NO_SCHEMES_PRESENT_CLEAR_FILTERS))) {

                Assert.assertTrue("When there no schemes returned the sort dropdown should not be present",
                        driver.findElements(By.id(SCHEME_SORT)).isEmpty());

            } else {
                WebElement selectElement = driver.findElement(By.id(SCHEME_SORT));
                Select select = new Select(selectElement);
                WebElement selectedOption = select.getFirstSelectedOption();
                Assert.assertEquals("Default sort should be selected", "No sort selected", selectedOption.getText());

            }

        }
    }

    private void removeAllHomePageAnchorsExpectSchemes(List<String> anchorsToRemove) {
        anchorsToRemove.addAll(headersMap.keySet());
        anchorsToRemove.addAll(footerMap.keySet());
        anchorsToRemove.add(ANCHOR_HOME_PAGE_CLEAR_FILTERS);
        //anchorsToRemove.add(ANCHOR_FIND_SCHEMES);
        anchorsToRemove.add(ANCHOR_HOME);

        anchorsToRemove.add(ANCHOR_HOME_SKILLS_FOR_CAREERS);
    }

    static void populateCombinationsMap(Map<List<Boolean>, List<String>> combinationsMap) {

        combinationsMap.put(new ArrayList<>(List.of(true, true, true, true, true, true, true, true)), Arrays.asList(ANCHOR_APPRENTICESHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_BOOTCAMPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_CARE_LEAVER_COVENANT.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_PRISONERS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_T_LEVELS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_SWAP.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_INTERNSHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_FREE_COURSES.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_MULTIPLY.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_HTQ.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(true, true, true, true, true, true, true, false)), Arrays.asList(ANCHOR_CARE_LEAVER_COVENANT.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_PRISONERS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_T_LEVELS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_SWAP.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_INTERNSHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_FREE_COURSES.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_MULTIPLY.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(true, true, true, true, true, true, false, true)), Arrays.asList(ANCHOR_APPRENTICESHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_BOOTCAMPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_HTQ.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(true, true, true, true, true, true, false, false)), Arrays.asList(ANCHOR_APPRENTICESHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_BOOTCAMPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_CARE_LEAVER_COVENANT.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_PRISONERS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_T_LEVELS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_SWAP.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_INTERNSHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_FREE_COURSES.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_MULTIPLY.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_HTQ.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(true, true, true, true, true, false, true, true)), Arrays.asList(ANCHOR_BOOTCAMPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_CARE_LEAVER_COVENANT.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_PRISONERS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_T_LEVELS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_SWAP.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_INTERNSHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_FREE_COURSES.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_MULTIPLY.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(true, true, true, true, true, false, true, false)), Arrays.asList(ANCHOR_CARE_LEAVER_COVENANT.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_PRISONERS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_T_LEVELS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_SWAP.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_INTERNSHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_FREE_COURSES.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_MULTIPLY.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(true, true, true, true, true, false, false, true)), Arrays.asList(ANCHOR_BOOTCAMPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(true, true, true, true, true, false, false, false)), Arrays.asList(ANCHOR_BOOTCAMPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_CARE_LEAVER_COVENANT.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_PRISONERS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_T_LEVELS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_SWAP.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_INTERNSHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_FREE_COURSES.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_MULTIPLY.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(true, true, true, true, false, true, true, true)), Arrays.asList(ANCHOR_APPRENTICESHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_BOOTCAMPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_CARE_LEAVER_COVENANT.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_PRISONERS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_T_LEVELS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_SWAP.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_FREE_COURSES.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_MULTIPLY.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_HTQ.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(true, true, true, true, false, true, true, false)), Arrays.asList(ANCHOR_CARE_LEAVER_COVENANT.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_PRISONERS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_T_LEVELS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_SWAP.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_FREE_COURSES.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_MULTIPLY.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(true, true, true, true, false, true, false, true)), Arrays.asList(ANCHOR_APPRENTICESHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_BOOTCAMPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_HTQ.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(true, true, true, true, false, true, false, false)), Arrays.asList(ANCHOR_APPRENTICESHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_BOOTCAMPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_CARE_LEAVER_COVENANT.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_PRISONERS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_T_LEVELS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_SWAP.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_FREE_COURSES.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_MULTIPLY.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_HTQ.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(true, true, true, true, false, false, true, true)), Arrays.asList(ANCHOR_BOOTCAMPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_CARE_LEAVER_COVENANT.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_PRISONERS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_T_LEVELS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_SWAP.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_FREE_COURSES.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_MULTIPLY.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(true, true, true, true, false, false, true, false)), Arrays.asList(ANCHOR_CARE_LEAVER_COVENANT.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_PRISONERS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_T_LEVELS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_SWAP.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_FREE_COURSES.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_MULTIPLY.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(true, true, true, true, false, false, false, true)), Arrays.asList(ANCHOR_BOOTCAMPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(true, true, true, true, false, false, false, false)), Arrays.asList(ANCHOR_BOOTCAMPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_CARE_LEAVER_COVENANT.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_PRISONERS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_T_LEVELS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_SWAP.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_FREE_COURSES.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_MULTIPLY.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(true, true, true, false, true, true, true, true)), Arrays.asList(ANCHOR_APPRENTICESHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_CARE_LEAVER_COVENANT.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_PRISONERS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_INTERNSHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_FREE_COURSES.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_MULTIPLY.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_HTQ.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(true, true, true, false, true, true, true, false)), Arrays.asList(ANCHOR_CARE_LEAVER_COVENANT.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_PRISONERS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_INTERNSHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_FREE_COURSES.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_MULTIPLY.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(true, true, true, false, true, true, false, true)), Arrays.asList(ANCHOR_APPRENTICESHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_HTQ.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(true, true, true, false, true, true, false, false)), Arrays.asList(ANCHOR_APPRENTICESHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_CARE_LEAVER_COVENANT.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_PRISONERS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_INTERNSHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_FREE_COURSES.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_MULTIPLY.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_HTQ.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(true, true, true, false, true, false, true, true)), Arrays.asList(ANCHOR_CARE_LEAVER_COVENANT.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_PRISONERS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_INTERNSHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_FREE_COURSES.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_MULTIPLY.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(true, true, true, false, true, false, true, false)), Arrays.asList(ANCHOR_CARE_LEAVER_COVENANT.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_PRISONERS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_INTERNSHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_FREE_COURSES.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_MULTIPLY.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(true, true, true, false, true, false, false, true)), Arrays.asList(ANCHOR_HOME_PAGE_NO_SCHEMES_PRESENT_CLEAR_FILTERS));
        combinationsMap.put(new ArrayList<>(List.of(true, true, true, false, true, false, false, false)), Arrays.asList(ANCHOR_CARE_LEAVER_COVENANT.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_PRISONERS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_INTERNSHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_FREE_COURSES.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_MULTIPLY.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(true, true, true, false, false, true, true, true)), Arrays.asList(ANCHOR_APPRENTICESHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_CARE_LEAVER_COVENANT.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_PRISONERS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_FREE_COURSES.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_MULTIPLY.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_HTQ.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(true, true, true, false, false, true, true, false)), Arrays.asList(ANCHOR_CARE_LEAVER_COVENANT.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_PRISONERS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_FREE_COURSES.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_MULTIPLY.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(true, true, true, false, false, true, false, true)), Arrays.asList(ANCHOR_APPRENTICESHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_HTQ.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(true, true, true, false, false, true, false, false)), Arrays.asList(ANCHOR_APPRENTICESHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_CARE_LEAVER_COVENANT.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_PRISONERS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_FREE_COURSES.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_MULTIPLY.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_HTQ.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(true, true, true, false, false, false, true, true)), Arrays.asList(ANCHOR_APPRENTICESHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_BOOTCAMPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_CARE_LEAVER_COVENANT.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_PRISONERS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_T_LEVELS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_SWAP.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_INTERNSHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_FREE_COURSES.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_MULTIPLY.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_HTQ.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(true, true, true, false, false, false, true, false)), Arrays.asList(ANCHOR_CARE_LEAVER_COVENANT.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_PRISONERS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_T_LEVELS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_SWAP.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_INTERNSHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_FREE_COURSES.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_MULTIPLY.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(true, true, true, false, false, false, false, true)), Arrays.asList(ANCHOR_APPRENTICESHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_BOOTCAMPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_HTQ.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(true, true, true, false, false, false, false, false)), Arrays.asList(ANCHOR_APPRENTICESHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_BOOTCAMPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_CARE_LEAVER_COVENANT.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_PRISONERS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_T_LEVELS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_SWAP.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_INTERNSHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_FREE_COURSES.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_MULTIPLY.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_HTQ.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(true, true, false, true, true, true, true, true)), Arrays.asList(ANCHOR_APPRENTICESHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_BOOTCAMPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_CARE_LEAVER_COVENANT.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_PRISONERS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_T_LEVELS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_SWAP.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_INTERNSHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_FREE_COURSES.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_MULTIPLY.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_HTQ.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(true, true, false, true, true, true, true, false)), Arrays.asList(ANCHOR_CARE_LEAVER_COVENANT.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_PRISONERS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_T_LEVELS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_SWAP.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_INTERNSHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_FREE_COURSES.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_MULTIPLY.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(true, true, false, true, true, true, false, true)), Arrays.asList(ANCHOR_APPRENTICESHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_BOOTCAMPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_HTQ.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(true, true, false, true, true, true, false, false)), Arrays.asList(ANCHOR_APPRENTICESHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_BOOTCAMPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_CARE_LEAVER_COVENANT.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_PRISONERS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_T_LEVELS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_SWAP.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_INTERNSHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_FREE_COURSES.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_MULTIPLY.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_HTQ.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(true, true, false, true, true, false, true, true)), Arrays.asList(ANCHOR_BOOTCAMPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_CARE_LEAVER_COVENANT.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_PRISONERS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_T_LEVELS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_SWAP.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_INTERNSHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_FREE_COURSES.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_MULTIPLY.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(true, true, false, true, true, false, true, false)), Arrays.asList(ANCHOR_CARE_LEAVER_COVENANT.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_PRISONERS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_T_LEVELS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_SWAP.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_INTERNSHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_FREE_COURSES.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_MULTIPLY.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(true, true, false, true, true, false, false, true)), Arrays.asList(ANCHOR_BOOTCAMPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(true, true, false, true, true, false, false, false)), Arrays.asList(ANCHOR_BOOTCAMPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_CARE_LEAVER_COVENANT.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_PRISONERS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_T_LEVELS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_SWAP.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_INTERNSHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_FREE_COURSES.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_MULTIPLY.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(true, true, false, true, false, true, true, true)), Arrays.asList(ANCHOR_APPRENTICESHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_BOOTCAMPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_CARE_LEAVER_COVENANT.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_PRISONERS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_T_LEVELS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_SWAP.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_FREE_COURSES.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_MULTIPLY.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_HTQ.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(true, true, false, true, false, true, true, false)), Arrays.asList(ANCHOR_CARE_LEAVER_COVENANT.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_PRISONERS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_T_LEVELS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_SWAP.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_FREE_COURSES.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_MULTIPLY.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(true, true, false, true, false, true, false, true)), Arrays.asList(ANCHOR_APPRENTICESHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_BOOTCAMPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_HTQ.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(true, true, false, true, false, true, false, false)), Arrays.asList(ANCHOR_APPRENTICESHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_BOOTCAMPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_CARE_LEAVER_COVENANT.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_PRISONERS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_T_LEVELS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_SWAP.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_FREE_COURSES.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_MULTIPLY.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_HTQ.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(true, true, false, true, false, false, true, true)), Arrays.asList(ANCHOR_BOOTCAMPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_CARE_LEAVER_COVENANT.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_PRISONERS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_T_LEVELS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_SWAP.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_FREE_COURSES.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_MULTIPLY.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(true, true, false, true, false, false, true, false)), Arrays.asList(ANCHOR_CARE_LEAVER_COVENANT.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_PRISONERS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_T_LEVELS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_SWAP.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_FREE_COURSES.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_MULTIPLY.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(true, true, false, true, false, false, false, true)), Arrays.asList(ANCHOR_BOOTCAMPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(true, true, false, true, false, false, false, false)), Arrays.asList(ANCHOR_BOOTCAMPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_CARE_LEAVER_COVENANT.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_PRISONERS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_T_LEVELS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_SWAP.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_FREE_COURSES.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_MULTIPLY.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(true, true, false, false, true, true, true, true)), Arrays.asList(ANCHOR_APPRENTICESHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_CARE_LEAVER_COVENANT.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_PRISONERS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_INTERNSHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_FREE_COURSES.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_MULTIPLY.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_HTQ.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(true, true, false, false, true, true, true, false)), Arrays.asList(ANCHOR_CARE_LEAVER_COVENANT.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_PRISONERS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_INTERNSHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_FREE_COURSES.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_MULTIPLY.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(true, true, false, false, true, true, false, true)), Arrays.asList(ANCHOR_APPRENTICESHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_HTQ.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(true, true, false, false, true, true, false, false)), Arrays.asList(ANCHOR_APPRENTICESHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_CARE_LEAVER_COVENANT.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_PRISONERS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_INTERNSHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_FREE_COURSES.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_MULTIPLY.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_HTQ.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(true, true, false, false, true, false, true, true)), Arrays.asList(ANCHOR_CARE_LEAVER_COVENANT.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_PRISONERS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_INTERNSHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_FREE_COURSES.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_MULTIPLY.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(true, true, false, false, true, false, true, false)), Arrays.asList(ANCHOR_CARE_LEAVER_COVENANT.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_PRISONERS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_INTERNSHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_FREE_COURSES.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_MULTIPLY.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(true, true, false, false, true, false, false, true)), Arrays.asList(ANCHOR_HOME_PAGE_NO_SCHEMES_PRESENT_CLEAR_FILTERS));
        combinationsMap.put(new ArrayList<>(List.of(true, true, false, false, true, false, false, false)), Arrays.asList(ANCHOR_CARE_LEAVER_COVENANT.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_PRISONERS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_INTERNSHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_FREE_COURSES.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_MULTIPLY.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(true, true, false, false, false, true, true, true)), Arrays.asList(ANCHOR_APPRENTICESHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_CARE_LEAVER_COVENANT.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_PRISONERS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_FREE_COURSES.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_MULTIPLY.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_HTQ.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(true, true, false, false, false, true, true, false)), Arrays.asList(ANCHOR_CARE_LEAVER_COVENANT.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_PRISONERS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_FREE_COURSES.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_MULTIPLY.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(true, true, false, false, false, true, false, true)), Arrays.asList(ANCHOR_APPRENTICESHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_HTQ.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(true, true, false, false, false, true, false, false)), Arrays.asList(ANCHOR_APPRENTICESHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_CARE_LEAVER_COVENANT.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_PRISONERS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_FREE_COURSES.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_MULTIPLY.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_HTQ.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(true, true, false, false, false, false, true, true)), Arrays.asList(ANCHOR_APPRENTICESHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_BOOTCAMPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_CARE_LEAVER_COVENANT.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_PRISONERS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_T_LEVELS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_SWAP.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_INTERNSHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_FREE_COURSES.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_MULTIPLY.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_HTQ.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(true, true, false, false, false, false, true, false)), Arrays.asList(ANCHOR_CARE_LEAVER_COVENANT.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_PRISONERS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_T_LEVELS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_SWAP.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_INTERNSHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_FREE_COURSES.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_MULTIPLY.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(true, true, false, false, false, false, false, true)), Arrays.asList(ANCHOR_APPRENTICESHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_BOOTCAMPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_HTQ.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(true, true, false, false, false, false, false, false)), Arrays.asList(ANCHOR_APPRENTICESHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_BOOTCAMPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_CARE_LEAVER_COVENANT.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_PRISONERS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_T_LEVELS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_SWAP.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_INTERNSHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_FREE_COURSES.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_MULTIPLY.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_HTQ.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(true, false, true, true, true, true, true, true)), Arrays.asList(ANCHOR_APPRENTICESHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_BOOTCAMPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_CARE_LEAVER_COVENANT.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_PRISONERS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_T_LEVELS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_SWAP.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_INTERNSHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_HTQ.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(true, false, true, true, true, true, true, false)), Arrays.asList(ANCHOR_CARE_LEAVER_COVENANT.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_PRISONERS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_T_LEVELS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_SWAP.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_INTERNSHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(true, false, true, true, true, true, false, true)), Arrays.asList(ANCHOR_APPRENTICESHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_BOOTCAMPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_HTQ.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(true, false, true, true, true, true, false, false)), Arrays.asList(ANCHOR_APPRENTICESHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_BOOTCAMPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_CARE_LEAVER_COVENANT.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_PRISONERS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_T_LEVELS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_SWAP.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_INTERNSHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_HTQ.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(true, false, true, true, true, false, true, true)), Arrays.asList(ANCHOR_BOOTCAMPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_CARE_LEAVER_COVENANT.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_PRISONERS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_T_LEVELS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_SWAP.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_INTERNSHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(true, false, true, true, true, false, true, false)), Arrays.asList(ANCHOR_CARE_LEAVER_COVENANT.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_PRISONERS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_T_LEVELS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_SWAP.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_INTERNSHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(true, false, true, true, true, false, false, true)), Arrays.asList(ANCHOR_BOOTCAMPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(true, false, true, true, true, false, false, false)), Arrays.asList(ANCHOR_BOOTCAMPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_CARE_LEAVER_COVENANT.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_PRISONERS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_T_LEVELS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_SWAP.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_INTERNSHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(true, false, true, true, false, true, true, true)), Arrays.asList(ANCHOR_APPRENTICESHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_BOOTCAMPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_CARE_LEAVER_COVENANT.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_PRISONERS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_T_LEVELS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_SWAP.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_HTQ.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(true, false, true, true, false, true, true, false)), Arrays.asList(ANCHOR_CARE_LEAVER_COVENANT.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_PRISONERS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_T_LEVELS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_SWAP.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(true, false, true, true, false, true, false, true)), Arrays.asList(ANCHOR_APPRENTICESHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_BOOTCAMPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_HTQ.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(true, false, true, true, false, true, false, false)), Arrays.asList(ANCHOR_APPRENTICESHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_BOOTCAMPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_CARE_LEAVER_COVENANT.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_PRISONERS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_T_LEVELS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_SWAP.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_HTQ.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(true, false, true, true, false, false, true, true)), Arrays.asList(ANCHOR_BOOTCAMPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_CARE_LEAVER_COVENANT.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_PRISONERS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_T_LEVELS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_SWAP.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(true, false, true, true, false, false, true, false)), Arrays.asList(ANCHOR_CARE_LEAVER_COVENANT.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_PRISONERS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_T_LEVELS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_SWAP.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(true, false, true, true, false, false, false, true)), Arrays.asList(ANCHOR_BOOTCAMPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(true, false, true, true, false, false, false, false)), Arrays.asList(ANCHOR_BOOTCAMPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_CARE_LEAVER_COVENANT.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_PRISONERS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_T_LEVELS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_SWAP.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(true, false, true, false, true, true, true, true)), Arrays.asList(ANCHOR_APPRENTICESHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_CARE_LEAVER_COVENANT.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_PRISONERS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_INTERNSHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_HTQ.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(true, false, true, false, true, true, true, false)), Arrays.asList(ANCHOR_CARE_LEAVER_COVENANT.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_PRISONERS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_INTERNSHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(true, false, true, false, true, true, false, true)), Arrays.asList(ANCHOR_APPRENTICESHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_HTQ.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(true, false, true, false, true, true, false, false)), Arrays.asList(ANCHOR_APPRENTICESHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_CARE_LEAVER_COVENANT.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_PRISONERS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_INTERNSHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_HTQ.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(true, false, true, false, true, false, true, true)), Arrays.asList(ANCHOR_CARE_LEAVER_COVENANT.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_PRISONERS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_INTERNSHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(true, false, true, false, true, false, true, false)), Arrays.asList(ANCHOR_CARE_LEAVER_COVENANT.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_PRISONERS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_INTERNSHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(true, false, true, false, true, false, false, true)), Arrays.asList(ANCHOR_HOME_PAGE_NO_SCHEMES_PRESENT_CLEAR_FILTERS));
        combinationsMap.put(new ArrayList<>(List.of(true, false, true, false, true, false, false, false)), Arrays.asList(ANCHOR_CARE_LEAVER_COVENANT.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_PRISONERS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_INTERNSHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(true, false, true, false, false, true, true, true)), Arrays.asList(ANCHOR_APPRENTICESHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_CARE_LEAVER_COVENANT.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_PRISONERS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_HTQ.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(true, false, true, false, false, true, true, false)), Arrays.asList(ANCHOR_CARE_LEAVER_COVENANT.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_PRISONERS.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(true, false, true, false, false, true, false, true)), Arrays.asList(ANCHOR_APPRENTICESHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_HTQ.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(true, false, true, false, false, true, false, false)), Arrays.asList(ANCHOR_APPRENTICESHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_CARE_LEAVER_COVENANT.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_PRISONERS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_HTQ.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(true, false, true, false, false, false, true, true)), Arrays.asList(ANCHOR_APPRENTICESHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_BOOTCAMPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_CARE_LEAVER_COVENANT.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_PRISONERS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_T_LEVELS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_SWAP.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_INTERNSHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_HTQ.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(true, false, true, false, false, false, true, false)), Arrays.asList(ANCHOR_CARE_LEAVER_COVENANT.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_PRISONERS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_T_LEVELS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_SWAP.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_INTERNSHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(true, false, true, false, false, false, false, true)), Arrays.asList(ANCHOR_APPRENTICESHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_BOOTCAMPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_HTQ.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(true, false, true, false, false, false, false, false)), Arrays.asList(ANCHOR_APPRENTICESHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_BOOTCAMPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_CARE_LEAVER_COVENANT.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_PRISONERS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_T_LEVELS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_SWAP.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_INTERNSHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_HTQ.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(true, false, false, true, true, true, true, true)), Arrays.asList(ANCHOR_APPRENTICESHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_BOOTCAMPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_CARE_LEAVER_COVENANT.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_PRISONERS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_T_LEVELS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_SWAP.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_INTERNSHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_HTQ.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(true, false, false, true, true, true, false, true)), Arrays.asList(ANCHOR_APPRENTICESHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_BOOTCAMPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_HTQ.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(true, false, false, true, true, true, false, false)), Arrays.asList(ANCHOR_APPRENTICESHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_BOOTCAMPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_CARE_LEAVER_COVENANT.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_PRISONERS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_T_LEVELS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_SWAP.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_INTERNSHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_HTQ.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(true, false, false, true, true, false, true, true)), Arrays.asList(ANCHOR_BOOTCAMPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_CARE_LEAVER_COVENANT.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_PRISONERS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_T_LEVELS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_SWAP.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_INTERNSHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(true, false, false, true, true, false, true, false)), Arrays.asList(ANCHOR_CARE_LEAVER_COVENANT.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_PRISONERS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_T_LEVELS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_SWAP.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_INTERNSHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(true, false, false, true, true, false, false, true)), Arrays.asList(ANCHOR_BOOTCAMPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(true, false, false, true, true, false, false, false)), Arrays.asList(ANCHOR_BOOTCAMPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_CARE_LEAVER_COVENANT.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_PRISONERS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_T_LEVELS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_SWAP.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_INTERNSHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(true, false, false, true, false, true, true, true)), Arrays.asList(ANCHOR_APPRENTICESHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_BOOTCAMPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_CARE_LEAVER_COVENANT.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_PRISONERS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_T_LEVELS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_SWAP.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_HTQ.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(true, false, false, true, false, true, true, false)), Arrays.asList(ANCHOR_CARE_LEAVER_COVENANT.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_PRISONERS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_T_LEVELS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_SWAP.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(true, false, false, true, false, true, false, true)), Arrays.asList(ANCHOR_APPRENTICESHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_BOOTCAMPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_HTQ.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(true, false, false, true, false, true, false, false)), Arrays.asList(ANCHOR_APPRENTICESHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_BOOTCAMPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_CARE_LEAVER_COVENANT.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_PRISONERS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_T_LEVELS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_SWAP.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_HTQ.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(true, false, false, true, false, false, true, true)), Arrays.asList(ANCHOR_BOOTCAMPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_CARE_LEAVER_COVENANT.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_PRISONERS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_T_LEVELS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_SWAP.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(true, false, false, true, false, false, true, false)), Arrays.asList(ANCHOR_CARE_LEAVER_COVENANT.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_PRISONERS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_T_LEVELS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_SWAP.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(true, false, false, true, false, false, false, true)), Arrays.asList(ANCHOR_BOOTCAMPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(true, false, false, true, false, false, false, false)), Arrays.asList(ANCHOR_BOOTCAMPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_CARE_LEAVER_COVENANT.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_PRISONERS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_T_LEVELS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_SWAP.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(true, false, false, false, true, true, true, true)), Arrays.asList(ANCHOR_APPRENTICESHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_CARE_LEAVER_COVENANT.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_PRISONERS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_INTERNSHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_HTQ.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(true, false, false, false, true, true, true, false)), Arrays.asList(ANCHOR_CARE_LEAVER_COVENANT.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_PRISONERS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_INTERNSHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(true, false, false, false, true, true, false, true)), Arrays.asList(ANCHOR_APPRENTICESHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_HTQ.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(true, false, false, false, true, true, false, false)), Arrays.asList(ANCHOR_APPRENTICESHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_CARE_LEAVER_COVENANT.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_PRISONERS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_INTERNSHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_HTQ.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(true, false, false, false, true, false, true, true)), Arrays.asList(ANCHOR_CARE_LEAVER_COVENANT.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_PRISONERS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_INTERNSHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(true, false, false, false, true, false, true, false)), Arrays.asList(ANCHOR_CARE_LEAVER_COVENANT.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_PRISONERS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_INTERNSHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(true, false, false, false, true, false, false, true)), Arrays.asList(ANCHOR_HOME_PAGE_NO_SCHEMES_PRESENT_CLEAR_FILTERS));
        combinationsMap.put(new ArrayList<>(List.of(true, false, false, false, true, false, false, false)), Arrays.asList(ANCHOR_CARE_LEAVER_COVENANT.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_PRISONERS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_INTERNSHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(true, false, false, false, false, true, true, true)), Arrays.asList(ANCHOR_APPRENTICESHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_CARE_LEAVER_COVENANT.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_PRISONERS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_HTQ.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(true, false, false, false, false, true, true, false)), Arrays.asList(ANCHOR_CARE_LEAVER_COVENANT.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_PRISONERS.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(true, false, false, false, false, true, false, true)), Arrays.asList(ANCHOR_APPRENTICESHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_HTQ.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(true, false, false, false, false, true, false, false)), Arrays.asList(ANCHOR_APPRENTICESHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_CARE_LEAVER_COVENANT.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_PRISONERS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_HTQ.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(true, false, false, false, false, false, true, true)), Arrays.asList(ANCHOR_APPRENTICESHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_BOOTCAMPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_CARE_LEAVER_COVENANT.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_PRISONERS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_T_LEVELS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_SWAP.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_INTERNSHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_HTQ.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(true, false, false, false, false, false, true, false)), Arrays.asList(ANCHOR_CARE_LEAVER_COVENANT.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_PRISONERS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_T_LEVELS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_SWAP.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_INTERNSHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(true, false, false, false, false, false, false, true)), Arrays.asList(ANCHOR_APPRENTICESHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_BOOTCAMPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_HTQ.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(true, false, false, false, false, false, false, false)), Arrays.asList(ANCHOR_APPRENTICESHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_BOOTCAMPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_CARE_LEAVER_COVENANT.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_PRISONERS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_T_LEVELS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_SWAP.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_INTERNSHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_HTQ.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(false, true, true, true, true, true, true, true)), Arrays.asList(ANCHOR_APPRENTICESHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_BOOTCAMPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_FREE_COURSES.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_MULTIPLY.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_HTQ.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_T_LEVELS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_SWAP.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_INTERNSHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(false, true, true, true, true, true, true, false)), Arrays.asList(ANCHOR_FREE_COURSES.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_MULTIPLY.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_T_LEVELS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_SWAP.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_INTERNSHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(false, true, true, true, true, true, false, true)), Arrays.asList(ANCHOR_APPRENTICESHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_BOOTCAMPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_HTQ.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(false, true, true, true, true, true, false, false)), Arrays.asList(ANCHOR_APPRENTICESHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_BOOTCAMPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_FREE_COURSES.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_MULTIPLY.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_HTQ.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_T_LEVELS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_SWAP.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_INTERNSHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(false, true, true, true, true, false, true, true)), Arrays.asList(ANCHOR_BOOTCAMPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_FREE_COURSES.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_MULTIPLY.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_T_LEVELS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_SWAP.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_INTERNSHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(false, true, true, true, true, false, true, false)), Arrays.asList(ANCHOR_FREE_COURSES.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_MULTIPLY.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_T_LEVELS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_SWAP.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_INTERNSHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(false, true, true, true, true, false, false, true)), Arrays.asList(ANCHOR_BOOTCAMPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(false, true, true, true, true, false, false, false)), Arrays.asList(ANCHOR_BOOTCAMPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_FREE_COURSES.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_MULTIPLY.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_T_LEVELS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_SWAP.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_INTERNSHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(false, true, true, true, false, true, true, true)), Arrays.asList(ANCHOR_APPRENTICESHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_BOOTCAMPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_FREE_COURSES.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_MULTIPLY.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_HTQ.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_T_LEVELS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_SWAP.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(false, true, true, true, false, true, true, false)), Arrays.asList(ANCHOR_FREE_COURSES.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_MULTIPLY.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_T_LEVELS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_SWAP.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(false, true, true, true, false, true, false, true)), Arrays.asList(ANCHOR_APPRENTICESHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_BOOTCAMPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_HTQ.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(false, true, true, true, false, true, false, false)), Arrays.asList(ANCHOR_APPRENTICESHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_BOOTCAMPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_FREE_COURSES.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_MULTIPLY.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_HTQ.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_T_LEVELS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_SWAP.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(false, true, true, true, false, false, true, true)), Arrays.asList(ANCHOR_BOOTCAMPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_FREE_COURSES.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_MULTIPLY.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_T_LEVELS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_SWAP.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(false, true, true, true, false, false, true, false)), Arrays.asList(ANCHOR_FREE_COURSES.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_MULTIPLY.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_T_LEVELS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_SWAP.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(false, true, true, true, false, false, false, true)), Arrays.asList(ANCHOR_BOOTCAMPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(false, true, true, true, false, false, false, false)), Arrays.asList(ANCHOR_BOOTCAMPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_FREE_COURSES.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_MULTIPLY.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_T_LEVELS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_SWAP.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(false, true, true, false, true, true, true, true)), Arrays.asList(ANCHOR_APPRENTICESHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_FREE_COURSES.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_MULTIPLY.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_HTQ.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_INTERNSHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(false, true, true, false, true, true, true, false)), Arrays.asList(ANCHOR_FREE_COURSES.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_MULTIPLY.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_INTERNSHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(false, true, true, false, true, true, false, true)), Arrays.asList(ANCHOR_APPRENTICESHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_HTQ.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(false, true, true, false, true, true, false, false)), Arrays.asList(ANCHOR_APPRENTICESHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_FREE_COURSES.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_MULTIPLY.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_HTQ.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_INTERNSHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(false, true, true, false, true, false, true, true)), Arrays.asList(ANCHOR_FREE_COURSES.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_MULTIPLY.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_INTERNSHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(false, true, true, false, true, false, true, false)), Arrays.asList(ANCHOR_FREE_COURSES.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_MULTIPLY.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_INTERNSHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(false, true, true, false, true, false, false, true)), Arrays.asList(ANCHOR_HOME_PAGE_NO_SCHEMES_PRESENT_CLEAR_FILTERS));
        combinationsMap.put(new ArrayList<>(List.of(false, true, true, false, true, false, false, false)), Arrays.asList(ANCHOR_FREE_COURSES.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_MULTIPLY.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_INTERNSHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(false, true, true, false, false, true, true, true)), Arrays.asList(ANCHOR_APPRENTICESHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_FREE_COURSES.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_MULTIPLY.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_HTQ.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(false, true, true, false, false, true, true, false)), Arrays.asList(ANCHOR_FREE_COURSES.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_MULTIPLY.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(false, true, true, false, false, true, false, true)), Arrays.asList(ANCHOR_APPRENTICESHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_HTQ.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(false, true, true, false, false, true, false, false)), Arrays.asList(ANCHOR_APPRENTICESHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_FREE_COURSES.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_MULTIPLY.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_HTQ.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(false, true, true, false, false, false, true, true)), Arrays.asList(ANCHOR_APPRENTICESHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_BOOTCAMPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_FREE_COURSES.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_MULTIPLY.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_HTQ.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_T_LEVELS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_SWAP.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_INTERNSHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(false, true, true, false, false, false, true, false)), Arrays.asList(ANCHOR_FREE_COURSES.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_MULTIPLY.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_T_LEVELS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_SWAP.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_INTERNSHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(false, true, true, false, false, false, false, true)), Arrays.asList(ANCHOR_APPRENTICESHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_BOOTCAMPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_HTQ.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(false, true, true, false, false, false, false, false)), Arrays.asList(ANCHOR_APPRENTICESHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_BOOTCAMPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_FREE_COURSES.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_MULTIPLY.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_HTQ.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_T_LEVELS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_SWAP.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_INTERNSHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(false, true, false, true, true, true, true, true)), Arrays.asList(ANCHOR_APPRENTICESHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_BOOTCAMPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_FREE_COURSES.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_MULTIPLY.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_HTQ.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(false, true, false, true, true, true, true, false)), Arrays.asList(ANCHOR_FREE_COURSES.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_MULTIPLY.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(false, true, false, true, true, true, false, true)), Arrays.asList(ANCHOR_APPRENTICESHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_BOOTCAMPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_HTQ.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(false, true, false, true, true, true, false, false)), Arrays.asList(ANCHOR_APPRENTICESHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_BOOTCAMPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_FREE_COURSES.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_MULTIPLY.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_HTQ.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(false, true, false, true, true, false, true, true)), Arrays.asList(ANCHOR_BOOTCAMPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_FREE_COURSES.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_MULTIPLY.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(false, true, false, true, true, false, true, false)), Arrays.asList(ANCHOR_FREE_COURSES.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_MULTIPLY.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(false, true, false, true, true, false, false, true)), Arrays.asList(ANCHOR_BOOTCAMPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(false, true, false, true, true, false, false, false)), Arrays.asList(ANCHOR_BOOTCAMPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_FREE_COURSES.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_MULTIPLY.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(false, true, false, true, false, true, true, true)), Arrays.asList(ANCHOR_APPRENTICESHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_BOOTCAMPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_FREE_COURSES.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_MULTIPLY.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_HTQ.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(false, true, false, true, false, true, true, false)), Arrays.asList(ANCHOR_FREE_COURSES.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_MULTIPLY.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(false, true, false, true, false, true, false, true)), Arrays.asList(ANCHOR_APPRENTICESHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_BOOTCAMPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_HTQ.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(false, true, false, true, false, true, false, false)), Arrays.asList(ANCHOR_APPRENTICESHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_BOOTCAMPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_FREE_COURSES.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_MULTIPLY.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_HTQ.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(false, true, false, true, false, false, true, true)), Arrays.asList(ANCHOR_BOOTCAMPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_FREE_COURSES.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_MULTIPLY.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(false, true, false, true, false, false, true, false)), Arrays.asList(ANCHOR_FREE_COURSES.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_MULTIPLY.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(false, true, false, true, false, false, false, true)), Arrays.asList(ANCHOR_BOOTCAMPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(false, true, false, true, false, false, false, false)), Arrays.asList(ANCHOR_BOOTCAMPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_FREE_COURSES.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_MULTIPLY.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(false, true, false, false, true, true, true, true)), Arrays.asList(ANCHOR_APPRENTICESHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_FREE_COURSES.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_MULTIPLY.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_HTQ.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(false, true, false, false, true, true, true, false)), Arrays.asList(ANCHOR_FREE_COURSES.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_MULTIPLY.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(false, true, false, false, true, true, false, true)), Arrays.asList(ANCHOR_APPRENTICESHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_HTQ.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(false, true, false, false, true, true, false, false)), Arrays.asList(ANCHOR_APPRENTICESHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_FREE_COURSES.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_MULTIPLY.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_HTQ.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(false, true, false, false, true, false, true, true)), Arrays.asList(ANCHOR_FREE_COURSES.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_MULTIPLY.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(false, true, false, false, true, false, true, false)), Arrays.asList(ANCHOR_FREE_COURSES.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_MULTIPLY.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(false, true, false, false, true, false, false, true)), Arrays.asList(ANCHOR_HOME_PAGE_NO_SCHEMES_PRESENT_CLEAR_FILTERS));
        combinationsMap.put(new ArrayList<>(List.of(false, true, false, false, true, false, false, false)), Arrays.asList(ANCHOR_FREE_COURSES.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_MULTIPLY.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(false, true, false, false, false, true, true, true)), Arrays.asList(ANCHOR_APPRENTICESHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_FREE_COURSES.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_MULTIPLY.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_HTQ.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(false, true, false, false, false, true, true, false)), Arrays.asList(ANCHOR_FREE_COURSES.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_MULTIPLY.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(false, true, false, false, false, true, false, true)), Arrays.asList(ANCHOR_APPRENTICESHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_HTQ.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(false, true, false, false, false, true, false, false)), Arrays.asList(ANCHOR_APPRENTICESHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_FREE_COURSES.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_MULTIPLY.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_HTQ.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(false, true, false, false, false, false, true, true)), Arrays.asList(ANCHOR_APPRENTICESHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_BOOTCAMPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_FREE_COURSES.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_MULTIPLY.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_HTQ.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(false, true, false, false, false, false, true, false)), Arrays.asList(ANCHOR_FREE_COURSES.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_MULTIPLY.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(false, true, false, false, false, false, false, true)), Arrays.asList(ANCHOR_APPRENTICESHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_BOOTCAMPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_HTQ.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(false, true, false, false, false, false, false, false)), Arrays.asList(ANCHOR_APPRENTICESHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_BOOTCAMPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_FREE_COURSES.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_MULTIPLY.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_HTQ.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(false, false, true, true, true, true, true, true)), Arrays.asList(ANCHOR_BOOTCAMPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_T_LEVELS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_SWAP.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_INTERNSHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(false, false, true, true, true, true, true, false)), Arrays.asList(ANCHOR_T_LEVELS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_SWAP.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_INTERNSHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(false, false, true, true, true, true, false, true)), Arrays.asList(ANCHOR_BOOTCAMPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(false, false, true, true, true, true, false, false)), Arrays.asList(ANCHOR_BOOTCAMPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_T_LEVELS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_SWAP.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_INTERNSHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(false, false, true, true, true, false, true, true)), Arrays.asList(ANCHOR_BOOTCAMPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_T_LEVELS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_SWAP.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_INTERNSHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(false, false, true, true, true, false, true, false)), Arrays.asList(ANCHOR_T_LEVELS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_SWAP.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_INTERNSHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(false, false, true, true, true, false, false, true)), Arrays.asList(ANCHOR_BOOTCAMPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(false, false, true, true, true, false, false, false)), Arrays.asList(ANCHOR_BOOTCAMPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_T_LEVELS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_SWAP.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_INTERNSHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(false, false, true, true, false, true, true, true)), Arrays.asList(ANCHOR_BOOTCAMPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_T_LEVELS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_SWAP.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(false, false, true, true, false, true, true, false)), Arrays.asList(ANCHOR_T_LEVELS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_SWAP.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(false, false, true, true, false, true, false, true)), Arrays.asList(ANCHOR_BOOTCAMPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(false, false, true, true, false, true, false, false)), Arrays.asList(ANCHOR_BOOTCAMPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_T_LEVELS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_SWAP.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(false, false, true, true, false, false, true, true)), Arrays.asList(ANCHOR_BOOTCAMPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_T_LEVELS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_SWAP.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(false, false, true, true, false, false, true, false)), Arrays.asList(ANCHOR_T_LEVELS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_SWAP.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(false, false, true, true, false, false, false, true)), Arrays.asList(ANCHOR_BOOTCAMPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(false, false, true, true, false, false, false, false)), Arrays.asList(ANCHOR_BOOTCAMPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_T_LEVELS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_SWAP.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(false, false, true, false, true, true, true, true)), Arrays.asList(ANCHOR_INTERNSHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(false, false, true, false, true, true, true, false)), Arrays.asList(ANCHOR_INTERNSHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(false, false, true, false, true, true, false, true)), Arrays.asList(ANCHOR_HOME_PAGE_NO_SCHEMES_PRESENT_CLEAR_FILTERS));
        combinationsMap.put(new ArrayList<>(List.of(false, false, true, false, true, true, false, false)), Arrays.asList(ANCHOR_INTERNSHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(false, false, true, false, true, false, true, true)), Arrays.asList(ANCHOR_INTERNSHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(false, false, true, false, true, false, true, false)), Arrays.asList(ANCHOR_INTERNSHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(false, false, true, false, true, false, false, true)), Arrays.asList(ANCHOR_HOME_PAGE_NO_SCHEMES_PRESENT_CLEAR_FILTERS));
        combinationsMap.put(new ArrayList<>(List.of(false, false, true, false, true, false, false, false)), Arrays.asList(ANCHOR_INTERNSHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(false, false, true, false, false, true, true, true)), Arrays.asList(ANCHOR_HOME_PAGE_NO_SCHEMES_PRESENT_CLEAR_FILTERS));
        combinationsMap.put(new ArrayList<>(List.of(false, false, true, false, false, true, true, false)), Arrays.asList(ANCHOR_HOME_PAGE_NO_SCHEMES_PRESENT_CLEAR_FILTERS));
        combinationsMap.put(new ArrayList<>(List.of(false, false, true, false, false, true, false, true)), Arrays.asList(ANCHOR_HOME_PAGE_NO_SCHEMES_PRESENT_CLEAR_FILTERS));
        combinationsMap.put(new ArrayList<>(List.of(false, false, true, false, false, true, false, false)), Arrays.asList(ANCHOR_HOME_PAGE_NO_SCHEMES_PRESENT_CLEAR_FILTERS));
        combinationsMap.put(new ArrayList<>(List.of(false, false, true, false, false, false, true, true)), Arrays.asList(ANCHOR_BOOTCAMPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_T_LEVELS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_SWAP.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_INTERNSHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(false, false, true, false, false, false, true, false)), Arrays.asList(ANCHOR_T_LEVELS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_SWAP.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_INTERNSHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(false, false, true, false, false, false, false, true)), Arrays.asList(ANCHOR_BOOTCAMPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(false, false, true, false, false, false, false, false)), Arrays.asList(ANCHOR_BOOTCAMPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_T_LEVELS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_SWAP.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_INTERNSHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(false, false, false, true, true, true, true, true)), Arrays.asList(ANCHOR_CARE_LEAVER_COVENANT.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_PRISONERS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_FREE_COURSES.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_MULTIPLY.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_SWAP.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_T_LEVELS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_BOOTCAMPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_INTERNSHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_HTQ.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_APPRENTICESHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(false, false, false, true, true, true, true, false)), Arrays.asList(ANCHOR_CARE_LEAVER_COVENANT.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_PRISONERS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_FREE_COURSES.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_MULTIPLY.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_SWAP.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_T_LEVELS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_INTERNSHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(false, false, false, true, true, true, false, true)), Arrays.asList(ANCHOR_BOOTCAMPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_HTQ.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_APPRENTICESHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(false, false, false, true, true, true, false, false)), Arrays.asList(ANCHOR_CARE_LEAVER_COVENANT.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_PRISONERS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_FREE_COURSES.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_MULTIPLY.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_SWAP.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_T_LEVELS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_BOOTCAMPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_INTERNSHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_HTQ.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_APPRENTICESHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(false, false, false, true, true, false, true, true)), Arrays.asList(ANCHOR_CARE_LEAVER_COVENANT.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_PRISONERS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_FREE_COURSES.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_MULTIPLY.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_SWAP.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_T_LEVELS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_BOOTCAMPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_INTERNSHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(false, false, false, true, true, false, true, false)), Arrays.asList(ANCHOR_CARE_LEAVER_COVENANT.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_PRISONERS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_FREE_COURSES.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_MULTIPLY.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_SWAP.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_T_LEVELS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_INTERNSHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(false, false, false, true, true, false, false, true)), Arrays.asList(ANCHOR_BOOTCAMPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(false, false, false, true, true, false, false, false)), Arrays.asList(ANCHOR_CARE_LEAVER_COVENANT.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_PRISONERS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_FREE_COURSES.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_MULTIPLY.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_SWAP.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_T_LEVELS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_BOOTCAMPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_INTERNSHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(false, false, false, true, false, true, true, true)), Arrays.asList(ANCHOR_CARE_LEAVER_COVENANT.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_PRISONERS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_FREE_COURSES.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_MULTIPLY.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_SWAP.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_T_LEVELS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_BOOTCAMPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_HTQ.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_APPRENTICESHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(false, false, false, true, false, true, false, true)), Arrays.asList(ANCHOR_BOOTCAMPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_HTQ.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_APPRENTICESHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(false, false, false, true, false, true, false, false)), Arrays.asList(ANCHOR_CARE_LEAVER_COVENANT.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_PRISONERS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_FREE_COURSES.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_MULTIPLY.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_SWAP.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_T_LEVELS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_BOOTCAMPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_HTQ.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_APPRENTICESHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(false, false, false, true, false, false, true, true)), Arrays.asList(ANCHOR_CARE_LEAVER_COVENANT.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_PRISONERS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_FREE_COURSES.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_MULTIPLY.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_SWAP.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_T_LEVELS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_BOOTCAMPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(false, false, false, true, false, false, true, false)), Arrays.asList(ANCHOR_CARE_LEAVER_COVENANT.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_PRISONERS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_FREE_COURSES.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_MULTIPLY.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_SWAP.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_T_LEVELS.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(false, false, false, true, false, false, false, true)), Arrays.asList(ANCHOR_BOOTCAMPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(false, false, false, true, false, false, false, false)), Arrays.asList(ANCHOR_CARE_LEAVER_COVENANT.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_PRISONERS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_FREE_COURSES.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_MULTIPLY.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_SWAP.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_T_LEVELS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_BOOTCAMPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(false, false, false, false, true, true, true, true)), Arrays.asList(ANCHOR_CARE_LEAVER_COVENANT.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_PRISONERS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_FREE_COURSES.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_INTERNSHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_MULTIPLY.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_HTQ.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_APPRENTICESHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(false, false, false, false, true, true, true, false)), Arrays.asList(ANCHOR_CARE_LEAVER_COVENANT.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_PRISONERS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_FREE_COURSES.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_INTERNSHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_MULTIPLY.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(false, false, false, false, true, true, false, true)), Arrays.asList(ANCHOR_HTQ.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_APPRENTICESHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(false, false, false, false, true, true, false, false)), Arrays.asList(ANCHOR_CARE_LEAVER_COVENANT.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_PRISONERS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_FREE_COURSES.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_INTERNSHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_MULTIPLY.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_HTQ.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_APPRENTICESHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(false, false, false, false, true, false, true, true)), Arrays.asList(ANCHOR_CARE_LEAVER_COVENANT.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_PRISONERS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_FREE_COURSES.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_INTERNSHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_MULTIPLY.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(false, false, false, false, true, false, true, false)), Arrays.asList(ANCHOR_CARE_LEAVER_COVENANT.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_PRISONERS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_FREE_COURSES.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_INTERNSHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_MULTIPLY.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(false, false, false, false, true, false, false, true)), Arrays.asList(ANCHOR_HOME_PAGE_NO_SCHEMES_PRESENT_CLEAR_FILTERS));
        combinationsMap.put(new ArrayList<>(List.of(false, false, false, false, true, false, false, false)), Arrays.asList(ANCHOR_CARE_LEAVER_COVENANT.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_PRISONERS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_FREE_COURSES.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_INTERNSHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_MULTIPLY.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(false, false, false, false, false, true, true, true)), Arrays.asList(ANCHOR_HTQ.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_APPRENTICESHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_FREE_COURSES.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_CARE_LEAVER_COVENANT.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_PRISONERS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_MULTIPLY.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(false, false, false, false, false, true, true, false)), Arrays.asList(ANCHOR_FREE_COURSES.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_CARE_LEAVER_COVENANT.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_PRISONERS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_MULTIPLY.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(false, false, false, false, false, true, false, true)), Arrays.asList(ANCHOR_HTQ.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_APPRENTICESHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(false, false, false, false, false, true, false, false)), Arrays.asList(ANCHOR_HTQ.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_APPRENTICESHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_FREE_COURSES.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_CARE_LEAVER_COVENANT.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_PRISONERS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_MULTIPLY.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(false, false, false, false, false, false, true, true)), Arrays.asList(ANCHOR_CARE_LEAVER_COVENANT.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_PRISONERS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_FREE_COURSES.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_MULTIPLY.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_T_LEVELS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_SWAP.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_INTERNSHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_APPRENTICESHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_BOOTCAMPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_HTQ.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(false, false, false, false, false, false, true, false)), Arrays.asList(ANCHOR_CARE_LEAVER_COVENANT.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_PRISONERS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_FREE_COURSES.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_MULTIPLY.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_T_LEVELS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_SWAP.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_INTERNSHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(false, false, false, false, false, false, false, true)), Arrays.asList(ANCHOR_APPRENTICESHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_BOOTCAMPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_HTQ.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
        combinationsMap.put(new ArrayList<>(List.of(false, false, false, false, false, false, false, false)), Arrays.asList(ANCHOR_APPRENTICESHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_BOOTCAMPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_CARE_LEAVER_COVENANT.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_PRISONERS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_T_LEVELS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_SWAP.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_INTERNSHIPS.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_FREE_COURSES.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_MULTIPLY.get(SCHEME_ANCHOR_NAME_HOMEPAGE), ANCHOR_HTQ.get(SCHEME_ANCHOR_NAME_HOMEPAGE)));
    }

    @When("the user wants to compare the schemes")
    public void theUserWantsToCompareTheSchemes() {
        WebElement button = driver.findElement(By.xpath("//button[normalize-space(text())='Compare these schemes in a table']"));
        button.click();
    }

    private void theySeeGivenSchemesCompared(Map<String, String> schemesMap, boolean clickAnchor) throws InterruptedException {
        List<String> anchorsToRemove = new ArrayList<>();
        //anchorsToRemove.add(ANCHOR_COMPARED_PAGE_RETURN_TO_LIST);
        //anchorsToRemove.add(ANCHOR_HEADER_SKIP_TO_MAIN_CONTENT);
        //anchorsToRemove.add(ANCHOR_FIND_SCHEMES);
        anchorsToRemove.add(ANCHOR_HOME);

        anchorsToRemove.addAll(footerMap.keySet());
        anchorsToRemove.addAll(headersMap.keySet());

        anchorsToRemove.add(ANCHOR_SHARE_EMAIL); //stays on same page
        anchorsToRemove.add(ANCHOR_SHARE_FACEBOOK); //stays on same page
        anchorsToRemove.add(ANCHOR_SHARE_X); //stays on same page
        anchorsToRemove.add(ANCHOR_SHARE_LINKEDIN); //stays on same page

        anchorsToRemove.addAll(comparisonPageMap.keySet()); //stays on same page

        assertBreadCrumbs("Scheme Comparison");
        Map<String, String> expectedAnchorsMap = new HashMap<>();
        expectedAnchorsMap.putAll(headersMap);

        expectedAnchorsMap.putAll(schemesMap);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("comparison-results-table"))));
        } catch (TimeoutException t) {
            fail("Comparison table did not appear afer " + 10 + " seconds");
        }

        if (clickAnchor) {
            compareAndClickAnchors(expectedAnchorsMap, anchorsToRemove, new ComparisonPageSchemesComparator());
        } else {
            confirmActualAnchors(new ArrayList<>(expectedAnchorsMap.keySet()), anchorsToRemove, new ComparisonPageSchemesComparator());
        }
    }

    @Then("they see all the schemes compared")
    public void theySeeAllTheSchemesCompared() throws InterruptedException {
        theySeeGivenSchemesCompared(schemesComparisonPageMap, true);
    }

    @Then("all home page header and footer anchors link to the correct pages")
    public void allHomePageHeaderAndFooterAnchorsLinkToTheCorrectPages() throws InterruptedException {

        //WebElement imageElement = driver.findElement(By.cssSelector("img.app-header__department-for-education-logo"));
        //Assert.assertEquals("Accessibility - https://dfedigital.atlassian.net/browse/CE-303", "Teach in further education logo", imageElement.getAttribute("alt"));

        WebElement imageElement = driver.findElement(By.cssSelector("img.app-header__department-for-education-logo"));
        Assert.assertEquals("Teaching In further education Logo displayed", "Department for education logo", imageElement.getAttribute("alt"));


        //WebElement hiddenFilterButton = driver.findElement(By.id("filter-schemes"));
        //Assert.assertEquals("Accessibility - https://dfedigital.atlassian.net/browse/CE-306", "button", hiddenFilterButton.getAttribute("role"));

        WebElement anchorElement = getAnchor(ANCHOR_HOME_SKILLS_FOR_CAREERS);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String fontWeight = (String) js.executeScript("return window.getComputedStyle(arguments[0], null).getPropertyValue('font-weight');", anchorElement);

        //in chromedriver = 700, htmlunitdriver = bold
        Assert.assertTrue("Accessibility - https://dfedigital.atlassian.net/browse/CE-302", switch (fontWeight) {
            case "bold", "700" -> true;
            default -> false;
        });

        Map<String, String> expectedAnchorsMap = new HashMap<>();

        List<String> anchorsToRemove = new ArrayList<>();
        //anchorsToRemove.add(ANCHOR_FIND_SCHEMES);
        //anchorsToRemove.add(ANCHOR_HOME_PAGE_CLEAR_FILTERS);
        anchorsToRemove.addAll(findSchemesMap.keySet());
        anchorsToRemove.addAll(schemesHomePageDefaultMap.keySet());
        confirmPageHeaderAndFooterAnchors(expectedAnchorsMap, anchorsToRemove);
    }

    @Then("schemes are correctly sorted based on the chosen sort")
    public void schemesAreCorrectlySortedBasedOnTheChosenSort() throws InterruptedException, IOException {

        WebElement selectElement = driver.findElement(By.id(SCHEME_SORT));
        Select select = new Select(selectElement);

        select.selectByVisibleText("Popularity (most popular first)");

        confirmHomePageSchemes(new ArrayList<>(schemesHomePageDefaultMap.keySet()), new PopularityFindSchemePageSchemesComparator());

        select.selectByVisibleText("Duration (shortest first)");

        confirmHomePageSchemes(new ArrayList<>(schemesHomePageDefaultMap.keySet()), new DurationFindSchemePageSchemesComparator());

        select.selectByVisibleText("Cost (cheapest first)");

        confirmHomePageSchemes(new ArrayList<>(schemesHomePageDefaultMap.keySet()), new CostFindSchemePageSchemesComparator());

        //filter on 'Less than 6 months'
        Map<List<Boolean>, List<String>> combinationsMap = new LinkedHashMap<>();
        populateCombinationsMap(combinationsMap);

        driver.findElement(By.id("duration--less-than-6-months")).click();
        filterSchemesClicked();

        //should be default order for 'Less than 6 months' filter
        confirmHomePageSchemes(combinationsMap.get(new ArrayList<>(List.of(false, false, false, true, false, false, false, false))), new DefaultFindSchemePageSchemesComparator());
        selectElement = driver.findElement(By.id(SCHEME_SORT));
        select = new Select(selectElement);

        select.selectByVisibleText("Popularity (most popular first)");
        confirmHomePageSchemes(combinationsMap.get(new ArrayList<>(List.of(false, false, false, true, false, false, false, false))), new PopularityFindSchemePageSchemesComparator());

        select.selectByVisibleText("Duration (shortest first)");
        confirmHomePageSchemes(combinationsMap.get(new ArrayList<>(List.of(false, false, false, true, false, false, false, false))), new DurationFindSchemePageSchemesComparator());

        select.selectByVisibleText("Cost (cheapest first)");
        confirmHomePageSchemes(combinationsMap.get(new ArrayList<>(List.of(false, false, false, true, false, false, false, false))), new CostFindSchemePageSchemesComparator());

        //compare these schemes
        theUserWantsToCompareTheSchemes();
        Map<String, String> schemesMap = new HashMap<>();
        List schemes = combinationsMap.get(new ArrayList<>(List.of(false, false, false, true, false, false, false, false)));
        for (Object scheme : schemes) {
            schemesMap.put(homePageToComparePageSchemeMap.get(scheme), schemesComparisonPageMap.get(homePageToComparePageSchemeMap.get(scheme)));
        }


        theySeeGivenSchemesCompared(schemesMap, false);

        //clear filters on comparison page
        getAnchor(ANCHOR_COMPARISON_PAGE_CLEAR_FILTERS).click();
        theySeeGivenSchemesCompared(schemesComparisonPageMap, false);
    }



}