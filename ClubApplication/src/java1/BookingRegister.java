package java1;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookingRegister {
    private Map<Facility, ArrayList<Booking>> bookings;
    public BookingRegister(){
        bookings = new HashMap<Facility, ArrayList<Booking>>();
    }

    public void addBooking(Member m, Facility f, LocalDateTime start, LocalDateTime end){
        Booking b = new Booking(m, f, start, end);
        ArrayList<Booking> storedBookings = bookings.get(f);
        if(storedBookings == null){
            ArrayList facilityBooking = new ArrayList<Booking>();
            facilityBooking.add(b);
            bookings.put(f, facilityBooking);
        }
        else{
            for(Booking book : storedBookings){
                if(book.overlaps(b)){
                    try{
                        throw new BadBookingException("Booking already exist");
                    }catch (BadBookingException e){
                        System.out.println(e.getMessage());
                    }
                }
                storedBookings.add(b);
            }
        }
    }

    public List<Booking> getBookings(Facility f, LocalDateTime start, LocalDateTime end){
        ArrayList<Booking> storedBookings = bookings.get(f);
        ArrayList<Booking> selectedBookings = new ArrayList<Booking>();
        for(Booking b : storedBookings){
            if(start.isBefore(b.getStartDateTime()) && end.isAfter(b.getEndDateTime()) ||
            start.equals(b.getStartDateTime()) && end.equals(b.getEndDateTime())){
                selectedBookings.add(b);
            }
        }
        return selectedBookings.size() == 0 ? null : selectedBookings;
    }

    public boolean removeBooking(Booking b){
        ArrayList<Booking> storedBooking = bookings.get(b.getFacility());
        for(Booking book : storedBooking){
            if(book.equals(b)){
                storedBooking.remove(b);
                return true;
            }
        }
        return false;
    }

    public void showBookings(Facility f, LocalDateTime start, LocalDateTime end){
        List<Booking> storedBookings = this.getBookings(f, start, end);
        if(storedBookings == null){
            System.out.println("No bookings found");
        } else{
            for(Booking b : storedBookings){
                b.show();
            }
        }
    }
}
