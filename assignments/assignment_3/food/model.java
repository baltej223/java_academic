package food;

abstract class FoodOrder {
   static int total_orders = 0; 
   String orderId;
   String customerName;
   double amount;
   boolean discount_already_applied;

   static String restaurantName;
   abstract double calculateDeliveryCharge();
   abstract double applyDiscount();
   static int total_orders(){
     return total_orders;
   }
 }

interface discountable{
  double applyDiscount();
}

class RegularOrder extends FoodOrder{
  String orderId;
  String customerName;
  double amount;
  static String restaurantName;
 
boolean discount_already_applied = false;
  RegularOrder(String orderId, String customerName, int amount, String restaurantName) {
    this.orderId=orderId;
    this.customerName = customerName;
    this.amount = amount;
    this.restaurantName = restaurantName;
    this.total_orders++;
  }
  
  double calculateDeliveryCharge() {
    return 80;
  }

  double applyDiscount() {
    this.amount = this.amount * ((double) 0.9);
    return this.amount;
  }
}


class PremiumOrder extends FoodOrder {
  String orderId;
  String customerName;
  double amount;
  static String restaurantName;
  boolean discount_already_applied = false;

  PremiumOrder(String orderId, String customerName, int amount, String restaurantName) {
    this.orderId=orderId;
    this.customerName = customerName;
    this.amount = amount;
    this.restaurantName = restaurantName;
    this.total_orders++;
  }
  
  double calculateDeliveryCharge() {
    return 50;
  }


  double applyDiscount() {
    if (!discount_already_applied)
    this.amount = this.amount * ((double) 0.85);
    
    return this.amount;
  }
}
