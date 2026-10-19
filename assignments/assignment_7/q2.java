import java.util.Optional;

public class q2 {

    public static void main(String[] args) {
        Optional<String> studentEmail = Optional.of("student@thapar.edu");
        
        Optional<String> emptyEmail = Optional.empty();

        studentEmail.ifPresent(email -> System.out.println("Email is " + email));

        String defaultEmail = emptyEmail.orElse("no-reply@thapar.edu");
        System.out.println("Using orElse(): " + defaultEmail);

        try {
            String missing = emptyEmail.orElseThrow(() -> new IllegalArgumentException("Email address is missing!"));
        } catch (IllegalArgumentException e) {
            System.out.println("Caught Exception: " + e.getMessage());
        }
    }
}