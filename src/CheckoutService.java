import java.util.*;

public class CheckoutService {
    private static final double SHIPPING_COST = 30.0;

    public static void checkout(Customer customer, Cart cart) {
        if (cart.isEmpty()) {
            System.out.println("Error: Cart is empty.");
            return;
        }

        double subtotal = 0;
        List<ShippableProduct> shippableItems = new ArrayList<>();

        for (Map.Entry<Product, Integer> entry : cart.getItems().entrySet()) {
            Product product = entry.getKey();
            int qty = entry.getValue();

            if (product.isExpired()) {
                System.out.println("Error: Product " + product.getName() + " is expired.");
                return;
            }

            if (qty > product.getQuantity()) {
                System.out.println("Error: Product " + product.getName() + " is out of stock.");
                return;
            }

            subtotal += product.getPrice() * qty;

            if (product.isShippable() && product instanceof ShippableProduct) {
                for (int i = 0; i < qty; i++) {
                    shippableItems.add((ShippableProduct) product);
                }
            }
        }

        double total = subtotal + SHIPPING_COST;
        if (customer.getBalance() < total) {
            System.out.println("Error: Insufficient balance.");
            return;
        }

        if (!shippableItems.isEmpty()) {
            ShippingService.ship(shippableItems);
        }

        for (Map.Entry<Product, Integer> entry : cart.getItems().entrySet()) {
            entry.getKey().reduceQuantity(entry.getValue());
        }

        customer.deductBalance(total);

        System.out.println("** Checkout receipt **");
        for (Map.Entry<Product, Integer> entry : cart.getItems().entrySet()) {
            System.out.printf("%dx %-12s %.0f%n", entry.getValue(), entry.getKey().getName(),
                    entry.getKey().getPrice() * entry.getValue());
        }
        System.out.println("----------------------");
        System.out.printf("Subtotal         %.0f%n", subtotal);
        System.out.printf("Shipping         %.0f%n", SHIPPING_COST);
        System.out.printf("Amount           %.0f%n", total);
        System.out.printf("Remaining Balance %.0f%n", customer.getBalance());

        cart.clear();
    }
}