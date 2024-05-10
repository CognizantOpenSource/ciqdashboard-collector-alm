
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

package com.cognizant.collector.alm.beans.test;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/*
 * TestRun
 *
 * @author Cognizant
 */

@Document(collection = "#{T(com.cognizant.collector.alm.component.CommonUtilComponent).getAlmCollectionName()}")
@CompoundIndex(name = "proj_test_index",
        def = "{'projectName': 1, 'testId': 1}",
        unique = true)
@Data
public class Test {
    @Id
    private String id;
    private String testId;
    private String testName;
    private String assetType = "Test";
    private String description;
    private String domainName;
    private String projectName;
    private String status;
    private String template;
    private String timeout;
    private String attachment;
    /*owner*/
    private String designer;
    private String steps;
    /*subtype-id*/
    private String type;
    /*exec-status*/
    private String executionStatus;
    /*dev-comments*/
    private String devComments;

    /*base-test-id*/
    private String baseTestId;
    /*has-linkage*/
    private String hasLinkage;

    /*order-id*/
    private String orderId;
    /*parent-id*/
    private String parentId;
    /*step-param*/
    private String stepParam;

    /*creation-time*/
    private Date creationDate;
    /*last-modified*/
    private Date lastModified;

}
