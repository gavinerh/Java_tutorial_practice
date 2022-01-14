import java.time.LocalDateTime;

public class LocalDateTimePractice {
    // LocalDate: represents a date (year, month, day)

    // LocalTime: represents a time (H, m, s)

    // LocalDateTime: represent date and time

    // ZonedDateTime: represent date, time and time zone

    // DateTimeFormatter: displays and parses time/date objects
    public static void main(String[] args){
        localDateTimeInstance();
    }

    private static void localDateTimeInstance(){
        LocalDateTime currDateTime = LocalDateTime.now();
        System.out.println(currDateTime);

        LocalDateTime dt = LocalDateTime.of(2019, 12, 31, 11, 59, 59);
        System.out.println(dt);

        // manipulate datetime

        LocalDateTime newDt = dt.withYear(2020)
                .plusMonths(11)
                .plusDays(14)
                .plusHours(13)
                .plusMinutes(30)
                .plusSeconds(44);

        System.out.println(newDt.isBefore(dt));
    }
}


