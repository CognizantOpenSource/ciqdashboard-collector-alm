
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

package com.cognizant.collector.alm.beans.cycle;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/*
 * Cycle
 *
 * @author Cognizant
 */

@Data
@Document(collection = "#{T(com.cognizant.collector.alm.component.CommonUtilComponent).getAlmCycleCollectionName()}")
@CompoundIndex(name = "proj_cyc_index",
        def = "{'projectName': 1, 'cycleId': 1}",
        unique = true)
public class Cycle {
    @Id
    private String id;
    private String cycleId;
    private String cycleName;
    private String description;
    private String domainName;
    private String projectName;
    /*parent-id*/
    private String releaseId;
    /*req-count*/
    private int reqCount;
    /*ver-stamp*/
    private String verStamp;
    /*cf-count*/
    private int cfCount;
    /*has-attachments*/
    private String hasAttachments;
    /*start-date*/
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date startDate;
    /*end-date*/
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date endDate;
    /*last-modified*/
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastModified;


}
