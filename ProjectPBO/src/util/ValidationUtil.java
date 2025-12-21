package util;

public class ValidationUtil {
    public static void validateInput(String input) throws ValidationException {
        if (input == null || input.trim().isEmpty()) {
            throw new ValidationException("Input tidak boleh kosong!");
        }
    }
}