import java.util.*;

// Reservation class
class Reservation {
    String guestName;
    String roomType;

    Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }
}

public class UseCase11ConcurrentBookingSimulation {

    // Shared resources
    static Queue<Reservation> bookingQueue = new LinkedList<>();
    static Map<String, Integer> inventory = new HashMap<>();

    // Synchronized booking processor
    public static synchronized void processBooking() {

        if (bookingQueue.isEmpty()) return;

        Reservation r = bookingQueue.poll();

        System.out.println(Thread.currentThread().getName() +
                " processing " + r.guestName);

        if (inventory.getOrDefault(r.roomType, 0) > 0) {

            // Critical section
            inventory.put(r.roomType, inventory.get(r.roomType) - 1);

            System.out.println("Booking Confirmed for " + r.guestName +
                    " [" + r.roomType + "]");

        } else {
            System.out.println("Booking Failed for " + r.guestName +
                    " (No rooms)");
        }
    }

    public static void main(String[] args) {

        // Initialize inventory
        inventory.put("Deluxe", 1);
        inventory.put("Suite", 1);

        // Add booking requests
        bookingQueue.add(new Reservation("Aditi", "Deluxe"));
        bookingQueue.add(new Reservation("Rahul", "Deluxe"));
        bookingQueue.add(new Reservation("Sneha", "Suite"));

        // Create threads (simulate multiple users)
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 2; i++) {
                processBooking();
            }
        }, "Thread-1");

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 2; i++) {
                processBooking();
            }
        }, "Thread-2");

        // Start threads
        t1.start();
        t2.start();
    }
}