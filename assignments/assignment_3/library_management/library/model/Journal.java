package library.model;

public class Journal extends LibraryResource implements Printable {

    public Journal(int resourceId, String title, String author) {
        super(resourceId, title, author);
    }

    @Override
    public double calculateFine(int overdueDays) {
        // Journal fine rate is not specified in the question.
        // Assumption: Rs. 3 per day.
        return 3.0 * overdueDays;
    }

    @Override
    public void printDetails() {
        System.out.println("Resource Type : Journal");
        printCommonDetails();
    }
}
