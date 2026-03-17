import java.util.*;

// Custom Exception
class InvalidBookingException extends Exception {
    public InvalidBookingException(String message) {
        super(message);
    }
}

class Reservation {
    String guestName;
    String roomType;

    Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }
}

public class UseCase9ErrorHandlingValidation {

    // Validation method
    public static void validateBooking(Reservation r, Map<String, Integer> inventory)
            throws InvalidBookingException {

        // Check valid room type
        if (!inventory.containsKey(r.roomType)) {
            throw new InvalidBookingException("Invalid Room Type: " + r.roomType);
        }

        // Check availability
        if (inventory.get(r.roomType) <= 0) {
            throw new InvalidBookingException("No rooms available for: " + r.roomType);
        }
    }

    public static void main(String[] args) {

        // Inventory
        Map<String, Integer> inventory = new HashMap<>();
        inventory.put("Deluxe", 1);
        inventory.put("Suite", 0);

        // Test reservations
        Reservation r1 = new Reservation("Aditi", "Deluxe");   // valid
        Reservation r2 = new Reservation("Rahul", "Suite");    // no rooms
        Reservation r3 = new Reservation("Sneha", "Premium");  // invalid type

        List<Reservation> requests = Arrays.asList(r1, r2, r3);

        for (Reservation r : requests) {
            try {
                System.out.println("\nProcessing booking for: " + r.guestName);

                // Validate
                validateBooking(r, inventory);

                // If valid → allocate
                inventory.put(r.roomType, inventory.get(r.roomType) - 1);

                System.out.println("Booking Successful for " + r.guestName);

            } catch (InvalidBookingException e) {
                // Handle error gracefully
                System.out.println("Booking Failed: " + e.getMessage());
            }
        }
    }
}