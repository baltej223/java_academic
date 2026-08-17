package library.model;

public class Book extends LibraryResource implements Printable {
    public Book(int resourceId, String title, String author) {
        super(resourceId, title, author);
    }

    public double calculateFine(int overdueDays) {
        return 5.0 * overdueDays;
    }

    public void printDetails() {
        System.out.println("Resource Type : Book");
        printCommonDetails();
    }
}