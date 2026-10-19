public class q1 {

    public static <T extends Number> double calculateSum(T[] array) {
        double sum = 0.0;
        for (T element : array) {
            sum += element.doubleValue();
        }
        return sum;
    }

    public static void main(String[] args) {
        Integer[] intArray = {10, 20, 30, 40};
        Float[] floatArray = {1.5f, 2.5f, 3.5f};
        Double[] doubleArray = {10.25, 20.50, 30.75};

        System.out.println("Sum of Integer array: " + calculateSum(intArray));
        System.out.println("Sum of Float array: " + calculateSum(floatArray));
        System.out.println("Sum of Double array: " + calculateSum(doubleArray));
    }
}