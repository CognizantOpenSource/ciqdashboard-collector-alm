package com.cognizant.collector.alm.beans.requirement;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalTime;
import java.util.Date;

@Data
@Document(collection = "#{T(com.cognizant.collector.alm.component.CommonUtilComponent).getAlmRequirementCollectionName()}")
@CompoundIndex(name = "proj_req_index",
        def = "{'projectName': 1, 'requirementId': 1}",
        unique = true)
public class Requirement {
    @Id
    private String id;
    private String requirementId;
    private String requirementName;
    private String description;
    private String attachment;
    private String status;
    private String domainName;
    private String projectName;
    /*target-rel*/
    private String targetReleaseId;
    private String targetReleaseName;
    /*target-rcyc*/
    private String targetCycleId;
    private String targetCycleName;
    /*owner*/
    private String author;
    /*parent-id*/
    private String parentId;
    private String comments;
    /*type-id*/
    private String requirementTypeId;
    private String requirementTypeName;
    /*req-product*/
    private String product;
    /*father-name*/
    private String fatherName;
    /*req-reviewed*/
    private String reviewed;
    /*req-priority*/
    private String priority;
    /*has-linkage*/
    private String hasLinkage;
    /*no-of-sons*/
    private String noOfSons;
    /*has-rich-content*/
    private String hasRichContent;
    /*ver-stamp*/
    private String verStamp;
    /*istemplate*/
    private String isTemplate;
    /*hierarchical-path*/
    private String hierarchicalPath;
    /*order-id*/
    private String orderId;
    /*creation-time*/
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date creationDate;
    /*req-time*/
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    private LocalTime creationTime;
    /*last-modified*/
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastModified;
    /*vc-version-number*/
    private String versionNumber;

}
