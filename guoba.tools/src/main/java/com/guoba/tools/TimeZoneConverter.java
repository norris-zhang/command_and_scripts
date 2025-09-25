package com.guoba.tools;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;
import java.util.stream.Collectors;

public class TimeZoneConverter {
    public static void main(String[] args) {
//        listZones();
//        localToZoned();
//        dateTimeToTimestamp();
        convertTimezone();
//        convertOffset();
    }

    private static void convertOffset() {
        ZoneId cet = ZoneId.of("CET");
        ZonedDateTime cetTime = LocalDateTime.parse("2024-08-10T21:33").atZone(cet);
        ZonedDateTime aucklandTime = cetTime.withZoneSameInstant(ZoneId.of("Pacific/Auckland"));
        System.out.println("Auckland time is: " + aucklandTime);


        System.out.println("Auckland time is: " + LocalDateTime.parse("2024-08-10T05:30").atZone(TimeZone.getTimeZone("AEST").toZoneId()).withZoneSameInstant(ZoneId.of("Pacific/Auckland")));
        System.out.println("Auckland time is: " + LocalDateTime.parse("2024-08-10T05:30").atOffset(ZoneOffset.ofHours(10)).atZoneSameInstant(ZoneId.of("Pacific/Auckland")));
        System.out.println("Auckland time is: " + LocalDateTime.parse("2024-08-10T21:30").atOffset(ZoneOffset.ofHours(2)).atZoneSameInstant(ZoneId.of("Pacific/Auckland")));
        System.out.println("Auckland time is: " + LocalDateTime.parse("2024-08-10T21:30").atZone(ZoneId.of("Europe/Paris")).withZoneSameInstant(ZoneId.of("Pacific/Auckland")));

        System.out.println(ZoneId.getAvailableZoneIds().stream().sorted().collect(Collectors.toList()));
        // 10 August 2024 02:33 GMT+12
        String offsetDateTime = "2024-08-10 02:33 GMT+12";
        OffsetDateTime offsetDT = LocalDateTime.parse("2024-08-10T02:33").atOffset(ZoneOffset.ofHours(12));
        ZonedDateTime zonedDateTime = offsetDT.atZoneSameInstant(ZoneId.of("Pacific/Auckland"));
        System.out.println(zonedDateTime);
    }

    private static void convertTimezone() {
        Instant now = Instant.now();
        System.out.println(now.toEpochMilli());
        System.out.println(now.getNano());
        String localDateTimeStr = "2025-09-22T16:00:00.000";
//        String fromZone = "Australia/Melbourne";
        String fromZone = "Pacific/Auckland";
//        String fromZone = "UTC";
//        String fromZone = "America/New_York";
//        String toZone = "UTC";
//        String toZone = "Australia/Melbourne";
//        String toZone = "Pacific/Auckland";
        String toZone = "America/Los_Angeles";
        LocalDateTime localDateTime = LocalDateTime.parse(localDateTimeStr);
        ZonedDateTime fromDateTime = localDateTime.atZone(ZoneId.of(fromZone));
        ZonedDateTime toDateTime = fromDateTime.withZoneSameInstant(ZoneId.of(toZone));
        System.out.printf(" %s = %s%n", fromDateTime, toDateTime);
        System.out.printf("%s timestamp is %d%n%n", fromDateTime, fromDateTime.toInstant().toEpochMilli());

        long timestamp = 1727399319194L;
        Instant instant = Instant.ofEpochMilli(timestamp);
        ZonedDateTime zonedDateTime = instant.atZone(ZoneId.of("Pacific/Auckland"));
        System.out.printf("timestamp %d is %s%n%n", timestamp, zonedDateTime);


    }

    private static void dateTimeToTimestamp() {
        System.out.println("===== dateTimeToTimestamp =====");
        ZonedDateTime zdt = ZonedDateTime.now(ZoneId.of("UTC"));
        System.out.println(zdt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")));

    }

    private static void listZones() {
        System.out.println(ZoneId.getAvailableZoneIds());
    }

    private static void localToZoned() {
        String localStr = "2024-05-28T16:12:04";
        LocalDateTime localDateTime = LocalDateTime.parse(localStr);
        ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.of("UTC"));
        System.out.println(zonedDateTime);

        ZonedDateTime utc = zonedDateTime.withZoneSameInstant(ZoneId.of("Pacific/Auckland"));
        System.out.println(utc);
    }
}
