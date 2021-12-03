package Practice;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class Main {
    public static void main(String[] args){
        LocalDateTime dt = LocalDateTime.of(2020, 1, 1, 8, 10);
        ZoneId sgZone = ZoneId.of("Asia/Singapore");
        ZonedDateTime start = ZonedDateTime.of(dt, sgZone);
        long unixStart = start.toEpochSecond();
        long duration = 18 * 3600 + 10 * 60;
        long unixEnd = unixStart + duration;
        Instant i = Instant.ofEpochSecond(unixEnd);
        ZonedDateTime nydt = ZonedDateTime.ofInstant(i, ZoneId.of("America/New_York"));
        System.out.println("Flight ended at: " + nydt);

//        dt = dt.plusHours(18).plusMinutes(10);
    }
}
