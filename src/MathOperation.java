public abstract class MathOperation implements Calculable {

    private final String name;
    private final String symbol;

    public MathOperation(String name, String symbol) {
        this.name = name;
        this.symbol = symbol;
    }

    @Override
    public abstract double calculate(double operand1, double operand2) throws CalculatorException;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

    protected void validateResult(double result) throws OverflowException {
        if (Double.isInfinite(result) || Double.isNaN(result)) {
            throw new OverflowException();
        }
    }

    @Override
    public String toString() {
        return name + " [" + symbol + "]";
    }
}
