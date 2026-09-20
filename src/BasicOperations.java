public class BasicOperations {

    private BasicOperations() {}

    public static class Addition extends MathOperation {
        public Addition() {
            super("Addition", "+");
        }

        @Override
        public double calculate(double operand1, double operand2) throws CalculatorException {
            double result = operand1 + operand2;
            validateResult(result);
            return result;
        }
    }

    public static class Subtraction extends MathOperation {
        public Subtraction() {
            super("Subtraction", "−");
        }

        @Override
        public double calculate(double operand1, double operand2) throws CalculatorException {
            double result = operand1 - operand2;
            validateResult(result);
            return result;
        }
    }

    public static class Multiplication extends MathOperation {
        public Multiplication() {
            super("Multiplication", "×");
        }

        @Override
        public double calculate(double operand1, double operand2) throws CalculatorException {
            double result = operand1 * operand2;
            validateResult(result);
            return result;
        }
    }

    public static class Division extends MathOperation {
        public Division() {
            super("Division", "÷");
        }

        @Override
        public double calculate(double operand1, double operand2) throws CalculatorException {
            if (operand2 == 0.0) {
                throw new DivisionByZeroException();
            }
            double result = operand1 / operand2;
            validateResult(result);
            return result;
        }
    }
}
