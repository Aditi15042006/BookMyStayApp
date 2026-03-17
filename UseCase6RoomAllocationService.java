import java.util.*;

class Reservation {
    String guestName;
    String roomType;

    Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }
}

public class UseCase6RoomAllocationService {

    public static void main(String[] args) {

        // Queue from UC5
        Queue<Reservation> bookingQueue = new LinkedList<>();
        bookingQueue.add(new Reservation("Aditi", "Deluxe"));
        bookingQueue.add(new Reservation("Rahul", "Suite"));
        bookingQueue.add(new Reservation("Sneha", "Standard"));

        // Inventory (room availability)
        Map<String, Integer> inventory = new HashMap<>();
        inventory.put("Deluxe", 2);
        inventory.put("Suite", 1);
        inventory.put("Standard", 1);

        // Track allocated room IDs
        Set<String> allocatedRooms = new HashSet<>();

        // Map room type → allocated room IDs
        Map<String, Set<String>> roomMap = new HashMap<>();

        // Process queue
        while (!bookingQueue.isEmpty()) {

            Reservation req = bookingQueue.poll();
            String type = req.roomType;

            System.out.println("\nProcessing request for: " + req.guestName);

            // Check availability
            if (inventory.getOrDefault(type, 0) > 0) {

                // Generate unique room ID
                String roomId = type.substring(0, 2).toUpperCase() + (allocatedRooms.size() + 1);

                // Ensure uniqueness
                while (allocatedRooms.contains(roomId)) {
                    roomId = type.substring(0, 2).toUpperCase() + (allocatedRooms.size() + 1);
                }

                // Allocate room
                allocatedRooms.add(roomId);

                roomMap.putIfAbsent(type, new HashSet<>());
                roomMap.get(type).add(roomId);

                // Update inventory
                inventory.put(type, inventory.get(type) - 1);

                System.out.println("Booking Confirmed!");
                System.out.println("Guest: " + req.guestName);
                System.out.println("Room Type: " + type);
                System.out.println("Room ID: " + roomId);

            } else {
                System.out.println("Booking Failed! No rooms available for " + type);
            }
        }
    }
}