import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

class Event {
    protected String name;
    protected LocalDate date;
    protected LocalTime startTime;
    protected Optional<String> venue;

    public Event(String name, LocalDate date, LocalTime startTime, String venue) {
        this.name = name;
        this.date = date;
        this.startTime = startTime;
        this.venue = Optional.ofNullable(venue);
    }

    public void displayDetails() {
        System.out.println("Event: " + name);
        System.out.println("Date: " + date);
        System.out.println("Start Time: " + startTime);
        System.out.println("Venue: " + venue.orElse("Venue not specified"));

        LocalDate today = LocalDate.now();
        if (date.isAfter(today)) {
            System.out.println("Status: Scheduled in the future.");
        } else if (date.isBefore(today)) {
            System.out.println("Status: Event has passed.");
        } else {
            System.out.println("Status: Event is happening TODAY!");
        }

        long daysBetween = ChronoUnit.DAYS.between(today, date);
        if (daysBetween >= 0) {
            System.out.println("Days remaining: " + daysBetween);
        } else {
            System.out.println("Days since event: " + Math.abs(daysBetween));
        }

        LocalTime endTime = startTime.plusHours(2);
        System.out.println("End Time: " + endTime);
        System.out.println("---------------------------------");
    }
}

public class q4 {
    public static void main(String[] args) {
        Event techConference = new Event("Tech Conference 2026", 
            LocalDate.of(2026, 12, 1), 
            LocalTime.of(10, 0), 
            "Main Auditorium");
            
        Event hackathon = new Event("Campus Hackathon", 
            LocalDate.now().minusDays(5), 
            LocalTime.of(9, 30), 
            null);
            
        techConference.displayDetails();
        hackathon.displayDetails();
    }
}