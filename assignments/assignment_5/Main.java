import java.util.*;

public class Main {
    static class InvalidMarksException extends Exception {
        private int marks;
        public InvalidMarksException(int marks) {
            super("Invalid marks: " + marks + ". Marks must be between 0 and 100.");
            this.marks = marks;
        }
        public int getMarks() { return marks; }
    }

    static class InsufficientBalanceException extends Exception {
        private double balance;
        private double withdrawAmount;
        public InsufficientBalanceException(double balance, double withdrawAmount) {
            super("Cannot withdraw " + withdrawAmount + ". Current balance: " + balance);
            this.balance = balance;
            this.withdrawAmount = withdrawAmount;
        }
        public double getBalance() { return balance; }
        public double getWithdrawAmount() { return withdrawAmount; }
    }

    static class InvalidAmountException extends Exception {
        private double amount;
        public InvalidAmountException(double amount) {
            super("Invalid amount: " + amount + ". Amount must be positive.");
            this.amount = amount;
        }
        public double getAmount() { return amount; }
    }

    static class AccountNotFoundException extends Exception {
        private String accountNumber;
        public AccountNotFoundException(String accountNumber) {
            super("Account not found: " + accountNumber);
            this.accountNumber = accountNumber;
        }
        public String getAccountNumber() { return accountNumber; }
    }

    static class InvalidUsernameException extends Exception {
        private String username;
        public InvalidUsernameException(String username) {
            super("Invalid username: " + username);
            this.username = username;
        }
        public String getUsername() { return username; }
    }

    static class InvalidPasswordException extends Exception {
        private String password;
        public InvalidPasswordException(String password) {
            super("Invalid password provided.");
            this.password = password;
        }
        public String getPassword() { return password; }
    }

    static class AccountLockedException extends Exception {
        private String username;
        public AccountLockedException(String username) {
            super("Account locked: " + username + ". Too many failed login attempts.");
            this.username = username;
        }
        public String getUsername() { return username; }
    }

    static class ApplicationException extends Exception {
        public ApplicationException(String message) { super(message); }
    }

    static class ProductException extends ApplicationException {
        public ProductException(String message) { super(message); }
    }

    static class ProductNotFoundException extends ProductException {
        private String productId;
        public ProductNotFoundException(String productId) {
            super("Product not found: " + productId);
            this.productId = productId;
        }
        public String getProductId() { return productId; }
    }

    static class OutOfStockException extends ProductException {
        private String productId;
        public OutOfStockException(String productId) {
            super("Product out of stock: " + productId);
            this.productId = productId;
        }
        public String getProductId() { return productId; }
    }

    static class PaymentException extends ApplicationException {
        public PaymentException(String message) { super(message); }
    }

    static class InvalidPaymentException extends PaymentException {
        public InvalidPaymentException(String message) { super(message); }
    }

    static class InsufficientFundsException extends PaymentException {
        private double amount;
        private double balance;
        public InsufficientFundsException(double amount, double balance) {
            super("Insufficient funds for payment of " + amount + ". Available: " + balance);
            this.amount = amount;
            this.balance = balance;
        }
        public double getAmount() { return amount; }
        public double getBalance() { return balance; }
    }

    static class OrderException extends ApplicationException {
        public OrderException(String message) { super(message); }
    }

    static class EmptyCartException extends OrderException {
        public EmptyCartException() { super("Cannot place order. Cart is empty."); }
    }

    // ===================== Experiment 1: Exception Hierarchy =====================
    public static void q1() {
        System.out.println("--- 1. ArithmeticException ---");
        try {
            int result = 10 / 0;
        } catch (ArithmeticException e) {
            System.out.println("ArithmeticException: " + e.getMessage());
        }

        System.out.println("\n--- 2. NullPointerException ---");
        try {
            String str = null;
            str.length();
        } catch (NullPointerException e) {
            System.out.println("NullPointerException: " + e.getMessage());
        }

        System.out.println("\n--- 3. ArrayIndexOutOfBoundsException ---");
        try {
            int[] arr = {1, 2, 3};
            System.out.println(arr[5]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("ArrayIndexOutOfBoundsException: " + e.getMessage());
        }

        System.out.println("\n--- 4. NumberFormatException ---");
        try {
            int num = Integer.parseInt("abc");
        } catch (NumberFormatException e) {
            System.out.println("NumberFormatException: " + e.getMessage());
        }

        System.out.println("\n--- 5. Exception Hierarchy ---");
        System.out.println("ArithmeticException extends RuntimeException");
        System.out.println("  -> extends Exception");
        System.out.println("  -> extends Throwable");
        System.out.println("NullPointerException extends RuntimeException");
        System.out.println("ArrayIndexOutOfBoundsException extends RuntimeException");
        System.out.println("NumberFormatException extends RuntimeException");
    }

    // ===================== Experiment 2: Nested Try-Catch =====================
    public static void q2() {
        System.out.println("--- Nested Try-Catch Blocks ---");
        try {
            int[] arr = new int[5];
            try {
                arr[10] = 50;
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Inner catch: " + e.getClass().getSimpleName() + " - " + e.getMessage());
            }
            int result = 100 / 0;
        } catch (ArithmeticException e) {
            System.out.println("Outer catch: " + e.getClass().getSimpleName() + " - " + e.getMessage());
        } finally {
            System.out.println("Finally block executed.");
        }
    }

    // ===================== Experiment 3: Calculator =====================
    public static void q3() {
        Scanner s = new Scanner(System.in);
        System.out.println("--- Calculator ---");
        try {
            System.out.print("Enter first number: ");
            double a = Double.parseDouble(s.next());
            System.out.print("Enter second number: ");
            double b = Double.parseDouble(s.next());
            System.out.print("Enter operator (+, -, *, /): ");
            String op = s.next();

            switch (op) {
                case "+":
                    System.out.println("Result: " + (a + b));
                    break;
                case "-":
                    System.out.println("Result: " + (a - b));
                    break;
                case "*":
                    System.out.println("Result: " + (a * b));
                    break;
                case "/":
                    try {
                        System.out.println("Result: " + (a / b));
                    } catch (ArithmeticException e) {
                        System.out.println("Error: Division by zero is not allowed.");
                    }
                    break;
                default:
                    throw new IllegalArgumentException("Invalid operator: " + op);
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid numeric input. Please enter valid numbers.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
        s.close();
    }

    // ===================== Experiment 4: Student Result Processing =====================
    public static void q4() throws InvalidMarksException {
        Scanner s = new Scanner(System.in);
        System.out.println("--- Student Result Processing ---");
        System.out.print("Enter number of subjects: ");
        int n = s.nextInt();
        int[] marks = new int[n];
        int total = 0;

        for (int i = 0; i < n; i++) {
            System.out.print("Enter marks for subject " + (i + 1) + ": ");
            marks[i] = s.nextInt();
            if (marks[i] < 0 || marks[i] > 100) {
                throw new InvalidMarksException(marks[i]);
            }
            total += marks[i];
        }

        double percentage = (double) total / n;
        String grade;
        if (percentage >= 90) grade = "A+";
        else if (percentage >= 80) grade = "A";
        else if (percentage >= 70) grade = "B";
        else if (percentage >= 60) grade = "C";
        else if (percentage >= 50) grade = "D";
        else grade = "F";

        System.out.println("Total: " + total);
        System.out.printf("Percentage: %.2f%%%n", percentage);
        System.out.println("Grade: " + grade);
        s.close();
    }

    // ===================== Experiment 5: Banking Application =====================
    public static void q5() {
        System.out.println("--- Banking Application ---");
        try {
            BankAccount acc = new BankAccount("1234567890", 5000.0);
            acc.deposit(1000.0);
            acc.withdraw(2000.0);
            acc.withdraw(5000.0); // This should throw InsufficientBalanceException
        } catch (InsufficientBalanceException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (InvalidAmountException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (AccountNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    static class BankAccount {
        private String accountNumber;
        private double balance;

        public BankAccount(String accountNumber, double initialBalance) throws AccountNotFoundException {
            if (accountNumber == null || accountNumber.isEmpty()) {
                throw new AccountNotFoundException(accountNumber);
            }
            this.accountNumber = accountNumber;
            this.balance = initialBalance;
        }

        public void deposit(double amount) throws InvalidAmountException {
            if (amount <= 0) {
                throw new InvalidAmountException(amount);
            }
            balance += amount;
            System.out.println("Deposited: " + amount + ". New balance: " + balance);
        }

        public void withdraw(double amount) throws InsufficientBalanceException, InvalidAmountException {
            if (amount <= 0) {
                throw new InvalidAmountException(amount);
            }
            if (amount > balance) {
                throw new InsufficientBalanceException(balance, amount);
            }
            balance -= amount;
            System.out.println("Withdrew: " + amount + ". New balance: " + balance);
        }

        public double getBalance() { return balance; }
    }

    // ===================== Experiment 6: Login System =====================
    public static void q6() {
        System.out.println("--- Login System ---");
        Scanner s = new Scanner(System.in);
        String validUsername = "admin";
        String validPassword = "password123";
        int maxAttempts = 3;
        int attempts = 0;
        boolean loggedIn = false;

        while (attempts < maxAttempts && !loggedIn) {
            System.out.print("Enter username: ");
            String username = s.next();
            System.out.print("Enter password: ");
            String password = s.next();

            try {
                if (!username.equals(validUsername)) {
                    throw new InvalidUsernameException(username);
                }
                if (!password.equals(validPassword)) {
                    throw new InvalidPasswordException(password);
                }
                loggedIn = true;
                System.out.println("Login successful!");
            } catch (InvalidUsernameException e) {
                System.out.println("Error: " + e.getMessage());
                attempts++;
            } catch (InvalidPasswordException e) {
                System.out.println("Error: " + e.getMessage());
                attempts++;
            }
        }

        if (!loggedIn && attempts >= maxAttempts) {
            try {
                throw new AccountLockedException(validUsername);
            } catch (AccountLockedException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        s.close();
    }

    // ===================== Experiment 7: Shopping Cart =====================
    public static void q7() {
        System.out.println("--- Shopping Cart Application ---");
        Scanner s = new Scanner(System.in);
        List<Product> cart = new ArrayList<>();

        while (true) {
            System.out.println("\n1. Add Product");
            System.out.println("2. Remove Product");
            System.out.println("3. Search Product");
            System.out.println("4. Checkout");
            System.out.println("5. Exit");
            System.out.print("Choose option: ");
            int choice = s.nextInt();

            try {
                switch (choice) {
                    case 1:
                        System.out.print("Enter product ID: ");
                        String addId = s.next();
                        System.out.print("Enter quantity: ");
                        int qty = s.nextInt();
                        cart.add(new Product(addId, "Product", qty));
                        System.out.println("Added to cart.");
                        break;
                    case 2:
                        System.out.print("Enter product ID to remove: ");
                        String remId = s.next();
                        cart.removeIf(p -> p.productId.equals(remId));
                        System.out.println("Removed from cart.");
                        break;
                    case 3:
                        System.out.print("Enter product ID to search: ");
                        String searchId = s.next();
                        boolean found = false;
                        for (Product p : cart) {
                            if (p.productId.equals(searchId)) {
                                System.out.println("Found: " + p.name + " (Qty: " + p.quantity + ")");
                                found = true;
                            }
                        }
                        if (!found) {
                            throw new ProductNotFoundException(searchId);
                        }
                        break;
                    case 4:
                        checkout(cart, s);
                        cart.clear();
                        break;
                    case 5:
                        System.out.println("Exiting...");
                        s.close();
                        return;
                    default:
                        System.out.println("Invalid option.");
                }
            } catch (ProductException e) {
                System.out.println("Product Error: " + e.getMessage());
            } catch (PaymentException e) {
                System.out.println("Payment Error: " + e.getMessage());
            } catch (OrderException e) {
                System.out.println("Order Error: " + e.getMessage());
            }
        }
    }

    static void checkout(List<Product> cart, Scanner s) throws EmptyCartException, InsufficientFundsException {
        if (cart.isEmpty()) {
            throw new EmptyCartException();
        }
        double total = 0;
        for (Product p : cart) {
            total += p.price * p.quantity;
        }
        System.out.println("Total amount: " + total);
        System.out.print("Enter payment amount: ");
        double payment = s.nextDouble();
        if (payment < total) {
            throw new InsufficientFundsException(total, payment);
        }
        System.out.println("Payment successful! Order placed.");
    }

    static class Product {
        String productId;
        String name;
        int quantity;
        double price;

        Product(String productId, String name, int quantity) {
            this.productId = productId;
            this.name = name;
            this.quantity = quantity;
            this.price = 100.0;
        }
    }

    // ===================== Main Menu =====================
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("=== Assignment 5: Exception & Debugging ===");
        System.out.println("1. Common Exception Hierarchy");
        System.out.println("2. Nested Try-Catch");
        System.out.println("3. Calculator");
        System.out.println("4. Student Result Processing");
        System.out.println("5. Banking Application");
        System.out.println("6. Login System");
        System.out.println("7. Shopping Cart");
        System.out.print("Select experiment (1-7): ");
        int q = s.nextInt();

        try {
            switch (q) {
                case 1:
                    q1();
                    break;
                case 2:
                    q2();
                    break;
                case 3:
                    q3();
                    break;
                case 4:
                    try {
                        q4();
                    } catch (InvalidMarksException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case 5:
                    q5();
                    break;
                case 6:
                    q6();
                    break;
                case 7:
                    q7();
                    break;
                default:
                    System.out.println("Invalid selection.");
            }
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            s.close();
        }
    }
}
