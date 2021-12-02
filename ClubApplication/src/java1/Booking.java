package java1;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Booking {
    private LocalDateTime start;
    private LocalDateTime end;
    private Member member;
    private Facility facility;

    public Booking(Member m, Facility f, LocalDateTime startDate, LocalDateTime endDate){
        try{
            if (!(m instanceof Member || m == null)){
                System.out.println("Member is null");
                throw new BadBookingException();
            }
            if (!(f instanceof Facility || f == null)){
                System.out.println("Facility is null");
                throw new BadBookingException();
            }
            if (startDate == null || endDate == null){
                System.out.println("Date is null");
                throw new BadBookingException("No start or end date provided");
            }
            member = m;
            facility = f;
            start = startDate;
            end = endDate;
        } catch(BadBookingException e){
            e.getMessage();
        } catch (Exception e){
            e.getMessage();
        }

    }

    public Member getMember(){
        return member;
    }

    public LocalDateTime getStartDateTime(){
        return start;
    }
    public LocalDateTime getEndDateTime(){
        return end;
    }
    public Facility getFacility(){
        return facility;
    }
    public boolean overlaps(Booking another){
        if(end.isAfter(another.start) || start.isAfter(another.end)){
            return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object o){
        Booking b = (Booking) o;
        if (!this.start.equals(b.getStartDateTime())) return false;
        if (!this.end.equals(b.getEndDateTime())) return false;
        if (!this.member.equals(b.getMember())) return false;
        if (!this.facility.equals(b.getFacility())) return false;
        return true;
    }

    public void show(){
        System.out.println(toString());
    }
    @Override
    public String toString(){
        return "Member name: " + member.toString()
                + "\nFacility Name: " + facility.getName()
                + "\n Start Date: " + start.format(DateTimeFormatter.ofPattern("d/MMM/yyyy HH:mm"))
                + " \nEnd Date: " + end.format(DateTimeFormatter.ofPattern("d/MMM/yyyy HH:mm"));
    }

}

class BadBookingException extends Exception{
    public BadBookingException(String message){
        super(message);
    }
    public BadBookingException(){
        this("Bad booking made");
    }
}
