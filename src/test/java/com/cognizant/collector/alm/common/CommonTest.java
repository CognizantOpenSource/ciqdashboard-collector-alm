package com.cognizant.collector.alm.common;

import com.cognizant.collector.alm.component.CommonUtilComponent;
import com.cognizant.collector.alm.util.Util;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

class CommonTest {

    @Test
    void dateFromString(){
        String strDate = "2019-07-30";
        LocalDate localDate = LocalDate.parse(strDate);
        Date from = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        System.out.println("");
        Assertions.assertNotNull(from);
    }

    @Test
    void dateTest(){
        CommonUtilComponent utilComponent = new CommonUtilComponent();
        String date = utilComponent.parseDateToString(Calendar.getInstance().getTime());
        System.out.println(date);
        Date dateFromString = Util.getDateFromString(date);
        System.out.println(dateFromString);
        String date2 = utilComponent.parseDateToString(dateFromString);
        System.out.println(date2);
        Assertions.assertEquals(date2, date);
    }

    @Test
    void stringToDateTest(){
        LocalDate withOutTime = LocalDate.parse("2018-10-26");
        LocalDate withTime = LocalDate.parse("2018-10-26T07:14:06");
        Assertions.assertNotNull(withOutTime);
    }
}
