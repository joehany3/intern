public class Cheese extends Product implements ShippableProduct {
    private double weight;
    private boolean expired;

    public Cheese(String name, double price, int quantity, double weight, boolean expired) {
        super(name, price, quantity);
        this.weight = weight;
        this.expired = expired;
    }

    public boolean isExpired() {
        return expired;
    }

    public boolean isShippable() {
        return true;
    }

    public double getWeight() {
        return weight;
    }
}