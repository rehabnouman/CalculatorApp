public class DivisionByZeroException extends CalculatorException {
    public DivisionByZeroException() {
        super("Error: Cannot divide by zero.");
    }
    public DivisionByZeroException(String message) {
        super(message);
    }
}