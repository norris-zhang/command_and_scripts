package com.guoba.tools;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Set;

public class TimeZoneConverter {
    public static void main(String[] args) {
//        listZones();
//        zonedConversion();
//        localToZoned();
        whichTimeZoneHasDifferentDateFromUTC();
    }

    private static void whichTimeZoneHasDifferentDateFromUTC() {
        Set<String> availableZoneIds = ZoneId.getAvailableZoneIds();
        ZonedDateTime zonedDateTime = ZonedDateTime.of(2022, 1, 1, 0, 0, 0, 0, ZoneId.of("UTC"));
        System.out.println(zonedDateTime);
        int dayOfMonth = zonedDateTime.getDayOfMonth();
        availableZoneIds.forEach(tz -> {
            ZonedDateTime zdt = zonedDateTime.withZoneSameInstant(ZoneId.of(tz));
            if (zdt.getDayOfMonth() != dayOfMonth) {
                System.out.println("timezone: " + tz);
                System.out.println(zdt);
            }
        });
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
