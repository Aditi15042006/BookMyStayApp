import java.util.*;

class Reservation {
    String guestName;
    String roomType;

    Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String toString() {
        return "Guest: " + guestName + ", Room Type: " + roomType;
    }
}

public class UseCase5BookingRequestQueue {

    public static void main(String[] args) {

        // Create Queue (FIFO)
        Queue<Reservation> bookingQueue = new LinkedList<>();

        // Add booking requests
        bookingQueue.add(new Reservation("Aditi", "Deluxe"));
        bookingQueue.add(new Reservation("Rahul", "Suite"));
        bookingQueue.add(new Reservation("Sneha", "Standard"));

        // Display all requests
        System.out.println("Booking Requests in Queue (FIFO Order):");
        for (Reservation r : bookingQueue) {
            System.out.println(r);
        }

        // Show next request
        System.out.println("\nNext request to process:");
        System.out.println(bookingQueue.peek());
    }
}