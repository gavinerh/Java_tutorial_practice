package java1;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ClubApplication {
	public static void main(String[] args) {//
		Club c1 = new Club();
		c1.addFacility("Gym", "For exercising");
		c1.addFacility("Library", "For reading");
		c1.addFacility("Malls", "To relax and shop");
		c1.addFacility("Food court", "To enjoy good food");
		c1.addFacility("Bus stop", "To wait for bus in comfort");
		c1.addFacility("Taxi Stand", "To wait for taxi in comfort");

		c1.addMember("Tan", "Ah", "Gao");
		c1.addMember("Tan", "Ah", "Beng");
		c1.addMember("Stan", "Laurel", null);
		c1.addMember("Nicole", "Seah", "Jian");
		c1.addMember("Gavin", "Erh", "Choon");
		c1.addMember("Singapore", "Serangoon", "Kovan");
		c1.addMember("United States", "Washington", "San Francisco");

		Member tan = c1.getMember("Tan", "Ah", "Gao");
		Facility f = c1.getFacility("Gym");
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("d/MM/yyyy HH:mm");
		LocalDateTime start = LocalDateTime.parse("9/03/2020 08:00", dateFormat);
		LocalDateTime end = LocalDateTime.parse("9/03/2020 10:00", dateFormat);
		Booking b1 = new Booking(tan,f, start,end);
		BookingRegister br = new BookingRegister();
		br.addBooking(tan, f, start, end);
//		br.removeBooking(b1);
		br.showBookings(f, start, end);

	}
}
