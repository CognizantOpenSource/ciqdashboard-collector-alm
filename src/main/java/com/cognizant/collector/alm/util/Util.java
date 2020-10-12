package com.cognizant.collector.alm.util;

import com.cognizant.collector.alm.beans.cycle.Cycle;
import com.cognizant.collector.alm.beans.release.Release;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;

public class Util {
    private Util() {
    }

    public static String getUniqueId(String projectId, String componentId){
        return projectId+"P"+componentId;
    }

    public static Cycle getDefaultCycle(String domainName, String projectName, String releaseId){
        Cycle cycle = new Cycle();
        cycle.setCycleId("0000");
        cycle.setCycleName("DEFAULT");
        cycle.setDomainName(domainName);
        cycle.setProjectName(projectName);
        cycle.setReleaseId(releaseId);
        return cycle;
    }

    public static Release getDefaultRelease(String domainName, String projectName) {
        Release defaultRelease = new Release();
        defaultRelease.setReleaseId("0000");
        defaultRelease.setReleaseName("DEFAULT");
        defaultRelease.setDomainName(domainName);
        defaultRelease.setProjectName(projectName);
        return defaultRelease;
    }

    public static Date getDateFromString(String strDate) {
        if (StringUtils.isEmpty(strDate)) return null;
        try {
            if (strDate.length() == 10) {
                return Date.from(LocalDate.parse(strDate).atStartOfDay(ZoneId.systemDefault()).toInstant());
            } else if (strDate.length() == 19) {
                if (strDate.contains(" ")) strDate = strDate.replace(" ", "T");
                return Date.from(LocalDateTime.parse(strDate).atZone(ZoneId.systemDefault()).toInstant());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static LocalTime getTimeFromString(String strTime) {
        try {
            return LocalTime.parse(strTime);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
