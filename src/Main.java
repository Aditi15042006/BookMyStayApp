import java.util.HashMap;
import java.util.Map;

/**
 * Book My Stay App
 * Use Case 4: Room Search & Availability Check
 *
 * Demonstrates read-only access to inventory and safe room search
 * without modifying system state.
 *
 * @author Aditi
 * @version 4.0
 */

// Room classes from UC2
abstract class Room {

    protected String roomType;
    protected int beds;
    protected double price;

    public Room(String roomType, int beds, double price) {
        this.roomType = roomType;
        this.beds = beds;
        this.price = price;
    }

    public void displayRoomDetails() {
        System.out.println("Room Type: " + roomType);
        System.out.println("Beds: " + beds);
        System.out.println("Price per night: $" + price);
    }

    public String getRoomType() {
        return roomType;
    }
}

class SingleRoom extends Room {
    public SingleRoom() {
        super("Single Room", 1, 100);
    }
}

class DoubleRoom extends Room {
    public DoubleRoom() {
        super("Double Room", 2, 180);
    }
}

class SuiteRoom extends Room {
    public SuiteRoom() {
        super("Suite Room", 3, 350);
    }
}

// Centralized inventory from UC3
class RoomInventory {

    private HashMap<String, Integer> inventory;

    public RoomInventory() {
        inventory = new HashMap<>();
        inventory.put("Single Room", 5);
        inventory.put("Double Room", 3);
        inventory.put("Suite Room", 2);
    }

    // Read-only access
    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    public void displayInventory() {
        System.out.println("\n--- Current Room Inventory ---");
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }
}

/**
 * Application Entry Point for UC4
 */
public class UseCase4RoomSearch {

    public static void main(String[] args) {

        System.out.println("===== Book My Stay - Room Search v4.0 =====");

        // Initialize room objects
        Room[] rooms = { new SingleRoom(), new DoubleRoom(), new SuiteRoom() };

        // Initialize inventory
        RoomInventory inventory = new RoomInventory();

        System.out.println("\n--- Available Rooms ---");

        // Search & display only rooms with availability > 0
        for (Room room : rooms) {
            int available = inventory.getAvailability(room.getRoomType());
            if (available > 0) {
                room.displayRoomDetails();
                System.out.println("Available Rooms: " + available);
                System.out.println();
            }
        }

        System.out.println("Search complete. Inventory state remains unchanged.");
    }
}