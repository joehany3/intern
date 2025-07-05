import java.util.List;

public class ShippingService {
    public static void ship(List<ShippableProduct> items) {
        double totalWeight = 0;
        System.out.println("** Shipment notice **");
        for (ShippableProduct item : items) {
            System.out.printf("1x %s\t\t%.0fg%n", item.getName(), item.getWeight() * 1000);
            totalWeight += item.getWeight();
        }
        System.out.println("Total package weight " + totalWeight + "kg");
    }
}