package java1;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AnotherMain {
    public static void main(String[] args) {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("d-MM-yyyy HH:mm");
        LocalDateTime start = LocalDateTime.parse("9-03-2020 08:30", dateFormat);
        LocalDateTime end = LocalDateTime.parse("9-04-2020 10:20", dateFormat);

        DateTimeFormatter df = DateTimeFormatter.ofPattern("d-MMM-yyyy H:mm");
        LocalDateTime dt = LocalDateTime.parse("1-Aug-2007 09:00", df);
    }
}
