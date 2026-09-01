class Main {
    public static void main(String[] args) {
        System.out.println("========== Q1: Exception Hierarchy & Handling ==========");
        q1();
        System.out.println();
        System.out.println("========== Q2: Custom Exceptions & Debugging ==========");
        q2();
    }

    // Q1: Exception hierarchy and handling mechanisms
    public static void q1() {

        // 1. ArithmeticException: / by zero
        System.out.println("--- 1. ArithmeticException ---");
        try {
            int a = 10;
            int b = 0;
            int result = a / b;
            System.out.println(result);
        } catch (ArithmeticException e) {
            System.out.println("Exception Occurred: " + e.getMessage());
        }

        // 2. ArrayIndexOutOfBoundsException: index 3 on size-3 array
        System.out.println("\n--- 2. ArrayIndexOutOfBoundsException ---");
        try {
            int[] numbers = {10, 20, 30};
            System.out.println(numbers[3]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Exception Occurred: " + e.getMessage());
        }

        // 3. NullPointerException: calling method on null
        System.out.println("\n--- 3. NullPointerException ---");
        try {
            String str = null;
            System.out.println(str.length());
        } catch (NullPointerException e) {
            System.out.println("Exception Occurred: " + e.getMessage());
        }

        // 4. StringIndexOutOfBoundsException: charAt beyond length
        System.out.println("\n--- 4. StringIndexOutOfBoundsException ---");
        try {
            String str = "Hello";
            char ch = str.charAt(10);
            System.out.println(ch);
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("Exception Occurred: " + e.getMessage());
        }

        // 5. NumberFormatException: parsing non-numeric string
        System.out.println("\n--- 5. NumberFormatException ---");
        try {
            int num = Integer.parseInt("abc");
            System.out.println(num);
        } catch (NumberFormatException e) {
            System.out.println("Exception Occurred: " + e.getMessage());
        }

        // 6. ClassCastException: invalid type cast
        System.out.println("\n--- 6. ClassCastException ---");
        try {
            Object obj = "Hello";
            Integer num = (Integer) obj;
            System.out.println(num);
        } catch (ClassCastException e) {
            System.out.println("Exception Occurred: " + e.getMessage());
        }

        // 7. Multiple catch: specific before general
        System.out.println("\n--- 7. Multiple catch blocks (specific to general) ---");
        try {
            int[] arr = {1, 2, 3};
            int val = arr[5] / 0; // index error first
            System.out.println(val);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Caught first: " + e.getClass().getSimpleName() + " - " + e.getMessage());
        } catch (ArithmeticException e) {
            System.out.println("Caught second: " + e.getClass().getSimpleName() + " - " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Caught last (general): " + e.getClass().getSimpleName() + " - " + e.getMessage());
        }

        // 8. Finally always runs
        System.out.println("\n--- 8. Try-catch-finally ---");
        try {
            int result = 100 / 5;
            System.out.println("Result: " + result);
        } catch (ArithmeticException e) {
            System.out.println("Exception: " + e.getMessage());
        } finally {
            System.out.println("Finally block executed (always runs).");
        }

        // 9. Chaining exceptions: wrapping one inside another
        System.out.println("\n--- 9. Exception Chaining ---");
        try {
            try {
                int[] arr = new int[2];
                arr[10] = 50;
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new RuntimeException("Wrapped original exception", e);
            }
        } catch (RuntimeException e) {
            System.out.println("Chained exception message: " + e.getMessage());
            System.out.println("Original cause: " + e.getCause());
        }

        // 10. Exception class hierarchy
        System.out.println("\n--- 10. Exception Hierarchy ---");
        try {
            int res = 10 / 0;
        } catch (ArithmeticException e) {
            System.out.println("Exception: " + e.getClass().getName());
            System.out.println("  -> extends RuntimeException");
            System.out.println("  -> extends Exception");
            System.out.println("  -> extends Throwable");
        }
    }

    // Q2: Custom exceptions and debugging techniques

    // Custom checked exception (must be declared)
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

    // Custom unchecked exception (no need to declare)
    static class InvalidAgeException extends RuntimeException {
        private int age;

        public InvalidAgeException(int age) {
            super("Invalid age: " + age + ". Age must be between 0 and 150.");
            this.age = age;
        }

        public int getAge() { return age; }
    }

    // Custom exception with error code
    static class DatabaseException extends Exception {
        private int errorCode;

        public DatabaseException(int errorCode, String message) {
            super(message);
            this.errorCode = errorCode;
        }

        // Constructor with cause for chaining
        public DatabaseException(int errorCode, String message, Throwable cause) {
            super(message, cause);
            this.errorCode = errorCode;
        }

        public int getErrorCode() { return errorCode; }
    }

    // BankAccount: throws custom exception on insufficient funds
    static class BankAccount {
        private String name;
        private double balance;

        public BankAccount(String name, double balance) {
            this.name = name;
            this.balance = balance;
        }

        // Throws checked exception if funds are not enough
        public void withdraw(double amount) throws InsufficientBalanceException {
            if (amount <= 0) {
                throw new IllegalArgumentException("Withdrawal amount must be positive.");
            }
            if (amount > balance) {
                throw new InsufficientBalanceException(balance, amount);
            }
            balance -= amount;
            System.out.println("Withdrew " + amount + ". New balance: " + balance);
        }

        public double getBalance() { return balance; }
    }

    public static void q2() {

        // 1. Custom checked exception in action
        System.out.println("--- 1. Custom Checked Exception (InsufficientBalanceException) ---");
        BankAccount account = new BankAccount("Alice", 1000.0);
        try {
            account.withdraw(500);
            account.withdraw(600); // triggers exception
        } catch (InsufficientBalanceException e) {
            System.out.println("Error: " + e.getMessage());
            System.out.println("  Balance: " + e.getBalance());
            System.out.println("  Attempted: " + e.getWithdrawAmount());
        }

        // 2. Custom unchecked exception in action
        System.out.println("\n--- 2. Custom Unchecked Exception (InvalidAgeException) ---");
        try {
            validateAge(25);  // valid
            validateAge(-5);  // triggers exception
        } catch (InvalidAgeException e) {
            System.out.println("Error: " + e.getMessage());
            System.out.println("  Invalid age provided: " + e.getAge());
        }

        // 3. printStackTrace for debugging
        System.out.println("\n--- 3. Debugging with printStackTrace ---");
        try {
            simulateDatabaseOperation(1);
        } catch (DatabaseException e) {
            System.out.println("Database Error [Code " + e.getErrorCode() + "]: " + e.getMessage());
            System.out.println("Full stack trace (for debugging):");
            e.printStackTrace(System.out);
        }

        // 4. Chaining exceptions preserves root cause
        System.out.println("\n--- 4. Exception Chaining for Debugging ---");
        try {
            try {
                int[] arr = new int[3];
                arr[5] = 10;
            } catch (ArrayIndexOutOfBoundsException original) {
                throw new DatabaseException(500, "Data processing failed", original);
            }
        } catch (DatabaseException e) {
            System.out.println("Error [Code " + e.getErrorCode() + "]: " + e.getMessage());
            System.out.println("Root cause: " + e.getCause());
        }

        // 5. Bug fixes: validate before operating
        System.out.println("\n--- 5. Error Identification and Correction ---");

        // Fix 1: check divisor
        System.out.println("Bug 1: Division by zero -> Fix: Check divisor before dividing");
        try {
            int a = 10, b = 0;
            if (b != 0) {
                System.out.println("  Result: " + (a / b));
            } else {
                System.out.println("  Corrected: Divisor is zero, skipping division.");
            }
        } catch (Exception e) {
            System.out.println("  " + e.getMessage());
        }

        // Fix 2: check array index
        System.out.println("Bug 2: Array out of bounds -> Fix: Check index before access");
        try {
            int[] nums = {10, 20, 30};
            int index = 3;
            if (index >= 0 && index < nums.length) {
                System.out.println("  Value: " + nums[index]);
            } else {
                System.out.println("  Corrected: Index " + index + " is out of bounds [0-" + (nums.length - 1) + "]");
            }
        } catch (Exception e) {
            System.out.println("  " + e.getMessage());
        }

        // Fix 3: null check
        System.out.println("Bug 3: Null reference -> Fix: Check for null before use");
        try {
            String str = null;
            if (str != null) {
                System.out.println("  Length: " + str.length());
            } else {
                System.out.println("  Corrected: String is null, skipping length call.");
            }
        } catch (Exception e) {
            System.out.println("  " + e.getMessage());
        }
    }

    // Validates age, throws custom exception if invalid
    public static void validateAge(int age) {
        if (age < 0 || age > 150) {
            throw new InvalidAgeException(age);
        }
        System.out.println("Age " + age + " is valid.");
    }

    // Simulates DB ops, throws custom exception with error codes
    public static void simulateDatabaseOperation(int operationCode) throws DatabaseException {
        if (operationCode == 1) {
            throw new DatabaseException(404, "Record not found");
        } else if (operationCode == 2) {
            throw new DatabaseException(500, "Connection timeout",
                new java.net.ConnectException("localhost:3306"));
        }
        System.out.println("Operation completed successfully.");
    }
}
