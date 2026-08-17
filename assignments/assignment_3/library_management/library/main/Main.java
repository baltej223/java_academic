package library.main;

import library.model.Book;
import library.model.DigitalResource;
import library.model.Journal;
import library.model.LibraryResource;
import library.util.InputValidator;

public class Main {

    public static void main(String[] args) {

        InputValidator validator = new InputValidator();

        int overdueDays = 3;

        // Validate fine days
        if (!validator.validateFineDays(overdueDays)) {
            System.out.println("Invalid overdue days!");
            return;
        }

        LibraryResource[] resources = new LibraryResource[5];

        // Resource IDs
        int id1 = 101;
        int id2 = 102;
        int id3 = 103;
        int id4 = 104;
        int id5 = 105;

        // Validate resource IDs
        if (!validator.validateResourceId(id1) ||
            !validator.validateResourceId(id2) ||
            !validator.validateResourceId(id3) ||
            !validator.validateResourceId(id4) ||
            !validator.validateResourceId(id5)) {

            System.out.println("Invalid resource ID!");
            return;
        }

        // Create resources
        resources[0] = new Book(
                id1,
                "Java Programming",
                "John Doe"
        );

        resources[1] = new DigitalResource(
                id2,
                "Design Patterns",
                "Jane Smith"
        );

        resources[2] = new Book(
                id3,
                "Data Structures",
                "Alice Johnson"
        );

        resources[3] = new DigitalResource(
                id4,
                "Database Systems",
                "Bob Wilson"
        );

        resources[4] = new Journal(
                id5,
                "Computer Science Review",
                "Charlie Brown"
        );

        System.out.println("========== LIBRARY RESOURCE DETAILS ==========");

        double totalFine = 0;

        for (LibraryResource resource : resources) {

            resource.printDetails();

            double fine = resource.calculateFine(overdueDays);

            System.out.println("Overdue Fine  : Rs. " + fine);
            System.out.println("----------------------------------------");

            totalFine += fine;
        }

        System.out.println("========== TOTAL STATISTICS ==========");
        System.out.println(
                "Total Resources Created: "
                + LibraryResource.total_resources()
        );

        System.out.println(
                "Total Fine for "
                + overdueDays
                + " days overdue: Rs. "
                + totalFine
        );

        System.out.println(
                "Library Name: "
                + LibraryResource.getLibraryName()
        );
    }
}
