import java.util.*;
interface Discountable {
    void applyDiscount(double percentage);
    String getDiscountDetails();
}

abstract class FoodItem implements Discountable {
    private String itemName;
    protected double price;
    protected int quantity;
    protected double discountAmount = 0;

    public FoodItem(String itemName, double price, int quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }

    public abstract double calculateTotalPrice();

    public String getItemDetails() {
        return itemName + " - $" + price + " x " + quantity;
    }

    public String getItemName() {
        return itemName;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getBasePrice() {
        return price;
    }

    @Override
    public void applyDiscount(double percentage) {
        double totalBeforeDiscount = calculateTotalPrice();
        discountAmount = totalBeforeDiscount * (percentage / 100);
    }

    @Override
    public String getDiscountDetails() {
        return "Discount Applied: $" + String.format("%.2f", discountAmount);
    }

    public double getDiscountAmount() {
        return discountAmount;
    }
}
class VegItem extends FoodItem {

    public VegItem(String itemName, double price, int quantity) {
        super(itemName, price, quantity);
    }

    @Override
    public double calculateTotalPrice() {
        return price * quantity - discountAmount;
    }
}

class NonVegItem extends FoodItem {

    public NonVegItem(String itemName, double price, int quantity) {
        super(itemName, price, quantity);
    }

    @Override
    public double calculateTotalPrice() {
        double extraCharge = 2.0 * quantity; 
        return (price * quantity + extraCharge) - discountAmount;
    }
}

public class FoodDeliverySystem {
    public static void main(String[] args) {
        List<FoodItem> order = new ArrayList<>();

        order.add(new VegItem("Paneer Butter Masala", 10.0, 2));
        order.add(new NonVegItem("Chicken Biryani", 12.0, 3));

        processOrder(order);
    }

    public static void processOrder(List<FoodItem> items) {
        System.out.println("Order Summary:");
        double total = 0;
        for (FoodItem item : items) {
            System.out.println(item.getItemDetails());

            item.applyDiscount(10);
            System.out.println(item.getDiscountDetails());

            double itemTotal = item.calculateTotalPrice();
            System.out.println("Total after discount: $" + String.format("%.2f", itemTotal));

            total += itemTotal;
            System.out.println("-------------------------");
        }
        System.out.println("Final Payable Amount: $" + String.format("%.2f", total));
    }
}
