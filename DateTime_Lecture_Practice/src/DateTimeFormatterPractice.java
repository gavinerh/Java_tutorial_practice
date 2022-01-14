import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateTimeFormatterPractice {
    public static void main(String[] args){
        DateTimeFormatting();
    }

    private static void DateTimeFormatting(){
        DateTimeFormatter df = DateTimeFormatter.ofPattern("d/MM/yyyy");
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now.format(df));

        DateTimeFormatter df2 = DateTimeFormatter.ofPattern("HH:mm a");
        System.out.println(now.format(df2));

        ZonedDateTime zonedNow = ZonedDateTime.of(now, ZoneId.of("Asia/Singapore"));
        ZonedDateTime ny = zonedNow.withZoneSameInstant(ZoneId.of("America/New_York"));
        DateTimeFormatter df3 = DateTimeFormatter.ofPattern("d/MM/yy HH:mm a");
        System.out.println(ny.format(df3));
    }
}
