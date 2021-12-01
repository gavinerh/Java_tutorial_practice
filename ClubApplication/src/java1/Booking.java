package java1;

import java.time.LocalDateTime;

public class Booking {
    LocalDateTime start;
    LocalDateTime end;
    Member member;
    Facility facility;
    public Booking(Member m, Facility f, String startDate, String endDate){
        member = m;
        f = facility;

    }
}
