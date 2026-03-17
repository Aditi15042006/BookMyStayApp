import java.io.*;
import java.util.*;

// Reservation class (Serializable)
class Reservation implements Serializable {
    String reservationId;
    String roomType;

    Reservation(String reservationId, String roomType) {
        this.reservationId = reservationId;
        this.roomType = roomType;
    }
}

public class UseCase12DataPersistenceRecovery {

    static final String FILE_NAME = "data.ser";

    public static void main(String[] args) {

        // Data structures
        Map<String, Integer> inventory = new HashMap<>();
        List<Reservation> bookings = new ArrayList<>();

        // Try to load previous data
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {

            inventory = (Map<String, Integer>) ois.readObject();
            bookings = (List<Reservation>) ois.readObject();

            System.out.println("Data loaded successfully!");

        } catch (Exception e) {
            System.out.println("No previous data found. Starting fresh.");

            // Initialize default data
            inventory.put("Deluxe", 2);
            inventory.put("Suite", 1);

            bookings.add(new Reservation("R1", "Deluxe"));
            bookings.add(new Reservation("R2", "Suite"));
        }

        // Display current state
        System.out.println("\nInventory: " + inventory);
        System.out.println("Bookings:");
        for (Reservation r : bookings) {
            System.out.println(r.reservationId + " - " + r.roomType);
        }

        // Save data before exit
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {

            oos.writeObject(inventory);
            oos.writeObject(bookings);

            System.out.println("\nData saved successfully!");

        } catch (IOException e) {
            System.out.println("Error saving data!");
        }
    }
}