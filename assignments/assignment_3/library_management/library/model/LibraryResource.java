package library.model;

public abstract class LibraryResource {
    private int resourceId;
    private String title;
    private String author;
    private static String libraryName = "University Library";
    private static int total_resources = 0;

    public LibraryResource(int resourceId, String title, String author) {
        this.resourceId = resourceId;
        this.title = title;
        this.author = author;
        total_resources++;
    }

    public abstract double calculateFine(int overdueDays);

    public abstract void printDetails();

    protected void printCommonDetails() {
        System.out.println("Resource ID   : " + resourceId);
        System.out.println("Title         : " + title);
        System.out.println("Author        : " + author);
        System.out.println("Library       : " + libraryName);
    }

    public int getResourceId() {
        return resourceId;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public static int total_resources() {
        return total_resources;
    }

    public static String getLibraryName() {
        return libraryName;
    }
}