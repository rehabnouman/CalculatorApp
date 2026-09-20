public interface Calculable {
    double calculate(double operand1, double operand2) throws CalculatorException;
    String getSymbol();
    String getName();
}
