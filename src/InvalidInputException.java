public class InvalidInputException extends CalculatorException {
    public InvalidInputException(String detail) {
        super("Invalid Input: " + detail);
    }
}