
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

package com.cognizant.collector.alm.beans.release;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/*
 * Release
 *
 * @author Cognizant
 */

@Data
@Document(collection = "#{T(com.cognizant.collector.alm.component.CommonUtilComponent).getAlmCollectionName()}")
@CompoundIndex(name = "proj_rel_index", def = "{'projectName': 1, 'releaseId': 1}", unique = true)
public class Release {
    @Id
    private String id;
    private String releaseId;
    private String releaseName;
    private String assetType = "Release";
    private String domainId;
    private String domainName;
    private String projectId;
    private String projectName;
    private String description;
    private String parentId;
    private Date startDate;
    private Date endDate;
    private Date lastModified;
    private int reqCount;
    private String verStamp;
    private int scopeItemsCount;
    private int milestonesCount;
    private Object hasAttachments;
}
