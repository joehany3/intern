import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class ShippingService {
    public static void ship(List<ShippableProduct> items) {
        Map<String, Integer> counts = new HashMap<>();
        Map<String, Double> weights = new HashMap<>();
        double totalWeight = 0;

        for (ShippableProduct item : items) {
            String name = item.getName();
            double weight = item.getWeight();

            counts.put(name, counts.getOrDefault(name, 0) + 1);
            weights.put(name, weights.getOrDefault(name, 0.0) + weight);
            totalWeight += weight;
        }

        System.out.println("** Shipment notice **");
        for (String name : counts.keySet()) {
            int count = counts.get(name);
            double weight = weights.get(name);
            System.out.printf("%dx %-12s %.0fg%n", count, name, weight * 1000); // weight in grams
        }

        System.out.printf("Total package weight %.1fkg%n", totalWeight);
    }
}
