import java.util.*;

class Reservation {
    String reservationId;
    String roomType;

    Reservation(String reservationId, String roomType) {
        this.reservationId = reservationId;
        this.roomType = roomType;
    }
}

public class UseCase10BookingCancellation {

    public static void main(String[] args) {

        // Inventory
        Map<String, Integer> inventory = new HashMap<>();
        inventory.put("Deluxe", 1);
        inventory.put("Suite", 1);

        // Booking history (confirmed bookings)
        Map<String, Reservation> bookings = new HashMap<>();
        bookings.put("R1", new Reservation("R1", "Deluxe"));
        bookings.put("R2", new Reservation("R2", "Suite"));

        // Stack for rollback (LIFO)
        Stack<String> rollbackStack = new Stack<>();

        // Simulate cancellation requests
        String[] cancelRequests = {"R2", "R3"}; // R3 is invalid

        for (String resId : cancelRequests) {

            System.out.println("\nProcessing cancellation for: " + resId);

            // Validate reservation
            if (!bookings.containsKey(resId)) {
                System.out.println("Cancellation Failed: Reservation not found");
                continue;
            }

            Reservation r = bookings.get(resId);

            // Push to rollback stack
            rollbackStack.push(resId);

            // Restore inventory
            inventory.put(r.roomType, inventory.get(r.roomType) + 1);

            // Remove booking
            bookings.remove(resId);

            System.out.println("Cancellation Successful for " + resId);
        }

        // Show rollback history
        System.out.println("\nRollback Stack (recent cancellations):");
        while (!rollbackStack.isEmpty()) {
            System.out.println(rollbackStack.pop());
        }
    }
}