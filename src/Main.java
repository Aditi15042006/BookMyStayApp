import java.util.HashMap;
import java.util.Map;

/**
 * Book My Stay App
 * Combined Use Cases 3 and 4 in one file for single public class
 * @author Aditi
 * @version 4.0
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("===== Book My Stay - Hotel Booking System =====");

        System.out.println("\n--- Running Use Case 3: Inventory Setup ---");
        useCase3InventorySetup();

        System.out.println("\n--- Running Use Case 4: Room Search ---");
        useCase4RoomSearch();
    }

    // Use Case 3 logic
    public static void useCase3InventorySetup() {
        RoomInventory inventory = new RoomInventory();

        inventory.displayInventory();

        System.out.println("\nUpdating availability for Single Room...");
        inventory.updateAvailability("Single Room", 4);

        inventory.displayInventory();
    }

    // Use Case 4 logic
    public static void useCase4RoomSearch() {
        Room[] rooms = { new SingleRoom(), new DoubleRoom(), new SuiteRoom() };

        RoomInventory inventory = new RoomInventory();

        System.out.println("\n--- Available Rooms ---");

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

    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    public void updateAvailability(String roomType, int newCount) {
        inventory.put(roomType, newCount);
    }

    public void displayInventory() {
        System.out.println("\n--- Current Room Inventory ---");
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }
}