import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class ManipulatingZonedDateTime {
    public static void main(String[] args){
        populatingZonedDateTime();
    }

    private static void populatingZonedDateTime(){
        LocalDateTime dt = LocalDateTime.of(2020, 9,8,0,0,0);
        ZoneId sgZone = ZoneId.of("Asia/Singapore");
        ZonedDateTime zDt = ZonedDateTime.of(dt, sgZone);

        ZoneId tokyo = ZoneId.of("Asia/Tokyo");
        ZonedDateTime tkZdt = ZonedDateTime.now(tokyo);
        System.out.println("Time in tokyo: " + tkZdt);
    }

    private static void timeToLand(){
        LocalDateTime sg = LocalDateTime.of(2020, 1, 1, 8, 10, 0);
        sg.plusHours(18).plusMinutes(25);
        ZoneId sgZone = ZoneId.of("Asia/Singapore");
        ZonedDateTime sgZDT = ZonedDateTime.of(sg, sgZone);
        ZonedDateTime end = sgZDT.withZoneSameInstant(ZoneId.of("America/New_York"));
    }
}
