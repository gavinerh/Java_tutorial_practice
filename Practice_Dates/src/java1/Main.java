package java1;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class Main {
    public static void TimePractice(String[] args){
        // Time in unix time milliseconds
        long ts = System.currentTimeMillis();

        // LocalDate

        //LocalTime

        //LocalDateTime
        LocalDateTime curDateTime = LocalDateTime.now();
        LocalDateTime aYearEnd = LocalDateTime.of(2019, 12, 31, 11, 59, 59);
        LocalDateTime newDt = aYearEnd.withYear(aYearEnd.getYear() + 1)
                .minusMonths(2)
                .minusDays(3)
                .plusHours(4)
                .plusMinutes(5)
                .plusSeconds(6);

        //ZoneId
        ZoneId sgZone = ZoneId.of("Asia/Singapore");

        //ZonedDateTime : takes in datetime and time zone
        ZonedDateTime dtWithTz = ZonedDateTime.of(newDt, sgZone);

        //can provide a timezone in the now method or not
        ZonedDateTime toktoTime = ZonedDateTime.now(ZoneId.of("Asia/Tokyo"));

        //DateTimeFormatter
        DateTimeFormatter df = DateTimeFormatter.ofPattern("d/MM/yyyy HH:mm");
        LocalDateTime date1 = LocalDateTime.parse("01/02/2021 00:00", df);
    }

    public static void main(String[] args){
        LocalDateTime dt = LocalDateTime.of(2020,1,1,8,10);
        ZoneId sgZone = ZoneId.of("Asia/Singapore");

        ZonedDateTime start = ZonedDateTime.of(dt, sgZone);
        System.out.println("Flight started at " + start);

        ZoneId nyZone = ZoneId.of("America/New_York");
        ZonedDateTime end = start.plusHours(18)
                .plusMinutes(10)
                .withZoneSameInstant(nyZone);
        System.out.println("Flight ended at " + end.format(DateTimeFormatter.ofPattern("d/MM/yyyy HH:mm")));

        ZonedDateTime endTime = calculateWithUnixTime(start, nyZone);
        System.out.println("Flight ended with unix calculations: " + endTime.format(DateTimeFormatter.ofPattern("d-MM-yyyy HH:mm")));
    }

    private static ZonedDateTime calculateWithUnixTime(ZonedDateTime start, ZoneId zone){
        long unixStart = start.toEpochSecond();
        long duration = getUnixTimeDuration(18, 10, 0);
        long unixEnd = unixStart + duration;
        Instant i = Instant.ofEpochSecond(unixEnd);
        return ZonedDateTime.ofInstant(i, zone);
    }

    private static long getUnixTimeDuration(long hours, long min, long sec){
        return hours * 3600 + min * 60 + sec;
    }
}
