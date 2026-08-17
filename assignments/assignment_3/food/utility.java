package food;

class OrderUtility{
    boolean validate_order(FoodOrder f) {
        if (f instanceof RegularOrder) {
            return f.calculateDeliveryCharge() == 80;
        } else if (f instanceof PremiumOrder) {
            return f.calculateDeliveryCharge() == 50;
        }
        return false;
    }
    
    void generate_summary(FoodOrder f) {
        if (!validate_order(f)) {
            System.out.println("The Object of instance food order is wrong!");
            return;
        }
        System.out.println("BAAH");
    }
}