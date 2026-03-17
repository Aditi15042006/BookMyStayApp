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

class Service {
    String serviceName;
    int cost;

    Service(String serviceName, int cost) {
        this.serviceName = serviceName;
        this.cost = cost;
    }
}

public class UseCase7AddOnServiceSelection {

    public static void main(String[] args) {

        Reservation r1 = new Reservation("Aditi", "Deluxe", "R1");
        Reservation r2 = new Reservation("Rahul", "Suite", "R2");

        Map<String, List<Service>> serviceMap = new HashMap<>();

        List<Service> servicesR1 = new ArrayList<>();
        servicesR1.add(new Service("Breakfast", 500));
        servicesR1.add(new Service("Spa", 1000));
        serviceMap.put(r1.reservationId, servicesR1);

        List<Service> servicesR2 = new ArrayList<>();
        servicesR2.add(new Service("Airport Pickup", 800));
        serviceMap.put(r2.reservationId, servicesR2);

        for (String resId : serviceMap.keySet()) {

            System.out.println("\nServices for Reservation ID: " + resId);

            int totalCost = 0;

            for (Service s : serviceMap.get(resId)) {
                System.out.println("Service: " + s.serviceName + ", Cost: " + s.cost);
                totalCost += s.cost;
            }

            System.out.println("Total Add-On Cost: " + totalCost);
        }
    }
}