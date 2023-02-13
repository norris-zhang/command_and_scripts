package com.guoba.tools;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;

public class TimeZoneConverter {
    public static void main(String[] args) {
//        listZones();
        zonedConversion();
//        localToZoned();
//        whichTimeZoneHasDifferentDateFromUTC();
//        dateTimeToTimestamp();
    }

    private static void dateTimeToTimestamp() {
        ZonedDateTime zdt = ZonedDateTime.parse("2022-10-31T02:02:02+13:00", DateTimeFormatter.ISO_OFFSET_DATE_TIME);
        System.out.println(zdt.toString());

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
        String timeStr = "2023-02-08T01:40:53.000Z";
        ZonedDateTime zdt = ZonedDateTime.parse(timeStr);
        ZonedDateTime destZdt = zdt.withZoneSameInstant(ZoneId.of("Pacific/Auckland"));
        System.out.println(zdt);
        System.out.println(destZdt);
    }
}
