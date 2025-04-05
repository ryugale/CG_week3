import java.util.ArrayList;
import java.util.List;

abstract class Product {
   private int productId, price;
   private String name;

   Product(int productId,int price, String name) {
       this.productId = productId;
       this.name = name;
   }

   abstract void calcualteDiscount();
   public String getName() {
       return name;
   }

   public void setPrice(int price) {
       this.price = price;
   }

   public int getPrice() {
       return price;
   }

   public int getProductId() {
       return productId;
   }
}

interface Taxable {
   void calculateTax();


   void getTaxDetails();
}

class Electronics extends Product implements Taxable {
   double tax,discount;
   Electronics(int productId, int price, String name) {
       super(productId,price, name);
   }
   public double getTax() {
       return tax;
   }

   @Override
   void calcualteDiscount() {
       discount = (int) (getPrice() * 0.15);
       System.out.println("15% discount applied on Electronics");
   }

   @Override
   public void calculateTax() {
       tax = getPrice() * 0.10;
   }

   @Override
   public void getTaxDetails() {
       System.out.println("10% tax on Electronic products");
   }


}

class Clothing extends Product implements Taxable {
   double tax,discount;
   Clothing(int productId, int price, String name) {
       super(productId, price,name);
   }
   public double getTax() {
       return tax;
   }

   @Override
   void calcualteDiscount() {
       discount = (int) (getPrice() * 0.10);
       System.out.println("10% discount applied on Clothing");
   }

   @Override
   public void calculateTax() {
       tax = getPrice() * 0.18;
   }

   @Override
   public void getTaxDetails() {
       System.out.println("18% tax on Clothing products");
   }


}
class Groceries extends Product implements Taxable {
   double tax,discount;
   Groceries(int productId, int price, String name) {
       super(productId,price, name);
   }

   public double getTax() {
       return tax;
   }

   @Override
   void calcualteDiscount() {
       discount = (int) (getPrice() * 0.05);
       System.out.println("5% discount applied on Groceries");
   }

   @Override
   public void calculateTax() {
       tax = getPrice() * 0.18;
   }

   @Override
   public void getTaxDetails() {
       System.out.println("18% tax on Groceries products");
   }
}

public class ECommerce {
   static double finalPrice(Product p){
       if (p instanceof Taxable) {
           Taxable taxableProduct = (Taxable) p;
           taxableProduct.calculateTax();
           p.calcualteDiscount();
           return p.getPrice() + ((Electronics) p).getTax() - ((Electronics) p).discount;
       }
       return p.getPrice();
   }

   public static void main(String[] args) {
       List<Product> products = new ArrayList<>();
       Product laptop = new Electronics(1, 50000, "Laptop");
       Product tShirt = new Clothing(1, 800, "Tshirt");
       Product milk = new Groceries(3, 20, "Milk");

       products.add(laptop);
       products.add(tShirt);
       products.add(milk);
       finalPrice(laptop);
       for (Product product : products) {
           System.out.println(finalPrice(product));
       }
   }
}