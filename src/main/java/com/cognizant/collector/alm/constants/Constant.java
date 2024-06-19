
/*
 *    © [2021] Cognizant. All rights reserved.
 *
 *     Licensed under the Apache License, Version 2.0 (the "License");
 *     you may not use this file except in compliance with the License.
 *     You may obtain a copy of the License at
 *
 *         http:www.apache.orglicensesLICENSE-2.0
 *
 *     Unless required by applicable law or agreed to in writing, software
 *     distributed under the License is distributed on an "AS IS" BASIS,
 *     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *     See the License for the specific language governing permissions and
 *     limitations under the License.
 */

package com.cognizant.collector.alm.constants;

/*
 * Constant
 *
 * @author Cognizant
 */

import java.util.*;

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

    public static final String SOURCE = "source_alm_";

    public static final List<String> PARENT_TYPES = List.of("test", "requirement", "defect", "run");

    private Constant() {
    }
}
