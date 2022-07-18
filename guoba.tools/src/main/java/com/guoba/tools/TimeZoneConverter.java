package com.guoba.tools;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class TimeZoneConverter {
    public static void main(String[] args) {
        //UTC  2022-07-12T00:02:13.571Z
        //Pacific/Auckland 2022-07-12T12:02:13.571+12:00[Pacific/Auckland]
        String timeStr = "2022-07-18T12:22:01.001+12:00";
        ZonedDateTime zdt = ZonedDateTime.parse(timeStr);
        ZonedDateTime destZdt = zdt.withZoneSameInstant(ZoneId.of("UTC"));
        System.out.println(zdt);
        System.out.println(destZdt);
    }
}
