
// Online Java Compiler
// Use this editor to write, compile and run your Java code online

class Main {
    public static void main(String[] args){
        q1();
        q2();
    }
    public static void q1() {
        try {
            int a = 10;
            int b = 0;
            int result = a / b;
            System.out.println(result);
        }
        catch (ArithmeticException e) {
            System.out.println("Exception Occured: "+ e.getMessage());
        }

        try {
            int[] numbers = {10, 20, 30};
            System.out.println(numbers[3]);
        }
        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Exception Occured: "+ e.getMessage());
        }
        catch (Exception e) {
            System.out.println("Some Error occured. Error: " + e.getMessage());
        }
    }
    

    public static void q2() {
        try {
            int a = 10;
            int b = 0;
            int result = a / b;
            System.out.println(result);
        }
        catch (ArithmeticException e) {
            System.out.println("Exception Occured: "+ e.getMessage());
        }

        try {
            int[] numbers = {10, 20, 30};
            System.out.println(numbers[3]);
        }
        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Exception Occured: "+ e.getMessage());
        }
        catch (Exception e) {
            System.out.println("Some Error occured. Error: " + e.getMessage());
        }
        

}