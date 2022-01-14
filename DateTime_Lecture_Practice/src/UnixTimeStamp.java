import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

// what is the difference in the time between singapore 8am and tokyo 10am

public class UnixTimeStamp {
    public static void main(String[] args){
        LocalDateTime sgAt8am = LocalDateTime.of(2020, 12, 1, 8,0,0);
        ZonedDateTime sgZoned = ZonedDateTime.of(sgAt8am, ZoneId.of("Asia/Singapore"));
        ZonedDateTime sgConvertTokyoZoned = sgZoned.withZoneSameInstant(ZoneId.of("Asia/Tokyo"));
        long unixsgConvertTokyoZoned = sgConvertTokyoZoned.toEpochSecond();

        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy/MM/d HH:mm:ss");
        LocalDateTime tokyoAt10am = LocalDateTime.parse("2020/12/1 10:0:0", df);
        ZonedDateTime tokyoAt10amZoned = ZonedDateTime.of(tokyoAt10am, ZoneId.of("Asia/Tokyo"));
        long unixtokyo10am = tokyoAt10amZoned.toEpochSecond();
        long diff = unixtokyo10am - unixsgConvertTokyoZoned;
        if(diff < 0){
            long positiveDiff = Math.abs(diff);
            System.out.println("Singapore is ahead by ");
        }
    }


    private static void calculateWithUnixTime(ZonedDateTime start, ZoneId id){
        long unixStart = start.toEpochSecond();
        long duration = getUnixTimeDuration(18, 10, 0);
        long end = unixStart + duration;
        Instant instant = Instant.ofEpochSecond(end);
    }

    private static long getUnixTimeDuration(long hours, long min, long sec){
        return hours * 3600 + min * 60 + sec;
    }

}
