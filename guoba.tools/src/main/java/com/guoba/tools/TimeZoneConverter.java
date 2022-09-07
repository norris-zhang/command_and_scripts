package com.guoba.tools;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class TimeZoneConverter {
    public static void main(String[] args) {
//        listZones();
//        zonedConversion();
        localToZoned();
    }

    private static void listZones() {
        System.out.println(ZoneId.getAvailableZoneIds());
    }

    private static void localToZoned() {
        String localStr = "2022-08-02T00:00:00.000";
        LocalDateTime localDateTime = LocalDateTime.parse(localStr);
        System.out.println(localDateTime);
        ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.of("Australia/Sydney"));
        System.out.println(zonedDateTime);
        ZonedDateTime nzTime = zonedDateTime.withZoneSameInstant(ZoneId.of("Pacific/Auckland"));
        System.out.println(nzTime);
    }

    private static void zonedConversion() {
        //UTC  2022-07-12T00:02:13.571Z
        //Pacific/Auckland 2022-07-12T12:02:13.571+12:00[Pacific/Auckland]
        String timeStr = "2022-08-02T00:00:00.000+12:00";
        ZonedDateTime zdt = ZonedDateTime.parse(timeStr);
        ZonedDateTime destZdt = zdt.withZoneSameInstant(ZoneId.of("UTC"));
        System.out.println(zdt);
        System.out.println(destZdt);
    }
}
