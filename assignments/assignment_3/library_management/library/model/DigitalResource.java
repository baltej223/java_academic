package library.model;

public class DigitalResource extends LibraryResource implements Printable {
    public DigitalResource(int resourceId, String title, String author) {
        super(resourceId, title, author);
    }

    public double calculateFine(int overdueDays) {
        return 2.0 * overdueDays;
    }

    public void printDetails() {
        System.out.println("Resource Type : Digital Resource");
        printCommonDetails();
    }
}