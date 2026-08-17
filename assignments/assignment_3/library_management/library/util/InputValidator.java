package library.util;

public class InputValidator {

    public boolean validateResourceId(int resourceId) {
        return resourceId > 0;
    }

    public boolean validateFineDays(int fineDays) {
        return fineDays >= 0;
    }
}
