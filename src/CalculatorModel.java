public class CalculatorModel {

    private double firstOperand;
    private MathOperation pendingOperation;
    private double lastResult;
    private double lastSecondOperand;
    private MathOperation lastOperation;
    private boolean newNumberExpected;

    private final MathOperation[] operations;

    public CalculatorModel() {
        operations = new MathOperation[] {
            new BasicOperations.Addition(),
            new BasicOperations.Subtraction(),
            new BasicOperations.Multiplication(),
            new BasicOperations.Division()
        };
        reset();
    }

    public void setFirstOperand(double value) {
        this.firstOperand = value;
    }

    public double getFirstOperand() {
        return firstOperand;
    }

    public void setPendingOperation(MathOperation op) {
        this.pendingOperation = op;
    }

    public MathOperation getPendingOperation() {
        return pendingOperation;
    }

    public boolean isNewNumberExpected() {
        return newNumberExpected;
    }

    public void expectNewNumber() {
        this.newNumberExpected = true;
    }

    public void continueCurrentNumber() {
        this.newNumberExpected = false;
    }

    public MathOperation[] getOperations() {
        return operations;
    }

    public double getLastResult() {
        return lastResult;
    }

    public double compute(double secondOperand) throws CalculatorException {
        if (pendingOperation == null) {
            throw new InvalidInputException("No operation has been selected.");
        }

        this.lastSecondOperand = secondOperand;
        this.lastOperation = pendingOperation;

        double result = pendingOperation.calculate(firstOperand, secondOperand);
        this.lastResult = result;
        return result;
    }

    public double repeatLastComputation(double currentValue) throws CalculatorException {
        if (lastOperation == null) {
            throw new InvalidInputException("No previous operation to repeat.");
        }
        double result = lastOperation.calculate(currentValue, lastSecondOperand);
        this.lastResult = result;
        return result;
    }

    public double squareRoot(double value) throws CalculatorException {
        if (value < 0) {
            throw new InvalidInputException("Square root of a negative number is not a real number.");
        }
        return Math.sqrt(value);
    }

    public double toPercentage(double value) {
        return value / 100.0;
    }

    public double negate(double value) {
        return -value;
    }

    public double parseDisplayValue(String displayText) throws CalculatorException {
        try {
            return Double.parseDouble(displayText);
        } catch (NumberFormatException ex) {
            throw new InvalidInputException("\"" + displayText + "\" is not a valid number.");
        }
    }

    public void reset() {
        firstOperand = 0.0;
        pendingOperation = null;
        lastResult = 0.0;
        lastSecondOperand = 0.0;
        lastOperation = null;
        newNumberExpected = true;
    }

    @Override
    public String toString() {
        return "CalculatorModel{firstOperand=" + firstOperand
                + ", pendingOp=" + (pendingOperation != null ? pendingOperation.getSymbol() : "none")
                + "}";
    }
}