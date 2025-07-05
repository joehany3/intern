public class Main {
    public static void main(String[] args) {
        Cheese cheese = new Cheese("Cheese", 10, 5, 0.2,false);
        TV tv = new TV("TV", 300, 3, 10);
        ScratchCard scratchCard = new ScratchCard("ScratchCard", 50, 2);

        Customer customer = new Customer("Youssef", 1000);


        Cart cart = new Cart();
        cart.add(cheese, 2);
        cart.add(tv, 1);
        cart.add(scratchCard, 2);


        CheckoutService.checkout(customer, cart);
    }
}