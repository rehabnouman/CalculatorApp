public class OverflowException extends CalculatorException {
    public OverflowException() {
        super("Error: Result exceeds the numeric range (overflow).");
    }
    public OverflowException(String message) {
        super(message);
    }
}