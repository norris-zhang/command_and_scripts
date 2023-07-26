package com.guoba.tools;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;

public class TimeZoneConverter {
    public static void main(String[] args) {
//        listZones();
//        zonedConversion();
        localToZoned();
//        whichTimeZoneHasDifferentDateFromUTC();
//        dateTimeToTimestamp();
//        testZoneLocal();
    }

    private static void testZoneLocal() {
        ZonedDateTime now = ZonedDateTime.now();
        System.out.println(now);
        System.out.println(now.toLocalDateTime());
        System.out.println(now);
        ZonedDateTime zonedDateTime = now.withZoneSameInstant(ZoneId.of("America/Los_Angeles"));
        System.out.println("LA");
        System.out.println(zonedDateTime);
        System.out.println(zonedDateTime.toLocalDateTime());
        System.out.println(zonedDateTime);
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
        String localStr = "2023-07-13T11:15:15.999";
        LocalDateTime localDateTime = LocalDateTime.parse(localStr);
        ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.of("Pacific/Auckland"));
        System.out.println("Auckland");
        System.out.println(zonedDateTime);

        ZonedDateTime utc = zonedDateTime.withZoneSameInstant(ZoneId.of("UTC"));
        System.out.println("UTC");
        System.out.println(utc);
    }

    private static void zonedConversion() {

        //"2023-05-11 17:20:36 UTC+1200"
        String zonedStr = "2023-05-16T00:00:00+12:00";
        ZonedDateTime zonedDateTime = ZonedDateTime.parse(zonedStr);
        System.out.println("UTC+12");
        System.out.println(zonedDateTime);

        ZonedDateTime utc = zonedDateTime.withZoneSameInstant(ZoneId.of("UTC"));
        System.out.println("UTC");
        System.out.println(utc);


    }
}
