package com.cognizant.collector.alm.constants;

public class Constant {

    public static final int RESULTS_PER_PAGE = 100;
    public static final int PAGE_STARTS_AT = 1;

    public static final String PAGE_SIZE = "page-size";
    public static final String START_INDEX = "start-index";

    public static final String PROJECT = "project='%s'";

    public static final String REQ_RELEASE = "release.id['%s']";
    public static final String DEF_RELEASE = "detected-in-rel['%s']";
    public static final String TEST_RELEASE = "{requirement.target-rel['%s']}";
    public static final String RUN_CYCLE = "{assign-rcyc['%s']}";

    public static final String TEST_REQCOVERAGE= "{test.id[%s]}";
    public static final String DEF_DEFECTLINK= "{first-end-point[%s]}";


    public static final String QUERY_CREATION_TIME = "{creation-time[>='%s']}"; //Not Used
    public static final String QUERY_LAST_MODIFIED = "last-modified[>='%s']";
    public static final String QUERY_LAST_MODIFIED_GTE = "last-modified[>='%s']";
    public static final String QUERY_LAST_MODIFIED_LT = "last-modified[<'%s']";
    public static final String QUERY_AND = "&";
    public static final String QUERY = "query";

    private Constant() {
    }
}
