package food;

public class Main {
    public static void main(String[] args) {
        FoodOrder[] orders = new FoodOrder[6];

        orders[0] = new RegularOrder("ORD001", "Alice", 250, "Tasty Bites");
        orders[1] = new PremiumOrder("ORD002", "Bob", 500, "Tasty Bites");
        orders[2] = new RegularOrder("ORD003", "Charlie", 180, "Tasty Bites");
        orders[3] = new PremiumOrder("ORD004", "Diana", 750, "Tasty Bites");
        orders[4] = new RegularOrder("ORD005", "Eve", 320, "Tasty Bites");
        orders[5] = new PremiumOrder("ORD006", "Frank", 450, "Tasty Bites");

        double totalBill = 0;
        double totalDiscount = 0;
        double totalDeliveryCharge = 0;

        System.out.println("========== ORDER SUMMARY ==========");
        System.out.printf("%-10s %-12s %-10s %-10s %-15s %-10s%n", 
            "Order ID", "Customer", "Amount", "Discount", "Delivery Charge", "Final Amt");
        System.out.println("---------------------------------------------------------------");

        for (FoodOrder order : orders) {
            double originalAmount = order.amount;
            double discountedAmount = order.applyDiscount();
            double discount = originalAmount - discountedAmount;
            double deliveryCharge = order.calculateDeliveryCharge();
            double finalAmount = discountedAmount + deliveryCharge;

            totalBill += originalAmount;
            totalDiscount += discount;
            totalDeliveryCharge += deliveryCharge;

            System.out.printf("%-10s %-12s %-10.2f %-10.2f %-15.2f %-10.2f%n",
                order.orderId, order.customerName, originalAmount, discount, deliveryCharge, finalAmount);
        }

        System.out.println("---------------------------------------------------------------");
        System.out.println("========== FINAL SUMMARY ==========");
        System.out.printf("Total Bill Amount    : %.2f%n", totalBill);
        System.out.printf("Total Discount       : %.2f%n", totalDiscount);
        System.out.printf("Total Delivery Charge: %.2f%n", totalDeliveryCharge);
        System.out.printf("Final Payable Amount : %.2f%n", (totalBill - totalDiscount + totalDeliveryCharge));
        System.out.println("===================================");
    }
}