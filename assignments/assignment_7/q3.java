import java.util.Objects;
import java.util.Optional;

class Result<T extends Number> {
    protected T marks;
    protected double maxMarks;

    public Result(T marks, double maxMarks) {
        this.marks = Objects.requireNonNull(marks, "Marks cannot be null");
        this.maxMarks = maxMarks;
    }

    public double calculatePercentage() {
        return (marks.doubleValue() / maxMarks) * 100.0;
    }

    public boolean hasPassed(double thresholdPercentage) {
        return calculatePercentage() >= thresholdPercentage;
    }

    public void displayResult(double threshold) {
        Optional<T> optionalMarks = Optional.ofNullable(marks);
        
        optionalMarks.ifPresent(m -> {
            System.out.println("Marks Obtained: " + m + " / " + maxMarks);
            System.out.printf("Percentage: %.2f%%\n", calculatePercentage());
            System.out.println("Status: " + (hasPassed(threshold) ? "PASSED" : "FAILED"));
        });
    }
}

public class q3 {
    public static void main(String[] args) {
        Result<Double> student1 = new Result<>(425.5, 500.0);
        student1.displayResult(40.0);

        Result<Integer> student2 = new Result<>(25, 100);
        student2.displayResult(40.0);

        try {
            Result<Integer> student3 = new Result<>(null, 100.0);
        } catch (NullPointerException e) {
            System.out.println("Validation caught error: " + e.getMessage());
        }
    }
}