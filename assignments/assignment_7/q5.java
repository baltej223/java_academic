import java.util.Objects;
import java.util.Optional;

class Utility {
    
    public static <T> boolean isEqual(T a, T b) {
        return Objects.equals(a, b);
    }

    public static <T> T getDefault(Optional<T> value, T defaultValue) {
        return value.orElse(defaultValue);
    }

    public static <T extends Comparable<T>> T max(T a, T b) {
        return (a.compareTo(b) > 0) ? a : b;
    }

    public static boolean isNull(Object obj) {
        return Objects.isNull(obj);
    }
}

public class q5 {
    public static void main(String[] args) {
        System.out.println("isEqual: " + Utility.isEqual("Java", "Java"));
        System.out.println("isEqual: " + Utility.isEqual(10, 20));

        Optional<String> emptyOpt = Optional.empty();
        Optional<String> fullOpt = Optional.of("Hello!");
        System.out.println("getDefault: " + Utility.getDefault(emptyOpt, "Fallback"));
        System.out.println("getDefault: " + Utility.getDefault(fullOpt, "Fallback"));

        System.out.println("max: " + Utility.max(50, 100));
        System.out.println("max: " + Utility.max("Apple", "Orange"));

        String str = null;
        Integer num = 5;
        System.out.println("isNull: " + Utility.isNull(str));
        System.out.println("isNull: " + Utility.isNull(num));
    }
}