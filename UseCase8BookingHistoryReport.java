import java.util.*;

class Reservation {
    String guestName;
    String roomType;
    String reservationId;

    Reservation(String guestName, String roomType, String reservationId) {
        this.guestName = guestName;
        this.roomType = roomType;
        this.reservationId = reservationId;
    }
}

public class UseCase8BookingHistoryReport {

    public static void main(String[] args) {

        // Booking history (List maintains order)
        List<Reservation> bookingHistory = new ArrayList<>();

        // Simulate confirmed bookings
        bookingHistory.add(new Reservation("Aditi", "Deluxe", "R1"));
        bookingHistory.add(new Reservation("Rahul", "Suite", "R2"));
        bookingHistory.add(new Reservation("Sneha", "Standard", "R3"));

        // Display booking history
        System.out.println("Booking History:");

        for (Reservation r : bookingHistory) {
            System.out.println("Reservation ID: " + r.reservationId +
                               ", Guest: " + r.guestName +
                               ", Room Type: " + r.roomType);
        }

        // Generate simple report
        System.out.println("\nTotal Bookings: " + bookingHistory.size());
    }
}