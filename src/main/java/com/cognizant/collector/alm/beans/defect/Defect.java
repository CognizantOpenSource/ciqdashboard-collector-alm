
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

package com.cognizant.collector.alm.beans.defect;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/*
 * Defect
 *
 * @author Cognizant
 */

@Data
@Document(collection = "#{T(com.cognizant.collector.alm.component.CommonUtilComponent).getAlmCollectionName()}")
@CompoundIndex(name = "proj_def_index",
        def = "{'projectName': 1, 'defectId': 1}",
        unique = true)
public class Defect {
    @Id
    private String id;
    private String defectId;
    private String defectName;
    private String assetType = "Defect";
    private String domainName;
    private String projectName;
    private String description;
    private String severity;
    private String status;
    private String subject;
    private String project;
    private String attachment;
    private String reproducible;
    private String priority;
    private String environment;

    /*detected-by*/
    private String detectedBy;
    /*owner*/
    private String assignedTo;

    /*detected-in-rcyc*/
    private String detectedCycleId;
    private String detectedCycleName;
    /*detected-in-rel*/
    private String detectedReleaseId;
    private String detectedReleaseName;
    /*target-rcyc*/
    private String targetCycleId;
    private String targetCycleName;
    /*target-rel*/
    private String targetReleaseId;
    private String targetReleaseName;
    /*dev-comments*/
    private String devComments;
    /*actual-fix-time*/
    private Long actualFixTime;

    /*detection-version*/
    private String detectionVersion;
    /*has-linkage*/
    private String hasLinkage;
    /*has-others-linkage*/
    private String hasOthersLinkage;
    /*cycle-id*/
    private String cycleId;
    /*request-type*/
    private String requestType;
    /*run-reference*/
    private String runReference;
    /*request-note*/
    private String requestNote;
    /*request-server*/
    private String requestServer;
    /*to-mail*/
    private String toMail;
    /*step-reference*/
    private String stepReference;
    /*estimated-fix-time*/
    private String estimatedFixTime;
    /*ver-stamp*/
    private String verStamp;
    /*request-id*/
    private String requestId;
    /*cycle-reference*/
    private String cycleReference;
    /*test-reference*/
    private String testReference;
    /*planned-closing-ver*/
    private String plannedClosingVer;
    /*extended-reference*/
    private String extendedReference;
    /*closing-version*/
    private String closingVersion;
    /*has-change*/
    private String hasChange;

    /*closing-date*/
    private Date closingDate;
    /*last-modified*/
    private Date lastModified;
    /*creation-time*/
    private Date creationDate;
}
